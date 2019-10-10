CREATE TABLE order_items
(
    id       BIGINT           NOT NULL PRIMARY KEY,
    order_id BIGINT           NOT NULL,
    item_id  BIGINT           NOT NULL,
    quantity INTEGER          NOT NULL,
    price    DOUBLE PRECISION NOT NULL,
    created_at timestamptz,
    updated_at timestamptz,
    version  INTEGER          NOT NULL DEFAULT 0,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (item_id) REFERENCES items (id),
    UNIQUE (order_id, item_id)
);