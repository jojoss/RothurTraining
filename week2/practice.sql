--create a table
CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    password INT,
    flagged BOOLEAN,
    ids TEXT
);


--insert data
INSERT INTO employees (
    first_name,
    last_name,
    email,
    password,
    flagged,
    ids
); VALUES (
    'Jake',
    'Sun',
    'JakeSun@example.com',
    123456,
    TRUE,
    '123'
);


--select data
SELECT first_name, email FROM employees;

SELECT * FROM employees
WHERE last_name = 'Sun';

SELECT * FROM employees
WHERE 
    last_name = 'Sun' AND
    email = 'JakeSun@example.com';

SELECT * FROM employees
WHERE 
    last_name = 'Sun' OR
    email = 'JakeSun@example.com';


--update data
UPDATE employees
SET password = 654321
WHERE email = 'JakeSun@example.com';


--delete data
DELETE FROM employees
WHERE email = 'JakeSun@example.com';


--count data
SELECT COUNT(*) FROM employees
WHERE flagged = TRUE;


--group data
SELECT flagged, COUNT(*) AS total
FROM employees
GROUP BY flagged;

SELECT flagged,
    COUNT(*) AS total,
    AVG(password) AS avg_pwd,
FROM employees
GROUP BY flagged;


-- join data
SELECT employees.first_name, departments.dept_name
FROM employees
JOIN departments
ON employees.dept_id = departments.id;