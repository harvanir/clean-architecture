CREATE TABLE order_items
(
    id         bigserial        not null primary key,
    order_id   bigint           not null,
    item_id    bigint           not null,
    quantity   integer          not null,
    price      double precision not null,
    created_at timestamptz,
    updated_at timestamptz,
    version    integer          not null default 0,
    foreign key (order_id) references orders (id),
    foreign key (item_id) references items (id),
    unique (order_id, item_id)
);