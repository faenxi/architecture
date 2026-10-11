package shop;

/**
 * Клас {@code CartItem} представляє одну позицію в кошику покупця —
 * пару "товар + кількість". Використовується агрегацією всередині
 * {@link ShoppingCart}.
 */
public class CartItem {

    private final Product product;
    private int quantity;

    /**
     * Створює позицію кошика.
     *
     * @param product товар
     * @param quantity кількість
     */
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Обчислює вартість позиції (ціна товару * кількість).
     *
     * @return сума за позицію
     */
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    /**
     * Повертає товар цієї позиції.
     *
     * @return товар цієї позиції
     */
    public Product getProduct() { return product; }

    /**
     * Повертає кількість одиниць товару.
     *
     * @return кількість одиниць товару
     */
    public int getQuantity() { return quantity; }

    /**
     * Змінює кількість одиниць товару.
     *
     * @param quantity нова кількість одиниць
     */
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return product.getName() + " x" + quantity + " = " + getSubtotal();
    }
}
