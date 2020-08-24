package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.OrderPage;
import ru.netology.pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderFormTest {
    private final String startPageUrl = "http://localhost:8080";
    private StartPage startPage;
    private OrderPage orderPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openStartPage() {
        startPage = open(startPageUrl, StartPage.class);
    }

    @AfterAll
    public static void postConditions() {
        //DbHelper.clearAuthCodesTable();
    }

    @Test
    public void shouldOpenOrderPage() {
//        OrderPage orderPage = startPage.openOrderPage();
//        assertNotNull(orderPage);
        assertNotNull(startPage.openOrderPage());
    }
}
