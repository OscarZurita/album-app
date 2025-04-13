--this data.sql is intended to be run using the containerized application

INSERT INTO album (title, last_modified_date, upload_date, description, url)
VALUES ('Album1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'first example album','http://example.com/album1');

-- Create photos associated with album 1
INSERT INTO photo (id, title, file_path, description, url, last_modified_date, upload_date, album_id)
VALUES (1, 'Laura con gorro', './pictures/Album1/Photo1.jpg', 'Laura con un gorro gracioso en el centro comercial de málaga', 'http://example.com/photo1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO photo (id, title, file_path, description, url, last_modified_date, upload_date, album_id)
VALUES (2, 'Photo2', './pictures/Album1/Photo2.jpg', 'Description for Photo2', 'http://example.com/photo2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO photo (id, title, file_path, description, url, last_modified_date, upload_date, album_id)
VALUES (3, 'Photo3', './pictures/Album1/Photo3.jpg', 'Description for Photo3', 'http://example.com/photo3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO photo (id, title, file_path, description, url, last_modified_date, upload_date, album_id)
VALUES (4, 'Photo4', './pictures/Album1/Photo4.jpg', 'Description for Photo4', 'http://example.com/photo4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO photo (id, title, file_path, description, url, last_modified_date, upload_date, album_id)
VALUES (5, 'Photo5', './pictures/Album1/Photo5.jpg', 'Description for Photo5', 'http://example.com/photo5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO photo (id, title, file_path, description, url, last_modified_date, upload_date, album_id)
VALUES (6, 'Photo6', './pictures/Album1/Photo6.jpg', 'Description for Photo6', 'http://example.com/photo6', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO photo (id, title, file_path, description, url, last_modified_date, upload_date, album_id)
VALUES (7, 'Photo7', './pictures/Album1/Photo7.jpg', 'Description for Photo7', 'http://example.com/photo7', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO photo (id, title, file_path, description, url, last_modified_date, upload_date, album_id)
VALUES (8, 'Photo8', './pictures/Album1/Photo8.jpg', 'Description for Photo8', 'http://example.com/photo8', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO photo (id, title, file_path, description, url, last_modified_date, upload_date, album_id)
VALUES (9, 'Photo9', './pictures/backgrounds/background1.jpg', 'background', 'http://example.com/background1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO user (name, email, joined_date, description, profile_pic)
VALUES ('Laura', 'laura@gmail.com', CURRENT_TIMESTAMP, 'empty', './pictures/profiles/profile1.jpg');

INSERT INTO user (name, email, joined_date, description)
VALUES ('Oscar', 'oscar@gmail.com', CURRENT_TIMESTAMP, 'empty');

INSERT INTO user_albums (user_id, album_id)
VALUES (1, 1);

UPDATE album
SET cover_photo_id = 1, background_photo_id = 9
WHERE id = 1;