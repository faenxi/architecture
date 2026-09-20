package ua.edu.shopcart.domain;

import java.math.BigDecimal;

/**
 * Фізичний товар, який потрібно доставити поштою або кур'єром.
 */
public final class PhysicalProduct extends Product {
    /** Вага товару в кілограмах. */
    private final double weightKg;

    /**
     * Створює фізичний товар.
     *
     * @param id ідентифікатор товару
     * @param name назва товару
     * @param unitPrice ціна одиниці
     * @param weightKg додатна вага в кілограмах
     */
    public PhysicalProduct(String id, String name, BigDecimal unitPrice, double weightKg) {
        super(id, name, unitPrice);
        if (weightKg <= 0) {
            throw new IllegalArgumentException("weightKg має бути більшою за нуль");
        }
        this.weightKg = weightKg;
    }

    /**
     * Повертає вагу товару.
     *
     * @return вага в кілограмах
     */
    public double getWeightKg() {
        return weightKg;
    }

    /**
     * Повертає спосіб доставки фізичного товару.
     *
     * @return опис кур'єрської доставки
     */
    @Override
    public String getDeliveryInfo() {
        return "Кур'єрська доставка, вага: " + weightKg + " кг";
    }
}
