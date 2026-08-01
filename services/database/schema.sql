create database shoppingproducts;

create table products (
    code serial not null primary key,
    name varchar(100) not null,
    unit_price decimal(16, 2) not null
);

create database shoppingcustoomers;

create table customers (
    code serial not null primary key,
    name varchar(150) not null,
    cpf char(11) not null,
    street varchar(100),
    number varchar(10),
    neighborhood varchar(100),
    email varchar(150),
    phone varchar(20)
);

create database shoppingorders;

create table orders (
    code serial not null primary key,
    customer_code bigint not null,
    date_customers timestamp not null default now(),
    payment_key text,
    observation text,
    status varchar(20) check (
        status in (
            'REALIZADO',
            'PAGO',
            'FATURADO',
            'ENVIADO',
            'ERRO_PAGAMENTO',
            'PREPARANDO_ENVIO'
        )
    ),
    total decimal(16, 2) not null,
    tracking_number varchar(255),
    url_nf text
);

create table orders_item (
    code serial not null primary key,
    orders_code bigint not null references orders (code),
    product_code bigint not null,
    amount int not null,
    unit_price decimal(16, 2) not null
);