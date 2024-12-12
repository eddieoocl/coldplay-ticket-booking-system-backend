DROP TABLE IF EXISTS users, concerts, ticket_types, ticket_inventory, orders, order_tickets, payments, tickets, merchandise, order_merchandise;

CREATE TABLE users
(
    user_id    INT PRIMARY KEY AUTO_INCREMENT,     -- 用户ID
    username   VARCHAR(100) NOT NULL UNIQUE,       -- 用户名
    email      VARCHAR(255) NOT NULL UNIQUE,       -- 邮箱
    phone      VARCHAR(20),                        -- 电话
    password   VARCHAR(255) NOT NULL,              -- 密码（加密后）
    first_name VARCHAR(100),                       -- 姓名
    last_name  VARCHAR(100),                       -- 姓氏
    city       VARCHAR(100),                       -- 城市
    country    VARCHAR(100),                       -- 国家
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 注册时间
);

CREATE TABLE concerts
(
    concert_id        INT PRIMARY KEY AUTO_INCREMENT, -- 演唱会ID
    name              VARCHAR(255) NOT NULL,          -- 演唱会名称
    date              DATETIME     NOT NULL,          -- 演唱会日期
    venue             VARCHAR(255) NOT NULL,          -- 场地名称
    city              VARCHAR(100),                   -- 城市
    country           VARCHAR(100),                   -- 国家
    description       TEXT,                           -- 演唱会描述
    image_url         VARCHAR(255),                   -- 演唱会封面图
    ticket_sale_start DATETIME,                       -- 票务销售开始时间
    ticket_sale_end   DATETIME,                       -- 票务销售结束时间
    total_seats       INT          NOT NULL,          -- 演唱会总座位数
    status            VARCHAR(30) DEFAULT 'SOLD OUT'  -- 演唱会状态
);

CREATE TABLE ticket_types
(
    ticket_type_id INT PRIMARY KEY AUTO_INCREMENT,            -- 票种ID
    concert_id     INT,                                       -- 演唱会ID（外键）
    type_name      VARCHAR(100)   NOT NULL,                   -- 票种名称（如VIP、普通票）
    price          DECIMAL(10, 2) NOT NULL,                   -- 票价
    FOREIGN KEY (concert_id) REFERENCES Concerts (concert_id) -- 外键关联演唱会表
);

CREATE TABLE ticket_inventory
(
    ticket_inventory_id INT PRIMARY KEY AUTO_INCREMENT,                   -- 库存ID
    ticket_type_id      INT,                                              -- 票种ID（外键）
    available_quantity  INT NOT NULL,                                     -- 当前剩余票数
    sold_quantity       INT DEFAULT 0,                                    -- 已售出数量
    FOREIGN KEY (ticket_type_id) REFERENCES Ticket_Types (ticket_type_id) -- 外键关联票种表
);

CREATE TABLE orders
(
    order_id       INT PRIMARY KEY AUTO_INCREMENT,        -- 订单ID
    user_id        INT,                                   -- 用户ID（外键）
    order_date     TIMESTAMP   DEFAULT CURRENT_TIMESTAMP, -- 订单时间
    total_price    DECIMAL(10, 2) NOT NULL,               -- 订单总价
    status         VARCHAR(30) DEFAULT 'pending',         -- 订单状态
    payment_method VARCHAR(30)    NOT NULL,               -- 支付方式
    FOREIGN KEY (user_id) REFERENCES Users (user_id)      -- 外键关联用户表
);

CREATE TABLE order_tickets
(
    order_ticket_id INT PRIMARY KEY AUTO_INCREMENT,                       -- 订单项ID
    order_id        INT,                                                  -- 订单ID（外键）
    ticket_type_id  INT,                                                  -- 票种ID（外键）
    quantity        INT            NOT NULL,                              -- 购买的数量
    price           DECIMAL(10, 2) NOT NULL,                              -- 单张票的价格
    FOREIGN KEY (order_id) REFERENCES Orders (order_id),                  -- 外键关联订单表
    FOREIGN KEY (ticket_type_id) REFERENCES Ticket_Types (ticket_type_id) -- 外键关联票务表
);

CREATE TABLE payments
(
    payment_id     INT PRIMARY KEY AUTO_INCREMENT,        -- 支付ID
    order_id       INT,                                   -- 订单ID（外键）
    payment_date   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP, -- 支付时间
    amount         DECIMAL(10, 2) NOT NULL,               -- 支付金额
    payment_status VARCHAR(30) DEFAULT 'pending',         -- 支付状态
    payment_method VARCHAR(30)    NOT NULL,               -- 支付方式
    FOREIGN KEY (order_id) REFERENCES Orders (order_id)   -- 外键关联订单表
);

