package sqlUtils;

import lombok.val;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlUtils {
    public static Connection getConnection() throws SQLException {
        String dbUrl = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        final Connection connection = DriverManager.getConnection(dbUrl, login, password);
        return connection;
    }

    public static String getPaymentId() throws SQLException {
        String paymentId = null;
        val idSQL = "SELECT paymentId FROM order_entity order by created desc limit 1;";
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(idSQL)) {
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    paymentId = rs.getString("paymentId");
                }
            }
        }
        return paymentId;
    }

    public static String getStatusForPayment(String paymentId) throws SQLException {
        String statusSQL = "SELECT status FROM payment_entity WHERE transaction_id =?; ";
        String status = null;
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(statusSQL)) {
            statusStmt.setString(1, paymentId);
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }

    public static String getStatusForCredit(String paymentId) throws SQLException {
        String statusSQL = "SELECT status FROM credit_request_entity WHERE bank_id =?; ";
        String status = null;
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(statusSQL)) {
            statusStmt.setString(1, paymentId);
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }
}


