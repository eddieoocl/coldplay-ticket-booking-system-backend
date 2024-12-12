SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM tickets;
DELETE FROM concerts;
DELETE FROM order_merchandise;
DELETE FROM orders;
DELETE FROM payments;
DELETE FROM ticket_inventory;
DELETE FROM ticket_types;
DELETE FROM users;
DELETE FROM order_tickets;
DELETE FROM merchandise;

SET FOREIGN_KEY_CHECKS = 1;

-- Insert initial data into Users table
INSERT INTO users (user_id, username, email, phone, password, first_name, last_name, city, country, created_at)
VALUES (1, 'john_doe', 'john.doe@example.com', '1234567890', 'encrypted_password1', 'John', 'Doe', 'New York', 'USA', '2025-02-23 20:00:00'),
       (2, 'jane_smith', 'jane.smith@example.com', '0987654321', 'encrypted_password2', 'Jane', 'Smith', 'Los Angeles',
        'USA', '2025-02-23 20:00:00');

-- Insert initial data into Concerts table
INSERT INTO concerts (concert_id, name, date, venue, city, country, description, image_url, ticket_sale_start, ticket_sale_end,
                      total_seats, status)
VALUES (1, 'Coldplay Live', '2025-02-22 20:00:00', 'Zayed Sports City Stadium', 'Abu Dhabi', 'USA',
        'Coldplay live concert', 'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/20231008144859_27550.png',
        '2025-01-20 10:00:00', '2025-01-27 10:00:00', 30000, 'BUY NOW'),
       (2, 'Coldplay Live', '2025-02-23 20:00:00', 'Zayed Sports City Stadium', 'Abu Dhabi', 'USA',
        'Coldplay live concert', 'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/20231008144859_27550.png',
        '2025-01-20 10:00:00', '2025-01-27 10:00:00', 30000, 'SOLD OUT'),
       (3, 'Coldplay Live', '2025-02-24 20:00:00', 'Zayed Sports City Stadium', 'Abu Dhabi', 'USA',
        'Coldplay live concert', 'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/20231008144859_27550.png',
        '2025-01-20 10:00:00', '2025-01-27 10:00:00', 30000, 'BUY NOW'),
       (4, 'Coldplay Live', '2025-03-11 20:00:00', 'Kai Tak Stadium', 'Hong Kong', 'China', 'Coldplay live concert',
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/75b99f7c88df4ddeb3d8114545f89472.webp', '2025-01-20 10:00:00',
        '2025-01-27 10:00:00', 30000, 'SOLD OUT'),
       (5, 'Coldplay Live', '2025-03-12 20:00:00', 'Kai Tak Stadium', 'Hong Kong', 'China', 'Coldplay live concert',
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/75b99f7c88df4ddeb3d8114545f89472.webp', '2025-01-20 10:00:00',
        '2025-01-27 10:00:00', 30000, 'BUY NOW'),
       (6, 'Coldplay Live', '2025-03-13 20:00:00', 'Kai Tak Stadium', 'Hong Kong', 'China', 'Coldplay live concert',
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/75b99f7c88df4ddeb3d8114545f89472.webp', '2025-01-20 10:00:00',
        '2025-01-27 10:00:00', 30000, 'BUY NOW');

-- Insert initial data into Ticket_Types table
INSERT INTO ticket_types (ticket_type_id, concert_id, type_name, price)
VALUES (1, 1, 'VIP', 3680.00),
       (2, 1, 'Gold', 2780.00),
       (3, 1, 'Silver', 2250.00),
       (4, 1, 'General', 1980.00),
       (5, 1, 'Regular', 1680.00),
       (6, 1, 'Economy', 1360.00),
       (7, 1, 'Back Row', 1099.00),
       (8, 2, 'VIP', 3680.00),
       (9, 2, 'Gold', 2780.00),
       (10, 2, 'Silver', 2250.00),
       (11, 2, 'General', 1980.00),
       (12, 2, 'Regular', 1680.00),
       (13, 2, 'Economy', 1360.00),
       (14, 2, 'Back Row', 1099.00),
       (15, 3, 'VIP', 3680.00),
       (16, 3, 'Gold', 2780.00),
       (17, 3, 'Silver', 2250.00),
       (18,3, 'General', 1980.00),
       (19, 3, 'Regular', 1680.00),
       (20, 3, 'Economy', 1360.00),
       (21, 3, 'Back Row', 1099.00),
       (22, 4, 'VIP', 3680.00),
       (23, 4, 'Gold', 2780.00),
       (24, 4, 'Silver', 2250.00),
       (25, 4, 'General', 1980.00),
       (26, 4, 'Regular', 1680.00),
       (27, 4, 'Economy', 1360.00),
       (28,4, 'Back Row', 1099.00),
       (29, 5, 'VIP', 3680.00),
       (30, 5, 'Gold', 2780.00),
       (31, 5, 'Silver', 2250.00),
       (32, 5, 'General', 1980.00),
       (33, 5, 'Regular', 1680.00),
       (34, 5, 'Economy', 1360.00),
       (35, 5, 'Back Row', 1099.00),
       (36, 6, 'VIP', 3680.00),
       (37, 6, 'Gold', 2780.00),
       (38, 6, 'Silver', 2250.00),
       (39, 6, 'General', 1980.00),
       (40, 6, 'Regular', 1680.00),
       (41, 6, 'Economy', 1360.00),
       (42, 6, 'Back Row', 1099.00);

