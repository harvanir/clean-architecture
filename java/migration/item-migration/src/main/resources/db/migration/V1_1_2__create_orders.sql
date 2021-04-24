create table orders
(
    id          bigserial primary key,
    status      varchar  not null,
    status_code smallint not null,
    created_at  timestamptz,
    updated_at  timestamptz,
    version     bigint   not null default 0
);