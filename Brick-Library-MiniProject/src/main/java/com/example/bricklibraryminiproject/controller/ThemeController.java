package com.example.bricklibraryminiproject.controller;


import com.example.bricklibraryminiproject.exception.AlreadyExistsException;
import com.example.bricklibraryminiproject.model.Theme;
import com.example.bricklibraryminiproject.service.ThemeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for handling HTTP requests related to themes
 */
@RestController
@RequestMapping("/api") // http://localhost:9022/api
public class ThemeController {
    private ThemeService themeService;


    /**
     * Constructor injection of ThemeService
     *
     * @param themeService The ThemeService instance to handle theme-related operations
     */
    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    /**
     * POST requests to create a new theme
     *
     * @param theme The theme object to be created
     * @return ResponseEntity with the created theme and HTTP status code
     */
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

    /**
     * GET requests to retrieve all themes
     *
     * @return ResponseEntity with a list of themes and HTTP status code
     */
    @GetMapping("/themes/all") // http://localhost:9022/api/themes/all
    public ResponseEntity<List<Theme>> getAllThemes() {
        List<Theme> allThemes = themeService.getAllThemes();
        return new ResponseEntity<>(allThemes, HttpStatus.OK);
    }

    @PutMapping("/themes/update/{id}") // http://localhost:9022/api/themes/update/1
    public ResponseEntity<Theme> updateTheme(@PathVariable Long id, @RequestBody Theme updatedTheme) {
        Theme updatedThemeResult = themeService.updateTheme(id, updatedTheme);
        return new ResponseEntity<>(updatedThemeResult, HttpStatus.OK);
    }
}
