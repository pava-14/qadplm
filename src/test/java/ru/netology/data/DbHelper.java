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
    private final static String urlMysql = "jdbc:mysql://localhost:3306/app";
    private final static String urlPostgres = "jdbc:postgresql://host.docker.internal:5432/app";
    private final static String user = "app";
    private final static String password = "pass";

    public static void clearTable(boolean usePostgres) {
        String url = (usePostgres) ? urlPostgres : urlMysql;
        val truncateOrderSQL = "TRUNCATE TABLE order_entity;";
        val truncatePaymentSQL = "TRUNCATE TABLE payment_entity;";
        val truncateCrefitSQL = "TRUNCATE TABLE credit_request_entity;";
        val runner = new QueryRunner();
        try {
            try (val conn = DriverManager.getConnection(url, user, password)) {
                runner.execute(conn, truncateOrderSQL, new ScalarHandler<>());
                runner.execute(conn, truncatePaymentSQL, new ScalarHandler<>());
                runner.execute(conn, truncateCrefitSQL, new ScalarHandler<>());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //TODO: SQL ...
    public static String getOrderInfo(int amount, boolean credit, boolean usePostgres) {
        String url = (usePostgres) ? urlPostgres : urlMysql;
        val statusSQL = (credit) ?
                "SELECT credit_request_entity.status "
                        + "FROM credit_request_entity "
                        + "INNER JOIN order_entity "
                        + "ON credit_request_entity.id = order_entity.payment_id;" :
                "SELECT true_payment_entity.status "
                        + "FROM true_payment_entity "
                        + "INNER JOIN true_order_entity "
                        + "ON true_order_entity.payment_id = true_payment_entity.id "
                        + "WHERE true_payment_entity.amount = ?;";
        val runner = new QueryRunner();
        String result = "";
        try {
            try (val conn = DriverManager.getConnection(url, user, password)) {
                result = runner.query(conn, statusSQL, new ScalarHandler<>(), amount);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
