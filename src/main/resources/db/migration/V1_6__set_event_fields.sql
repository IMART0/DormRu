ALTER TABLE events
    ALTER COLUMN event_date TYPE timestamp
        USING event_date::timestamp;