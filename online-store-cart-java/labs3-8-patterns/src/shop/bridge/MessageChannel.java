package shop.bridge;

/** Implementor у Bridge: абстракція каналу доставки повідомлень. */
public interface MessageChannel {
    /**
     * Надсилає текст отримувачу.
     *
     * @param recipient email або номер телефону
     * @param text повідомлення
     */
    void send(String recipient, String text);
}
