package shop;

/**
 * Оплата готівкою при отриманні.
 * Ще одна варіативна реалізація {@link PaymentProcessor} (Polymorphism).
 */
public class CashOnDeliveryPayment implements PaymentProcessor {

    /**
     * ЗАГЛУШКА бізнес-логіки: реєстрація типу оплати "готівкою"
     * у службі доставки тут не реалізується.
     */
    @Override
    public boolean pay(double amount) {
        System.out.println("[STUB] CashOnDeliveryPayment.pay(amount=" + amount
                + ") -> імітація позначення замовлення як 'оплата при отриманні'");
        return true;
    }

    @Override
    public String getMethodName() {
        return "Готівкою при отриманні";
    }
}
