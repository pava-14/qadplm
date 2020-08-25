package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class OrderPage {
    private final String buttonOrderCaption = "Купить";
    private final String buttonCreditCaption = "Купить в кредит";
    private final String buttonContinueCaption = "Продолжить";

    private SelenideElement buttonContinue = $(withText(buttonContinueCaption));
    private SelenideElement buttonCredit = $(withText(buttonCreditCaption));
    private SelenideElement buttonOrder = $(withText(buttonContinueCaption));

    //private SelenideElement orderButton = $(
    /*
    By.xpath(String.format(xptext, linkTabletsText)
     */
//    @FindBy(xpath = "//*[text()='Компьютеры']")
//    private WebElement linkComputers;
//public DashboardPage() {
//    heading.shouldBe(visible);
//  }
//   @FindBy(css = "[data-test-id=login] input")
//  private SelenideElement loginField;
//    Купить
//    Купить в кредит
/*
    $$('.input__control')[0] - Card number
    $$('.input__control')[1] - Month
    $$('.input__control')[2] - Year
    $$('.input__control')[3] - Owner
    $$('.input__control')[4] - CVC/CVV
    ('.form-field button') - Продолжить
     */


}
