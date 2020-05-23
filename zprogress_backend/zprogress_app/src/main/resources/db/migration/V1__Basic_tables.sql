CREATE TABLE user
(
    name     VARCHAR(45)        NOT NULL,
    password VARCHAR(32)        NOT NULL,
    PRIMARY KEY (name)
);

insert into user (name, password) values ('arvin', md5('mypass'));
insert into user (name, password) values ('argin', md5('mypass'));
insert into user (name, password) values ('awenia', md5('mypass'));

CREATE TABLE goal
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    user_name   VARCHAR(45)            NOT NULL,

    name        VARCHAR(100)   NOT NULL,
    description VARCHAR(10000) NOT NULL,
    deadline    DATE           NOT NULL,

    CONSTRAINT user_id_fk
        FOREIGN KEY (user_name)
            REFERENCES user (name)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    AUTO_INCREMENT = 1;

CREATE TABLE step
(
    id               INT AUTO_INCREMENT NOT NULL,
    goal_id          INT                NOT NULL,
    user_name   VARCHAR(45)            NOT NULL,

    name             VARCHAR(450)        NOT NULL,
    importance       INT                NOT NULL,
    startDate        DATE               NOT NULL,
    repetitionType   VARCHAR(7)         NOT NULL,
    nextReminderDate DATETIME           NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT goal_id_fk
        FOREIGN KEY (goal_id)
            REFERENCES goal (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT step_user_id_fk
        FOREIGN KEY (user_name)
            REFERENCES user (name)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    AUTO_INCREMENT = 1;


insert into goal (name, description, deadline, user_name) VALUES ('eat healthy', 'eat healthy', '2022-09-01', 'arvin');
insert into goal (name, description, deadline, user_name) VALUES ('develop hard skills', 'develop hard skills', '2022-09-01', 'arvin');
insert into goal (name, description, deadline, user_name) VALUES ('develop soft skills', 'develop soft skills', '2022-09-01', 'arvin');

insert into goal (name, description, deadline, user_name) VALUES ('YOU SHOULD SEE THIS GOAL', 'NO PERMISSION...ACTUALLY!', '2022-09-01', 'argin');

insert into step (goal_id, user_name, name, importance, startDate, repetitionType, nextReminderDate) VALUES (1, 'arvin', 'buy healthy food', 3, CURDATE(), 'WEEKLY', NOW() + INTERVAL 1 WEEK);
insert into step (goal_id, user_name, name, importance, startDate, repetitionType, nextReminderDate) VALUES (1, 'arvin', 'cook in everything in the morning', 3, CURDATE(), 'DAILY', NOW() + INTERVAL 1 DAY);
insert into step (goal_id, user_name, name, importance, startDate, repetitionType, nextReminderDate) VALUES (1, 'arvin', 'journal how you feel', 3, CURDATE(), 'DAILY', NOW() + INTERVAL 1 DAY);


insert into step (goal_id, user_name, name, importance, startDate, repetitionType, nextReminderDate) VALUES (2, 'arvin', 'learn everyday for at least two hours', 4, CURDATE(), 'DAILY', NOW() + INTERVAL 1 DAY);
insert into step (goal_id, user_name, name, importance, startDate, repetitionType, nextReminderDate) VALUES (2, 'arvin', 'read about new promising technologies weekly', 3, CURDATE(), 'WEEKLY', NOW() + INTERVAL 1 WEEK);
insert into step (goal_id, user_name, name, importance, startDate, repetitionType, nextReminderDate) VALUES (2, 'arvin', 'journal about your progress daily', 5, CURDATE(), 'DAILY', NOW() + INTERVAL 1 DAY);


insert into step (goal_id, user_name, name, importance, startDate, repetitionType, nextReminderDate) VALUES (3, 'arvin', 'plan networking events to attend', 3, CURDATE(), 'WEEKLY', NOW() + INTERVAL 1 WEEK);
insert into step (goal_id, user_name, name, importance, startDate, repetitionType, nextReminderDate) VALUES (3, 'arvin', 'attend the networking event and socialize with people', 5, CURDATE(), 'DAILY', NOW() + INTERVAL 1 DAY);
insert into step (goal_id, user_name, name, importance, startDate, repetitionType, nextReminderDate) VALUES (3, 'arvin', 'journal daily', 5, CURDATE(), 'DAILY', NOW() + INTERVAL 1 DAY);