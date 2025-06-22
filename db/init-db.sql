-- Cài đặt encoding để hỗ trợ tiếng Việt
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- Xác định DB sử dụng
USE QLT;

-- =================================================================
-- Bảng 1: users - Lưu trữ thông tin người dùng (Bệnh nhân & Bác sĩ)
-- =================================================================
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password_hash` VARCHAR(255) NOT NULL,
  `full_name` VARCHAR(255) NOT NULL,
  `date_of_birth` DATE NULL,
  `gender` ENUM('Nam', 'Nữ', 'Khác') NULL,
  `phone_number` VARCHAR(20) NULL,
  `address` TEXT NULL,
  `role` ENUM('patient', 'doctor') NOT NULL DEFAULT 'patient',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_email` (`email`),
  UNIQUE KEY `uk_phone_number` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =================================================================
-- Bảng 2: doctor_profiles - Thông tin mở rộng cho Bác sĩ
-- =================================================================
DROP TABLE IF EXISTS `doctor_profiles`;
CREATE TABLE `doctor_profiles` (
  `user_id` BIGINT UNSIGNED NOT NULL,
  `specialty` VARCHAR(255) NULL COMMENT 'Chuyên khoa',
  `license_number` VARCHAR(100) NULL COMMENT 'Số giấy phép hành nghề',
  `clinic_address` TEXT NULL COMMENT 'Địa chỉ phòng khám',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_license_number` (`license_number`),
  CONSTRAINT `fk_doctor_profiles_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =================================================================
-- Bảng 3: drugs - Lưu trữ thông tin các loại thuốc
-- =================================================================
DROP TABLE IF EXISTS `drugs`;
CREATE TABLE `drugs` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `api_drug_id` VARCHAR(100) NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NULL,
  `dosage_form` VARCHAR(100) NULL COMMENT 'Dạng bào chế',
  `manufacturer` VARCHAR(255) NULL COMMENT 'Nhà sản xuất',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_api_drug_id` (`api_drug_id`),
  INDEX `idx_drug_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =================================================================
-- Bảng 4: prescriptions - Thông tin chung về đơn thuốc
-- =================================================================
DROP TABLE IF EXISTS `prescriptions`;
CREATE TABLE `prescriptions` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `patient_id` BIGINT UNSIGNED NOT NULL,
  `doctor_id` BIGINT UNSIGNED NOT NULL,
  `diagnosis` TEXT NOT NULL COMMENT 'Chuẩn đoán',
  `notes` TEXT NULL COMMENT 'Ghi chú chung cho đơn thuốc',
  `issue_date` DATE NOT NULL COMMENT 'Ngày kê đơn',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `idx_patient_id` (`patient_id`),
  INDEX `idx_doctor_id` (`doctor_id`),
  CONSTRAINT `fk_prescriptions_patient` FOREIGN KEY (`patient_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_prescriptions_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =================================================================
-- Bảng 5: prescription_details - Chi tiết các thuốc trong một đơn
-- =================================================================
DROP TABLE IF EXISTS `prescription_details`;
CREATE TABLE `prescription_details` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `prescription_id` BIGINT UNSIGNED NOT NULL,
  `drug_id` BIGINT UNSIGNED NOT NULL,
  `dosage` VARCHAR(100) NOT NULL COMMENT 'Liều lượng, vd: 500mg',
  `frequency` VARCHAR(255) NOT NULL COMMENT 'Tần suất dùng, vd: 2 lần/ngày',
  `quantity` INT UNSIGNED NOT NULL COMMENT 'Số lượng',
  `duration` VARCHAR(100) NULL COMMENT 'Thời gian dùng, vd: 7 ngày',
  `notes` TEXT NULL COMMENT 'Ghi chú riêng cho thuốc này',
  PRIMARY KEY (`id`),
  INDEX `idx_prescription_id` (`prescription_id`),
  INDEX `idx_drug_id` (`drug_id`),
  CONSTRAINT `fk_details_prescription` FOREIGN KEY (`prescription_id`) REFERENCES `prescriptions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_details_drug` FOREIGN KEY (`drug_id`) REFERENCES `drugs` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =================================================================
-- Bảng 6: prescription_requests - Yêu cầu kê đơn thuốc
-- =================================================================
DROP TABLE IF EXISTS `prescription_requests`;
CREATE TABLE `prescription_requests` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `patient_id` BIGINT UNSIGNED NOT NULL,
  `doctor_id` BIGINT UNSIGNED NULL COMMENT 'Bác sĩ được chỉ định (nếu có)',
  `reason` TEXT NOT NULL COMMENT 'Lý do/triệu chứng',
  `status` ENUM('pending', 'approved', 'rejected') NOT NULL DEFAULT 'pending',
  `doctor_notes` TEXT NULL COMMENT 'Ghi chú phản hồi của bác sĩ',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `idx_request_patient_id` (`patient_id`),
  INDEX `idx_request_doctor_id` (`doctor_id`),
  INDEX `idx_request_status` (`status`),
  CONSTRAINT `fk_requests_patient` FOREIGN KEY (`patient_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_requests_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =================================================================
-- Bảng 7: medication_reminders - Nhắc nhở uống thuốc
-- =================================================================
DROP TABLE IF EXISTS `medication_reminders`;
CREATE TABLE `medication_reminders` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT UNSIGNED NOT NULL,
  `prescription_detail_id` BIGINT UNSIGNED NOT NULL,
  `reminder_time` TIME NOT NULL COMMENT 'Thời gian nhắc trong ngày',
  `is_active` BOOLEAN NOT NULL DEFAULT TRUE,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `idx_reminder_user_id` (`user_id`),
  CONSTRAINT `fk_reminders_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_reminders_prescription_detail` FOREIGN KEY (`prescription_detail_id`) REFERENCES `prescription_details` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =================================================================
-- Bảng 8: password_resets - Lưu token quên mật khẩu
-- =================================================================
DROP TABLE IF EXISTS `password_resets`;
CREATE TABLE `password_resets` (
  `email` VARCHAR(255) NOT NULL,
  `token` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`email`),
  UNIQUE KEY `uk_token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


SET FOREIGN_KEY_CHECKS = 1;