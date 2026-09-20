package ua.edu.shopcart.repository;

import java.util.List;
import ua.edu.shopcart.domain.Order;

/**
 * Абстракція сховища замовлень, яка ізолює бізнес-логіку від способу збереження.
 */
public interface OrderRepository {
    /**
     * Зберігає замовлення.
     *
     * @param order замовлення для збереження
     */
    void save(Order order);

    /**
     * Повертає всі збережені замовлення.
     *
     * @return незмінний список замовлень
     */
    List<Order> findAll();
}
