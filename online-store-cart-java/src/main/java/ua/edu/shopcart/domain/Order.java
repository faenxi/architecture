package ua.edu.shopcart.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Оформлене замовлення, пов'язане з покупцем і агрегованими рядками.
 */
public final class Order {
    /** Унікальний номер замовлення. */
    private final String id;

    /** Покупець, який створив замовлення. */
    private final Customer customer;

    /** Незмінний знімок рядків кошика. */
    private final List<CartItem> items;

    /** Остаточна сума після застосування знижки. */
    private final BigDecimal total;

    /** Поточний стан замовлення. */
    private OrderStatus status;

    /**
     * Створює оформлене замовлення.
     *
     * @param id номер замовлення
     * @param customer покупець
     * @param items непорожній список рядків
     * @param total невід'ємна остаточна сума
     * @param status початковий стан
     */
    public Order(String id, Customer customer, List<CartItem> items,
                 BigDecimal total, OrderStatus status) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id не може бути порожнім");
        }
        this.customer = Objects.requireNonNull(customer, "customer не може бути null");
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("items не може бути порожнім");
        }
        if (total == null || total.signum() < 0) {
            throw new IllegalArgumentException("total має бути невід'ємною");
        }
        this.id = id;
        this.items = List.copyOf(items);
        this.total = total;
        this.status = Objects.requireNonNull(status, "status не може бути null");
    }

    /**
     * Повертає номер замовлення.
     *
     * @return номер замовлення
     */
    public String getId() {
        return id;
    }

    /**
     * Повертає покупця.
     *
     * @return покупець
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Повертає незмінний список рядків.
     *
     * @return рядки замовлення
     */
    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Повертає остаточну суму.
     *
     * @return сума після знижки
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Повертає поточний стан.
     *
     * @return стан замовлення
     */
    public OrderStatus getStatus() {
        return status;
    }

    /** Скасовує замовлення. */
    public void cancel() {
        status = OrderStatus.CANCELLED;
    }

    /**
     * Формує стислий опис замовлення.
     *
     * @return номер, сума та стан
     */
    @Override
    public String toString() {
        return id + ", сума=" + total + " грн, стан=" + status;
    }
}
