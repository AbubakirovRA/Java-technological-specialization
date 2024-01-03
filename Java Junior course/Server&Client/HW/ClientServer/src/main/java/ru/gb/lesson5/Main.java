// Main.java

package ru.gb.lesson5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ru.gb.lesson5.Server.*;

public class Main {
    // Логгер для записи логов
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        // Поток для чтения ввода с клавиатуры
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                while (true) {
                    String consoleInput = reader.readLine();
                    if ("quit".equals(consoleInput)) {
                        // Выход из бесконечного цикла сервера
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);

            // Бесконечный цикл для принятия новых клиентских соединений
            while (true) {
                try {
                    // Принятие соединения от нового клиента
                    final Socket client = server.accept();

                    // Генерация уникального идентификатора для клиента
                    final long clientId = clientIdCounter++;

                    // Создание объекта SocketWrapper для клиента
                    SocketWrapper wrapper = new SocketWrapper(clientId, client);
                    System.out.println("Подключился новый клиент [" + wrapper + "]");

                    // Добавление клиента в список активных клиентов
                    clients.put(clientId, wrapper);

                    // Запуск нового потока для обработки клиента
                    new Thread(new ClientHandler(wrapper)).start();
                } catch (IOException e) {
                    // Обработка ошибок ввода-вывода при принятии клиента
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            // Обработка ошибок ввода-вывода при создании ServerSocket
            e.printStackTrace();
        }
    }

    // Класс для обработки клиентских соединений
    private static class ClientHandler implements Runnable {
        private final SocketWrapper wrapper;

        public ClientHandler(SocketWrapper wrapper) {
            this.wrapper = wrapper;
        }

        @Override
        public void run() {
            try (Scanner input = wrapper.getInput(); PrintWriter output = wrapper.getOutput()) {
                // Отправка клиенту сообщения об успешном подключении и списке активных клиентов
                output.println("Подключение успешно. Список всех клиентов: " + clients);

                // Флаг для отслеживания состояния подключения клиента
                boolean connected = true;

                // Цикл для обработки ввода от клиента
                while (connected && input.hasNextLine()) {
                    String clientInput = input.nextLine();
                    if (Objects.equals("q", clientInput)) {
                        // Если клиент отправил "q", отключаем его
                        clients.remove(wrapper.getId());
                        clients.values().forEach(it -> it.getOutput().println("Клиент[" + wrapper.getId() + "] отключился"));
                        connected = false;
                    } else if (clientInput.startsWith("@")) {
                        // Если сообщение начинается с "@", обрабатываем как личное сообщение
                        handlePrivateMessage(wrapper.getId(), clientInput);
                    } else if (clientInput.startsWith("kick") && wrapper.isAdmin()) {
                        // Если клиент является администратором и отправил "kick", отключаем указанного клиента
                        long targetClientId = Long.parseLong(clientInput.substring(5).trim());
                        kickClient(targetClientId);
                    } else {
                        // Иначе рассылаем сообщение всем клиентам
                        broadcastMessage(wrapper.getId(), clientInput);
                    }
                }

                // Блок для закрытия SocketWrapper при выходе из цикла
                try {
                    wrapper.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
