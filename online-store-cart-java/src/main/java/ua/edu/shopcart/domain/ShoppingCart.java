package ua.edu.shopcart.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Кошик покупця, який агрегує рядки {@link CartItem}.
 *
 * <p>Кошик є інформаційним експертом для загальної суми та творцем рядків,
 * оскільки саме він зберігає й керує ними.</p>
 */
public final class ShoppingCart {
    /** Внутрішній список рядків кошика. */
    private final List<CartItem> items = new ArrayList<>();

    /**
     * Додає товар або збільшує його кількість.
     *
     * @param product товар для додавання
     * @param quantity додатна кількість
     */
    public void addProduct(Product product, int quantity) {
        Objects.requireNonNull(product, "product не може бути null");
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity має бути більшою за нуль");
        }

        for (int index = 0; index < items.size(); index++) {
            CartItem item = items.get(index);
            if (item.getProduct().equals(product)) {
                items.set(index, new CartItem(product, item.getQuantity() + quantity));
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    /**
     * Видаляє товар із кошика за ідентифікатором.
     *
     * @param productId ідентифікатор товару
     * @return {@code true}, якщо товар був у кошику
     */
    public boolean removeProduct(String productId) {
        return items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    /**
     * Повертає незмінну копію рядків кошика.
     *
     * @return рядки кошика
     */
    public List<CartItem> getItems() {
        return List.copyOf(items);
    }

    /**
     * Обчислює суму кошика до знижки.
     *
     * @return сума всіх рядків
     */
    public BigDecimal getSubtotal() {
        return items.stream()
                .map(CartItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Повертає загальну кількість одиниць товарів.
     *
     * @return сумарна кількість
     */
    public int getTotalQuantity() {
        return items.stream().mapToInt(CartItem::getQuantity).sum();
    }

    /**
     * Перевіряє, чи кошик не містить товарів.
     *
     * @return {@code true} для порожнього кошика
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /** Видаляє всі рядки після успішного оформлення замовлення. */
    public void clear() {
        items.clear();
    }
}
