package shop.builder;

import java.util.ArrayList;
import java.util.List;
import shop.CartItem;
import shop.Customer;
import shop.Order;

/**
 * ConcreteBuilder, який накопичує параметри й створює об'єкт {@link Order}.
 */
public final class OnlineOrderBuilder implements OrderBuilder {

    /** Покупець майбутнього замовлення. */
    private Customer customer;

    /** Позиції майбутнього замовлення. */
    private List<CartItem> items;

    /** Остаточна сума. */
    private double total;

    /** Адреса доставки. */
    private String deliveryAddress;

    /** Спосіб доставки. */
    private String deliveryMethod;

    /** Спосіб оплати. */
    private String paymentMethod;

    /** Ознака подарункового пакування. */
    private boolean giftWrap;

    /** Додатковий коментар. */
    private String comment;

    /** Створює порожній Builder із типовими параметрами. */
    public OnlineOrderBuilder() {
        reset();
    }

    /** {@inheritDoc} */
    @Override
    public OrderBuilder reset() {
        customer = null;
        items = new ArrayList<>();
        total = 0.0;
        deliveryAddress = null;
        deliveryMethod = "Стандартна доставка";
        paymentMethod = "Оплата при отриманні";
        giftWrap = false;
        comment = "";
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public OrderBuilder customer(Customer value) {
        customer = value;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public OrderBuilder items(List<CartItem> value) {
        items = value == null ? null : new ArrayList<>(value);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public OrderBuilder total(double value) {
        total = value;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public OrderBuilder deliveryAddress(String value) {
        deliveryAddress = value;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public OrderBuilder deliveryMethod(String value) {
        deliveryMethod = value;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public OrderBuilder paymentMethod(String value) {
        paymentMethod = value;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public OrderBuilder giftWrap(boolean value) {
        giftWrap = value;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public OrderBuilder comment(String value) {
        comment = value;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Order build() {
        if (customer == null) {
            throw new IllegalStateException("Builder: покупець не заданий");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalStateException("Builder: позиції замовлення не задані");
        }
        if (deliveryAddress == null || deliveryAddress.isBlank()) {
            throw new IllegalStateException("Builder: адреса доставки не задана");
        }

        Order result = new Order(customer, items, total, deliveryAddress,
                deliveryMethod, paymentMethod, giftWrap, comment);
        System.out.println("[BUILDER] OnlineOrderBuilder.build(customer="
                + customer.getName() + ", items=" + items.size() + ", total=" + total + ")");
        reset();
        return result;
    }
}
