ALTER TABLE application_entity
    ADD created_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE application_entity
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE request_entity
    ADD created_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE request_entity
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;