// Server.java
package ru.gb.lesson5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Server {

    // Определение порта сервера
    public static final int PORT = 8181;

    // Счетчик идентификаторов клиентов
    public static long clientIdCounter = 1L;

    // Коллекция для хранения подключенных клиентов
    public static final Map<Long, SocketWrapper> clients = new HashMap<>();

    // Точка входа в программу
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(SocketWrapper.class);
        // Создание серверного сокета, прослушивающего указанный порт
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);

            // Бесконечный цикл ожидания подключения клиентов
            while (true) {
                try {
                    // Принятие нового подключения от клиента
                    final Socket client = server.accept();
                    final long clientId = clientIdCounter++;

                    // Создание объекта SocketWrapper для управления подключением
                    SocketWrapper wrapper = new SocketWrapper(clientId, client);
                    System.out.println("Подключился новый клиент [" + wrapper + "]");

                    // Добавление клиента в коллекцию
                    clients.put(clientId, wrapper);

                    // Создание и запуск отдельного потока для обработки сообщений клиента
                    new Thread(() -> {
                        try (Scanner input = wrapper.getInput(); PrintWriter output = wrapper.getOutput()) {
                            // Отправка клиенту списка всех подключенных клиентов
                            output.println("Подключение успешно. Список всех клиентов: " + clients);

                            // Бесконечный цикл приема сообщений от клиента
                            while (true) {
                                String clientInput = input.nextLine();

                                // Обработка команды отключения клиента
                                if (Objects.equals("q", clientInput)) {
                                    clients.remove(clientId);
                                    clients.values().forEach(it -> it.getOutput().println("Клиент[" + clientId + "] отключился"));
                                    break;
                                }

                                // Проверка, направлено ли сообщение конкретному клиенту
                                if (clientInput.startsWith("@")) {
                                    handlePrivateMessage(clientId, clientInput);
                                } else if (clientInput.startsWith("kick") && wrapper.isAdmin()) {
                                    // Команда админа для отключения клиента
                                    long targetClientId = Long.parseLong(clientInput.substring(5).trim());
                                    kickClient(targetClientId);
                                } else {
                                    // Рассылка сообщения всем клиентам
                                    broadcastMessage(clientId, clientInput);
                                }
                            }
                        }
                    }).start();
                } catch (IOException e) {
                    // Обработка ошибок в процессе подключения клиента
                    logger.error("Произошла ошибка ввода-вывода", e);
                }
            }
        } catch (IOException e) {
            // Обработка ошибок при создании серверного сокета
            logger.error("Произошла ошибка ввода-вывода", e);
        }
    }

    // Метод для обработки приватного сообщения от клиента
    public static void handlePrivateMessage(long senderId, String message) {
        long destinationId = Long.parseLong(message.substring(1, 2));
        SocketWrapper destination = clients.get(destinationId);
        if (destination != null) {
            destination.getOutput().println("Приватное сообщение от " + senderId + ": " + message.substring(2));
        }
    }

    // Метод для рассылки сообщения всем клиентам
    public static void broadcastMessage(long senderId, String message) {
        clients.values().forEach(it -> it.getOutput().println("Клиент[" + senderId + "]: " + message));
    }

    // Метод для отключения клиента по запросу админа
    public static void kickClient(long targetClientId) {
        final Logger logger = LoggerFactory.getLogger(SocketWrapper.class);
        SocketWrapper target = clients.get(targetClientId);
        if (target != null) {
            try {
                // Закрытие соединения с целевым клиентом
                target.close();
            } catch (Exception e) {
                logger.error("Произошла ошибка ввода-вывода", e);
            }
            // Удаление клиента из коллекции
            clients.remove(targetClientId);
            // Отправка сообщения о отключении клиента всем подключенным клиентам
            clients.values().forEach(it -> it.getOutput().println("Клиент[" + targetClientId + "] был отключен админом"));
        }
    }
}
