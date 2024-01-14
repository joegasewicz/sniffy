ALTER TABLE application_entity
    DROP COLUMN pole_rate;

ALTER TABLE application_entity
    DROP COLUMN pole_status;

ALTER TABLE application_entity
    ALTER COLUMN poll_rate SET NOT NULL;