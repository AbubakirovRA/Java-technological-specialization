package ru.gb.SpringAOP;

import java.lang.annotation.*;

/**
 * Аннотация для обработки исключений.
 * Может быть применена только к методам.
 * Позволяет определить список исключений, которые необходимо обрабатывать.
 * Если метод выбрасывает исключение, не указанное в списке noRecoverFor, оно будет перехвачено и обработано.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface RecoverException {

    /**
     * Список исключений, которые необходимо игнорировать и не обрабатывать.
     *
     * @return массив классов исключений
     */
    Class<? extends RuntimeException>[] noRecoverFor() default {};
}

