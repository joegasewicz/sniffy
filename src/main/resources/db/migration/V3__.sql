CREATE SEQUENCE IF NOT EXISTS request_entity_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE application_entity_request_entities
(
    application_entity_id BIGINT NOT NULL,
    request_entities_id   BIGINT NOT NULL
);

CREATE TABLE request_entity
(
    id  BIGINT NOT NULL,
    log VARCHAR(255),
    CONSTRAINT pk_request_entity PRIMARY KEY (id)
);

ALTER TABLE application_entity_request_entities
    ADD CONSTRAINT uc_application_entity_request_entities_requestentities UNIQUE (request_entities_id);

ALTER TABLE application_entity_request_entities
    ADD CONSTRAINT fk_appentreqent_on_application_entity FOREIGN KEY (application_entity_id) REFERENCES application_entity (id);

ALTER TABLE application_entity_request_entities
    ADD CONSTRAINT fk_appentreqent_on_request_entity FOREIGN KEY (request_entities_id) REFERENCES request_entity (id);