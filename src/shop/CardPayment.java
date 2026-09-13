package shop;

/**
 * Оплата банківською карткою.
 * Одна з варіативних реалізацій {@link PaymentProcessor}
 * (Polymorphism + Protected Variations).
 */
public class CardPayment implements PaymentProcessor {

    private final String cardNumberMasked;

    public CardPayment(String cardNumberMasked) {
        this.cardNumberMasked = cardNumberMasked;
    }

    /**
     * ЗАГЛУШКА бізнес-логіки: реальний виклик платіжного шлюзу
     * (наприклад, банківського API) тут не реалізується.
     */
    @Override
    public boolean pay(double amount) {
        System.out.println("[STUB] CardPayment.pay(amount=" + amount
                + ") card=" + cardNumberMasked + " -> імітація звернення до банківського API");
        return true;
    }

    @Override
    public String getMethodName() {
        return "Банківська картка";
    }
}
