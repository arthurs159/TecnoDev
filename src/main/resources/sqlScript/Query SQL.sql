 ###### PRIMEIRO CARD: CONSULTA ######

SELECT * FROM Category WHERE `active` ORDER BY order_in_system;
SELECT * FROM Subcategory WHERE `active` ORDER BY `name`;
SELECT * FROM Course WHERE visibility = 'PUBLIC';
SELECT `name` FROM Subcategory WHERE `description` = '' OR `description` IS NULL;

######################################

 ###### CONSULTA AVANÇADA #####
SELECT DISTINCT s.`name` , s.order_in_system
FROM Subcategory s
INNER JOIN Course c
ON c.subcategory_id = s.id
WHERE `active`
ORDER BY order_in_system;

SELECT teacher, COUNT(*) as 'quantity'
FROM Course
GROUP BY teacher
ORDER BY quantity DESC
LIMIT 1;

SELECT cat.`name`, COUNT(course.id) as 'Qtd cursos', coalesce(sum(estimated_time_in_hours), 0) as "Horas de cursos"
FROM Category cat
LEFT JOIN Subcategory sub
on cat.id = sub.category_id
LEFT JOIN Course course
ON sub.id = course.subcategory_id
WHERE cat.`active`
GROUP BY cat.name;

#############################