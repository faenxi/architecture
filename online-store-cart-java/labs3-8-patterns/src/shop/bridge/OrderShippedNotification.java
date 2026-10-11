package shop.bridge;

/** RefinedAbstraction для сповіщення про відправлення замовлення. */
public final class OrderShippedNotification extends OrderNotification {
    /**
     * Створює сповіщення про відправлення замовлення.
     *
     * @param channel канал доставки
     */
    public OrderShippedNotification(MessageChannel channel) { super(channel); }

    /** {@inheritDoc} */
    @Override
    public void notify(String recipient, String orderId) {
        send(recipient, "Замовлення " + orderId + " передано в доставку");
    }
}
