package ru.gb.SpringAOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MethodExecutionAspect {

    @Around("@annotation(recoverExceptionAnnotation)")
    public Object handleException(ProceedingJoinPoint joinPoint, RecoverException recoverExceptionAnnotation) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            Class<? extends Throwable> exceptionType = throwable.getClass();

            // Если тип исключения не входит в список исключений для восстановления,
            // пробросить исключение дальше
            if (!isExceptionInHierarchy(exceptionType, recoverExceptionAnnotation.noRecoverFor())) {
                throw throwable;
            }

            // Иначе возвращаем значение по умолчанию для типа возвращаемого значения метода
            return getDefaultValueForReturnType(joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName()).getReturnType());
        }
    }

    // Метод для проверки, принадлежит ли тип исключения иерархии перечисленных исключений
    private boolean isExceptionInHierarchy(Class<? extends Throwable> exceptionType, Class<? extends RuntimeException>[] noRecoverFor) {
        for (Class<? extends RuntimeException> clazz : noRecoverFor) {
            if (clazz.isAssignableFrom(exceptionType)) {
                return true;
            }
        }
        return false;
    }

    // Метод для получения значения по умолчанию для типа возвращаемого значения
    private Object getDefaultValueForReturnType(Class<?> returnType) {
        if (returnType.equals(void.class) || returnType.equals(Void.class)) {
            return null;
        } else if (returnType.equals(boolean.class) || returnType.equals(Boolean.class)) {
            return false;
        } else if (returnType.isPrimitive()) {
            // Для примитивных типов, кроме boolean, возвращаем 0
            return 0;
        } else {
            // Для остальных типов возвращаем null
            return null;
        }
    }
}
