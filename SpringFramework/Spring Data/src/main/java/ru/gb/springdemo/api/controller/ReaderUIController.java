package ru.gb.springdemo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

@Controller
@RequestMapping("/ui/reader")
public class ReaderUIController {

    @Autowired
    private ReaderService readerService;

    @GetMapping
    public String getAllReaders(Model model) {
        List<Reader> readers = readerService.getReaders();
        model.addAttribute("readers", readers);
        return "readers";
    }
}
