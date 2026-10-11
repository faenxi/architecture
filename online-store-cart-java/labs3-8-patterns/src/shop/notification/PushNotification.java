package shop.notification;

/**
 * {@code ConcreteProduct} у шаблоні Factory Method: push-сповіщення
 * в мобільному застосунку магазину.
 */
public class PushNotification implements Notification {

    /** Створює канал push-сповіщень. */
    public PushNotification() {
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("[STUB] PushNotification.send: push на пристрій '" + recipient
                + "', текст='" + message + "'");
    }
}
