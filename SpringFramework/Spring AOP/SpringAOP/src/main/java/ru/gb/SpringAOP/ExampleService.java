package ru.gb.SpringAOP;

import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    @Timer
    public void methodWithTimerAnnotation() {
        try {
            Thread.sleep(2000); // имитация долгого выполнения метода
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RecoverException(noRecoverFor = {CustomException.class})
    public String methodWithRecoverExceptionAnnotation(boolean shouldThrowException) {
        if (shouldThrowException) {
            throw new RuntimeException("This is a runtime exception");
        }
        return "Success";
    }
}
