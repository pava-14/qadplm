 winpty docker container exec -it postgres psql -U app -c "TRUNCATE TABLE credit_request_entity;TRUNCATE TABLE payment_entity;TRUNCATE TABLE order_entity;"
