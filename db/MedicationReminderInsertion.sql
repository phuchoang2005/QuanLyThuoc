INSERT INTO medication_reminders (id, user_id, prescription_detail_id, reminder_time, is_active, created_at) VALUES
(1, 1, 1, '08:00:00', true, '2024-05-20 12:00:00'),
(2, 1, 2, '20:00:00', true, '2024-05-20 12:01:00'),
(3, 2, 3, '07:30:00', true, '2024-06-10 15:00:00'),
(4, 2, 3, '19:30:00', true, '2024-06-10 15:01:00'),
(5, 3, 5, '08:00:00', false, '2024-06-20 10:00:00'),
(6, 3, 5, '20:00:00', false, '2024-06-20 10:01:00');