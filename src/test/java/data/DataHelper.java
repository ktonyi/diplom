package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    static Faker enFaker = new Faker(new Locale("en"));
    static Faker ruFaker = new Faker(new Locale("ru"));
    static DateGenerator dateGenerator = new DateGenerator();
    static CardNumberGenerator cardNumberGenerator = new CardNumberGenerator();

    @Value
    public static class CardInformation {
        private String cardNumber;
        private String year;
        private String month;
        private String holder;
        private String securityCode;
    }

    public static CardInformation getValidCardInformation() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.shiftYear(5).getYear(), dateGenerator.shiftMonth(2).getMonth(), enFaker.name().fullName(), Integer.toString(enFaker.number().numberBetween(100, 999)));
    }

    public static CardInformation getInvalidCardInformation() {
        return new CardInformation(cardNumberGenerator.getDeclinedCardNumber(), dateGenerator.shiftYear(5).getYear(), dateGenerator.shiftMonth(2).getMonth(), enFaker.name().fullName(), Integer.toString(enFaker.number().numberBetween(100, 999)));
    }

    public static CardInformation getEmptyCardInformation() {
        return new CardInformation(" ", " ", " ", " ", " ");
    }

    public static CardInformation getCardInformationWithInvalidNumber() {
        return new CardInformation(cardNumberGenerator.getInvalidCardNumber(), dateGenerator.shiftYear(5).getYear(), dateGenerator.shiftMonth(2).getMonth(), enFaker.name().fullName(), Integer.toString(enFaker.number().numberBetween(100, 999)));
    }

    public static CardInformation getCardInformationWithExpiredYear() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.shiftYear(-2).getYear(), dateGenerator.shiftMonth(0).getMonth(), enFaker.name().fullName(), Integer.toString(enFaker.number().numberBetween(100, 999)));
    }

    public static CardInformation getCardInformationWithExpiredMonth() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.shiftYear(0).getYear(), dateGenerator.shiftMonth(-1).getMonth(), enFaker.name().fullName(), Integer.toString(enFaker.number().numberBetween(100, 999)));
    }

    public static CardInformation getCardInformationWithWrongFormatDate() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.wrongYear().getYear(), dateGenerator.wrongMonth().getMonth(), enFaker.name().fullName(), Integer.toString(enFaker.number().numberBetween(100, 999)));
    }
    public static CardInformation getCardInformationWithCyrillicName() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.shiftYear(5).getYear(), dateGenerator.shiftMonth(2).getMonth(), ruFaker.name().fullName(), Integer.toString(enFaker.number().numberBetween(100, 999)));
    }
    public static CardInformation getCardInformationWithNumericName() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.shiftYear(5).getYear(), dateGenerator.shiftMonth(2).getMonth(), Integer.toString(enFaker.number().numberBetween(1, 999)), Integer.toString(enFaker.number().numberBetween(100, 999)));
    }
}
