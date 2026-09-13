package shop;

import java.util.List;

/**
 * Допоміжний (штучний) клас для розрахунку підсумкової суми кошика
 * з урахуванням знижок.
 * <p>
 * Реалізує <b>Pure Fabrication</b>: у предметній області немає поняття
 * "калькулятор цін" — цей клас не відповідає жодній сутності реального
 * світу, він введений штучно, щоб не переобтяжувати {@link ShoppingCart}
 * логікою знижок і зберегти в ньому <b>High Cohesion</b> (кошик
 * відповідає лише за зберігання рядків, а не за правила знижок).
 * <p>
 * Одночасно реалізує <b>Indirection</b>: цей клас є посередником між
 * {@link ShoppingCart} та правилами нарахування знижок/промокодів,
 * тому кошик не залежить напряму від деталей цих правил.
 */
public class PriceCalculator {

    private static final double BULK_DISCOUNT_THRESHOLD = 1000.0;
    private static final double BULK_DISCOUNT_RATE = 0.05;

    /**
     * Обчислює суму рядків кошика без знижок.
     *
     * @param items рядки кошика
     * @return сума всіх {@link CartItem#getSubtotal()}
     */
    public double calculateSubtotal(List<CartItem> items) {
        double sum = 0.0;
        for (CartItem item : items) {
            sum += item.getSubtotal();
        }
        return sum;
    }

    /**
     * Застосовує знижку за промокодом та/або накопичувальну знижку
     * за обсяг покупки. Проста демонстраційна логіка (не заглушка),
     * оскільки саме вона показує роль класу-посередника.
     *
     * @param subtotal сума без знижок
     * @param promoCode промокод або null, якщо не застосовується
     * @return фінальна сума до сплати
     */
    public double applyDiscounts(double subtotal, String promoCode) {
        double total = subtotal;

        if (promoCode != null && promoCode.equalsIgnoreCase("STUDENT10")) {
            total -= subtotal * 0.10;
        }

        if (subtotal >= BULK_DISCOUNT_THRESHOLD) {
            total -= subtotal * BULK_DISCOUNT_RATE;
        }

        return Math.max(total, 0.0);
    }
}
