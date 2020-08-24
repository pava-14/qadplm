package ru.netology.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class StartPage {
    private final String headerCaption = "Путешествие дня";

    public OrderPage openOrderPage() {
        $(withText(headerCaption)).waitUntil(visible, 15000);
        return page(OrderPage.class);
    }
}
