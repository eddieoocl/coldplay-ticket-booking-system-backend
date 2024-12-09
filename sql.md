1. 用户信息表 (Users)
   存储用户的基本信息，包括个人认证信息、会员状态等。


CREATE TABLE Users (
user_id INT PRIMARY KEY AUTO_INCREMENT,     -- 用户ID
username VARCHAR(100) NOT NULL UNIQUE,      -- 用户名
email VARCHAR(255) NOT NULL UNIQUE,         -- 邮箱
phone VARCHAR(20),                          -- 电话
password VARCHAR(255) NOT NULL,        -- 密码（加密后）
first_name VARCHAR(100),                    -- 姓名
last_name VARCHAR(100),                     -- 姓氏
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 注册时间
);




2. 会员订阅表 (Membership_Subscriptions)
   存储用户的会员订阅信息，用于记录会员订阅的有效期和类型。
CREATE TABLE Membership_Subscriptions (
subscription_id INT PRIMARY KEY AUTO_INCREMENT,   -- 订阅ID
user_id INT,                                      -- 用户ID（外键）
start_date DATE,                                  -- 订阅开始时间
end_date DATE,                                    -- 订阅结束时间
subscription_type ENUM('monthly', 'yearly') NOT NULL,  -- 订阅类型
FOREIGN KEY (user_id) REFERENCES Users(user_id)   -- 外键关联用户表
);
3. 演唱会信息表 (Concerts)
   存储每场演唱会的基本信息，如日期、地点、描述等。


CREATE TABLE Concerts (
concert_id INT PRIMARY KEY AUTO_INCREMENT,  -- 演唱会ID
name VARCHAR(255) NOT NULL,                 -- 演唱会名称
date DATETIME NOT NULL,                     -- 演唱会日期
venue VARCHAR(255) NOT NULL,                -- 场地名称
city VARCHAR(100),                          -- 城市
country VARCHAR(100),                       -- 国家
description TEXT,                           -- 演唱会描述
image_url VARCHAR(255),                     -- 演唱会封面图
ticket_sale_start DATETIME,                 -- 票务销售开始时间
ticket_sale_end DATETIME,                   -- 票务销售结束时间
total_seats INT NOT NULL,                   -- 演唱会总座位数
);



4. 票务表 (Ticket_Types)
   存储每场演唱会的不同票种信息，如普通票、VIP票等。


CREATE TABLE Ticket_Types (
ticket_type_id INT PRIMARY KEY AUTO_INCREMENT,  -- 票种ID
concert_id INT,                                  -- 演唱会ID（外键）
type_name VARCHAR(100) NOT NULL,                 -- 票种名称（如VIP、普通票）
price DECIMAL(10, 2) NOT NULL,                   -- 票价
quantity INT NOT NULL,                           -- 总票数
available_quantity INT NOT NULL,                 -- 当前剩余票数
FOREIGN KEY (concert_id) REFERENCES Concerts(concert_id)  -- 外键关联演唱会表
);


5. 订单表 (Orders)
   存储用户的订单信息，包括订单状态、总价等。


CREATE TABLE Orders (
order_id INT PRIMARY KEY AUTO_INCREMENT,      -- 订单ID
user_id INT,                                  -- 用户ID（外键）
order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 订单时间
total_price DECIMAL(10, 2) NOT NULL,          -- 订单总价
status ENUM('pending', 'paid', 'cancelled', 'failed') DEFAULT 'pending',  -- 订单状态
payment_method ENUM('credit_card', 'paypal', 'bank_transfer') NOT NULL,  -- 支付方式
FOREIGN KEY (user_id) REFERENCES Users(user_id)  -- 外键关联用户表
);


6. 订单项表 (Order_Items)
   存储每个订单的具体项，包括每种票务和周边商品。


CREATE TABLE Order_Items (
order_item_id INT PRIMARY KEY AUTO_INCREMENT,   -- 订单项ID
order_id INT,                                    -- 订单ID（外键）
ticket_type_id INT,                              -- 票种ID（外键）
quantity INT NOT NULL,                           -- 购买的数量
price DECIMAL(10, 2) NOT NULL,                   -- 单张票的价格
FOREIGN KEY (order_id) REFERENCES Orders(order_id),  -- 外键关联订单表
FOREIGN KEY (ticket_type_id) REFERENCES Ticket_Types(ticket_type_id) -- 外键关联票务表
);



7. 支付信息表 (Payments)
   存储订单支付信息，包括支付金额、支付状态等。


CREATE TABLE Payments (
payment_id INT PRIMARY KEY AUTO_INCREMENT,    -- 支付ID
order_id INT,                                 -- 订单ID（外键）
payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 支付时间
amount DECIMAL(10, 2) NOT NULL,                -- 支付金额
payment_status ENUM('pending', 'success', 'failed', 'refund') DEFAULT 'pending',  -- 支付状态
payment_method ENUM('credit_card', 'paypal', 'bank_transfer') NOT NULL,  -- 支付方式
FOREIGN KEY (order_id) REFERENCES Orders(order_id)  -- 外键关联订单表
);



8. 门票表 (Tickets)
   存储实际发放给用户的门票信息，包括用户的个人身份信息和票务详情。


