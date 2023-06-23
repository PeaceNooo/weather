CREATE TABLE teacher (
                         teacher_id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         address TEXT
);

CREATE TABLE student (
                         student_id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         address TEXT
);

CREATE TABLE teacher_student (
                                 id SERIAL PRIMARY KEY,
                                 teacher_id INTEGER REFERENCES teacher(teacher_id),
                                 student_id INTEGER REFERENCES student(student_id)
);

INSERT INTO teacher (name, email, address) VALUES
                                               ('John Smith', 'johnsmith@gmail.com', '123 Maple St, Anytown, USA'),
                                               ('Maria Garcia', 'mgarcia@yahoo.com', '456 Pine Rd, Othertown, USA'),
                                               ('Lee Chen', 'lchen@hotmail.com', '789 Oak Ave, Sometown, USA'),
                                               ('Sophia Patel', 'spatel@icloud.com', '321 Birch Blvd, Thattown, USA'),
                                               ('Mohammed Khan', 'mkhan@outlook.com', '654 Spruce Way, Theirtown, USA');

INSERT INTO student (name, email, address) VALUES
                                               ('Olivia Johnson', 'ojohnson@gmail.com', '987 Cedar Dr, Mytown, USA'),
                                               ('Liam Williams', 'lwilliams@yahoo.com', '234 Elm Rd, Yourtown, USA'),
                                               ('Ava Taylor', 'ataylor@hotmail.com', '567 Willow Ave, Histown, USA'),
                                               ('Noah Brown', 'nbrown@icloud.com', '890 Beech Blvd, Hertown, USA'),
                                               ('Isabella Davies', 'idavies@outlook.com', '345 Cherry Way, Ourtown, USA'),
                                               ('Lucas Thomas', 'lthomas@gmail.com', '678 Poplar Dr, Theirtown, USA'),
                                               ('Mia Wilson', 'mwilson@yahoo.com', '123 Hawthorn Rd, Oldtown, USA'),
                                               ('Alexander Robinson', 'arobinson@hotmail.com', '456 Ash Ave, Newtown, USA'),
                                               ('Charlotte Jackson', 'cjackson@icloud.com', '789 Aspen Blvd, Fartown, USA'),
                                               ('William Harris', 'wharris@outlook.com', '321 Pine Way, Neartown, USA');

INSERT INTO teacher_student (teacher_id, student_id) VALUES
                                                         (1, 1),
                                                         (1, 2),
                                                         (1, 3),
                                                         (2, 4),
                                                         (2, 5),
                                                         (2, 6),
                                                         (3, 7),
                                                         (3, 8),
                                                         (3, 9),
                                                         (4, 10),
                                                         (4, 1),
                                                         (5, 2),
                                                         (5, 3),
                                                         (5, 4);
