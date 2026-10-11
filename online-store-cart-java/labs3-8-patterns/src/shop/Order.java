package shop;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Незмінний продукт шаблону Builder, що описує оформлене замовлення.
 *
 * <p>Обов'язкові й додаткові параметри замовлення поетапно встановлює
 * {@code OnlineOrderBuilder}. Старий короткий конструктор залишено для
 * сумісності з лабораторними роботами №3-4.</p>
 */
public final class Order {

    /** Унікальний номер замовлення. */
    private final String orderId;

    /** Покупець, який оформив замовлення. */
    private final Customer customer;

    /** Незмінний знімок позицій кошика. */
    private final List<CartItem> items;

    /** Остаточна сума замовлення. */
    private final double total;

    /** Адреса або відділення доставки. */
    private final String deliveryAddress;

    /** Обраний спосіб доставки. */
    private final String deliveryMethod;

    /** Обраний спосіб оплати. */
    private final String paymentMethod;

    /** Ознака подарункового пакування. */
    private final boolean giftWrap;

    /** Додатковий коментар покупця. */
    private final String comment;

    /**
     * Створює базове замовлення для сумісності з попередніми роботами.
     *
     * @param customer покупець
     * @param items позиції замовлення
     * @param total остаточна сума
     */
    public Order(Customer customer, List<CartItem> items, double total) {
        this(customer, items, total, "Не вказано", "Стандартна доставка",
                "Не вказано", false, "");
    }

    /**
     * Створює повністю налаштований продукт Builder.
     *
     * @param customer покупець
     * @param items позиції замовлення
     * @param total остаточна сума
     * @param deliveryAddress адреса доставки
     * @param deliveryMethod спосіб доставки
     * @param paymentMethod спосіб оплати
     * @param giftWrap чи потрібне подарункове пакування
     * @param comment коментар покупця
     */
    public Order(Customer customer, List<CartItem> items, double total,
                 String deliveryAddress, String deliveryMethod,
                 String paymentMethod, boolean giftWrap, String comment) {
        this.orderId = UUID.randomUUID().toString();
        this.customer = Objects.requireNonNull(customer, "customer не може бути null");
        this.items = List.copyOf(Objects.requireNonNull(items, "items не може бути null"));
        if (this.items.isEmpty()) {
            throw new IllegalArgumentException("Замовлення повинно містити хоча б одну позицію");
        }
        if (total < 0) {
            throw new IllegalArgumentException("Сума замовлення не може бути від'ємною");
        }
        this.total = total;
        this.deliveryAddress = requireText(deliveryAddress, "deliveryAddress");
        this.deliveryMethod = requireText(deliveryMethod, "deliveryMethod");
        this.paymentMethod = requireText(paymentMethod, "paymentMethod");
        this.giftWrap = giftWrap;
        this.comment = comment == null ? "" : comment;
    }

    /**
     * Перевіряє, що текстовий параметр заповнений.
     *
     * @param value значення
     * @param fieldName назва поля для повідомлення про помилку
     * @return перевірене значення
     */
    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " не може бути порожнім");
        }
        return value;
    }

    /**
     * Імітує передачу замовлення службі доставки.
     */
    public void ship() {
        System.out.println("[STUB] Order.ship(orderId=" + orderId
                + ", customer=" + customer.getName()
                + ", method=" + deliveryMethod
                + ", address=" + deliveryAddress + ")");
    }

    /**
     * Повертає номер замовлення.
     *
     * @return унікальний номер замовлення
     */
    public String getOrderId() { return orderId; }

    /**
     * Повертає покупця.
     *
     * @return покупець
     */
    public Customer getCustomer() { return customer; }

    /**
     * Повертає позиції замовлення.
     *
     * @return незмінний список позицій
     */
    public List<CartItem> getItems() { return items; }

    /**
     * Повертає остаточну суму.
     *
     * @return остаточна сума
     */
    public double getTotal() { return total; }

    /**
     * Повертає адресу доставки.
     *
     * @return адреса доставки
     */
    public String getDeliveryAddress() { return deliveryAddress; }

    /**
     * Повертає спосіб доставки.
     *
     * @return спосіб доставки
     */
    public String getDeliveryMethod() { return deliveryMethod; }

    /**
     * Повертає спосіб оплати.
     *
     * @return спосіб оплати
     */
    public String getPaymentMethod() { return paymentMethod; }

    /**
     * Перевіряє потребу в подарунковому пакуванні.
     *
     * @return {@code true}, якщо потрібне подарункове пакування
     */
    public boolean isGiftWrap() { return giftWrap; }

    /**
     * Повертає коментар покупця.
     *
     * @return коментар покупця
     */
    public String getComment() { return comment; }

    /**
     * Повертає текстове представлення всіх параметрів замовлення.
     *
     * @return опис замовлення
     */
    @Override
    public String toString() {
        return "Order{id='" + orderId + "', customer='" + customer.getName()
                + "', total=" + total + ", delivery='" + deliveryMethod
                + "', address='" + deliveryAddress + "', payment='" + paymentMethod
                + "', giftWrap=" + giftWrap + ", comment='" + comment
                + "', items=" + items + "}";
    }
}