CREATE TABLE Tickets (
ticket_id INT PRIMARY KEY AUTO_INCREMENT,     -- 门票ID
order_item_id INT,                             -- 订单项ID（外键）
user_id INT,                                  -- 用户ID（外键）
ticket_number VARCHAR(100) UNIQUE,            -- 唯一门票编号
issue_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 发放时间
seat_number VARCHAR(50),                      -- 座位号
status ENUM('issued', 'used', 'cancelled') DEFAULT 'issued',  -- 门票状态
FOREIGN KEY (order_item_id) REFERENCES Order_Items(order_item_id),  -- 外键关联订单项表
FOREIGN KEY (user_id) REFERENCES Users(user_id)  -- 外键关联用户表
);



9. 周边商品表 (Merchandise)
   存储演唱会相关的周边商品信息。


CREATE TABLE Merchandise (
merchandise_id INT PRIMARY KEY AUTO_INCREMENT, -- 商品ID
concert_id INT,                                 -- 演唱会ID（外键）
name VARCHAR(255) NOT NULL,                     -- 商品名称
description TEXT,                               -- 商品描述
price DECIMAL(10, 2) NOT NULL,                  -- 商品价格
quantity INT NOT NULL,                          -- 商品数量
available_quantity INT NOT NULL,                -- 当前库存
image_url VARCHAR(255),                         -- 商品图片
FOREIGN KEY (concert_id) REFERENCES Concerts(concert_id)  -- 外键关联演唱会表
);



10. 订单中的周边商品表 (Order_Merchandise)
    存储用户购买的每个订单中的周边商品信息。


CREATE TABLE Order_Merchandise (
order_merchandise_id INT PRIMARY KEY AUTO_INCREMENT,  -- 订单商品ID
order_id INT,                                         -- 订单ID（外键）
merchandise_id INT,                                   -- 商品ID（外键）
quantity INT NOT NULL,                                -- 购买数量
price DECIMAL(10, 2) NOT NULL,                        -- 单个商品价格
FOREIGN KEY (order_id) REFERENCES Orders(order_id),  -- 外键关联订单表
FOREIGN KEY (merchandise_id) REFERENCES Merchandise(merchandise_id)  -- 外键关联周边商品表
);









对于不同场馆的座位分布，确实需要更灵活的设计，因为每个场馆的座位分布可能包含多个区域、层次、类别等。而且，座位号、座位区、座位类型等信息可能都会影响到票务的管理和分配。因此，设计一个能够适应不同场馆座位分布的数据库结构是非常重要的。

推荐的设计方案：
场馆表 (Venues)
这张表记录每个场馆的基本信息，包括场馆的名称、位置等。


CREATE TABLE Venues (
venue_id INT PRIMARY KEY AUTO_INCREMENT,       -- 场馆ID
name VARCHAR(255) NOT NULL,                    -- 场馆名称
city VARCHAR(100),                             -- 城市
country VARCHAR(100),                          -- 国家
address VARCHAR(255),                          -- 地址
capacity INT NOT NULL,                         -- 最大容纳人数
description TEXT                               -- 场馆描述
);
场馆座位表 (Seats)
每个场馆可能有多个区域、座位类别等，这张表记录具体的座位信息，包括座位所在的区域、层次等。每个座位有唯一标识，并且关联到具体的场馆。


CREATE TABLE Seats (
seat_id INT PRIMARY KEY AUTO_INCREMENT,        -- 座位ID
venue_id INT,                                  -- 场馆ID（外键）
section VARCHAR(100),                          -- 区域（如“VIP区”，“A区”，“B区”）
row VARCHAR(10),                               -- 排（如“1排”，“2排”）
seat_number VARCHAR(10),                       -- 座位号（如“1”，“A”，“B”）
seat_type ENUM('regular', 'VIP', 'premium') NOT NULL, -- 座位类型（如普通座、VIP座等）
price DECIMAL(10, 2) NOT NULL,                  -- 该座位的票价
is_available BOOLEAN DEFAULT TRUE,             -- 是否可用（售出后置为FALSE）
FOREIGN KEY (venue_id) REFERENCES Venues(venue_id) -- 外键关联场馆表
);
演唱会与座位关系表 (Concert_Seats)
如果演唱会的座位分布跟场馆的座位分布不同（比如针对某些演唱会可能会重新安排座位区、层次等），可以创建一个“演唱会座位表”来记录演唱会和座位的具体关系。


CREATE TABLE Concert_Seats (
concert_seat_id INT PRIMARY KEY AUTO_INCREMENT,  -- 演唱会座位ID
concert_id INT,                                  -- 演唱会ID（外键）
seat_id INT,                                     -- 座位ID（外键）
seat_status ENUM('available', 'booked', 'reserved', 'sold') DEFAULT 'available',  -- 座位状态
FOREIGN KEY (concert_id) REFERENCES Concerts(concert_id), -- 外键关联演唱会表
FOREIGN KEY (seat_id) REFERENCES Seats(seat_id)   -- 外键关联座位表
);
订单与座位分配表 (Order_Seats)
用于记录用户在下订单时选择的具体座位。这张表将关联订单与已分配的具体座位信息。


CREATE TABLE Order_Seats (
order_seat_id INT PRIMARY KEY AUTO_INCREMENT,   -- 订单座位ID
order_id INT,                                    -- 订单ID（外键）
concert_seat_id INT,                             -- 演唱会座位ID（外键）
user_id INT,                                     -- 用户ID（外键）
seat_price DECIMAL(10, 2) NOT NULL,              -- 座位价格
FOREIGN KEY (order_id) REFERENCES Orders(order_id), -- 外键关联订单表
FOREIGN KEY (concert_seat_id) REFERENCES Concert_Seats(concert_seat_id), -- 外键关联演唱会座位表
FOREIGN KEY (user_id) REFERENCES Users(user_id)  -- 外键关联用户表
);