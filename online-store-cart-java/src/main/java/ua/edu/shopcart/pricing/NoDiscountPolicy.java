package ua.edu.shopcart.pricing;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Політика без знижки, яка не змінює початкову суму.
 */
public final class NoDiscountPolicy implements DiscountPolicy {
    /**
     * Повертає початкову суму без змін.
     *
     * @param subtotal сума до знижки
     * @return та сама сума
     */
    @Override
    public BigDecimal apply(BigDecimal subtotal) {
        Objects.requireNonNull(subtotal, "subtotal не може бути null");
        if (subtotal.signum() < 0) {
            throw new IllegalArgumentException("subtotal має бути невід'ємною");
        }
        return subtotal;
    }

    /**
     * Повертає назву політики.
     *
     * @return опис відсутності знижки
     */
    @Override
    public String getDescription() {
        return "Без знижки";
    }
}
