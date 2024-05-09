CREATE TABLE discount_details (
    id INT NOT NULL,
    food_tyoe VARCHAR(25),
    furniture_tyoe VARCHAR(25),
    discount INT NOT NULL
);

CREATE TABLE discount (
    id INT NOT NULL,
    type VARCHAR(25) NOT NULL,
    discount_details_id INT not null
);

CREATE TABLE customer_discount_map (
    cunstomer_id INT NOT NULL,
    discount_id INT NOT NULL
);

CREATE TABLE customer (
    id INT NOT NULL,
    member BIT
);

CREATE TABLE customer_order (
    id INT NOT NULL,
    customer_id INT NOT NULL,
    price INT NOT NULL
);

CREATE TABLE order_item (
    id INT NOT NULL,
    customer_order_id INT NOT NULL,
    product_id INT,
    food_id INT,
    discount INT
);

CREATE TABLE warehouse_product (
    id INT NOT NULL,
    type VARCHAR(25) NOT NULL,
    status VARCHAR(25) NOT NULL,
    item_number INT NOT NULL UNIQUE,
    description VARCHAR(50),
    price INT,
    weight INT,
    color VARCHAR(50)
);

CREATE TABLE food_product (
    id INT NOT NULL,
    type VARCHAR(25),
    flavor VARCHAR(50),
    price INT,
    description VARCHAR(50),
    stock INT NOT NULL
);