package ru.netology.data;

/*
credit_request_entity;
+---------+--------------+------+-----+---------+-------+
| Field   | Type         | Null | Key | Default | Extra |
+---------+--------------+------+-----+---------+-------+
| id      | varchar(255) | NO   | PRI | NULL    |       |
| bank_id | varchar(255) | YES  | UNI | NULL    |       |
| created | datetime(6)  | YES  |     | NULL    |       |
| status  | varchar(255) | YES  |     | NULL    |       |
+---------+--------------+------+-----+---------+-------+

payment_entity;
+----------------+--------------+------+-----+---------+-------+
| Field          | Type         | Null | Key | Default | Extra |
+----------------+--------------+------+-----+---------+-------+
| id             | varchar(255) | NO   | PRI | NULL    |       |
| amount         | int(11)      | NO   |     | NULL    |       |
| created        | datetime(6)  | YES  |     | NULL    |       |
| status         | varchar(255) | YES  |     | NULL    |       |
| transaction_id | varchar(255) | YES  | UNI | NULL    |       |
+----------------+--------------+------+-----+---------+-------+

order_entity;
+------------+--------------+------+-----+---------+-------+
| Field      | Type         | Null | Key | Default | Extra |
+------------+--------------+------+-----+---------+-------+
| id         | varchar(255) | NO   | PRI | NULL    |       |
| created    | datetime(6)  | YES  |     | NULL    |       |
| credit_id  | varchar(255) | YES  |     | NULL    |       |
| payment_id | varchar(255) | YES  |     | NULL    |       |
+------------+--------------+------+-----+---------+-------+
 */
public class DbHelper {
    private final static String url = "jdbc:mysql://localhost:3306/app";
    private final static String user = "app";
    private final static String password = "pass";



}
