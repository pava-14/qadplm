winpty docker container exec -it postgres psql -U app -c "SELECT * FROM payment_entity;"

winpty docker container exec -it postgres psql -U app -c "SELECT * FROM order_entity;"

winpty docker container exec -it postgres psql -U app -c "SELECT * FROM credit_request_entity;"
