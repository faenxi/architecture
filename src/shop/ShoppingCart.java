package shop;

import java.util.ArrayList;
import java.util.List;

/**
 * Кошик покупця в інтернет-магазині.
 * <p>
 * Реалізує <b>Creator</b>: саме ShoppingCart створює екземпляри
 * {@link CartItem}, оскільки кошик агрегує (містить) ці рядки.
 * <p>
 * Реалізує <b>High Cohesion</b>: клас відповідає лише за керування
 * набором рядків кошика (додавання/видалення/пошук), а розрахунок
 * знижок делегований {@link PriceCalculator} — це не змішується
 * в одному класі.
 * <p>
 * Реалізує <b>Low Coupling</b>: ShoppingCart не знає нічого про
 * оплату чи сповіщення — лише про свої товари та калькулятор цін.
 */
public class ShoppingCart {

    private final List<CartItem> items = new ArrayList<>();
    private final PriceCalculator priceCalculator;
    private String appliedPromoCode;

    public ShoppingCart(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    /**
     * Додає товар у кошик. Якщо товар вже є в кошику — збільшує
     * кількість замість дублювання рядка.
     * Creator: кошик сам створює новий {@link CartItem}, коли товару
     * ще немає в кошику.
     *
     * @param product  товар
     * @param quantity кількість (&gt; 0)
     */
    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.increaseQuantity(quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    /**
     * Видаляє товар з кошика за ідентифікатором.
     *
     * @param productId ідентифікатор товару
     * @return true, якщо товар було знайдено та видалено
     */
    public boolean removeItem(String productId) {
        return items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    /** Застосовує промокод, який буде враховано при розрахунку суми. */
    public void applyPromoCode(String promoCode) {
        this.appliedPromoCode = promoCode;
    }

    /**
     * Обчислює фінальну суму кошика.
     * Делегує розрахунок {@link PriceCalculator} (Indirection),
     * замість того, щоб самому знати правила знижок.
     *
     * @return сума до сплати з урахуванням знижок
     */
    public double calculateTotal() {
        double subtotal = priceCalculator.calculateSubtotal(items);
        return priceCalculator.applyDiscounts(subtotal, appliedPromoCode);
    }

    /** @return незмінна копія поточних рядків кошика (для формування Order) */
    public List<CartItem> getItemsSnapshot() {
        return new ArrayList<>(items);
    }

    /** @return true, якщо кошик порожній */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /** Очищає кошик (використовується після оформлення замовлення). */
    public void clear() {
        items.clear();
        appliedPromoCode = null;
    }
}
