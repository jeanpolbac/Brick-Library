package com.example.bricklibraryminiproject.controller;


import com.example.bricklibraryminiproject.service.ThemeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // http://localhost:9022/api
public class ThemeController {
    private ThemeService themeService;
}
