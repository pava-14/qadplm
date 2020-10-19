package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
    private final static String dbUrlDefault = "jdbc:mysql://host.docker.internal:3306/app";
    private final static String user = "app";
    private final static String password = "pass";

    private static String getDbUrl() {
        String dbUrl = System.getProperty("db.url");
        if (dbUrl.isEmpty()) {
            dbUrl = dbUrlDefault;
        }
        return dbUrl;
    }

    public static void clearTable() {
        val url = getDbUrl();
        val truncateOrderSQL = "TRUNCATE TABLE order_entity;";
        val truncatePaymentSQL = "TRUNCATE TABLE payment_entity;";
        val truncateCreditSQL = "TRUNCATE TABLE credit_request_entity;";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url, user, password)) {
            runner.execute(conn, truncateOrderSQL, new ScalarHandler<>());
            runner.execute(conn, truncatePaymentSQL, new ScalarHandler<>());
            runner.execute(conn, truncateCreditSQL, new ScalarHandler<>());
        } catch (SQLException e) {
            //do nothing
        }
    }

    private static String getOperationStatus(int amount, String statusSQL) {
        String url = getDbUrl();
        val runner = new QueryRunner();
        String status = "";
        try (val conn = DriverManager.getConnection(url, user, password)) {
            status = runner.query(conn, statusSQL, new ScalarHandler<>(), amount);
        } catch (SQLException e) {
            // do nothing
        }
        return status;
    }

    public static String getPaymentInfo(int amount) {
        val statusSQL = "SELECT payment_entity.status "
                + "FROM payment_entity "
                + "INNER JOIN order_entity "
                + "ON order_entity.payment_id = payment_entity.id "
                + "WHERE payment_entity.amount = ?;";
        return getOperationStatus(amount, statusSQL);
    }

    public static String getCreditInfo(int amount) {
        val statusSQL = "SELECT payment_entity.status "
                + "FROM payment_entity "
                + "INNER JOIN order_entity "
                + "ON payment_entity.id = order_entity.payment_id "
                + "INNER JOIN credit_request_entity "
                + "ON order_entity.credit_id = credit_request_entity.id "
                + "WHERE payment_entity.amount = ?;";
        return getOperationStatus(amount, statusSQL);
    }
}
