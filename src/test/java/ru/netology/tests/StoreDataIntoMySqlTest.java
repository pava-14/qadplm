package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardGenerator;
import ru.netology.data.DbHelper;
import ru.netology.data.SutData;
import ru.netology.pages.OrderPage;
import ru.netology.pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreDataIntoMySqlTest {
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
        startPage = open(SutData.startPageUrl, StartPage.class);
    }

    @Test
    public void shouldStoreApprovedOrderByCardIntoMySqlDatabase() {
        DbHelper.clearTable(false);
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.waitForApproved();
        String actualStatus = DbHelper.getPaymebtInfo(SutData.amount, false);
        assertEquals(SutData.approvedStatus, actualStatus);
    }

    @Test
    public void shouldStoreApprovedCreditByCardIntoMySqlDatabase() {
        DbHelper.clearTable(false);
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.waitForApproved();
        String actualStatus = DbHelper.getCreditInfo(SutData.amount, false);
        assertEquals(SutData.approvedStatus, actualStatus);
    }

    @Test
    public void shouldStoreDeclinedOrderByCardIntoMySqlDatabase() {
        DbHelper.clearTable(false);
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
        orderPage.waitForDeclined();
        String actualStatus = DbHelper.getPaymebtInfo(SutData.amount, false);
        assertEquals(SutData.declinedStatus, actualStatus);
    }

    @Test
    public void shouldStoreDeclinedCreditByCardIntoMySqlDatabase() {
        DbHelper.clearTable(false);
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
        orderPage.waitForDeclined();
        String actualStatus = DbHelper.getCreditInfo(SutData.amount, false);
        assertEquals(SutData.declinedStatus, actualStatus);
    }
}
