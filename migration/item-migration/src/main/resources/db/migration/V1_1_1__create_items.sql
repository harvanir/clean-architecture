create table items
(
    id         bigserial primary key,
    name       varchar not null,
    quantity   integer not null,
    price      double precision,
    created_at timestamptz,
    updated_at timestamptz,
    version    bigint  not null default 0
);