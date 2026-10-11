package shop.checkout;

/**
 * {@code ConcreteProduct B2}: міжнародна доставка. Належить до
 * "родини" {@link InternationalCheckoutFactory}.
 */
public class DhlShippingProvider implements ShippingProvider {

    /** Створює провайдера міжнародної доставки DHL. */
    public DhlShippingProvider() {
    }

    @Override
    public void deliver(String orderId, String customerName) {
        System.out.println("[STUB] DhlShippingProvider.deliver: замовлення " + orderId
                + " передано міжнародному перевізнику для покупця '" + customerName + "'");
    }
}
