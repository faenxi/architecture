package shop.checkout;

/**
 * {@code AbstractProduct A} у шаблоні <b>Abstract Factory</b>.
 * Спільний інтерфейс для всіх способів обробки оплати замовлення.
 */
public interface PaymentProcessor {

    /**
     * Обробляє оплату замовлення.
     *
     * @param amount       сума до сплати
     * @param customerName ім'я покупця
     */
    void processPayment(double amount, String customerName);
}
