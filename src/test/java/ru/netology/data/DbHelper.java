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
        String url = (usePostgres) ? urlPostgres: urlMysql;
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

    /*
    Таблица payment_entity содержит строку операции оплаты,
    в поле amount значение 4500000, в поле status значение APPROVED.
    Таблица order_entity содержит строку операции покупки.
    Поля transaction_id и payment_id содержат одинаковые значения.
    */
    public static void getOrderPaymentInfo (int amount, String status, boolean usePostgres) {
        String url = (usePostgres) ? urlPostgres: urlMysql;

        val codeSQL = "SELECT payment_entity.status "
        + "FROM payment_entity INNER JOIN transaction_id ON payment_entity.transaction_id "
                + "= order_entity.payment_id WHERE payment_entity.amount = ? AND payment_entity.status = ?";
        val runner = new QueryRunner();
        String code = "";
        try {
            try (val conn = DriverManager.getConnection(url, user, password)) {
                code = runner.query(conn, codeSQL, new ScalarHandler<>(), amount, status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new VerificationCode(code);
    }

}
