package ua.edu.shopcart.domain;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Базовий клас товару інтернет-магазину.
 *
 * <p>Клас зберігає спільні атрибути всіх товарів, а спосіб доставки
 * делегує підкласам. Таке рішення демонструє наслідування та поліморфізм.</p>
 */
public abstract class Product {
    /** Унікальний ідентифікатор товару. */
    private final String id;

    /** Назва товару, яку бачить покупець. */
    private final String name;

    /** Ціна однієї одиниці товару. */
    private final BigDecimal unitPrice;

    /**
     * Створює товар зі спільними характеристиками.
     *
     * @param id унікальний непорожній ідентифікатор
     * @param name непорожня назва
     * @param unitPrice невід'ємна ціна
     * @throws IllegalArgumentException якщо аргументи некоректні
     */
    protected Product(String id, String name, BigDecimal unitPrice) {
        this.id = requireText(id, "id");
        this.name = requireText(name, "name");
        if (unitPrice == null || unitPrice.signum() < 0) {
            throw new IllegalArgumentException("unitPrice має бути невід'ємною");
        }
        this.unitPrice = unitPrice;
    }

    /**
     * Повертає ідентифікатор товару.
     *
     * @return ідентифікатор товару
     */
    public String getId() {
        return id;
    }

    /**
     * Повертає назву товару.
     *
     * @return назва товару
     */
    public String getName() {
        return name;
    }

    /**
     * Повертає ціну одиниці товару.
     *
     * @return ціна одиниці
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Формує опис способу доставки конкретного типу товару.
     *
     * @return текстовий опис доставки
     */
    public abstract String getDeliveryInfo();

    /**
     * Перевіряє, що текстовий аргумент заповнений.
     *
     * @param value значення для перевірки
     * @param fieldName назва поля в повідомленні про помилку
     * @return перевірений текст
     */
    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " не може бути порожнім");
        }
        return value;
    }

    /**
     * Порівнює товари за унікальним ідентифікатором.
     *
     * @param other інший об'єкт
     * @return {@code true}, якщо ідентифікатори однакові
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Product product)) {
            return false;
        }
        return id.equals(product.id);
    }

    /**
     * Обчислює хеш-код за ідентифікатором.
     *
     * @return хеш-код товару
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Формує стислий опис товару.
     *
     * @return назва та ціна товару
     */
    @Override
    public String toString() {
        return name + " (" + unitPrice + " грн)";
    }
}
