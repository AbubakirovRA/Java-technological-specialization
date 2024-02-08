package ru.gb.springdemo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.IssueService;


@Controller
@RequestMapping("/ui/issues")
public class IssueUIController {

    @Autowired
    private IssueService issueService;

    @GetMapping
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }
}

