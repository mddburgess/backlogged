CREATE TABLE titles
(
    id    INTEGER GENERATED BY DEFAULT AS IDENTITY,
    token UUID NOT NULL,
    name  VARCHAR,
    PRIMARY KEY (id)
);

CREATE TABLE copies
(
    id       INTEGER GENERATED BY DEFAULT AS IDENTITY,
    title_id INTEGER NOT NULL,
    platform VARCHAR,
    service  VARCHAR,
    PRIMARY KEY (id),
    FOREIGN KEY (title_id)
        REFERENCES titles (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
