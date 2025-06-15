CREATE DATABASE QUANLYDONTHUOC;
USE QUANLYDONTHUOC;

-- 1. Bảng User (chung cho cả bác sĩ và bệnh nhân)
CREATE TABLE users (
    id CHAR(36) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    role ENUM('DOCTOR', 'PATIENT') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
alter table users add column (user_name VARCHAR(50));
alter table users add column (age int);

-- 2. Bảng Medication (thuốc)
CREATE TABLE medication (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    unit VARCHAR(20) NOT NULL,
    description TEXT
);

-- 3. Bảng Prescription (đơn thuốc)
CREATE TABLE prescription (
    id CHAR(36) PRIMARY KEY,
    doctor_id CHAR(36) NOT NULL,
    patient_id CHAR(36) NOT NULL,
    diagnosis TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (doctor_id) REFERENCES users(id),
    FOREIGN KEY (patient_id) REFERENCES users(id)
);

-- 4. Bảng PrescriptionDetail (chi tiết đơn thuốc)
CREATE TABLE prescription_detail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prescription_id CHAR(36) NOT NULL,
    medication_id CHAR(36) NOT NULL,
    dosage VARCHAR(100) NOT NULL, -- ví dụ: 1 viên x 2 lần/ngày
    duration_days INT NOT NULL,   -- số ngày dùng thuốc

    FOREIGN KEY (prescription_id) REFERENCES prescription(id) ON DELETE CASCADE,
    FOREIGN KEY (medication_id) REFERENCES medication(id)
);
