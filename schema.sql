-- Vehicle Rental System - Database Schema
-- Run this in MySQL (e.g. via phpMyAdmin or the mysql CLI) before starting the app.

CREATE DATABASE IF NOT EXISTS rental_db;
USE rental_db;

CREATE TABLE IF NOT EXISTS vehicles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(20) NOT NULL,
    model VARCHAR(50) NOT NULL,
    base_rate DOUBLE NOT NULL,
    is_available BOOLEAN DEFAULT TRUE,
    extra_feature VARCHAR(50)
);

-- Sample seed data
INSERT INTO vehicles (type, model, base_rate, extra_feature) VALUES
    ('Car', 'Tesla Model 3', 50.0, '4 Doors'),
    ('Bike', 'Yamaha R1', 25.0, '998cc');
