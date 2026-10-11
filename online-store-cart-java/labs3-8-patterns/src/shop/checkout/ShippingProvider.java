package shop.checkout;

/**
 * {@code AbstractProduct B} у шаблоні <b>Abstract Factory</b>.
 * Спільний інтерфейс для всіх служб доставки замовлення.
 */
public interface ShippingProvider {

    /**
     * Оформлює доставку замовлення покупцю.
     *
     * @param orderId      ідентифікатор замовлення
     * @param customerName ім'я покупця-отримувача
     */
    void deliver(String orderId, String customerName);
}
