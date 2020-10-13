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
        OrderPage orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.shouldBeApproved();
        String actualStatus = DbHelper.getPaymebtInfo(SutData.amount, true);
        assertEquals(SutData.approvedStatus, actualStatus);
    }

    @Test
    public void shouldStoreApprovedCreditByCardIntoPgDatabase() {
        DbHelper.clearTable(true);
        OrderPage orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.shouldBeApproved();
        String actualStatus = DbHelper.getCreditInfo(SutData.amount, true);
        assertEquals(SutData.approvedStatus, actualStatus);
    }

    @Test
    public void shouldStoreDeclinedOrderByCardIntoMyPgDatabase() {
        DbHelper.clearTable(true);
        OrderPage orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
        orderPage.shouldBeDeclined();
        String actualStatus = DbHelper.getPaymebtInfo(SutData.amount, true);
        assertEquals(SutData.declinedStatus, actualStatus);
    }

    @Test
    public void shouldStoreDeclinedCreditByCardIntoMyPgDatabase() {
        DbHelper.clearTable(true);
        OrderPage orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
        orderPage.shouldBeDeclined();
        String actualStatus = DbHelper.getCreditInfo(SutData.amount, true);
        assertEquals(SutData.declinedStatus, actualStatus);
    }
}
