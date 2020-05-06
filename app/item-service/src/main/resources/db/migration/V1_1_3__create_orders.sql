CREATE TABLE orders
(
    id          BIGINT   NOT NULL PRIMARY KEY,
    status      VARCHAR  NOT NULL,
    status_code SMALLINT not null,
    created_at timestamptz,
    updated_at timestamptz,
    version     INTEGER  not null default 0
);