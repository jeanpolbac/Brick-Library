package com.example.bricklibraryminiproject.repository;

import com.example.bricklibraryminiproject.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    Theme findByName(String themeName);
}
