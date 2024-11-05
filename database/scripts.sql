SELECT *
FROM categories;

SELECT *
FROM notes;

SELECT *
FROM note_categories;

SELECT n.note_id, title, text, ca.category_id, category_name, status
FROM notes n INNER JOIN note_categories nc
ON n.note_id = nc.note_id INNER JOIN categories ca
ON nc.category_id = ca.category_id;