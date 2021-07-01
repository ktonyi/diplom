package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import page.TourOfferPage;

import static com.codeborne.selenide.Selenide.open;


public class SadPathTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        String appUrl = System.getProperty("app.url");
        open(appUrl);
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @DisplayName("Негативный сценарий. Покупка с незаполненными данными карты.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyForm() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val invalidCardInformation = DataHelper.getEmptyCardInformation();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.verifyInvalidFormat();
    }

    @DisplayName("Негативный сценарий. Покупка в кредит с незаполненными данными карты.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyForm() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val invalidCardInformation = DataHelper.getEmptyCardInformation();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.verifyInvalidFormat();
    }

    @DisplayName("Негативный сценарий. Покупка по карте с невалидным номером.")
    @Test
    public void shouldNotConfirmPaymentWithInvalidCardNumber() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val invalidCardInformation = DataHelper.getCardInformationWithInvalidNumber();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.assertNotSuccessfulPayment();
    }

    @DisplayName("Негативный сценарий. Покупка в кредит по данным карты с невалидным номером.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithInvalidCardNumber() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val invalidCardInformation = DataHelper.getCardInformationWithInvalidNumber();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.assertNotSuccessfulPayment();
    }

    @DisplayName("Негативный сценарий. Покупка по карте с истекшим сроком действия(в прошлых годах).")
    @Test
    public void shouldNotConfirmPaymentWithExpiredYearCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val invalidCardInformation = DataHelper.getCardInformationWithExpiredYear();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.checkExpiredYear();
    }

    @DisplayName("Негативный сценарий. Покупка в кредит по по данным карты с истекшим сроком действия(в прошлых годах).")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithExpiredYearCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val invalidCardInformation = DataHelper.getCardInformationWithExpiredYear();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.checkExpiredYear();
    }

    @DisplayName("Негативный сценарий. Покупка по карте с истекшим сроком действия(в прошлых месяце).")
    @Test
    public void shouldNotConfirmPaymentWithExpiredMonthCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val invalidCardInformation = DataHelper.getCardInformationWithExpiredMonth();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.checkExpiredMonth();
    }

    @DisplayName("Негативный сценарий. Покупка в кредит по по данным карты с истекшим сроком действия(в прошлых месяце).")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithExpiredMonthCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val invalidCardInformation = DataHelper.getCardInformationWithExpiredMonth();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.checkExpiredMonth();
    }

    @DisplayName("Негативный сценарий. Покупка по карте с неверным форматом полей даты.")
    @Test
    public void shouldNotConfirmPaymentWithWrongFormatFieldsCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongFormatDate();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.checkExpiredMonth();
    }

    @DisplayName("Негативный сценарий. Покупка в кредит по данным карты с неверным форматом полей даты.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithWrongFormatFieldsCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val invalidCardInformation = DataHelper.getCardInformationWithWrongFormatDate();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.checkExpiredMonth();
    }

    @DisplayName("Негативный сценарий. Покупка по карте с именем держателя на кириллице.")
    @Test
    public void shouldNotConfirmPaymentWithCyrillicHolderFieldCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val invalidCardInformation = DataHelper.getCardInformationWithCyrillicName();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.verifyInvalidFormat();
    }

    @DisplayName("Негативный сценарий. Покупка в кредит  по данным карты с именем держателя на кириллице.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithCyrillicHolderFieldCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val invalidCardInformation = DataHelper.getCardInformationWithCyrillicName();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.verifyInvalidFormat();
    }

    @DisplayName("Негативный сценарий. Покупка по карте с цифрами в имени держателя.")
    @Test
    public void shouldNotConfirmPaymentWithNumericHolderFieldCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val invalidCardInformation = DataHelper.getCardInformationWithNumericName();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.verifyInvalidFormat();
    }

    @DisplayName("Негативный сценарий. Покупка в кредит  по данным карты с цифрами в имени держателя.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithNumericHolderFieldCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val invalidCardInformation = DataHelper.getCardInformationWithNumericName();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.verifyInvalidFormat();
    }
}
