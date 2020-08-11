winpty docker container exec -it mysql mysql -u app -ppass app -e "TRUNCATE TABLE credit_request_entity;TRUNCATE TABLE payment_entity;TRUNCATE TABLE order_entity;"
