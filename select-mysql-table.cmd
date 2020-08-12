winpty docker container exec -it mysql mysql -u app -ppass app -e "SELECT * FROM payment_entity;"

winpty docker container exec -it mysql mysql -u app -ppass app -e "SELECT * FROM order_entity;"

winpty docker container exec -it mysql mysql -u app -ppass app -e "SELECT * FROM credit_request_entity;"
