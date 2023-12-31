# Brick-Library

BrickLibrary is a web API built with Java Spring Boot, allowing users to keep track of their LEGO sets. It provides various functionalities, including registering user accounts, adding new LEGO sets, and searching sets by theme.

> **Status**: 🚧 Under Development 🚧

## Table of Contents

- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [User Stories](#user-stories)
- [ERD Diagram](#erd-diagram)
- [REST API Endpoints](#rest-api-endpoints)
- [Changelog](#changelog)
- [Resources](#resources)
- [Acknowledgments](#acknowledgments)

## Technologies Used
- **Java SDK** 17
- **Spring Boot** 2.7.15
- **Spring Security**
- **JWT**
- **H2 Database**
- **Maven**
- **IntelliJ IDE**
- **Git**

## User Stories
### 1. User Registration and Login
**User Story**: As a user, I want to register an account so I can have personalized experiences and save my favorite Lego sets.

**Acceptance Criteria**: Given I'm a visitor wanting a personalized experience, When I register with unique credentials, Then I should have an account to save my favorite Lego sets.

**User Story**: As a user, I want to log in so I can access my saved Lego sets and personalized preferences.

**Acceptance Criteria**: Given I'm a user with an account, When I provide my correct credentials, Then I should access my personalized dashboard and saved Lego sets.


### 2. Adding a New Lego Set
**User Story**: As a user, I want to add a new Lego set so I can keep track of the sets I own.

**Acceptance Criteria**: Given I'm a user who owns a unique Lego set, When I provide all the required details of the set (e.g., set name, theme, piece count, release year), Then the set should be added to my personal collection in the system.


### 3. Editing a Lego Set
**User Story**: As a user, I want to update details of a set I added to correct mistakes or share new information.

**Acceptance Criteria**: Given I'm a user who provided set details, When I make and save edits, Then I should view the most accurate set information.

### 4. Removing a Lego Set
**User Story**: As a user, I want to remove a set I added if I no longer wish to keep track of it.

**Acceptance Criteria**: Given I'm a user who added a Lego set, When I decide to remove it, Then it should no longer appear in my profile.

### 5. Searching My Lego Sets by Theme
**User Story**: As a user, I want to search my Lego sets by theme so I can keep track of what I already own.

**Acceptance Criteria**: Given I'm a user with specific Lego interests, When I search for sets by theme, Then I should find sets that match my tastes.

### 6. Viewing Details of a Specific Lego Set
**User Story**: As a user, I want to view detailed information about a specific Lego set so I can learn more about it.

**Acceptance Criteria**: Given I'm curious about a specific Lego set, When I click on its details, Then I should discover more about the set.

## ERD Diagram
<!-- <img src="images\erd-diagram.png" style=" width:600px ; height:auto "> -->
![ERD Diagram](images/erd-diagram.png)

## REST API Endpoints
| Endpoint     | Request Type | URL                            | Functionality                                      | Access              |
|--------------|--------------|--------------------------------|----------------------------------------------------|---------------------|
| Register     | POST         | `/auth/users/register/`         | Registers a new user with the provided user data. | Public              |
| Login        | POST         | `/auth/users/login/`            | Authenticates a user and returns a JWT token.    | Public              |
| Edit         | PUT          | `/auth/users/edit/{id}/`        | Updates a user's information based on user ID.   | Authenticated Users |
| Delete       | DELETE       | `/auth/users/delete/{id}/`      | Deletes a user by their ID.                      | Authenticated Users |
| Create Theme | POST         | `/api/themes/create/`            | Creates a new theme.                              | Authenticated Users |
| Get All      | GET          | `/api/themes/`                  | Retrieves a list of all themes.                   | Public              |



## Changelog [Unreleased]

All notable changes to this project will be documented in this file.


### Added

- User registration endpoint.
- User login and authentication.
- User update and delete endpoints.
- Theme creation endpoint.
- Get all themes endpoint.
- API documentation.
- JWT token-based authentication.
- Exception handling for user and theme-related operations.

### Changed

- Refactored user service for better organization.
- Improved security configuration.

### Fixed

- Resolved issues related to user update and delete.
- Fixed theme creation issues.



## Resources
- [Spring Boot Official Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/html5/)
- [Spring Initializer](https://start.spring.io/)
- [JWT.io](https://jwt.io/)
- [RFC 7519 (JWT Standard)](https://tools.ietf.org/html/rfc7519)
- [H2 Database Official Website](https://www.h2database.com/)
- [Maven Official Website](https://maven.apache.org/)
- [Git Documentation](https://git-scm.com/doc)
- [Oracle Java Documentation](https://docs.oracle.com/en/java/) 

</br >


## Acknowledgments

I would like to express my gratitude to the following instructors for their exceptional guidance and unwavering support throughout this class:

- **Suresh Sigera**
- **Dhrubo Chowdhury**

Their expertise and dedication have played a pivotal role in my growth as a software engineer.
