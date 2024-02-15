package ru.gb.SpringAOP;

import java.lang.annotation.*;

/**
 * Аннотация для замера времени исполнения метода.
 * Может быть применена к методам или классам.
 * После выполнения метода с этой аннотацией логируется время его выполнения.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface Timer {
}
