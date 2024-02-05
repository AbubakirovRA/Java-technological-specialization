package ru.gb.springdemo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

@Controller
@RequestMapping("/ui/reader")
public class ReaderDetailUIController {

    @Autowired
    private ReaderService readerService;

    @GetMapping("/{id}")
    public String getReaderDetails(@PathVariable Long id, Model model) {
        Reader reader = readerService.getReaderById(id);
        model.addAttribute("reader", reader);
        return "readerDetails";
    }
}

