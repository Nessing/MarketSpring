create table products
(
    id         bigserial primary key,
    title      varchar(255),
    price      int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into products (title, price)
values
    ('Bread', 24),
    ('Milk', 65),
    ('Cheese', 320),
    ('Orange', 322),
    ('Tower', 323),
    ('Chair', 324),
    ('Table', 325),
    ('Soap Dish', 326),
    ('Tower Rack', 327),
    ('Curtain', 328),
    ('Spring Box', 328),
    ('Notebook', 328),
    ('Curtain Holder Rod', 328),
    ('Rubber Mat', 328),
    ('Rug', 328),
    ('Facecloth', 328),
    ('Hand Tower', 328);
