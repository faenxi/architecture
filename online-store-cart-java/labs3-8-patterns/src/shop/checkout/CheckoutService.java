package shop.checkout;

import shop.Order;

/**
 * {@code Client} у шаблоні <b>Abstract Factory</b>.
 * <p>
 * Отримує конкретну фабрику {@link CheckoutServiceFactory} через
 * конструктор (Dependency Injection) і працює далі виключно через
 * абстрактні інтерфейси {@link PaymentProcessor} та
 * {@link ShippingProvider} — йому невідомо, чи це LiqPay/NovaPoshta,
 * чи PayPal/DHL. Завдяки цьому логіка завершення замовлення
 * ({@link #finalizeOrder(Order)}) не залежить від конкретних
 * платіжних/логістичних провайдерів і не потребує змін при додаванні
 * нової родини (наприклад, "Європа": Stripe + DPD).
 */
public class CheckoutService {

    private final PaymentProcessor paymentProcessor;
    private final ShippingProvider shippingProvider;

    /**
     * Створює сервіс із сумісною родиною оплати й доставки.
     *
     * @param factory абстрактна фабрика сервісів checkout
     */
    public CheckoutService(CheckoutServiceFactory factory) {
        this.paymentProcessor = factory.createPaymentProcessor();
        this.shippingProvider = factory.createShippingProvider();
        System.out.println("[STUB] CheckoutService: ініціалізовано родину сервісів "
                + paymentProcessor.getClass().getSimpleName() + " + "
                + shippingProvider.getClass().getSimpleName());
    }

    /**
     * Завершує обробку вже оформленого замовлення {@link Order}:
     * приймає оплату та передає замовлення в обрану службу доставки.
     *
     * @param order замовлення, сформоване {@code ShoppingCart.checkout(...)}
     */
    public void finalizeOrder(Order order) {
        paymentProcessor.processPayment(order.getTotal(), order.getCustomer().getName());
        shippingProvider.deliver(order.getOrderId(), order.getCustomer().getName());
    }
}
