CREATE TABLE contacts.contacts
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(255),
    lastName VARCHAR(255)
);


CREATE TABLE contacts.users
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    googleId VARCHAR(255),
    createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    email VARCHAR(255)
);
CREATE UNIQUE INDEX users_googleId_uindex ON contacts.users (googleId);
CREATE UNIQUE INDEX users_email_uindex ON contacts.users (email);


CREATE TABLE contacts.sessions
(
    token VARCHAR(50) PRIMARY KEY NOT NULL,
    createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    userId INT NOT NULL
);
CREATE UNIQUE INDEX sessions_token_uindex ON contacts.sessions (token);
