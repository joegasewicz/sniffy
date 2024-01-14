ALTER TABLE request_entity
    ADD status_phrase VARCHAR(255);

ALTER TABLE request_entity
    ADD status_value INTEGER;

ALTER TABLE request_entity
    ALTER COLUMN status_value SET NOT NULL;

ALTER TABLE request_entity
    DROP COLUMN status;