CREATE TABLE tickets
(
    ticket_id     INT PRIMARY KEY AUTO_INCREMENT,                           -- 门票ID
    order_item_id INT,                                                      -- 订单项ID（外键）
    user_id       INT,                                                      -- 用户ID（外键）
    ticket_number VARCHAR(100) UNIQUE,                                      -- 唯一门票编号
    issue_date    TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,                    -- 发放时间
    seat_number   VARCHAR(50),                                              -- 座位号
    status        VARCHAR(30) DEFAULT 'issued',                             -- 门票状态
    FOREIGN KEY (order_item_id) REFERENCES Order_Tickets (order_ticket_id), -- 外键关联订单项表
    FOREIGN KEY (user_id) REFERENCES Users (user_id)                        -- 外键关联用户表
);

CREATE TABLE merchandise
(
    merchandise_id     INT PRIMARY KEY AUTO_INCREMENT,        -- 商品ID
    concert_id         INT,                                   -- 演唱会ID（外键）
    name               VARCHAR(255)   NOT NULL,               -- 商品名称
    description        TEXT,                                  -- 商品描述
    price              DECIMAL(10, 2) NOT NULL,               -- 商品价格
    quantity           INT            NOT NULL,               -- 商品数量
    available_quantity INT            NOT NULL,               -- 当前库存
    image_url          VARCHAR(255),                          -- 商品图片
    is_charity         BOOLEAN DEFAULT FALSE,                 -- 是否为慈善商品
    FOREIGN KEY (concert_id) REFERENCES Concerts (concert_id) -- 外键关联演唱会表
);

CREATE TABLE order_merchandise
(
    order_merchandise_id INT PRIMARY KEY AUTO_INCREMENT,                 -- 订单商品ID
    order_id             INT,                                            -- 订单ID（外键）
    merchandise_id       INT,                                            -- 商品ID（外键）
    quantity             INT            NOT NULL,                        -- 购买数量
    price                DECIMAL(10, 2) NOT NULL,                        -- 单个商品价格
    FOREIGN KEY (order_id) REFERENCES Orders (order_id),                 -- 外键关联订单表
    FOREIGN KEY (merchandise_id) REFERENCES Merchandise (merchandise_id) -- 外键关联周边商品表
);

-- Insert initial data into Users table
INSERT INTO users (username, email, phone, password, first_name, last_name, city, country)
VALUES ('john_doe', 'john.doe@example.com', '1234567890', 'encrypted_password1', 'John', 'Doe', 'New York', 'USA'),
       ('jane_smith', 'jane.smith@example.com', '0987654321', 'encrypted_password2', 'Jane', 'Smith', 'Los Angeles',
        'USA');

-- Insert initial data into Concerts table
INSERT INTO concerts (name, date, venue, city, country, description, image_url, ticket_sale_start, ticket_sale_end,
                      total_seats, status)
VALUES ('Coldplay Live', '2025-02-22 20:00:00', 'Zayed Sports City Stadium', 'Abu Dhabi', 'USA',
        'Coldplay live concert', 'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/20231008144859_27550.png',
        '2025-01-20 10:00:00', '2025-01-27 10:00:00', 30000, 'SOLD OUT'),
       ('Coldplay Live', '2025-02-23 20:00:00', 'Zayed Sports City Stadium', 'Abu Dhabi', 'USA',
        'Coldplay live concert', 'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/20231008144859_27550.png',
        '2025-01-20 10:00:00', '2025-01-27 10:00:00', 30000, 'SOLD OUT'),
       ('Coldplay Live', '2025-02-24 20:00:00', 'Zayed Sports City Stadium', 'Abu Dhabi', 'USA',
        'Coldplay live concert', 'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/20231008144859_27550.png',
        '2025-01-20 10:00:00', '2025-01-27 10:00:00', 30000, 'ADEQUATE'),
       ('Coldplay Live', '2025-03-11 20:00:00', 'Kai Tak Stadium', 'Hong Kong', 'China', 'Coldplay live concert',
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/75b99f7c88df4ddeb3d8114545f89472.webp', '2025-01-20 10:00:00',
        '2025-01-27 10:00:00', 30000, 'SOLD OUT'),
       ('Coldplay Live', '2025-03-12 20:00:00', 'Kai Tak Stadium', 'Hong Kong', 'China', 'Coldplay live concert',
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/75b99f7c88df4ddeb3d8114545f89472.webp', '2025-01-20 10:00:00',
        '2025-01-27 10:00:00', 30000, 'ADEQUATE'),
       ('Coldplay Live', '2025-03-13 20:00:00', 'Kai Tak Stadium', 'Hong Kong', 'China', 'Coldplay live concert',
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/75b99f7c88df4ddeb3d8114545f89472.webp', '2025-01-20 10:00:00',
        '2025-01-27 10:00:00', 30000, 'ADEQUATE');

