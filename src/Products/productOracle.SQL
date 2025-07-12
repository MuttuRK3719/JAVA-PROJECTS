create table product(
product_id int primary key,
product_name varchar(255),
product_price number,
product_quantity int);
create table custumer(
custumer_id int primary key,
customer_name varchar(250)
);
SELECT orderId
FROM (
    SELECT orderId
    FROM ProductOrder
    ORDER BY orderId DESC
)
WHERE ROWNUM = 1;
CREATE TABLE ProductOrder (
    orderId INT PRIMARY KEY,
    product_id INT,
    order_date DATE,
    customer_id INT,  -- ✅ Add data type here
    QUANTITY INT,
    CONSTRAINT fk_product
        FOREIGN KEY (product_id) REFERENCES product(product_id),
    CONSTRAINT fk_customer
        FOREIGN KEY (customer_id) REFERENCES custumer(custumer_id)
);

select *from ProductOrder;
INSERT INTO custumer (custumer_id, customer_name) VALUES (1, 'John Doe');

INSERT INTO ProductOrder (orderId, product_id, order_date, customer_id, QUANTITY)
VALUES (101, 1, TO_DATE('12/07/2025', 'DD/MM/YYYY'), 1, 10);

select po.orderId,c.custumer_id,c.customer_name,Pro.product_id,pro.product_name from ProductOrder po
join custumer c on c.custumer_id=po.customer_id join product pro on pro.product_id=po.product_id;
--STORED PRODECURE
create or replace procedure updateTable(Uproduct_id in product.product_id%type,quantity in product.product_quantity%type)
as
begin
update product
set product_quantity=product_quantity-quantity where product_id=Uproduct_id;
end;
/
begin updateTable(1,20);
end;
/

select * from ProductOrder;


alter table productOrder
add  customer_id int;-- Use uppercase table name
drop table ProductOrder;
ALTER TABLE productOrder
ADD CONSTRAINT fk_product
FOREIGN KEY (customer_id)
REFERENCES custumer(custumer_id);
alter table productOrder
add quantity int ;

select *from custumer;
select * from product;
SELECT
    custumer.custumer_id AS CID,
    custumer.customer_name AS CNAME,
    pro.product_id AS PID,
    pro.product_name AS PNAME,
    pro.product_price AS PRICE,
    pro_Ord.QUANTITY AS PQ
FROM
    ProductOrder pro_Ord
JOIN
    custumer custumer ON custumer.custumer_id = pro_Ord.customer_id
JOIN
    product pro ON pro.product_id = pro_Ord.product_id
WHERE
    custumer.custumer_id = 1;










