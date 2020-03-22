CREATE TABLE goal
(
    id          INT            NOT NULL,
    name        VARCHAR(100)   NOT NULL,
    description VARCHAR(10000) NOT NULL,
    deadline    DATE           NOT NULL,
    PRIMARY KEY (`id`)
);