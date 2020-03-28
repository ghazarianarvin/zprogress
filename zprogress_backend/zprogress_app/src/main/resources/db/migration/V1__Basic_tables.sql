CREATE TABLE goal
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100)   NOT NULL,
    description VARCHAR(10000) NOT NULL,
    deadline    DATE           NOT NULL
)
    AUTO_INCREMENT = 1;

CREATE TABLE step
(
    id               INT AUTO_INCREMENT NOT NULL,
    goal_id          INT                NOT NULL,
    name             VARCHAR(45)        NOT NULL,
    importance       INT                NOT NULL,
    startDate        DATE               NOT NULL,
    repetitionType   VARCHAR(7)         NOT NULL,
    nextReminderDate DATETIME               NULL,
    PRIMARY KEY (id),
    INDEX goal_id_fk_idx (goal_id ASC) VISIBLE,
    CONSTRAINT goal_id_fk
        FOREIGN KEY (goal_id)
            REFERENCES zprogress.goal (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    AUTO_INCREMENT = 1;