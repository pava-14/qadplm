package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Card;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OrderPage {
    private final String buttonContinueCaption = "Продолжить";
    private final String approvedMessage = "Операция одобрена Банком.";
    private final String declinedMessage = "Ошибка! Банк отказал в проведении операции.";
    private final String fieldErrorSelector = ".input__inner";
    private final String fieldInputSelector = ".input__control";
    private final String incorrectFormatErrorText = "Неверный формат";
    private final String incorrectSmallMonthYearText = "Истёк срок действия карты";
    private final String incorrectBigMonthYearText = "Неверно указан срок действия карты";
    private final String fieldRequiredErrorText = "Поле обязательно для заполнения";

    private final SelenideElement fieldCardNumber = $$(fieldInputSelector).get(0);
    private final SelenideElement fieldMonth = $$(fieldInputSelector).get(1);
    private final SelenideElement fieldYear = $$(fieldInputSelector).get(2);
    private final SelenideElement fieldOwner = $$(fieldInputSelector).get(3);
    private final SelenideElement fieldCvcCvv = $$(fieldInputSelector).get(4);

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

    public void shouldBeApproved() {
        $(withText(approvedMessage)).waitUntil(visible, 15000);
    }

    public void shouldBeDeclined() {
        $(withText(declinedMessage)).waitUntil(visible, 15000);
    }

    public void shouldBeIncorrectFormat() {
        $(withText(incorrectFormatErrorText)).waitUntil(visible, 15000);
    }

    public void shouldBeSmallMonthYearError() {
        $(withText(incorrectSmallMonthYearText)).
                waitUntil(visible, 15000);
    }

    public void shouldBeBigMonthYearError() {
        $(withText(incorrectBigMonthYearText)).
                waitUntil(visible, 15000);
    }

    public void shouldBeEmptyAllFieldsErrors() {
        List<SelenideElement> errorMessagesBox = $$(fieldErrorSelector);
        errorMessagesBox.get(0).$(withText(incorrectFormatErrorText)).waitUntil(visible, 15000);
        errorMessagesBox.get(1).$(withText(incorrectFormatErrorText)).waitUntil(visible, 15000);
        errorMessagesBox.get(2).$(withText(incorrectFormatErrorText)).waitUntil(visible, 15000);
        errorMessagesBox.get(3).$(withText(fieldRequiredErrorText)).waitUntil(visible, 15000);
        errorMessagesBox.get(4).$(withText(incorrectFormatErrorText)).waitUntil(visible, 15000);
    }

    public void shouldBeIncorrectNumberAndCvcErrors() {
        List<SelenideElement> errorMessagesBox = $$(fieldErrorSelector);
        errorMessagesBox.get(0).$(withText(incorrectFormatErrorText)).waitUntil(visible, 15000);
        errorMessagesBox.get(4).$(withText(incorrectFormatErrorText)).waitUntil(visible, 15000);
    }
}
