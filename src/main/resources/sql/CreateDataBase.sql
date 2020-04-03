--net start/stop mysql
--set global time_zone = '+8:00';
--redis-server.exe redis.windows.conf
--redis-cli -h 127.0.0.1 -p 6379
--redis-server /etc/redis/redis.conf
create table product
(
    id      int auto_increment
        primary key,
    ` name` varchar(50) null,
    stock   int         null
);

INSERT INTO mybatis.product (id, ` name`, stock) VALUES (1, '羊毛', 100000);
create table seckill_success
(
    id       int auto_increment
        primary key,
    telphone varchar(50) null,
    activity int         null,
    product  int         null,
    constraint seckill_success_product_id_fk
        foreign key (product) references product (id),
    constraint seckill_success_seckillactivity_id_fk
        foreign key (activity) references seckillactivity (id)
);


create table seckillactivity
(
    id           int auto_increment
        primary key,
    product      int         null,
    seckillnum   int         null,
    activityName varchar(20) null,
    constraint seckillactivity_product_id_fk
        foreign key (product) references product (id)
);