-- Insert initial data into Ticket_Inventory table
INSERT INTO ticket_inventory (ticket_inventory_id, ticket_type_id, available_quantity, sold_quantity)
VALUES (1, 1, 0, 5000),     -- Sold out
       (2, 2, 10000, 5000),
       (3, 3, 2000, 2000),
       (4, 4, 14000, 0),
       (5, 5, 5000, 5000),  -- Sold out
       (6, 6, 8000, 0),
       (7, 7, 6000, 0),
       (8, 8, 0, 5000),     -- Sold out
       (9, 9, 10000, 5000),
       (10, 10, 2000, 2000),
       (11, 11, 14000, 0),
       (12, 12, 5000, 5000), -- Sold out
       (13, 13, 8000, 0),
       (14, 14, 6000, 0),
       (15, 15, 0, 5000),    -- Sold out
       (16, 16, 10000, 5000),
       (17, 17, 2000, 2000),
       (18, 18, 14000, 0),
       (19, 19, 5000, 5000), -- Sold out
       (20,20, 8000, 0),
       (21,21, 6000, 0),
       (22, 22, 0, 5000),    -- Sold out
       (23, 23, 10000, 5000),
       (24, 24, 2000, 2000),
       (25, 25, 14000, 0),
       (26, 26, 5000, 5000), -- Sold out
       (27, 27, 8000, 0),
       (28, 28, 6000, 0),
       (29, 29, 0, 5000),    -- Sold out
       (30, 30, 10000, 5000),
       (31, 31, 2000, 2000),
       (32, 32, 14000, 0),
       (33, 33, 5000, 5000), -- Sold out
       (34, 34, 8000, 0),
       (35, 35, 6000, 0),
       (36, 36, 0, 5000),    -- Sold out
       (37, 37, 10000, 5000),
       (38, 38, 2000, 2000),
       (39, 39, 14000, 0),
       (40, 40, 5000, 5000), -- Sold out
       (41, 41, 8000, 0),
       (42, 42, 6000, 0);

-- Insert initial data into Orders table
INSERT INTO orders (order_id, user_id, total_price, status, payment_method, order_date)
VALUES (1, 1, 450.00, 'completed', 'credit_card', '2025-02-22 20:00:00'),
       (2, 2, 350.00, 'completed', 'paypal', '2025-02-22 20:00:00');

-- Insert initial data into Order_Tickets table
INSERT INTO order_tickets (order_ticket_id, order_id, ticket_type_id, quantity, price)
VALUES (1, 1, 1, 1, 3680.00),
       (2, 1, 2, 1, 2780.00),
       (3, 2, 3, 2, 4500.00);

-- Insert initial data into Payments table
INSERT INTO payments (payment_id, order_id, amount, payment_status, payment_method, payment_date)
VALUES (1, 1, 6460.00, 'completed', 'credit_card', '2025-02-22 20:00:00'),
       (2, 2, 4500.00, 'completed', 'paypal', '2025-02-22 20:00:00');

-- Insert initial data into Tickets table
INSERT INTO tickets (ticket_id, order_item_id, user_id, ticket_number, seat_number, status, issue_date)
VALUES (1, 1, 1, 'TICKET12345', 'A1 Block 16', 'issued', '2025-02-22 20:00:00'),
       (2, 2, 1, 'TICKET12346', 'A2 Block 6', 'issued', '2025-02-22 20:00:00'),
       (3, 3, 2, 'TICKET12347', 'B1 Block 3', 'issued', '2025-02-22 20:00:00'),
       (4, 3, 2, 'TICKET12348', 'B2 Block 23', 'issued', '2025-02-22 20:00:00');

