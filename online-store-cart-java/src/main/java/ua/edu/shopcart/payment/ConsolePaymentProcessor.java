package ua.edu.shopcart.payment;

import java.math.BigDecimal;
import java.util.Objects;
import ua.edu.shopcart.domain.Customer;

/**
 * Консольна заглушка зовнішньої платіжної системи.
 */
public final class ConsolePaymentProcessor implements PaymentProcessor {
    /**
     * Імітує оплату та виводить назву методу і його аргументи.
     *
     * @param customer покупець
     * @param amount сума оплати
     */
    @Override
    public void pay(Customer customer, BigDecimal amount) {
        Objects.requireNonNull(customer, "customer не може бути null");
        Objects.requireNonNull(amount, "amount не може бути null");
        System.out.printf("[STUB] pay(customer=%s, amount=%s грн)%n", customer, amount);
    }
}
