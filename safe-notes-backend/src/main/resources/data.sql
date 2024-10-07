INSERT INTO notes (note_id, title, status, note_date, text) VALUES
(nextval('note_seq'), 'Grocery Shopping', 'ACTIVE', '2024-09-10', 'Buy groceries for the week: milk, eggs, and bread.'),
(nextval('note_seq'), 'Math Homework', 'ARCHIVED', '2024-09-01', 'Complete math problems from page 45 to 50.'),
(nextval('note_seq'), 'Project Meeting', 'ACTIVE', '2024-09-05', 'Discuss the upcoming project deadlines and task distribution.'),
(nextval('note_seq'), 'Dinner with Friends', 'ARCHIVED', '2024-08-28', 'Have dinner at Joe''s Pizza at 7 PM.'),
(nextval('note_seq'), 'Doctor''s Appointment', 'ACTIVE', '2024-09-12', 'Annual check-up at the clinic, 10 AM.'),
(nextval('note_seq'), 'Read Book', 'ACTIVE', '2024-09-08', 'Finish reading ''Atomic Habits'' by James Clear.'),
(nextval('note_seq'), 'Plan Vacation', 'ACTIVE', '2024-09-03', 'Research destinations for a winter holiday trip.'),
(nextval('note_seq'), 'Team Feedback', 'ARCHIVED', '2024-08-30', 'Submit performance feedback for team members.'),
(nextval('note_seq'), 'Car Maintenance', 'ACTIVE', '2024-09-07', 'Schedule a service appointment for oil change and tire rotation.'),
(nextval('note_seq'), 'Birthday Gift', 'ARCHIVED', '2024-09-02', 'Buy a birthday gift for Sarah. Look for something related to hiking or photography.');

INSERT INTO categories (category_id, category_name) VALUES
(1, 'meal'),
(2, 'urgent'),
(3, 'school'),
(4, 'work'),
(5, 'social'),
(6, 'health'),
(7, 'leisure'),
(8, 'personal development'),
(9, 'personal'),
(10, 'travel'),
(11, 'admin');

INSERT INTO note_categories (note_id, category_id) VALUES
(1, 1), -- Grocery Shopping -> meal
(1, 2), -- Grocery Shopping -> urgent
(2, 3), -- Math Homework -> school
(2, 4), -- Math Homework -> work
(3, 4), -- Project Meeting -> work
(3, 2), -- Project Meeting -> urgent
(4, 1), -- Dinner with Friends -> meal
(4, 5), -- Dinner with Friends -> social
(5, 6), -- Doctor's Appointment -> health
(5, 2), -- Doctor's Appointment -> urgent
(6, 7), -- Read Book -> leisure
(6, 8), -- Read Book -> personal development
(7, 10), -- Plan Vacation -> travel
(7, 9), -- Plan Vacation -> personal
(8, 4), -- Team Feedback -> work
(8, 11), -- Team Feedback -> admin
(9, 9), -- Car Maintenance -> personal
(9, 2), -- Car Maintenance -> urgent
(10, 9), -- Birthday Gift -> personal
(10, 5); -- Birthday Gift -> social
