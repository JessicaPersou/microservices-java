-- Inserindo produtos eletrônicos
INSERT INTO products.product(product_identifier, name, description, price, category_id) VALUES
('ELE123', 'Smartphone', 'Um smartphone avançado', 799.99, 1),
('ELE456', 'Fones de Ouvido Bluetooth', 'Fones de ouvido sem fio de alta qualidade', 129.99, 1);

-- Inserindo móveis
INSERT INTO products.product(product_identifier, name, description, price, category_id) VALUES
('MOV789', 'Sofá de Couro', 'Sofá elegante e confortável', 899.99, 2),
('MOV101', 'Mesa de Jantar', 'Mesa espaçosa para refeições em família', 349.99, 2);

-- Inserindo brinquedos
INSERT INTO products.product(product_identifier, name, description, price, category_id) VALUES
('BRQ111', 'Carrinho de Controle Remoto', 'Carrinho de brinquedo com controle remoto', 39.99, 3),
('BRQ222', 'Quebra-Cabeça', 'Quebra-cabeça desafiador para todas as idades', 14.99, 3);
