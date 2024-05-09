package ru.surgu.medexambackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error(HttpServletRequest request) {
        // Обработка ошибки
        return "error"; // Предполагается, что у вас есть представление с именем "error"
    }

    public String getErrorPath() {
        return PATH;
    }
}