-- Insert initial data into Merchandise table
INSERT INTO merchandise (merchandise_id, concert_id, name, description, price, quantity, available_quantity, image_url, is_charity)
VALUES (1, 1, 'MOON MUSiC ECORECORD LP', 'MOON MUSiC ECORECORD LP', 30.50, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/06/Standard_Vinyl_Pink_220170226EC2NDIMAGE-600x600.webp',
        false),
       (2, 1, 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 220.99, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/10/FM-600x600.png', false),
       (3, 1, 'Coldplay T-Shirt', 'Official Coldplay T-Shirt', 5.00, 1000, 1000,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/t.jpg', false),
       (4, 1, 'Planting a tree', 'Plant a tree for a desert area and expand the oasis！', 15.00, 500, 500,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/tree.jpg', true),
       (5, 2, 'MOON MUSiC ECORECORD LP', 'MOON MUSiC ECORECORD LP', 30.50, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/06/Standard_Vinyl_Pink_220170226EC2NDIMAGE-600x600.webp',
        false),
       (6, 2, 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 220.99, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/10/FM-600x600.png', false),
       (7, 2, 'Coldplay T-Shirt', 'Official Coldplay T-Shirt', 5.00, 1000, 1000,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/t.jpg', false),
       (8, 2, 'Planting a tree', 'Plant a tree for a desert area and expand the oasis！', 15.00, 500, 500,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/tree.jpg', true),
       (9, 3, 'MOON MUSiC ECORECORD LP', 'MOON MUSiC ECORECORD LP', 30.50, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/06/Standard_Vinyl_Pink_220170226EC2NDIMAGE-600x600.webp',
        false),
       (10, 3, 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 220.99, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/10/FM-600x600.png', false),
       (11, 3, 'Coldplay T-Shirt', 'Official Coldplay T-Shirt', 5.00, 1000, 1000,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/t.jpg', false),
       (12, 3, 'Planting a tree', 'Plant a tree for a desert area and expand the oasis！', 15.00, 500, 500,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/tree.jpg', true),
       (13, 4, 'MOON MUSiC ECORECORD LP', 'MOON MUSiC ECORECORD LP', 30.50, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/06/Standard_Vinyl_Pink_220170226EC2NDIMAGE-600x600.webp',
        false),
       (14, 4, 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 220.99, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/10/FM-600x600.png', false),
       (15, 4, 'Coldplay T-Shirt', 'Official Coldplay T-Shirt', 5.00, 1000, 1000,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/t.jpg', false),
       (16, 4, 'Planting a tree', 'Plant a tree for a desert area and expand the oasis！', 15.00, 500, 500,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/tree.jpg', true),
       (17, 5, 'MOON MUSiC ECORECORD LP', 'MOON MUSiC ECORECORD LP', 30.50, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/06/Standard_Vinyl_Pink_220170226EC2NDIMAGE-600x600.webp', false),
       (18, 5, 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 220.99, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/10/FM-600x600.png', false),
       (19, 5, 'Coldplay T-Shirt', 'Official Coldplay T-Shirt', 5.00, 1000, 1000,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/t.jpg', false),
       (20, 5, 'Planting a tree', 'Plant a tree for a desert area and expand the oasis！', 15.00, 500, 500,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/tree.jpg', true),
       (21, 6, 'MOON MUSiC ECORECORD LP', 'MOON MUSiC ECORECORD LP', 30.50, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/06/Standard_Vinyl_Pink_220170226EC2NDIMAGE-600x600.webp',
        false),
       (22, 6, 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 220.99, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/10/FM-600x600.png', false),
       (23, 6, 'Coldplay T-Shirt', 'Official Coldplay T-Shirt', 5.00, 1000, 1000,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/t.jpg', false),
       (24, 6, 'Planting a tree', 'Plant a tree for a desert area and expand the oasis！', 15.00, 500, 500,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/tree.jpg', true)
;

-- Insert initial data into Order_Merchandise table
INSERT INTO order_merchandise (order_merchandise_id, order_id, merchandise_id, quantity, price)
VALUES (1, 1, 1, 2, 50.00),
       (2, 2, 2, 1, 15.00);