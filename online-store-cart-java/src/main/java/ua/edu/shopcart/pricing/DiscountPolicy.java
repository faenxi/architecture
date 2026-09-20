package ua.edu.shopcart.pricing;

import java.math.BigDecimal;

/**
 * Стабільний інтерфейс змінної політики знижок.
 *
 * <p>Будь-яка реалізація приймає невід'ємну суму та повертає невід'ємну
 * суму, не більшу за початкову. Завдяки контракту реалізації взаємозамінні.</p>
 */
public interface DiscountPolicy {
    /**
     * Застосовує знижку до початкової суми.
     *
     * @param subtotal сума до знижки
     * @return сума після знижки
     */
    BigDecimal apply(BigDecimal subtotal);

    /**
     * Повертає зрозумілий опис політики.
     *
     * @return опис знижки
     */
    String getDescription();
}
