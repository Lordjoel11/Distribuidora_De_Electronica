INSERT INTO payment_methods (name, description) VALUES ('CASH', 'Pago en efectivo');
INSERT INTO payment_methods (name, description) VALUES ('CREDIT_CARD', 'Tarjeta de crédito');
INSERT INTO payment_methods (name, description) VALUES ('DEBIT_CARD', 'Tarjeta de débito');
INSERT INTO payment_methods (name, description) VALUES ('TRANSFER', 'Transferencia bancaria');

INSERT INTO discount_types (name, percentage) VALUES ('COMMON', 0.0);
INSERT INTO discount_types (name, percentage) VALUES ('VIP', 10.0);

INSERT INTO users (public_id, email, password, role_type, approval_status)
VALUES (RANDOM_UUID(), 'admin@distribuidora.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN', 'APPROVED');