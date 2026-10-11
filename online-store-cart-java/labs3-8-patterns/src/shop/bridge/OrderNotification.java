package shop.bridge;

import java.util.Objects;

/**
 * Abstraction у Bridge: вид сповіщення незалежний від каналу доставки.
 */
public abstract class OrderNotification {
    /** Реалізація каналу, якій делегується надсилання. */
    private final MessageChannel channel;

    /**
     * Створює сповіщення з вибраним каналом.
     *
     * @param channel implementor каналу повідомлень
     */
    protected OrderNotification(MessageChannel channel) {
        this.channel = Objects.requireNonNull(channel, "channel не може бути null");
    }

    /**
     * Надсилає сформований текст покупцю.
     *
     * @param recipient адреса або телефон покупця
     * @param orderId ідентифікатор замовлення
     */
    public abstract void notify(String recipient, String orderId);

    /**
     * Делегує фактичну доставку повідомлення реалізації каналу.
     *
     * @param recipient адреса отримувача
     * @param text текст повідомлення
     */
    protected final void send(String recipient, String text) {
        channel.send(recipient, text);
    }
}
