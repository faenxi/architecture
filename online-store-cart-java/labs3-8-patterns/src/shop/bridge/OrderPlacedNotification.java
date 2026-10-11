package shop.bridge;

/** RefinedAbstraction для сповіщення про створене замовлення. */
public final class OrderPlacedNotification extends OrderNotification {
    /**
     * Створює сповіщення про створення замовлення.
     *
     * @param channel канал доставки
     */
    public OrderPlacedNotification(MessageChannel channel) { super(channel); }

    /** {@inheritDoc} */
    @Override
    public void notify(String recipient, String orderId) {
        send(recipient, "Замовлення " + orderId + " оформлено");
    }
}
