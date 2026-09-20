package ua.edu.shopcart.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Політика відсоткової знижки.
 *
 * <p>Новий спосіб розрахунку додано без зміни сервісу оформлення,
 * що демонструє принцип відкритості/закритості.</p>
 */
public final class PercentageDiscountPolicy implements DiscountPolicy {
    /** Розмір знижки від 0 до 100 відсотків. */
    private final int percent;

    /**
     * Створює відсоткову політику.
     *
     * @param percent значення від 0 до 100
     */
    public PercentageDiscountPolicy(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("percent має бути від 0 до 100");
        }
        this.percent = percent;
    }

    /**
     * Повертає розмір знижки.
     *
     * @return відсоток знижки
     */
    public int getPercent() {
        return percent;
    }

    /**
     * Зменшує суму на заданий відсоток.
     *
     * @param subtotal сума до знижки
     * @return округлена до копійок сума після знижки
     */
    @Override
    public BigDecimal apply(BigDecimal subtotal) {
        Objects.requireNonNull(subtotal, "subtotal не може бути null");
        if (subtotal.signum() < 0) {
            throw new IllegalArgumentException("subtotal має бути невід'ємною");
        }
        BigDecimal multiplier = BigDecimal.valueOf(100L - percent)
                .divide(BigDecimal.valueOf(100));
        return subtotal.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Повертає назву політики.
     *
     * @return опис із відсотком
     */
    @Override
    public String getDescription() {
        return "Знижка " + percent + "%";
    }
}
