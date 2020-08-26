package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
    private final String approvedCardNumber = "4444 4444 4444 4441";
    private final String declinedCardNumber = "4444 4444 4444 4442";
    private final String incorrectCardNumber = "555 5555 5555 5555";
    private final String validCardOwner = "Ivanov Ivan";
    private final String validCardMonth = "10";
    private final String validCardYear = "21";
    private final String validCardCvcCvv = "909";

    private String cardNumber;
    private String cardMonth;
    private String cardYear;
    private String cardOwner;
    private String cardCvcCvv;

    public Card getApprovedCardInfo() {
        return new Card(
                approvedCardNumber, validCardMonth, validCardYear, validCardOwner, validCardCvcCvv);

    }

    public Card getDeclinedCardInfo() {
        return new Card(
                approvedCardNumber, validCardMonth, validCardYear, validCardOwner, validCardCvcCvv);

    }

    public Card getIncorrectCardInfo() {
        return new Card(
                incorrectCardNumber, validCardMonth, validCardYear, validCardOwner, validCardCvcCvv);

    }
}
