CREATE TABLE items
(
    id       BIGINT  NOT NULL PRIMARY KEY,
    name     VARCHAR NOT NULL,
    quantity INTEGER NOT NULL,
    price    DOUBLE PRECISION,
    created_at timestamptz,
    updated_at timestamptz,
    version  BIGINT  not null default 0
);