package shop;

/**
 * Товар, який можна додати до кошика інтернет-магазину.
 * <p>
 * Клас предметної області. Є "інформаційним експертом" щодо власної ціни
 * та базових характеристик товару (принцип <b>Information Expert</b>):
 * лише сам товар знає свою актуальну ціну, тому саме він відповідає
 * за метод {@link #getPrice()}, яким користуються інші класи (наприклад,
 * {@link CartItem}) замість того, щоб зберігати ціну окремо.
 */
public class Product {

    private final String id;
    private final String name;
    private final double price;
    private final String category;

    /**
     * Створює новий товар.
     *
     * @param id       унікальний ідентифікатор товару
     * @param name     назва товару
     * @param price    ціна за одиницю (грн)
     * @param category категорія товару (наприклад, "Електроніка")
     */
    public Product(String id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    /** @return ідентифікатор товару */
    public String getId() {
        return id;
    }

    /** @return назву товару */
    public String getName() {
        return name;
    }

    /**
     * Повертає актуальну ціну товару.
     * Реалізує Information Expert: тільки Product знає власну ціну.
     *
     * @return ціна за одиницю
     */
    public double getPrice() {
        return price;
    }

    /** @return категорію товару */
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " (" + price + " грн)";
    }
}
