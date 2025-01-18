CREATE TABLE system_user
(
    id          int auto_increment
        primary key,
    username    varchar(45)  null   UNIQUE,
    password    varchar(125) null,
    created_by  varchar(45)  null,
    created_on  datetime     DEFAULT CURRENT_TIMESTAMP,
    modified_by varchar(45)  null,
    modified_on datetime     DEFAULT CURRENT_TIMESTAMP,
    email       varchar(45)  null   UNIQUE,
    first_name  varchar(45)  null,
    last_name   varchar(45)  null,
    phone       varchar(45)  null,
    address     varchar(45)  null,
    gender      tinyint      null,
    deleted     tinyint      null,
    status      varchar(3)   null,
    role        varchar(45)  not null
);

CREATE TABLE topic (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_by INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES system_user(id)
);

CREATE TABLE vocabulary (
    id INT AUTO_INCREMENT PRIMARY KEY,
    word VARCHAR(255) NOT NULL,
    meaning TEXT NOT NULL,
    topic_id INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (topic_id) REFERENCES topic(id)
);

ALTER TABLE vocabulary ADD INDEX vocabulary_uidx01 (topic_id);

CREATE TABLE topic_schedule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    topic_id INT NOT NULL,
    scheduled_date DATE NOT NULL,
    is_completed BOOLEAN DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (topic_id) REFERENCES topic(id)
);

CREATE TABLE topic_study_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    topic_id INT NOT NULL,
    study_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    success_rate DECIMAL(5, 2),
    notes TEXT,
    FOREIGN KEY (topic_id) REFERENCES topic(id)
);



