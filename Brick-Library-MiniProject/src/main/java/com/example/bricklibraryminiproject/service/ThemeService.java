package com.example.bricklibraryminiproject.service;

import com.example.bricklibraryminiproject.exception.AlreadyExistsException;
import com.example.bricklibraryminiproject.model.Theme;
import com.example.bricklibraryminiproject.repository.LegoSetRepository;
import com.example.bricklibraryminiproject.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Service class for managing themes
 */
@Service
public class ThemeService {
    private static final Logger logger = Logger.getLogger(ThemeService.class.getName());
    private ThemeRepository themeRepository;
    private LegoSetRepository legoSetRepository;


    /**
     * Set the theme repository
     *
     * @param themeRepository The theme repository to set
     */
    @Autowired
    public void setThemeRepository(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    /**
     * Set the LegoSet repository - NOT YET USED
     *
     * @param legoSetRepository The LegoSet repository to set
     */
    @Autowired
    public void setLegoSetRepository(LegoSetRepository legoSetRepository) {
        this.legoSetRepository = legoSetRepository;
    }

    /**
     * Creates a new theme if it doesn't already exist
     *
     * @param theme The theme to create
     * @return The created theme
     */
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

    /**
     * Retrieves a list of all themes
     *
     * @return A list of themes
     */
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }
}
