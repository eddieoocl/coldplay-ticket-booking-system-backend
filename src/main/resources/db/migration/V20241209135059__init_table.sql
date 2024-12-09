CREATE TABLE Users
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


CREATE TABLE Concerts
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
    total_seats       INT          NOT NULL           -- 演唱会总座位数
);

CREATE TABLE Ticket_Types
(
    ticket_type_id INT PRIMARY KEY AUTO_INCREMENT,            -- 票种ID
    concert_id     INT,                                       -- 演唱会ID（外键）
    type_name      VARCHAR(100)   NOT NULL,                   -- 票种名称（如VIP、普通票）
    price          DECIMAL(10, 2) NOT NULL,                   -- 票价
    FOREIGN KEY (concert_id) REFERENCES Concerts (concert_id) -- 外键关联演唱会表
);

CREATE TABLE Ticket_Inventory
(
    ticket_inventory_id INT PRIMARY KEY AUTO_INCREMENT,                   -- 库存ID
    ticket_type_id      INT,                                              -- 票种ID（外键）
    available_quantity  INT NOT NULL,                                     -- 当前剩余票数
    sold_quantity       INT DEFAULT 0,                                    -- 已售出数量
    FOREIGN KEY (ticket_type_id) REFERENCES Ticket_Types (ticket_type_id) -- 外键关联票种表
);

CREATE TABLE Orders
(
    order_id       INT PRIMARY KEY AUTO_INCREMENT,        -- 订单ID
    user_id        INT,                                   -- 用户ID（外键）
    order_date     TIMESTAMP   DEFAULT CURRENT_TIMESTAMP, -- 订单时间
    total_price    DECIMAL(10, 2) NOT NULL,               -- 订单总价
    status         VARCHAR(30) DEFAULT 'pending',         -- 订单状态
    payment_method VARCHAR(30)    NOT NULL,               -- 支付方式
    FOREIGN KEY (user_id) REFERENCES Users (user_id)      -- 外键关联用户表
);

CREATE TABLE Order_Tickets
(
    order_ticket_id INT PRIMARY KEY AUTO_INCREMENT,                       -- 订单项ID
    order_id        INT,                                                  -- 订单ID（外键）
    ticket_type_id  INT,                                                  -- 票种ID（外键）
    quantity        INT            NOT NULL,                              -- 购买的数量
    price           DECIMAL(10, 2) NOT NULL,                              -- 单张票的价格
    FOREIGN KEY (order_id) REFERENCES Orders (order_id),                  -- 外键关联订单表
    FOREIGN KEY (ticket_type_id) REFERENCES Ticket_Types (ticket_type_id) -- 外键关联票务表
);

CREATE TABLE Payments
(
    payment_id     INT PRIMARY KEY AUTO_INCREMENT,        -- 支付ID
    order_id       INT,                                   -- 订单ID（外键）
    payment_date   TIMESTAMP   DEFAULT CURRENT_TIMESTAMP, -- 支付时间
    amount         DECIMAL(10, 2) NOT NULL,               -- 支付金额
    payment_status VARCHAR(30) DEFAULT 'pending',         -- 支付状态
    payment_method VARCHAR(30)    NOT NULL,               -- 支付方式
    FOREIGN KEY (order_id) REFERENCES Orders (order_id)   -- 外键关联订单表
);

CREATE TABLE Tickets
(
    ticket_id     INT PRIMARY KEY AUTO_INCREMENT,                                 -- 门票ID
    order_item_id INT,                                                            -- 订单项ID（外键）
    user_id       INT,                                                            -- 用户ID（外键）
    ticket_number VARCHAR(100) UNIQUE,                                            -- 唯一门票编号
    issue_date    TIMESTAMP                            DEFAULT CURRENT_TIMESTAMP, -- 发放时间
    seat_number   VARCHAR(50),                                                    -- 座位号
    status        VARCHAR(30) DEFAULT 'issued',          -- 门票状态
    FOREIGN KEY (order_item_id) REFERENCES Order_Tickets (order_ticket_id),       -- 外键关联订单项表
    FOREIGN KEY (user_id) REFERENCES Users (user_id)                              -- 外键关联用户表
);

CREATE TABLE Merchandise
(
    merchandise_id     INT PRIMARY KEY AUTO_INCREMENT,        -- 商品ID
    concert_id         INT,                                   -- 演唱会ID（外键）
    name               VARCHAR(255)   NOT NULL,               -- 商品名称
    description        TEXT,                                  -- 商品描述
    price              DECIMAL(10, 2) NOT NULL,               -- 商品价格
    quantity           INT            NOT NULL,               -- 商品数量
    available_quantity INT            NOT NULL,               -- 当前库存
    image_url          VARCHAR(255),                          -- 商品图片
    FOREIGN KEY (concert_id) REFERENCES Concerts (concert_id) -- 外键关联演唱会表
);

CREATE TABLE Order_Merchandise
(
    order_merchandise_id INT PRIMARY KEY AUTO_INCREMENT,                 -- 订单商品ID
    order_id             INT,                                            -- 订单ID（外键）
    merchandise_id       INT,                                            -- 商品ID（外键）
    quantity             INT            NOT NULL,                        -- 购买数量
    price                DECIMAL(10, 2) NOT NULL,                        -- 单个商品价格
    FOREIGN KEY (order_id) REFERENCES Orders (order_id),                 -- 外键关联订单表
    FOREIGN KEY (merchandise_id) REFERENCES Merchandise (merchandise_id) -- 外键关联周边商品表
);