-- Insert initial data into Ticket_Types table
INSERT INTO ticket_types (concert_id, type_name, price)
VALUES (1, 'VIP', 3680.00),
       (1, 'Gold', 2780.00),
       (1, 'Silver', 2250.00),
       (1, 'General', 1980.00),
       (1, 'Regular', 1680.00),
       (1, 'Economy', 1360.00),
       (1, 'Back Row', 1099.00),
       (2, 'VIP', 3680.00),
       (2, 'Gold', 2780.00),
       (2, 'Silver', 2250.00),
       (2, 'General', 1980.00),
       (2, 'Regular', 1680.00),
       (2, 'Economy', 1360.00),
       (2, 'Back Row', 1099.00),
       (3, 'VIP', 3680.00),
       (3, 'Gold', 2780.00),
       (3, 'Silver', 2250.00),
       (3, 'General', 1980.00),
       (3, 'Regular', 1680.00),
       (3, 'Economy', 1360.00),
       (3, 'Back Row', 1099.00),
       (4, 'VIP', 3680.00),
       (4, 'Gold', 2780.00),
       (4, 'Silver', 2250.00),
       (4, 'General', 1980.00),
       (4, 'Regular', 1680.00),
       (4, 'Economy', 1360.00),
       (4, 'Back Row', 1099.00),
       (5, 'VIP', 3680.00),
       (5, 'Gold', 2780.00),
       (5, 'Silver', 2250.00),
       (5, 'General', 1980.00),
       (5, 'Regular', 1680.00),
       (5, 'Economy', 1360.00),
       (5, 'Back Row', 1099.00),
       (6, 'VIP', 3680.00),
       (6, 'Gold', 2780.00),
       (6, 'Silver', 2250.00),
       (6, 'General', 1980.00),
       (6, 'Regular', 1680.00),
       (6, 'Economy', 1360.00),
       (6, 'Back Row', 1099.00);

-- Insert initial data into Ticket_Inventory table
INSERT INTO ticket_inventory (ticket_type_id, available_quantity, sold_quantity)
VALUES (1, 0, 5000),     -- Sold out
       (2, 10000, 5000),
       (3, 2000, 2000),
       (4, 14000, 0),
       (5, 5000, 5000),  -- Sold out
       (6, 8000, 0),
       (7, 6000, 0),
       (8, 0, 5000),     -- Sold out
       (9, 10000, 5000),
       (10, 2000, 2000),
       (11, 14000, 0),
       (12, 5000, 5000), -- Sold out
       (13, 8000, 0),
       (14, 6000, 0),
       (15, 0, 5000),    -- Sold out
       (16, 10000, 5000),
       (17, 2000, 2000),
       (18, 14000, 0),
       (19, 5000, 5000), -- Sold out
       (20, 8000, 0),
       (21, 6000, 0),
       (22, 0, 5000),    -- Sold out
       (23, 10000, 5000),
       (24, 2000, 2000),
       (25, 14000, 0),
       (26, 5000, 5000), -- Sold out
       (27, 8000, 0),
       (28, 6000, 0),
       (29, 0, 5000),    -- Sold out
       (30, 10000, 5000),
       (31, 2000, 2000),
       (32, 14000, 0),
       (33, 5000, 5000), -- Sold out
       (34, 8000, 0),
       (35, 6000, 0),
       (36, 0, 5000),    -- Sold out
       (37, 10000, 5000),
       (38, 2000, 2000),
       (39, 14000, 0),
       (40, 5000, 5000), -- Sold out
       (41, 8000, 0),
       (42, 6000, 0);

-- Insert initial data into Orders table
INSERT INTO orders (user_id, total_price, status, payment_method)
VALUES (1, 450.00, 'completed', 'credit_card'),
       (2, 350.00, 'completed', 'paypal');

-- Insert initial data into Order_Tickets table
INSERT INTO order_tickets (order_id, ticket_type_id, quantity, price)
VALUES (1, 1, 1, 3680.00),
       (1, 2, 1, 2780.00),
       (2, 3, 2, 4500.00);

-- Insert initial data into Payments table
INSERT INTO payments (order_id, amount, payment_status, payment_method)
VALUES (1, 6460.00, 'completed', 'credit_card'),
       (2, 4500.00, 'completed', 'paypal');

