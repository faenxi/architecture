package shop.checkout;

/**
 * {@code ConcreteProduct A2}: обробка оплати через міжнародну платіжну
 * систему (долар США, міжнародний еквайринг). Належить до "родини"
 * {@link InternationalCheckoutFactory}.
 */
public class PayPalPaymentProcessor implements PaymentProcessor {

    /** Створює процесор міжнародних платежів PayPal. */
    public PayPalPaymentProcessor() {
    }

    @Override
    public void processPayment(double amount, String customerName) {
        System.out.println("[STUB] PayPalPaymentProcessor.processPayment: списано $" + amount
                + " з рахунку покупця '" + customerName + "' через PayPal");
    }
}
