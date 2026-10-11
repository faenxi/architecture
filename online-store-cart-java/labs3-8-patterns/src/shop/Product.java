package shop;

import java.util.Objects;

/**
 * Клас {@code Product} описує товар інтернет-магазину.
 * <p>
 * У структурі шаблону <b>Prototype</b> цей клас виконує роль
 * {@code ConcretePrototype}: він реалізує інтерфейс {@link Cloneable}
 * та перевизначає метод {@link #clone()}, що дозволяє створювати нові
 * товари шляхом копіювання вже існуючого (наприклад, "заготовки"
 * товару певної категорії) замість повторного виклику конструктора
 * та ручного заповнення всіх полів.
 * <p>
 * Це особливо зручно, коли створення об'єкта "з нуля" є витратним
 * (наприклад, потребує звернення до БД/каталогу характеристик) —
 * тоді дешевше клонувати вже готовий шаблонний екземпляр і змінити
 * лише кілька полів.
 */
public class Product implements Cloneable {

    /** Унікальний ідентифікатор товару. */
    private String id;

    /** Назва товару. */
    private String name;

    /** Категорія товару (напр. "Електроніка", "Одяг"). */
    private String category;

    /** Ціна товару в базовій валюті. */
    private double price;

    /** Опис товару. */
    private String description;

    /** Залишок товару на складі. */
    private int stockQuantity;

    /**
     * Створює новий товар.
     *
     * @param id            ідентифікатор товару
     * @param name          назва товару
     * @param category      категорія товару
     * @param price         ціна товару
     * @param description   опис товару
     * @param stockQuantity кількість на складі
     */
    public Product(String id, String name, String category, double price,
                    String description, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }

    /**
     * Реалізація патерну Prototype: створює та повертає глибоку копію
     * поточного товару. Оскільки всі поля класу є примітивами або
     * незмінним {@link String}, поверхневої копії {@code super.clone()}
     * достатньо для отримання коректної глибокої копії.
     *
     * @return новий об'єкт {@link Product}, незалежний від оригіналу
     */
    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            // Не повинно статись, бо клас реалізує Cloneable
            throw new AssertionError("Клонування Product неможливе", e);
        }
    }

    /**
     * Бізнес-метод оновлення залишку товару на складі.
     * Реальна логіка (звернення до складської системи) замінена заглушкою.
     *
     * @param delta зміна кількості (може бути від'ємною)
     */
    public void adjustStock(int delta) {
        this.stockQuantity += delta;
        System.out.println("[STUB] Product.adjustStock: товар='" + name
                + "', delta=" + delta + ", новий залишок=" + stockQuantity);
    }

    /**
     * Повертає ідентифікатор.
     *
     * @return ідентифікатор товару
     */
    public String getId() { return id; }

    /**
     * Змінює ідентифікатор.
     *
     * @param id новий ідентифікатор
     */
    public void setId(String id) { this.id = id; }

    /**
     * Повертає назву.
     *
     * @return назва товару
     */
    public String getName() { return name; }

    /**
     * Змінює назву.
     *
     * @param name нова назва
     */
    public void setName(String name) { this.name = name; }

    /**
     * Повертає категорію.
     *
     * @return категорія товару
     */
    public String getCategory() { return category; }

    /**
     * Змінює категорію.
     *
     * @param category нова категорія
     */
    public void setCategory(String category) { this.category = category; }

    /**
     * Повертає ціну.
     *
     * @return ціна товару
     */
    public double getPrice() { return price; }

    /**
     * Змінює ціну.
     *
     * @param price нова ціна
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * Повертає опис.
     *
     * @return опис товару
     */
    public String getDescription() { return description; }

    /**
     * Змінює опис.
     *
     * @param description новий опис
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Повертає залишок.
     *
     * @return кількість товару на складі
     */
    public int getStockQuantity() { return stockQuantity; }

    /**
     * Змінює залишок.
     *
     * @param stockQuantity нова кількість
     */
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{id='" + id + "', name='" + name + "', category='" + category
                + "', price=" + price + ", stock=" + stockQuantity + "}";
    }
}
