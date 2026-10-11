package shop.checkout;

/**
 * {@code ConcreteProduct B1}: доставка внутрішньою службою (локальний
 * перевізник). Належить до "родини" {@link DomesticCheckoutFactory}.
 */
public class NovaPoshtaShippingProvider implements ShippingProvider {

    /** Створює провайдера доставки Новою поштою. */
    public NovaPoshtaShippingProvider() {
    }

    @Override
    public void deliver(String orderId, String customerName) {
        System.out.println("[STUB] NovaPoshtaShippingProvider.deliver: замовлення " + orderId
                + " відправлено відділенням служби доставки покупцю '" + customerName + "'");
    }
}
