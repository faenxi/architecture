package ua.edu.shopcart.payment;

import java.math.BigDecimal;
import ua.edu.shopcart.domain.Customer;

/**
 * Вузький інтерфейс лише для проведення оплати.
 *
 * <p>Відокремлення від надсилання чеків демонструє ISP, а залежність
 * сервісу від цього інтерфейсу - DIP і Protected Variations.</p>
 */
public interface PaymentProcessor {
    /**
     * Проводить оплату замовлення.
     *
     * @param customer покупець
     * @param amount сума оплати
     */
    void pay(Customer customer, BigDecimal amount);
}
