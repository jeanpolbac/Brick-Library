package com.example.bricklibraryminiproject.service;

import com.example.bricklibraryminiproject.exception.AlreadyExistsException;
import com.example.bricklibraryminiproject.exception.NotFoundException;
import com.example.bricklibraryminiproject.model.Theme;
import com.example.bricklibraryminiproject.repository.LegoSetRepository;
import com.example.bricklibraryminiproject.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @PreAuthorize("hasRole('ROLE_USER')")
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
     * Updates an existing theme based on its ID, but only if the authenticated user is the owner of the theme
     *
     * @param id The ID of the theme to update
     * @param updatedTheme The updated theme details
     * @return The updated theme
     * @throws NotFoundException If the theme with the given ID is not found
     */
    @PreAuthorize("hasRole('ROLE_USER') and #theme.owner.username == authentication.principal.username")
    public Theme updateTheme(Long id, Theme updatedTheme) {
        Optional<Theme> existingThemeOptional = themeRepository.findById(id);
        if (existingThemeOptional.isEmpty()) {
            throw new NotFoundException("Theme with ID " + id + " not found!");
        }
        Theme existingTheme = existingThemeOptional.get();
        existingTheme.setName(updatedTheme.getName());
        return themeRepository.save(existingTheme);
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
