package ru.netology.test;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.DbInteractionDbUtils;
import ru.netology.mode.DataGenerator;
import ru.netology.mode.User;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;
import ru.netology.pages.VerificationPage;

import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSUT {
    User user = DataGenerator.generateUser();

    @Test
    @SneakyThrows
    @DisplayName("Логин с валидными данными")
    void loginWithValidData() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(user);
        DashboardPage dashboardPage = verificationPage.validVerify(DbInteractionDbUtils.getVerificationCode());
        assertEquals("Личный кабинет", dashboardPage.getHeading());
        DbInteractionDbUtils.CleanDB();
    }

}