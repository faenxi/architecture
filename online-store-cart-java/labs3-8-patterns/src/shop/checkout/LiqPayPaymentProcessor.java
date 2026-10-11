package shop.checkout;

/**
 * {@code ConcreteProduct A1}: обробка оплати через внутрішню платіжну
 * систему (гривня, локальний еквайринг). Належить до "родини"
 * {@link DomesticCheckoutFactory}.
 */
public class LiqPayPaymentProcessor implements PaymentProcessor {

    /** Створює процесор локальних платежів LiqPay. */
    public LiqPayPaymentProcessor() {
    }

    @Override
    public void processPayment(double amount, String customerName) {
        System.out.println("[STUB] LiqPayPaymentProcessor.processPayment: списано " + amount
                + " грн з картки покупця '" + customerName + "' через LiqPay");
    }
}
