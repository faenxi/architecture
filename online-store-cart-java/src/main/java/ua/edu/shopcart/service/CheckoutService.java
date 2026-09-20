package ua.edu.shopcart.service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import ua.edu.shopcart.domain.Customer;
import ua.edu.shopcart.domain.Order;
import ua.edu.shopcart.domain.OrderStatus;
import ua.edu.shopcart.domain.ShoppingCart;
import ua.edu.shopcart.payment.PaymentProcessor;
import ua.edu.shopcart.payment.ReceiptSender;
import ua.edu.shopcart.pricing.DiscountPolicy;
import ua.edu.shopcart.repository.OrderRepository;

/**
 * Координує оформлення кошика, оплату, збереження та надсилання чека.
 *
 * <p>Сервіс є Pure Fabrication та рівнем Indirection. Він отримує залежності
 * через абстракції, тому демонструє Low Coupling, Protected Variations і DIP.</p>
 */
public final class CheckoutService {
    /** Політика розрахунку остаточної суми. */
    private final DiscountPolicy discountPolicy;

    /** Абстракція зовнішньої платіжної системи. */
    private final PaymentProcessor paymentProcessor;

    /** Абстракція сховища замовлень. */
    private final OrderRepository orderRepository;

    /** Абстракція надсилання чека. */
    private final ReceiptSender receiptSender;

    /** Лічильник для простих демонстраційних номерів замовлень. */
    private final AtomicInteger orderSequence = new AtomicInteger(1);

    /**
     * Створює сервіс із залежностями, переданими ззовні.
     *
     * @param discountPolicy політика знижки
     * @param paymentProcessor платіжний процесор
     * @param orderRepository сховище замовлень
     * @param receiptSender сервіс надсилання чеків
     */
    public CheckoutService(DiscountPolicy discountPolicy,
                           PaymentProcessor paymentProcessor,
                           OrderRepository orderRepository,
                           ReceiptSender receiptSender) {
        this.discountPolicy = Objects.requireNonNull(discountPolicy);
        this.paymentProcessor = Objects.requireNonNull(paymentProcessor);
        this.orderRepository = Objects.requireNonNull(orderRepository);
        this.receiptSender = Objects.requireNonNull(receiptSender);
    }

    /**
     * Оформлює непорожній кошик і очищає його після успіху.
     *
     * @param customer покупець
     * @param cart кошик покупця
     * @return створене та оплачене замовлення
     * @throws IllegalStateException якщо кошик порожній
     */
    public Order checkout(Customer customer, ShoppingCart cart) {
        Objects.requireNonNull(customer, "customer не може бути null");
        Objects.requireNonNull(cart, "cart не може бути null");
        if (cart.isEmpty()) {
            throw new IllegalStateException("Неможливо оформити порожній кошик");
        }

        BigDecimal subtotal = cart.getSubtotal();
        BigDecimal total = discountPolicy.apply(subtotal);
        paymentProcessor.pay(customer, total);

        String orderId = "ORD-" + orderSequence.getAndIncrement();
        Order order = new Order(orderId, customer, cart.getItems(), total, OrderStatus.PAID);
        orderRepository.save(order);
        receiptSender.send(customer, order);
        cart.clear();
        return order;
    }

    /**
     * Повертає опис активної політики знижок.
     *
     * @return опис політики
     */
    public String getDiscountDescription() {
        return discountPolicy.getDescription();
    }
}
