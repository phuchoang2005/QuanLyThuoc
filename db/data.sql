-- Thêm bác sĩ
INSERT INTO users (id, full_name, email, password, phone, role)
VALUES (UUID(), 'Dr. John Doe', 'dr.john@example.com', 'hashedpassword', '0123456789', 'DOCTOR');

-- Thêm bệnh nhân
INSERT INTO users (id, full_name, email, password, phone, role)
VALUES (UUID(), 'Jane Smith', 'jane@example.com', 'hashedpassword', '0987654321', 'PATIENT');

-- Thêm thuốc
INSERT INTO medication (id, name, unit, description)
VALUES
(UUID(), 'Paracetamol', 'viên', 'Giảm đau, hạ sốt'),
(UUID(), 'Amoxicillin', 'viên', 'Kháng sinh');

-- Sau đó có thể dùng SELECT để lấy ID, kê đơn và chi tiết đơn thuốc
