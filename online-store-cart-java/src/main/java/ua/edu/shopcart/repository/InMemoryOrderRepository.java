package ua.edu.shopcart.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ua.edu.shopcart.domain.Order;

/**
 * Просте сховище замовлень у пам'яті.
 *
 * <p>Це штучний об'єкт (Pure Fabrication): він не є предметною сутністю,
 * але бере на себе технічну відповідальність збереження.</p>
 */
public final class InMemoryOrderRepository implements OrderRepository {
    /** Внутрішнє сховище замовлень. */
    private final List<Order> orders = new ArrayList<>();

    /**
     * Додає замовлення до пам'яті та виводить аргумент виклику.
     *
     * @param order замовлення для збереження
     */
    @Override
    public void save(Order order) {
        Objects.requireNonNull(order, "order не може бути null");
        orders.add(order);
        System.out.printf("[STUB] save(order=%s)%n", order);
    }

    /**
     * Повертає незмінну копію сховища.
     *
     * @return усі замовлення
     */
    @Override
    public List<Order> findAll() {
        return List.copyOf(orders);
    }
}
