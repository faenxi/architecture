package shop;

/**
 * Оплата через PayPal.
 * Третя варіативна реалізація {@link PaymentProcessor}, яка демонструє,
 * що додавання нового способу оплати не вимагає змін ні в
 * {@link Order}, ні в {@link CartController} (Protected Variations).
 */
public class PayPalPayment implements PaymentProcessor {

    private final String accountEmail;

    public PayPalPayment(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    /**
     * ЗАГЛУШКА бізнес-логіки: реальний виклик PayPal API не реалізується.
     */
    @Override
    public boolean pay(double amount) {
        System.out.println("[STUB] PayPalPayment.pay(amount=" + amount
                + ") account=" + accountEmail + " -> імітація звернення до PayPal API");
        return true;
    }

    @Override
    public String getMethodName() {
        return "PayPal";
    }
}
