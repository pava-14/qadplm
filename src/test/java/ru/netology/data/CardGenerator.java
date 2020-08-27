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

        private static final String tooSmallYear = "12";
        private static final String tooBiglYear = "55";
        private static final String tooSmallMonth = "00";
        private static final String tooBiglMonth = "13";
        private static final String incorrectCvcCvv = "0";
        private static final String incorrectOwnerCyrillic = "Иванов Иван";

        public static Card getCardInfoWithApprovedCardNumber() {
            return new Card(
                    approvedCardNumber, validCardMonth, validCardYear, validCardOwner, validCardCvcCvv);
        }

        public static Card getCardInfoWithDeclinedCardNumber() {
            return new Card(
                    declinedCardNumber, validCardMonth, validCardYear, validCardOwner, validCardCvcCvv);
        }

        public static Card getCardInfoWithUnknownCardNumber() {
            return new Card(
                    unknownCardNumber, validCardMonth, validCardYear, validCardOwner, validCardCvcCvv);
        }

        public static Card getCardInfoWithIncorrectCardNumber() {
            return new Card(
                    incorrectCardNumber, validCardMonth, validCardYear, validCardOwner, validCardCvcCvv);
        }

        public static Card getCardInfoWithTooSmallYear() {
            return new Card(
                    approvedCardNumber, validCardMonth, tooSmallYear, validCardOwner, validCardCvcCvv);
        }

        public static Card getCardInfoWithTooBigYear() {
            return new Card(
                    approvedCardNumber, validCardMonth, tooBiglYear, validCardOwner, validCardCvcCvv);
        }

        public static Card getCardInfoWithTooSmallMonth() {
            return new Card(
                    approvedCardNumber, tooSmallMonth, validCardYear, validCardOwner, validCardCvcCvv);
        }

        public static Card getCardInfoWithTooBigMonth() {
            return new Card(
                    approvedCardNumber, tooBiglMonth, validCardYear, validCardOwner, validCardCvcCvv);
        }

        public static Card getCardInfoWithIncorrectCardNumberAndCvc() {
            return new Card(
                    incorrectCardNumber, tooBiglMonth, validCardYear, validCardOwner, incorrectCvcCvv);
        }

        public static Card getCardInfoWithIncorrectOwnerByCyrillic() {
            return new Card(
                    approvedCardNumber, validCardMonth, validCardYear, incorrectOwnerCyrillic, validCardCvcCvv);
        }

    }
}