-- Insert initial data into Tickets table
INSERT INTO tickets (order_item_id, user_id, ticket_number, seat_number, status)
VALUES (1, 1, 'TICKET12345', 'A1 Block 16', 'issued'),
       (2, 1, 'TICKET12346', 'A2 Block 6', 'issued'),
       (3, 2, 'TICKET12347', 'B1 Block 3', 'issued'),
       (3, 2, 'TICKET12348', 'B2 Block 23', 'issued');

-- Insert initial data into Merchandise table
INSERT INTO merchandise (concert_id, name, description, price, quantity, available_quantity, image_url, is_charity)
VALUES (1, 'MOON MUSiC ECORECORD LP', 'MOON MUSiC ECORECORD LP', 30.50, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/06/Standard_Vinyl_Pink_220170226EC2NDIMAGE-600x600.webp',
        false),
       (1, 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 220.99, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/10/FM-600x600.png', false),
       (1, 'Coldplay T-Shirt', 'Official Coldplay T-Shirt', 5.00, 1000, 1000,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/t.jpg', false),
       (1, 'Planting a tree', 'Plant a tree for a desert area and expand the oasis！', 15.00, 500, 500,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/tree.jpg', true),
       (2, 'MOON MUSiC ECORECORD LP', 'MOON MUSiC ECORECORD LP', 30.50, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/06/Standard_Vinyl_Pink_220170226EC2NDIMAGE-600x600.webp',
        false),
       (2, 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 220.99, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/10/FM-600x600.png', false),
       (2, 'Coldplay T-Shirt', 'Official Coldplay T-Shirt', 5.00, 1000, 1000,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/t.jpg', false),
       (2, 'Planting a tree', 'Plant a tree for a desert area and expand the oasis！', 15.00, 500, 500,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/tree.jpg', true),
       (3, 'MOON MUSiC ECORECORD LP', 'MOON MUSiC ECORECORD LP', 30.50, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/06/Standard_Vinyl_Pink_220170226EC2NDIMAGE-600x600.webp',
        false),
       (3, 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 220.99, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/10/FM-600x600.png', false),
       (3, 'Coldplay T-Shirt', 'Official Coldplay T-Shirt', 5.00, 1000, 1000,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/t.jpg', false),
       (3, 'Planting a tree', 'Plant a tree for a desert area and expand the oasis！', 15.00, 500, 500,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/tree.jpg', true),
       (4, 'MOON MUSiC ECORECORD LP', 'MOON MUSiC ECORECORD LP', 30.50, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/06/Standard_Vinyl_Pink_220170226EC2NDIMAGE-600x600.webp',
        false),
       (4, 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 220.99, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/10/FM-600x600.png', false),
       (4, 'Coldplay T-Shirt', 'Official Coldplay T-Shirt', 5.00, 1000, 1000,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/t.jpg', false),
       (4, 'Planting a tree', 'Plant a tree for a desert area and expand the oasis！', 15.00, 500, 500,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/tree.jpg', true),
       (5, 'MOON MUSiC ECORECORD LP', 'MOON MUSiC ECORECORD LP', 30.50, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/06/Standard_Vinyl_Pink_220170226EC2NDIMAGE-600x600.webp', false),
       (5, 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 220.99, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/10/FM-600x600.png', false),
       (5, 'Coldplay T-Shirt', 'Official Coldplay T-Shirt', 5.00, 1000, 1000,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/t.jpg', false),
       (5, 'Planting a tree', 'Plant a tree for a desert area and expand the oasis！', 15.00, 500, 500,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/tree.jpg', true),
       (6, 'MOON MUSiC ECORECORD LP', 'MOON MUSiC ECORECORD LP', 30.50, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/06/Standard_Vinyl_Pink_220170226EC2NDIMAGE-600x600.webp',
        false),
       (6, 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 'MOON MUSiC (FULL MOON EDITION) DIGITAL', 220.99, 1000, 1000,
        'https://www.coldplay.com/wp/wp-content/uploads/2024/10/FM-600x600.png', false),
       (6, 'Coldplay T-Shirt', 'Official Coldplay T-Shirt', 5.00, 1000, 1000,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/t.jpg', false),
       (6, 'Planting a tree', 'Plant a tree for a desert area and expand the oasis！', 15.00, 500, 500,
        'https://ita-ticket.oss-cn-guangzhou.aliyuncs.com/tree.jpg', true)
;

-- Insert initial data into Order_Merchandise table
INSERT INTO order_merchandise (order_id, merchandise_id, quantity, price)
VALUES (1, 1, 2, 50.00),
       (2, 2, 1, 15.00);