INSERT INTO payment_methods (name, description) VALUES ('CASH', 'Pago en efectivo');
INSERT INTO payment_methods (name, description) VALUES ('CREDIT_CARD', 'Tarjeta de crédito');
INSERT INTO payment_methods (name, description) VALUES ('DEBIT_CARD', 'Tarjeta de débito');
INSERT INTO payment_methods (name, description) VALUES ('TRANSFER', 'Transferencia bancaria');

INSERT INTO discount_types (name, percentage) VALUES ('COMMON', 0.0);
INSERT INTO discount_types (name, percentage) VALUES ('VIP', 10.0);

INSERT INTO users (public_id, email, password, role_type, approval_status)
VALUES (RANDOM_UUID(), 'admin@distribuidora.com', '$2b$10$ViQ7oJuHCJ7hfF37CQXscelqQZu2PvHsdDCb.lhrZC/FJ.2PWpYi6', 'ADMIN', 'APPROVED');

INSERT INTO suppliers (name, cuit, contact) VALUES ('Tech Distribuciones SA', '30-12345678-9', 'contacto@techdist.com');
INSERT INTO suppliers (name, cuit, contact) VALUES ('Electro Insumos SRL', '30-87654321-5', 'ventas@electroinsumos.com');

INSERT INTO products (name, description, stock, unit_price, category, supplier_id) VALUES ('Cable HDMI 2m', 'Cable HDMI alta velocidad 4K', 50, 1500.99, 'CABLES', 1);
INSERT INTO products (name, description, stock, unit_price, category, supplier_id) VALUES ('Cable USB-C 1m', 'Cable de carga y datos USB-C', 80, 800.00, 'CABLES', 1);
INSERT INTO products (name, description, stock, unit_price, category, supplier_id) VALUES ('Cargador rápido 30W', 'Cargador USB-C carga rápida', 40, 4500.00, 'CHARGERS', 1);
INSERT INTO products (name, description, stock, unit_price, category, supplier_id) VALUES ('Auriculares Bluetooth', 'Auriculares inalámbricos con micrófono', 25, 8900.00, 'AUDIO', 2);
INSERT INTO products (name, description, stock, unit_price, category, supplier_id) VALUES ('Soldador eléctrico 60W', 'Soldador para reparación electrónica', 15, 3200.00, 'TOOLS', 2);
INSERT INTO products (name, description, stock, unit_price, category, supplier_id) VALUES ('Capacitor electrolítico 1000uF', 'Componente electrónico pasivo', 200, 50.00, 'COMPONENTS', 2);
INSERT INTO products (name, description, stock, unit_price, category, supplier_id) VALUES ('Pantalla LCD repuesto', 'Repuesto de pantalla genérico', 10, 12500.00, 'SCREENS', 1);
INSERT INTO products (name, description, stock, unit_price, category, supplier_id) VALUES ('Batería Li-ion 3.7V', 'Batería recargable de litio', 60, 1800.00, 'BATTERIES', 2);