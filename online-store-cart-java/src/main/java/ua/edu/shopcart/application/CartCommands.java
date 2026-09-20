package ua.edu.shopcart.application;

import ua.edu.shopcart.domain.Product;

/**
 * Вузький командний інтерфейс для зміни кошика.
 *
 * <p>Відокремлення команд від запитів демонструє Interface Segregation
 * Principle: клієнт залежить лише від потрібних йому операцій.</p>
 */
public interface CartCommands {
    /**
     * Додає товар до кошика.
     *
     * @param product товар
     * @param quantity кількість
     */
    void addProduct(Product product, int quantity);

    /**
     * Видаляє товар із кошика.
     *
     * @param productId ідентифікатор товару
     * @return {@code true}, якщо товар було видалено
     */
    boolean removeProduct(String productId);
}
