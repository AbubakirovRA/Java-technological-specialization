package ru.gb.SpringAOP;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    private final ExampleService exampleService;

    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping("/example")
    public String exampleEndpoint() {
        // Вызов метода с задержкой
        exampleService.methodWithTimerAnnotation();
        return "Example response";
    }
}
