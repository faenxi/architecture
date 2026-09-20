package ua.edu.shopcart.payment;

import ua.edu.shopcart.domain.Customer;
import ua.edu.shopcart.domain.Order;

/**
 * Вузький інтерфейс надсилання чека покупцеві.
 */
public interface ReceiptSender {
    /**
     * Надсилає чек за оформленим замовленням.
     *
     * @param customer одержувач чека
     * @param order оформлене замовлення
     */
    void send(Customer customer, Order order);
}
