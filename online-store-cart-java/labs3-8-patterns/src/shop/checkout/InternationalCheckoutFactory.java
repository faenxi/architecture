package shop.checkout;

/**
 * {@code ConcreteFactory 2}: родина об'єктів для міжнародних
 * замовлень — {@link PayPalPaymentProcessor} +
 * {@link DhlShippingProvider}.
 */
public class InternationalCheckoutFactory implements CheckoutServiceFactory {

    /** Створює фабрику міжнародної родини сервісів. */
    public InternationalCheckoutFactory() {
    }

    @Override
    public PaymentProcessor createPaymentProcessor() {
        return new PayPalPaymentProcessor();
    }

    @Override
    public ShippingProvider createShippingProvider() {
        return new DhlShippingProvider();
    }
}
