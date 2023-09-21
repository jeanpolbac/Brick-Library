package com.example.bricklibraryminiproject.service;

import com.example.bricklibraryminiproject.repository.LegoSetRepository;
import com.example.bricklibraryminiproject.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemeService {
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
}
