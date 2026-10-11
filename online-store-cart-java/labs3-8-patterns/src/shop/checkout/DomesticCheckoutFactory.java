package shop.checkout;

/**
 * {@code ConcreteFactory 1}: родина об'єктів для внутрішніх
 * (українських) замовлень — {@link LiqPayPaymentProcessor} +
 * {@link NovaPoshtaShippingProvider}.
 */
public class DomesticCheckoutFactory implements CheckoutServiceFactory {

    /** Створює фабрику української родини сервісів. */
    public DomesticCheckoutFactory() {
    }

    @Override
    public PaymentProcessor createPaymentProcessor() {
        return new LiqPayPaymentProcessor();
    }

    @Override
    public ShippingProvider createShippingProvider() {
        return new NovaPoshtaShippingProvider();
    }
}
