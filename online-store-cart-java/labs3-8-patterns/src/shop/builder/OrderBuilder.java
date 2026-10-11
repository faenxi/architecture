package shop.builder;

import java.util.List;
import shop.CartItem;
import shop.Customer;
import shop.Order;

/**
 * Інтерфейс Builder для поетапного конструювання складного замовлення.
 */
public interface OrderBuilder {

    /**
     * Очищає накопичені параметри.
     *
     * @return очищений Builder, готовий до нового замовлення
     */
    OrderBuilder reset();

    /**
     * Задає покупця.
     *
     * @param customer покупець
     * @return цей Builder
     */
    OrderBuilder customer(Customer customer);

    /**
     * Задає позиції замовлення.
     *
     * @param items позиції замовлення
     * @return цей Builder
     */
    OrderBuilder items(List<CartItem> items);

    /**
     * Задає остаточну суму.
     *
     * @param total остаточна сума
     * @return цей Builder
     */
    OrderBuilder total(double total);

    /**
     * Задає адресу доставки.
     *
     * @param address адреса доставки
     * @return цей Builder
     */
    OrderBuilder deliveryAddress(String address);

    /**
     * Задає спосіб доставки.
     *
     * @param method спосіб доставки
     * @return цей Builder
     */
    OrderBuilder deliveryMethod(String method);

    /**
     * Задає спосіб оплати.
     *
     * @param method спосіб оплати
     * @return цей Builder
     */
    OrderBuilder paymentMethod(String method);

    /**
     * Вмикає або вимикає подарункове пакування.
     *
     * @param enabled чи потрібне пакування
     * @return цей Builder
     */
    OrderBuilder giftWrap(boolean enabled);

    /**
     * Задає коментар покупця.
     *
     * @param comment коментар покупця
     * @return цей Builder
     */
    OrderBuilder comment(String comment);

    /**
     * Завершує побудову та перевіряє обов'язкові параметри.
     *
     * @return готове замовлення
     */
    Order build();
}
