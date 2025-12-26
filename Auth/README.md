# Spring Security with H2 (In-Memory)

## Overview
This project focuses on **Spring Security** fundamentals using an **H2 in-memory database**.  
It demonstrates authentication and authorization concepts in a simple, developer-friendly setup.

## Key Features
- Spring Security configuration using `SecurityFilterChain`
- Role-based authorization (`USER`, `ADMIN`)
- H2 **in-memory** database (no persistent storage)
- H2 Console enabled for development and testing
- Form-based authentication for browser access

## Database
- **Database:** H2 (In-Memory)
- Data exists **only while the application is running**
- Database is recreated on every restart

## H2 Console
- **URL:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:test`
- **Username:** `sa`
- **Password:** *(empty)*

## Purpose
This project is intended for:
- Learning Spring Security basics
- Understanding authentication vs authorization
- Practicing security configuration with an in-memory database

## Notes
- Not intended for production use
- H2 is used only for development/testing
- All data is lost on application restart
