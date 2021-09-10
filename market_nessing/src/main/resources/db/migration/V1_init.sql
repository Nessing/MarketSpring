CREATE TABLE users (
    id          bigserial,
    username    varchar(50) not null,
    password    varchar(100) not null,
    address     varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE roles (
    id          bigserial,
    name        varchar(50) not null,
    PRIMARY KEY (id)
);

CREATE TABLE users_roles (
    user_id     bigserial,
    role_id     bigserial,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE products (
    id          bigserial,
    title       varchar(255) not null,
    cost        int not null,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE categories (
    id          bigserial,
    title       varchar(100),
    PRIMARY KEY (id)
);

CREATE TABLE products_categories (
    product_id  bigserial,
    category_id bigserial,
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE orders (
    id          bigserial,      -- номер заказа
    user_id     bigserial,      -- номер заказа закреплен за пользователем
    product_id  bigserial,      -- наименование продуктов
    count       int not null,   -- количество продуктов
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
