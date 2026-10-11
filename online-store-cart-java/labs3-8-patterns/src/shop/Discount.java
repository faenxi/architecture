package shop;

/**
 * Клас {@code Discount} описує знижку/промокод, який може бути
 * застосований до кошика покупця.
 * <p>
 * Клас також є {@code ConcretePrototype} у шаблоні <b>Prototype</b> —
 * друга ілюстрація патерну в межах предметної області. Це показує,
 * що клонування корисне не лише для товарів: типову "базову" знижку
 * (наприклад, сезонну акцію -10%) можна клонувати та персоналізувати
 * (інший код, інша дата закінчення) без повторного створення об'єкта
 * вручну.
 */
public class Discount implements Cloneable {

    private String code;
    private double percentage;
    private String validUntil;

    /**
     * Створює знижку.
     *
     * @param code промокод
     * @param percentage відсоток знижки
     * @param validUntil дата завершення дії
     */
    public Discount(String code, double percentage, String validUntil) {
        this.code = code;
        this.percentage = percentage;
        this.validUntil = validUntil;
    }

    /**
     * Створює копію знижки (Prototype). Поля класу примітивні/незмінні,
     * тому {@code super.clone()} дає коректну глибоку копію.
     */
    @Override
    public Discount clone() {
        try {
            return (Discount) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Клонування Discount неможливе", e);
        }
    }

    /**
     * Обчислює суму знижки для заданої суми замовлення.
     * Заглушка бізнес-логіки перевірки чинності промокоду.
     *
     * @param amount сума, до якої застосовується знижка
     * @return розмір знижки в грошових одиницях
     */
    public double applyTo(double amount) {
        System.out.println("[STUB] Discount.applyTo: перевірка чинності промокоду '" + code
                + "' до " + validUntil);
        return amount * percentage / 100.0;
    }

    /**
     * Повертає промокод.
     *
     * @return промокод
     */
    public String getCode() { return code; }

    /**
     * Змінює промокод.
     *
     * @param code новий промокод
     */
    public void setCode(String code) { this.code = code; }

    /**
     * Повертає відсоток знижки.
     *
     * @return відсоток знижки
     */
    public double getPercentage() { return percentage; }

    /**
     * Змінює відсоток знижки.
     *
     * @param percentage новий відсоток
     */
    public void setPercentage(double percentage) { this.percentage = percentage; }

    /**
     * Повертає дату завершення дії.
     *
     * @return дата завершення
     */
    public String getValidUntil() { return validUntil; }

    /**
     * Змінює дату завершення дії.
     *
     * @param validUntil нова дата
     */
    public void setValidUntil(String validUntil) { this.validUntil = validUntil; }

    @Override
    public String toString() {
        return "Discount{code='" + code + "', percentage=" + percentage + ", validUntil='" + validUntil + "'}";
    }
}
