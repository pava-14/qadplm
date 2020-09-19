package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardGenerator;
import ru.netology.data.DbHelper;
import ru.netology.pages.OrderPage;
import ru.netology.pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderFormTest {
    private int amount = 4500000;
    private String approvedStatus = "APPROVED";
    private String declinedStatus = "DECLINED";
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
        assertNotNull(startPage);
    }

    @Test
    public void shouldApprovedCreditByCard() {
        OrderPage orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.waitForApproved();
    }

    @Test
    public void shouldApprovedOrderByCard() {
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.waitForApproved();
    }

    @Test
    public void shouldDeclinedCreditByDeclinedCard() {
        OrderPage orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
        orderPage.waitForDeclined();
    }

    @Test
    public void shouldDeclinedOrderByDeclinedCard() {
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
        orderPage.waitForDeclined();
    }

    @Test
    public void shouldDeclinedCreditByUnknownCard() {
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithUnknownCardNumber());
        orderPage.sendData();
        orderPage.waitForDeclined();
    }

    @Test
    public void shouldDeclinedOrderByUnknownCard() {
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithUnknownCardNumber());
        orderPage.sendData();
        orderPage.waitForDeclined();
    }

    @Test
    public void shouldNotSendOrderByCardWithTooSmallYear() {
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithTooSmallYear());
        orderPage.sendData();
        orderPage.waitForSmallMonthYearError();
    }

    @Test
    public void shouldNotSendOrderByCardWithTooBigYear() {
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithTooBigYear());
        orderPage.sendData();
        orderPage.waitForBigMonthYearError();
    }

    @Test
    public void shouldNotSendCreditByCardWithTooSmallYear() {
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithTooSmallYear());
        orderPage.sendData();
        orderPage.waitForSmallMonthYearError();
    }

    @Test
    public void shouldNotSendCreditByCardWithTooBigYear() {
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithTooBigYear());
        orderPage.sendData();
        orderPage.waitForBigMonthYearError();
    }

    @Test
    public void shouldNotSendOrderByCardWithTooSmallMonth() {
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithTooSmallMonth());
        orderPage.sendData();
        orderPage.waitForSmallMonthYearError();
    }

    @Test
    public void shouldNotSendOrderByCardWithTooBigMonth() {
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithTooBigMonth());
        orderPage.sendData();
        orderPage.waitForBigMonthYearError();
    }

    @Test
    public void shouldNotSendCreditByCardWithTooSmallMonth() {
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithTooSmallMonth());
        orderPage.sendData();
        orderPage.waitForSmallMonthYearError();
    }

    @Test
    public void shouldNotSendCreditByCardWithTooBigMonth() {
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithTooBigMonth());
        orderPage.sendData();
        orderPage.waitForBigMonthYearError();
    }

    @Test
    public void shouldNotSendCreditByCardWithEmtyFields() {
        orderPage = startPage.selectOrderByCredit();
        orderPage.sendData();
        orderPage.waitForEmptyAllFieldsErrors();
    }

    @Test
    public void shouldNotSendOrderByCardWithEmptyFields() {
        orderPage = startPage.selectOrderByCard();
        orderPage.sendData();
        orderPage.waitForEmptyAllFieldsErrors();
    }

    @Test
    public void shouldNotSendCreditByCardWithIncorrectNumberAndCvc() {
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithIncorrectCardNumberAndCvc());
        orderPage.sendData();
        orderPage.waitForIncorrectNumberAndCvcErrors();
    }

    @Test
    public void shouldNotSendOrderByCardWithIncorrectNumberAndCvc() {
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithIncorrectCardNumberAndCvc());
        orderPage.sendData();
        orderPage.waitForIncorrectNumberAndCvcErrors();
    }

    @Test
    public void shouldNotSendCreditByCardWithIncorrectOwner() {
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithIncorrectOwnerByCyrillic());
        orderPage.sendData();
        orderPage.waitForIncorrectFormat();
    }

    @Test
    public void shouldNotSendOrderByCardWithIncorrectOwner() {
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithIncorrectOwnerByCyrillic());
        orderPage.sendData();
        orderPage.waitForIncorrectFormat();
    }

    @Test
    public void shouldStoreApprovedOrderByCardIntoMySqlDatabase() {
        DbHelper.clearTable(false);
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.waitForApproved();
        String actualStatus = DbHelper.getOrderInfo(amount, false, false);
        assertEquals(approvedStatus, actualStatus);
    }

    @Test
    public void shouldStoreApprovedCreditByCardIntoMySqlDatabase() {
        DbHelper.clearTable(false);
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.waitForApproved();
        String actualStatus = DbHelper.getOrderInfo(amount, true, false);
        assertEquals(approvedStatus, actualStatus);
    }

    @Test
    public void shouldStoreDeclinedOrderByCardIntoMySqlDatabase() {
//        DbHelper.clearTable(false);
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
//        orderPage.waitForDeclined();
        orderPage.waitForApproved();
        String actualStatus = DbHelper.getOrderInfo(amount, false, false);
        assertEquals(declinedStatus, actualStatus);
    }

    @Test
    public void shouldStoreDeclinedCreditByCardIntoMySqlDatabase() {
//        DbHelper.clearTable(false);
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
//        orderPage.waitForDeclined();
        orderPage.waitForApproved();
        String actualStatus = DbHelper.getOrderInfo(amount, true, false);
        assertEquals(declinedStatus, actualStatus);
    }

    @Test
    public void shouldStoreApprovedOrderByCardIntoMyPgDatabase() {
//        DbHelper.clearTable(true);
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.waitForApproved();
        String actualStatus = DbHelper.getOrderInfo(amount, false, true);
        assertEquals(approvedStatus, actualStatus);
    }

    @Test
    public void shouldStoreApprovedCreditByCardIntoPgDatabase() {
//        DbHelper.clearTable(true);
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
        orderPage.sendData();
        orderPage.waitForApproved();
        String actualStatus = DbHelper.getOrderInfo(amount, true, true);
        assertEquals(approvedStatus, actualStatus);
    }

    @Test
    public void shouldStoreDeclinedOrderByCardIntoMyPgDatabase() {
//        DbHelper.clearTable(true);
        orderPage = startPage.selectOrderByCard();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
//        orderPage.waitForDeclined();
        orderPage.waitForApproved();
        String actualStatus = DbHelper.getOrderInfo(amount, false, true);
        assertEquals(declinedStatus, actualStatus);
    }

    @Test
    public void shouldStoreDeclinedCreditByCardIntoMyPgDatabase() {
//        DbHelper.clearTable(true);
        orderPage = startPage.selectOrderByCredit();
        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithDeclinedCardNumber());
        orderPage.sendData();
//        orderPage.waitForDeclined();
        orderPage.waitForApproved();
        String actualStatus = DbHelper.getOrderInfo(amount, true, true);
        assertEquals(declinedStatus, actualStatus);
    }

}
