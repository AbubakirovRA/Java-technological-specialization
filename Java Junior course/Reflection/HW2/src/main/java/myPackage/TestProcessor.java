package src.main.java.myPackage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Импортируем аннотации из пакета Homework
import src.main.java.myPackage.Homework.BeforeEach;
import src.main.java.myPackage.Homework.AfterEach;
import src.main.java.myPackage.Homework.Test;
import src.main.java.myPackage.Homework.Skip;

// Класс, ответственный за выполнение тестовых методов
public class TestProcessor {

    // Метод для запуска тестов в переданном классе
    public static void runTest(Class<?> testClass) {
        final Constructor<?> declaredConstructor;
        try {
            // Получаем конструктор без аргументов
            declaredConstructor = testClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            // В случае отсутствия конструктора, выбрасываем исключение
            throw new IllegalStateException("Для класса \"" + testClass.getName() + "\" не найден конструктор без аргументов");
        }

        final Object testObj;
        try {
            // Создаем объект тестируемого класса с использованием конструктора без аргументов
            testObj = declaredConstructor.newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            // В случае проблем с созданием объекта, выбрасываем исключение
            throw new RuntimeException("Не удалось создать объект класса \"" + testClass.getName() + "\"");
        }

        // Получаем отсортированный список тестовых методов
        List<Method> methods = getSortedTestMethods(testClass);

        // Запускаем каждый тестовый метод
        for (Method method : methods) {
            runTestMethod(method, testObj);
        }
    }

    // Метод для получения отсортированного списка тестовых методов
    private static List<Method> getSortedTestMethods(Class<?> testClass) {
        List<Method> methods = new ArrayList<>();
        // Перебираем все методы класса
        for (Method method : testClass.getDeclaredMethods()) {
            // Если метод помечен аннотацией @Test, добавляем его в список
            if (method.isAnnotationPresent(Test.class)) {
                checkTestMethod(method);
                methods.add(method);
            }
        }

        // Сортируем методы на основе параметра order в аннотации @Test
        methods.sort(Comparator.comparingInt(method -> method.getAnnotation(Test.class).order()));
        return methods;
    }

    // Проверка корректности тестового метода
    private static void checkTestMethod(Method method) {
        // Проверяем, что метод возвращает void и не имеет аргументов
        if (!method.getReturnType().isAssignableFrom(void.class) || method.getParameterCount() != 0) {
            throw new IllegalArgumentException("Метод \"" + method.getName() + "\" должен быть void и не иметь аргументов");
        }
    }

    // Метод для запуска тестового метода
    private static void runTestMethod(Method testMethod, Object testObj) {
        Class<?> testClass = testObj.getClass(); // Получаем класс объекта для вызова методов
    
        // Проверяем, есть ли аннотация @Skip
        if (testMethod.isAnnotationPresent(Skip.class)) {
            System.out.println("Тест \"" + testMethod.getName() + "\" пропущен");
            return;
        }
    
        // Вызываем методы, помеченные аннотацией @BeforeEach
        invokeBeforeMethods(testClass, testObj);
    
        try {
            // Запускаем тестовый метод
            testMethod.invoke(testObj);
        } catch (InvocationTargetException | IllegalAccessException e) {
            // В случае ошибки при вызове метода, выбрасываем исключение
            throw new RuntimeException("Не удалось запустить тестовый метод \"" + testMethod.getName() + "\"");
        } catch (AssertionError e) {
            // Обрабатываем ошибки утверждений, если необходимо
        }
    
        // Вызываем методы, помеченные аннотацией @AfterEach
        invokeAfterMethods(testClass, testObj);
    }

    // Метод для вызова методов с аннотацией @BeforeEach
    private static void invokeBeforeMethods(Class<?> testClass, Object testObj) {
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeEach.class)) {
                try {
                    method.invoke(testObj);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // В случае ошибки при вызове метода, выводим стек вызовов
                    e.printStackTrace();
                }
            }
        }
    }

    // Метод для вызова методов с аннотацией @AfterEach
    private static void invokeAfterMethods(Class<?> testClass, Object testObj) {
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(AfterEach.class)) {
                try {
                    method.invoke(testObj);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // В случае ошибки при вызове метода, выводим стек вызовов
                    e.printStackTrace();
                }
            }
        }
    }
}