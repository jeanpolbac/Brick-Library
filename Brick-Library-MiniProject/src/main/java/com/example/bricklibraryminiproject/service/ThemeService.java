package com.example.bricklibraryminiproject.service;

import com.example.bricklibraryminiproject.exception.AlreadyExistsException;
import com.example.bricklibraryminiproject.model.Theme;
import com.example.bricklibraryminiproject.repository.LegoSetRepository;
import com.example.bricklibraryminiproject.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ThemeService {
    private static final Logger logger = Logger.getLogger(ThemeService.class.getName());
    private ThemeRepository themeRepository;
    private LegoSetRepository legoSetRepository;

    @Autowired
    public void setThemeRepository(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Autowired
    public void setLegoSetRepository(LegoSetRepository legoSetRepository) {
        this.legoSetRepository = legoSetRepository;
    }

    public Theme createTheme(Theme theme) {
        try {
            Theme existingTheme = themeRepository.findByName(theme.getName());
            if (existingTheme != null) {
                 logger.severe("Failed to create theme " + theme.getName() + ", because it already exists.");
                throw new AlreadyExistsException("Theme with name '" + theme.getName() + "' already exists.");
            }

            return themeRepository.save(theme);
        } catch (Exception e) {
            logger.severe("Failed to create theme " + e.getMessage());
            throw new RuntimeException("Failed to create theme " + e.getMessage(), e);
        }
    }
}