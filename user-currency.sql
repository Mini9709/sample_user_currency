drop TABLE if exists user;
drop TABLE if exists currency;
drop TABLE if exists user_currency;

CREATE TABLE user
(
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(10) NOT NULL,
    email VARCHAR(30) NOT NULL,
    create_at DATETIME NOT NULL,
    registered_date DATETIME NOT NULL
);

CREATE TABLE currency
(
    currency_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    currency_name VARCHAR(20) NOT NULL,
    exchange_rate DOUBLE NOT NULL,
    symbol VARCHAR(1) NOT NULL,
    create_at DATETIME NOT NULL,
    registered_date DATETIME NOT NULL
);

CREATE TABLE user_currency
(
    user_currency_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount_in_krw BIGINT NOT NULL,
    amount_after_exchange DOUBLE NOT NULL,
    status VARCHAR(10) NOT NULL,
    create_at DATETIME NOT NULL,
    registered_date DATETIME NOT NULL,
    user_id BIGINT NOT NULL,
    to_currency_id BIGINT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (to_currency_id) REFERENCES currency(currency_id)
);