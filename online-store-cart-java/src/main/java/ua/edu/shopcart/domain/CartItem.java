package ua.edu.shopcart.domain;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Незмінний рядок кошика: товар і вибрана кількість.
 *
 * <p>Клас є інформаційним експертом для обчислення вартості свого рядка.</p>
 */
public final class CartItem {
    /** Товар у рядку кошика. */
    private final Product product;

    /** Кількість одиниць товару. */
    private final int quantity;

    /**
     * Створює рядок кошика.
     *
     * @param product товар
     * @param quantity додатна кількість
     */
    public CartItem(Product product, int quantity) {
        this.product = Objects.requireNonNull(product, "product не може бути null");
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity має бути більшою за нуль");
        }
        this.quantity = quantity;
    }

    /**
     * Повертає товар.
     *
     * @return товар рядка
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Повертає кількість.
     *
     * @return кількість одиниць
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Обчислює вартість рядка.
     *
     * @return ціна одиниці, помножена на кількість
     */
    public BigDecimal getLineTotal() {
        return product.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
    }

    /**
     * Формує текстовий опис рядка кошика.
     *
     * @return назва, кількість і сума
     */
    @Override
    public String toString() {
        return product.getName() + " x " + quantity + " = " + getLineTotal() + " грн";
    }
}
