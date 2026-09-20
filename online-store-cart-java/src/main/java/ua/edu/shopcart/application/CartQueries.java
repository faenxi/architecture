package ua.edu.shopcart.application;

import java.math.BigDecimal;
import java.util.List;
import ua.edu.shopcart.domain.CartItem;

/**
 * Вузький інтерфейс читання стану кошика.
 */
public interface CartQueries {
    /**
     * Повертає рядки кошика.
     *
     * @return незмінний список рядків
     */
    List<CartItem> getItems();

    /**
     * Обчислює суму до знижки.
     *
     * @return проміжна сума
     */
    BigDecimal getSubtotal();

    /**
     * Повертає загальну кількість одиниць.
     *
     * @return кількість одиниць товару
     */
    int getTotalQuantity();
}
