package ru.gb.SpringAOP;

/**
 * Класс исключения CustomException.
 * Этот класс расширяет RuntimeException и может быть использован для демонстрации работы аннотации @RecoverException.
 */
public class CustomException extends RuntimeException {

    /**
     * Конструктор с сообщением об ошибке.
     *
     * @param message сообщение об ошибке
     */
    public CustomException(String message) {
        super(message);
    }

    // Можно добавить другие конструкторы и методы по необходимости
}

