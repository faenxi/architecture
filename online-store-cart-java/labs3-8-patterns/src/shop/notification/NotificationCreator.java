package shop.notification;

/**
 * Клас {@code NotificationCreator} — роль {@code Creator} у шаблоні
 * <b>Factory Method</b>.
 * <p>
 * Оголошує фабричний метод {@link #createNotification()}, який
 * підкласи ({@code ConcreteCreator}) перевизначають, щоб повернути
 * конкретну реалізацію {@link Notification}. Сам {@code Creator} при
 * цьому реалізує спільну для всіх каналів логіку (шаблонний метод
 * {@link #notifyCustomer(String, String)}) і працює з продуктом лише
 * через абстрактний інтерфейс {@link Notification}, не знаючи його
 * конкретного класу.
 * <p>
 * Проблема, яку вирішує шаблон: код кошика/замовлення (клієнт) не
 * повинен знати про існування {@code EmailNotification},
 * {@code SmsNotification} тощо і не повинен змінюватися щоразу, коли
 * додається новий канал сповіщень — достатньо додати новий
 * {@code ConcreteCreator}.
 */
public abstract class NotificationCreator {

    /** Створює базову частину конкретного Creator. */
    protected NotificationCreator() {
    }

    /**
     * Фабричний метод. Кожен {@code ConcreteCreator} інстанціює свій
     * власний {@code ConcreteProduct}.
     *
     * @return новий об'єкт {@link Notification} відповідного каналу
     */
    protected abstract Notification createNotification();

    /**
     * Шаблонний метод: готує повідомлення й делегує його надсилання
     * продукту, отриманому від фабричного методу. Ця логіка спільна
     * для всіх каналів і визначена один раз у базовому класі.
     *
     * @param recipient отримувач (email/телефон/deviceId — залежно від каналу)
     * @param message   текст повідомлення
     */
    public final void notifyCustomer(String recipient, String message) {
        Notification notification = createNotification();
        System.out.println("[STUB] NotificationCreator.notifyCustomer: обрано канал "
                + notification.getClass().getSimpleName());
        notification.send(recipient, message);
    }
}
