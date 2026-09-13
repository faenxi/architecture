package shop;

/**
 * Один рядок кошика: товар + його кількість.
 * <p>
 * Реалізує <b>Information Expert</b>: CartItem знає і товар, і кількість,
 * тому саме він відповідає за обчислення власної проміжної суми
 * ({@link #getSubtotal()}), а не {@link ShoppingCart} чи {@link Product}.
 * <p>
 * Реалізує <b>Creator</b>: об'єкти CartItem створюються класом
 * {@link ShoppingCart}, оскільки кошик агрегує (містить) свої рядки.
 */
public class CartItem {

    private final Product product;
    private int quantity;

    /**
     * @param product  товар
     * @param quantity початкова кількість (&gt; 0)
     */
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    /** Збільшує кількість товару в рядку кошика. */
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    /**
     * Обчислює проміжну суму по рядку (ціна * кількість).
     * Information Expert: CartItem має всі дані, потрібні для цього
     * розрахунку, тому робить його сам.
     *
     * @return сума за цей рядок кошика
     */
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return product.getName() + " x" + quantity + " = " + getSubtotal() + " грн";
    }
}
