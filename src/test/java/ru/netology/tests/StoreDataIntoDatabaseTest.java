package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
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
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreDataIntoDatabaseTest {
    private StartPage startPage;
    private OrderPage orderPage;

    @SneakyThrows
    static void waitOnAppStart () {
        sleep(60 * 1000);
    }

    @BeforeAll
    static void setUpAll() {
        waitOnAppStart();
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
    public void shouldStoreApprovedOrderByCardIntoDatabase() {
        DbHelper.clearTable();
        OrderPage orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.shouldBeApproved();
        String actualStatus = DbHelper.getPaymentInfo(SutData.amount);
        assertEquals(SutData.approvedStatus, actualStatus);
    }

    @Test
    public void shouldStoreApprovedCreditByCardIntoDatabase() {
        DbHelper.clearTable();
        OrderPage orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.shouldBeApproved();
        String actualStatus = DbHelper.getCreditInfo(SutData.amount);
        assertEquals(SutData.approvedStatus, actualStatus);
    }

    @Test
    public void shouldStoreDeclinedOrderByCardIntoDatabase() {
        DbHelper.clearTable();
        OrderPage orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
        orderPage.shouldBeDeclined();
        String actualStatus = DbHelper.getPaymentInfo(SutData.amount);
        assertEquals(SutData.declinedStatus, actualStatus);
    }

    @Test
    public void shouldStoreDeclinedCreditByCardIntoDatabase() {
        DbHelper.clearTable();
        OrderPage orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
        orderPage.shouldBeDeclined();
        String actualStatus = DbHelper.getCreditInfo(SutData.amount);
        assertEquals(SutData.declinedStatus, actualStatus);
    }
}
