package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Card;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OrderPage {
    private final String buttonContinueCaption = "Продолжить";
    private final String approvedMessage = "Операция одобрена Банком.";
    private final String declinedMessage = "Ошибка! Банк отказал в проведении операции.";
    private final String fieldSelector = ".input__control";

    private final SelenideElement fieldCardNumber = $$(fieldSelector).get(0);
    private final SelenideElement fieldMonth = $$(fieldSelector).get(1);
    private final SelenideElement fieldYear = $$(fieldSelector).get(2);
    private final SelenideElement fieldOwner = $$(fieldSelector).get(3);
    private final SelenideElement fieldCvcCvv = $$(fieldSelector).get(4);

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
