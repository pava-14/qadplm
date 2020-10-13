package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
//    private final static String urlMysql = "jdbc:mysql://localhost:3306/app";
    private final static String urlMysql = "jdbc:mysql://host.docker.internal:3306/app";
    private final static String urlPostgres = "jdbc:postgresql://host.docker.internal:5432/app";
    private final static String user = "app";
    private final static String password = "pass";

    private static String getDbUrl() {
        String dbUrl = System.getProperty("db.url");
        return dbUrl;
    }

    public static void clearTable(boolean usePostgres) {
        String url = (usePostgres) ? urlPostgres : urlMysql;
        val truncateOrderSQL = "TRUNCATE TABLE order_entity;";
        val truncatePaymentSQL = "TRUNCATE TABLE payment_entity;";
        val truncateCrefitSQL = "TRUNCATE TABLE credit_request_entity;";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url, user, password)) {
            runner.execute(conn, truncateOrderSQL, new ScalarHandler<>());
            runner.execute(conn, truncatePaymentSQL, new ScalarHandler<>());
            runner.execute(conn, truncateCrefitSQL, new ScalarHandler<>());
        } catch (SQLException throwables) {
            //nothing to do
        }
    }

    @SneakyThrows
    private static String getOperationStatus(int amount, String statusSQL, boolean usePgDb) {
        String url = (usePgDb) ? urlPostgres : urlMysql;
        val runner = new QueryRunner();
        val conn = DriverManager.getConnection(url, user, password);
        return runner.query(conn, statusSQL, new ScalarHandler<>(), amount);
    }

    public static String getPaymebtInfo(int amount, boolean usePgDb) {
        String url = (usePgDb) ? urlPostgres : urlMysql;
        val statusSQL = "SELECT payment_entity.status "
                + "FROM payment_entity "
                + "INNER JOIN order_entity "
                + "ON order_entity.payment_id = payment_entity.id "
                + "WHERE payment_entity.amount = ?;";
        return getOperationStatus(amount, statusSQL, usePgDb);
    }

    public static String getCreditInfo(int amount, boolean usePgDb) {
        String url = (usePgDb) ? urlPostgres : urlMysql;
        val statusSQL = "SELECT payment_entity.status "
                + "FROM payment_entity "
                + "INNER JOIN order_entity "
                + "ON payment_entity.id = order_entity.payment_id "
                + "INNER JOIN credit_request_entity "
                + "ON order_entity.credit_id = credit_request_entity.id "
                + "WHERE payment_entity.amount = ?;";
        return getOperationStatus(amount, statusSQL, usePgDb);
    }
}
