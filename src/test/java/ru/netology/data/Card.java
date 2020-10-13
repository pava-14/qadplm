package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
    private String cardNumber;
    private String cardMonth;
    private String cardYear;
    private String cardOwner;
    private String cardCvcCvv;
}
