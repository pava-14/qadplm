package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Card;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OrderPage {
    private final String buttonContinueCaption = "Продолжить";
    private final String approvedMessage = "Операция одобрена Банком.";
    private final String declinedMessage = "Ошибка! Банк отказал в проведении операции.";
    private final String fieldErrorSelector = ".input__sub";
    private final String fieldInputSelector = ".input__control";
    private final String fieldCardNumberErrorText = "";
    private final String fieldMonthErrorText = "";
    private final String fieldYearErrorText = "";
    private final String fieldOwnerErrorText = "";
    private final String fieldCvcCvvErrorText = "";

    private final SelenideElement fieldCardNumber = $$(fieldInputSelector).get(0);
    private final SelenideElement fieldCardNumberError = $$(fieldErrorSelector).get(0);
    private final SelenideElement fieldMonth = $$(fieldInputSelector).get(1);
    private final SelenideElement fieldMonthError = $$(fieldErrorSelector).get(1);
    private final SelenideElement fieldYear = $$(fieldInputSelector).get(2);
    private final SelenideElement fieldYearError = $$(fieldErrorSelector).get(2);
    private final SelenideElement fieldOwner = $$(fieldInputSelector).get(3);
    private final SelenideElement fieldOwnerError = $$(fieldErrorSelector).get(3);
    private final SelenideElement fieldCvcCvv = $$(fieldInputSelector).get(4);
    private final SelenideElement fieldCvcCvvError = $$(fieldErrorSelector).get(4);

    public void setCardFields(Card card) {
        fieldCardNumber.setValue(card.getCardNumber());
        fieldMonth.setValue(card.getCardMonth());
        fieldYear.setValue(card.getCardYear());
        fieldOwner.setValue(card.getCardOwner());
        fieldCvcCvv.setValue(card.getCardCvcCvv());
    }

    public void sendData() {
        $(withText(buttonContinueCaption)).click();
    }

    public void waitApproved() {
        $(withText(approvedMessage)).waitUntil(visible, 15000);
    }

    public void waitDeclined() {
        $(withText(declinedMessage)).waitUntil(visible, 15000);
    }
}
