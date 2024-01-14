ALTER TABLE request_entity
    ADD app_id BIGINT;

ALTER TABLE request_entity
    ADD status_code INTEGER;

ALTER TABLE request_entity
    ALTER COLUMN status_code SET NOT NULL;

ALTER TABLE request_entity
    DROP COLUMN status_value;