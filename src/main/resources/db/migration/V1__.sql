CREATE SEQUENCE IF NOT EXISTS application_entity_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE application_entity
(
    id        BIGINT  NOT NULL,
    name      VARCHAR(255),
    type      VARCHAR(255),
    url       VARCHAR(255),
    status    VARCHAR(255),
    pole_rate INTEGER NOT NULL,
    CONSTRAINT pk_applicationentity PRIMARY KEY (id)
);