package com.example.bricklibraryminiproject.controller;


import com.example.bricklibraryminiproject.exception.AlreadyExistsException;
import com.example.bricklibraryminiproject.model.Theme;
import com.example.bricklibraryminiproject.service.ThemeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // http://localhost:9022/api
public class ThemeController {
    private ThemeService themeService;


    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @PostMapping("/themes/create/") // http://localhost:9022/api/themes/create
    public ResponseEntity<Theme> createTheme(@RequestBody Theme theme) {
        try {
            Theme createdTheme = themeService.createTheme(theme);
            return new ResponseEntity<>(createdTheme, HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
