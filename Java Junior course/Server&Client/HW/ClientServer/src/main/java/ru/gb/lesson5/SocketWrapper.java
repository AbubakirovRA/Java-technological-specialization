// SocketWrapper.java
package ru.gb.lesson5;

import lombok.Getter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

@Getter
public class SocketWrapper implements AutoCloseable {

    // Идентификатор клиента
    private final long id;

    // Объект сокета для обмена данными с клиентом
    private final Socket socket;

    // Объекты для чтения и записи данных через сокет
    private final Scanner input;
    private final PrintWriter output;

    // Флаг, указывающий, является ли клиент администратором
    private final boolean isAdmin;

    // Конструктор класса
    SocketWrapper(long id, Socket socket) throws IOException {
        this.id = id;
        this.socket = socket;

        // Инициализация объектов для ввода и вывода данных через сокет
        this.input = new Scanner(socket.getInputStream());
        this.output = new PrintWriter(socket.getOutputStream(), true);

        // Выполнение аутентификации для определения статуса администратора
        this.isAdmin = authenticateAdmin();
    }

    // Логика аутентификации админа
    private boolean authenticateAdmin() {
        // Отправка запроса на ввод пароля
        output.println("Введите пароль для аутентификации:");
        String password = input.nextLine();

        // Проверка введенного пароля и установка флага админа
        if ("admin".equals(password)) {
            output.println("Вы успешно аутентифицированы как администратор.");
            return true;
        } else {
            output.println("Вы аутентифицированы как обычный клиент.");
            return false;
        }
    }

    // Метод закрытия соединения с клиентом
    @Override
    public void close() throws IOException {
        socket.close();
    }

    // Метод для представления объекта в виде строки
    @Override
    public String toString() {
        return String.format("%s (Админ: %s)", socket.getInetAddress().toString(), isAdmin);
    }
}
