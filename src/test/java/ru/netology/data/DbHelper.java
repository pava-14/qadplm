package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

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

    public static String getOrderInfo(int amount, boolean credit, boolean usePgDb) {
        String url = (usePgDb) ? urlPostgres : urlMysql;
        val statusSQL = (credit) ?
                "SELECT payment_entity.status "
                        + "FROM payment_entity "
                        + "INNER JOIN order_entity "
                        + "ON payment_entity.id = order_entity.payment_id "
                        + "INNER JOIN credit_request_entity "
                        + "ON order_entity.credit_id = credit_request_entity.id "
                        + "WHERE payment_entity.amount = ?;" :
                "SELECT payment_entity.status "
                        + "FROM payment_entity "
                        + "INNER JOIN order_entity "
                        + "ON order_entity.payment_id = payment_entity.id "
                        + "WHERE payment_entity.amount = ?;";
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
