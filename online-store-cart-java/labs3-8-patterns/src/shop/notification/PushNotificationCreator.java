package shop.notification;

/**
 * {@code ConcreteCreator} у шаблоні Factory Method: створює
 * {@link PushNotification}.
 */
public class PushNotificationCreator extends NotificationCreator {

    /** Створює Creator push-сповіщень. */
    public PushNotificationCreator() {
    }

    @Override
    protected Notification createNotification() {
        return new PushNotification();
    }
}
