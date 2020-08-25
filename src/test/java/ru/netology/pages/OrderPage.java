package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OrderPage {
    private final String buttonOrderCaption = "Купить";
    private final String buttonCreditCaption = "Купить в кредит";
    private final String buttonContinueCaption = "Продолжить";

    private final String successMessage = "Операция одобрена Банком.";
    private final String errorMessage = "Ошибка! Банк отказал в проведении операции.";

    private SelenideElement buttonContinue = $(withText(buttonContinueCaption));
    private SelenideElement buttonCredit = $(withText(buttonCreditCaption));
    private SelenideElement buttonOrder = $(byText(buttonContinueCaption));

    private List<SelenideElement> formFields = $$(".input__control");
    private SelenideElement fieldCardNumber = formFields.get(0);
    private SelenideElement fieldMonth = formFields.get(1);
    private SelenideElement fieldYear = formFields.get(2);
    private SelenideElement fieldOwner = formFields.get(3);
    private SelenideElement fieldCvcCvv = formFields.get(4);


    /*
    Успешно
    Операция одобрена Банком.

    Ошибка
    Ошибка! Банк отказал в проведении операции.
     */

    //    @FindBy(xpath = "//*[text()='Компьютеры']")
//    private WebElement linkComputers;
//public DashboardPage() {
//    heading.shouldBe(visible);
//  }
//   @FindBy(css = "[data-test-id=login] input")
//  private SelenideElement loginField;

}
