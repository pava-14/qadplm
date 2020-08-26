package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class StartPage {
    private final String orderCaption = "Оплата по карте";
    private final String creditCaption = "Кредит по данным карты";
    private final String headerCaption = "Путешествие дня";
    private final String buttonOrderCaption = "Купить";
    private final String buttonCreditCaption = "Купить в кредит";
    private final SelenideElement buttonCredit = $(withText(buttonCreditCaption));
    private final SelenideElement buttonOrder = $(byText(buttonOrderCaption));

    public StartPage() {
        $(withText(headerCaption)).waitUntil(visible, 15000);
    }

    public OrderPage selectOrderByCard() {
        buttonOrder.click();
        $(withText(orderCaption)).waitUntil(visible, 5000);
        return page(OrderPage.class);
    }

    public OrderPage selectOrderByCredit() {
        buttonCredit.click();
        $(withText(creditCaption)).waitUntil(visible, 5000);
        return page(OrderPage.class);
    }
}
