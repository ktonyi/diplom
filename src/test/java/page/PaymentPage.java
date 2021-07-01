package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {

    private SelenideElement cardNumber = $("input[type=\"text\"][placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement expirationMonth = $("input[type=\"text\"][placeholder=\"08\"]");
    private SelenideElement expirationYear = $("input[type=\"text\"][placeholder=\"22\"]");
    private SelenideElement cardHolderName = $("form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input");
    private SelenideElement cardSecurityCode = $("input[type=\"text\"][placeholder=\"999\"]");
    private SelenideElement successNotification = $(withText("Успешно"));
    private SelenideElement errorNotification = $(withText("Ошибка"));
    private SelenideElement continueButton = $(withText("Продолжить"));
    private SelenideElement invalidFormatError = $(withText("Неверный формат"));
    private SelenideElement expiredYearError = $(withText("Истёк срок действия карты"));
    private SelenideElement expiredMonthError = $(withText("Неверно указан срок действия карты"));

    public void enterCardData(DataHelper.CardInformation cardInformation) {
        cardNumber.setValue(cardInformation.getCardNumber());
        expirationMonth.setValue(cardInformation.getMonth());
        expirationYear.setValue(cardInformation.getYear());
        cardHolderName.setValue(cardInformation.getHolder());
        cardSecurityCode.setValue(cardInformation.getSecurityCode());
        continueButton.click();
    }

    public void assertSuccessfulPayment() {
        successNotification.waitUntil(Condition.visible, 15000);
    }

    public void assertNotSuccessfulPayment() {
        errorNotification.waitUntil(Condition.visible, 15000);
    }

    public void verifyInvalidFormat() {
        invalidFormatError.shouldBe(Condition.visible);
    }

    public void checkExpiredYear() {
        expiredYearError.shouldBe(Condition.visible);
    }

    public void checkExpiredMonth() {
        expiredMonthError.shouldBe(Condition.visible);
    }
}
