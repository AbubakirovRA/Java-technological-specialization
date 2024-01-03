// Client.java
package ru.gb.lesson5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        // Создаем сокет для подключения к серверу по адресу "localhost" и порту, определенному в классе Server
        final Socket client = new Socket("localhost", Server.PORT);

        // Поток чтения данных от сервера
        new Thread(() -> {
            try (Scanner input = new Scanner(client.getInputStream())) {
                while (true) {
                    // Выводим на экран все сообщения от сервера
                    System.out.println(input.nextLine());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();

        // Поток записи данных на сервер
        new Thread(() -> {
            try (PrintWriter output = new PrintWriter(client.getOutputStream(), true)) {
                Scanner consoleScanner = new Scanner(System.in);
                while (true) {
                    // Считываем ввод пользователя с консоли и отправляем на сервер
                    String consoleInput = consoleScanner.nextLine();
                    output.println(consoleInput);

                    // Если пользователь ввел "q", закрываем соединение и завершаем цикл
                    if (Objects.equals("q", consoleInput)) {
                        client.close();
                        break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

// Код представляет собой простого консольного клиента для чата.
// Этот код создает два потока: один для чтения данных от сервера,
// другой для записи данных на сервер.
// Это обеспечивает асинхронное взаимодействие клиента с сервером,
// позволяя одновременно читать и отправлять сообщения.
