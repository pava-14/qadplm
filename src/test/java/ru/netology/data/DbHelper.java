package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

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

    /*
    "TRUNCATE TABLE credit_request_entity;
    TRUNCATE TABLE payment_entity;
    TRUNCATE TABLE order_entity;"
     */
    public static void clearTable() {
        val deleteAuthCodeSQL = " DELETE FROM auth_codes;";
        val runner = new QueryRunner();
        try {
            try (val conn = DriverManager.getConnection(url, user, password)) {
                runner.execute(conn, deleteAuthCodeSQL, new ScalarHandler<>());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
