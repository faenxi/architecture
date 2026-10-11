package shop.builder;

import java.util.List;
import shop.CartItem;
import shop.Customer;
import shop.Order;

/**
 * Director задає готові послідовності кроків Builder для типових замовлень.
 */
public final class OrderDirector {

    /** Створює Director без внутрішнього стану. */
    public OrderDirector() {
    }

    /**
     * Створює стандартне замовлення з оплатою при отриманні.
     *
     * @param builder конкретний Builder
     * @param customer покупець
     * @param items позиції
     * @param total сума
     * @param address адреса доставки
     * @return готове стандартне замовлення
     */
    public Order buildStandardOrder(OrderBuilder builder, Customer customer,
                                    List<CartItem> items, double total, String address) {
        return builder.reset()
                .customer(customer)
                .items(items)
                .total(total)
                .deliveryAddress(address)
                .deliveryMethod("Нова пошта")
                .paymentMethod("Оплата при отриманні")
                .giftWrap(false)
                .comment("Стандартне замовлення")
                .build();
    }

    /**
     * Створює подарункове замовлення з онлайн-оплатою.
     *
     * @param builder конкретний Builder
     * @param customer покупець
     * @param items позиції
     * @param total сума
     * @param address адреса доставки
     * @return готове подарункове замовлення
     */
    public Order buildGiftOrder(OrderBuilder builder, Customer customer,
                                List<CartItem> items, double total, String address) {
        return builder.reset()
                .customer(customer)
                .items(items)
                .total(total)
                .deliveryAddress(address)
                .deliveryMethod("Кур'єр")
                .paymentMethod("LiqPay")
                .giftWrap(true)
                .comment("Додати листівку: З найкращими побажаннями!")
                .build();
    }
}
