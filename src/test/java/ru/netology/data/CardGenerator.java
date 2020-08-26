package ru.netology.data;

public class CardGenerator {
    private CardGenerator() {

    }

    public static class CardInfo {
        private static final String approvedCardNumber = "4444 4444 4444 4441";
        private static final String declinedCardNumber = "4444 4444 4444 4442";
        private static final String unknownCardNumber = "5555 5555 5555 5555";
        private static final String incorrectCardNumber = "5555 5555";
        private static final String validCardMonth = "08";
        private static final String validCardYear = "22";
        private static final String validCardOwner = "Ivanov Ivan";
        private static final String validCardCvcCvv = "999";

        public static Card getCardInfoWithApprovedCardNumber() {
            return new Card(
                    approvedCardNumber, validCardMonth, validCardYear, validCardOwner, validCardCvcCvv);
        }

        public static Card getCardInfoWithDeclinedCardNumber() {
            return new Card(
                    declinedCardNumber, validCardMonth, validCardYear, validCardOwner, validCardCvcCvv);
        }

        public Card getCardInfoWithUnknownCardNumber() {
            return new Card(
                    unknownCardNumber, validCardMonth, validCardYear, validCardOwner, validCardCvcCvv);
        }

        public Card getCardInfoWithIncorrectCardNumber() {
            return new Card(
                    incorrectCardNumber, validCardMonth, validCardYear, validCardOwner, validCardCvcCvv);
        }

    }
}
