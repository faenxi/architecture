package ua.edu.shopcart.view;

import java.util.Objects;
import ua.edu.shopcart.application.CartQueries;
import ua.edu.shopcart.domain.CartItem;

/**
 * Просте консольне подання кошика.
 *
 * <p>Подання залежить лише від інтерфейсу читання, а не від команд,
 * що є практичним прикладом ISP і Low Coupling.</p>
 */
public final class ConsoleCartView {
    /** Доступ лише до запитів читання кошика. */
    private final CartQueries cartQueries;

    /**
     * Створює консольне подання.
     *
     * @param cartQueries інтерфейс читання кошика
     */
    public ConsoleCartView(CartQueries cartQueries) {
        this.cartQueries = Objects.requireNonNull(cartQueries, "cartQueries не може бути null");
    }

    /** Виводить усі рядки, кількість і проміжну суму кошика. */
    public void showCart() {
        System.out.println("Кошик:");
        for (CartItem item : cartQueries.getItems()) {
            System.out.println("  - " + item);
            System.out.println("    " + item.getProduct().getDeliveryInfo());
        }
        System.out.println("Кількість одиниць: " + cartQueries.getTotalQuantity());
        System.out.println("Сума до знижки: " + cartQueries.getSubtotal() + " грн");
    }
}
