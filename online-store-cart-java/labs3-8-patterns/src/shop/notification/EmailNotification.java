package shop.notification;

/**
 * {@code ConcreteProduct} у шаблоні Factory Method: сповіщення
 * електронною поштою.
 */
public class EmailNotification implements Notification {

    /** Створює канал електронної пошти. */
    public EmailNotification() {
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("[STUB] EmailNotification.send: лист на адресу '" + recipient
                + "', тема='Ваш інтернет-магазин', текст='" + message + "'");
    }
}
