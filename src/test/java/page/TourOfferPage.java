package page;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

public class TourOfferPage {

    private SelenideElement byCardButton = $(byText("Купить"));
    private SelenideElement onCreditButton = $(byText("Купить в кредит"));
    private SelenideElement methodOfPaymentHeader = $("#root > div > h3");

    public PaymentPage payByCard() {
        byCardButton.click();
        methodOfPaymentHeader.shouldHave(Condition.text("Оплата по карте"));
        return new PaymentPage();
    }

    public PaymentPage buyOnCredit() {
        onCreditButton.click();
        methodOfPaymentHeader.shouldHave(Condition.text("Кредит по данным карты"));
        return new PaymentPage();
    }
}
