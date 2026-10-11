package shop.notification;

/**
 * {@code ConcreteProduct} у шаблоні Factory Method: сповіщення
 * SMS-повідомленням.
 */
public class SmsNotification implements Notification {

    /** Створює канал SMS-сповіщень. */
    public SmsNotification() {
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("[STUB] SmsNotification.send: SMS на номер '" + recipient
                + "', текст='" + message + "'");
    }
}
