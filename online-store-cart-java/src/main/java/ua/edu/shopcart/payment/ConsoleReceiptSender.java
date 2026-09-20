package ua.edu.shopcart.payment;

import java.util.Objects;
import ua.edu.shopcart.domain.Customer;
import ua.edu.shopcart.domain.Order;

/**
 * Консольна заглушка сервісу електронних листів.
 */
public final class ConsoleReceiptSender implements ReceiptSender {
    /**
     * Імітує надсилання чека та друкує аргументи виклику.
     *
     * @param customer одержувач
     * @param order замовлення
     */
    @Override
    public void send(Customer customer, Order order) {
        Objects.requireNonNull(customer, "customer не може бути null");
        Objects.requireNonNull(order, "order не може бути null");
        System.out.printf("[STUB] send(customer=%s, order=%s)%n", customer, order);
    }
}
