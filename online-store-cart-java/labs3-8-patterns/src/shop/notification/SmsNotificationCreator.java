package shop.notification;

/**
 * {@code ConcreteCreator} у шаблоні Factory Method: створює
 * {@link SmsNotification}.
 */
public class SmsNotificationCreator extends NotificationCreator {

    /** Створює Creator SMS-повідомлень. */
    public SmsNotificationCreator() {
    }

    @Override
    protected Notification createNotification() {
        return new SmsNotification();
    }
}
