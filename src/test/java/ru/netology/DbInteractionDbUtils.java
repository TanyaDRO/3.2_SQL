package ru.netology;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.sql.DriverManager;

public class DbInteractionDbUtils {
    public static String getVerificationCode() {
        val codeSQL = "SELECT code FROM auth_codes WHERE created = (SELECT max(created) FROM auth_codes);";
        val runner = new QueryRunner();
        String verificationCode = "";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/mydb", "user", "pass"
                );
        ) {
            val code = runner.query(conn, codeSQL, new ScalarHandler<>());
            System.out.println(code);
            verificationCode = (String) code;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return verificationCode;
    }
}
