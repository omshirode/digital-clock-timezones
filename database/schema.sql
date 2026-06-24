-- Create database
CREATE DATABASE IF NOT EXISTS digital_clock_db;
USE digital_clock_db;

-- Create table
CREATE TABLE favorite_timezones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    timezone VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_timezone (timezone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;