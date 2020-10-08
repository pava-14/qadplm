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

public class StoreDataIntoPgSqlTest {
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
    public void shouldStoreApprovedOrderByCardIntoMyPgDatabase() {
        DbHelper.clearTable(true);
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.waitForApproved();
        String actualStatus = DbHelper.getOrderInfo(SutData.amount, false, true);
        assertEquals(SutData.approvedStatus, actualStatus);
    }

    @Test
    public void shouldStoreApprovedCreditByCardIntoPgDatabase() {
        DbHelper.clearTable(true);
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.waitForApproved();
        String actualStatus = DbHelper.getOrderInfo(SutData.amount, true, true);
        assertEquals(SutData.approvedStatus, actualStatus);
    }

    @Test
    public void shouldStoreDeclinedOrderByCardIntoMyPgDatabase() {
        DbHelper.clearTable(true);
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
        orderPage.waitForDeclined();
        String actualStatus = DbHelper.getOrderInfo(SutData.amount, false, true);
        assertEquals(SutData.declinedStatus, actualStatus);
    }

    @Test
    public void shouldStoreDeclinedCreditByCardIntoMyPgDatabase() {
        DbHelper.clearTable(true);
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
        orderPage.waitForDeclined();
        String actualStatus = DbHelper.getOrderInfo(SutData.amount, true, true);
        assertEquals(SutData.declinedStatus, actualStatus);
    }
}
