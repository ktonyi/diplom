package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import page.TourOfferPage;
import sqlUtils.SqlUtils;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HappyPathTest {

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

    @DisplayName("Позитивный сценарий. Успешная покупка по карте.")
    @Test
    public void shouldConfirmPaymentWithApprovedCard() throws SQLException {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val approvedCardInformation = DataHelper.getValidCardInformation();
        paymentPage.enterCardData(approvedCardInformation);
        paymentPage.assertSuccessfulPayment();
        val paymentId = SqlUtils.getPaymentId();
        val statusForPayment = SqlUtils.getStatusForPayment(paymentId);
        Assertions.assertEquals("APPROVED", statusForPayment);
    }

    @DisplayName("Позитивный сценарий. Успешная покупка в кредит.")
    @Test
    public void shouldConfirmBuyingOnCreditWithApprovedCard() throws SQLException {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val approvedCardInformation = DataHelper.getValidCardInformation();
        paymentPage.enterCardData(approvedCardInformation);
        paymentPage.assertSuccessfulPayment();
        val paymentId = SqlUtils.getPaymentId();
        val statusForCredit = SqlUtils.getStatusForCredit(paymentId);
        assertEquals("APPROVED", statusForCredit);
    }

    @DisplayName("Позитивный сценарий. Покупка по отклоняемой карте.")
    @Test
    public void shouldNotConfirmPaymentWithDeclinedCard() throws SQLException {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val declinedCardInformation = DataHelper.getInvalidCardInformation();
        paymentPage.enterCardData(declinedCardInformation);
        paymentPage.assertNotSuccessfulPayment();
        val paymentId = SqlUtils.getPaymentId();
        val statusForPayment = SqlUtils.getStatusForPayment(paymentId);
        assertThat(statusForPayment, equalTo("DECLINED"));
    }

    @DisplayName("Позитивный сценарий. Покупка в кредит по данным отклоняемой карты.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithDeclinedCard() throws SQLException {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val declinedCardInformation = DataHelper.getInvalidCardInformation();
        paymentPage.enterCardData(declinedCardInformation);
        paymentPage.assertNotSuccessfulPayment();
        val paymentId = SqlUtils.getPaymentId();
        val statusFortCredit = SqlUtils.getStatusForCredit(paymentId);
        assertThat(statusFortCredit, equalTo("DECLINED"));
    }

}
