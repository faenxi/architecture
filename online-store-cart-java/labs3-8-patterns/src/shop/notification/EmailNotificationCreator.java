package shop.notification;

/**
 * {@code ConcreteCreator} у шаблоні Factory Method: створює
 * {@link EmailNotification}.
 */
public class EmailNotificationCreator extends NotificationCreator {

    /** Створює Creator електронних листів. */
    public EmailNotificationCreator() {
    }

    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
}
