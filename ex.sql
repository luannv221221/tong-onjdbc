CREATE DATABASE Contact_Book;
USE Contact_Book;
CREATE TABLE contact(
                        id int primary key auto_increment,
                        name varchar(50) not null,
                        email varchar(50) not null unique,
                        phone varchar(50) not null unique,
                        sex bit(1) not null,
                        address varchar(100),
                        rating int not null,
                        created date
);
-- thu tuc luu tru
DELIMITER $$
CREATE PROCEDURE sp_get_all_contact()
BEGIN
SELECT * FROM contact;
end $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE sp_add_contact(
    IN p_name varchar(50),
    p_email varchar(50),
    p_phone varchar(50),
    p_sex bit(1),
    p_address varchar(100),
    p_rating int,
    p_created date
)
BEGIN
INSERT INTO contact(name, email, phone, sex, address, rating, created)
    VALUE (p_name,p_email,p_phone,p_sex,p_address,p_rating,p_created);
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_edit_contact(
    IN
        p_id int,
    p_name varchar(50),
    p_email varchar(50),
    p_phone varchar(50),
    p_sex bit(1),
    p_address varchar(100),
    p_rating int,
    p_created date
)
BEGIN
UPDATE contact SET name = p_name,email = p_email,phone=p_phone,
                   sex = p_sex,address = p_address,rating = p_rating,created = p_created WHERE id = p_id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_delete_contact(IN p_id int)
BEGIN
DELETE FROM contact WHERE id = p_id;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_find_contact_by_id(IN p_id int)
BEGIN
SELECT * FROM contact WHERE id = p_id;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_search_contact_by_name(IN p_name varchar(50))
BEGIN
SELECT * FROM contact where name LIKE CONCAT('%',p_name,'%');
end $$
DELIMITER ;

-- thu tuc kien tra xem co ban ghi nao co email truy vao hay chua

DELIMITER $$
CREATE PROCEDURE sp_check_email_exist(IN p_email varchar(50))
BEGIN
SELECT 1 FROM contact WHERE email = p_email;
END $$
DELIMITER ;