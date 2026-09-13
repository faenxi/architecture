package shop;

/**
 * Контролер, що координує роботу з кошиком: обробляє "зовнішні"
 * запити (наприклад, від UI або REST-шару) і керує потоком роботи
 * між {@link ShoppingCart}, {@link Customer}, {@link Order},
 * {@link PaymentProcessor} та {@link NotificationService}.
 * <p>
 * Реалізує <b>Controller</b>: логіка координації винесена в окремий
 * клас, а не "розмазана" по UI чи по доменних класах, що зберігає
 * High Cohesion доменних об'єктів.
 */
public class CartController {

    private final ShoppingCart cart;
    private final Customer customer;
    private final NotificationService notificationService;

    public CartController(ShoppingCart cart, Customer customer,
                           NotificationService notificationService) {
        this.cart = cart;
        this.customer = customer;
        this.notificationService = notificationService;
    }

    /** Обробляє запит на додавання товару в кошик. */
    public void handleAddToCart(Product product, int quantity) {
        System.out.println("[Controller] Додаємо в кошик: " + product.getName() + " x" + quantity);
        cart.addItem(product, quantity);
    }

    /** Обробляє запит на видалення товару з кошика. */
    public void handleRemoveFromCart(String productId) {
        System.out.println("[Controller] Видаляємо з кошика товар id=" + productId);
        cart.removeItem(productId);
    }

    /** Обробляє застосування промокоду. */
    public void handleApplyPromoCode(String promoCode) {
        System.out.println("[Controller] Застосовуємо промокод: " + promoCode);
        cart.applyPromoCode(promoCode);
    }

    /**
     * Обробляє запит на оформлення замовлення: координує створення
     * замовлення, оплату та сповіщення клієнта.
     *
     * @param paymentProcessor обраний клієнтом спосіб оплати
     * @return сформоване (і, за успіху, підтверджене) замовлення
     */
    public Order handleCheckout(PaymentProcessor paymentProcessor) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Кошик порожній — оформлення неможливе");
        }

        System.out.println("[Controller] Оформлення замовлення для " + customer.getName()
                + " через " + paymentProcessor.getMethodName());

        Order order = customer.createOrder(cart);
        order.setPaymentProcessor(paymentProcessor);

        boolean confirmed = order.confirm();
        if (confirmed) {
            notificationService.sendOrderNotification(order,
                    "Ваше замовлення " + order.getId() + " підтверджено на суму "
                            + order.getTotalAmount() + " грн.");
            cart.clear();
        } else {
            notificationService.sendOrderNotification(order,
                    "Оплату замовлення " + order.getId() + " не вдалося виконати.");
        }

        return order;
    }
}
