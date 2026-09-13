package shop;

/**
 * Служба сповіщень клієнта про статус замовлення.
 * <p>
 * Реалізує <b>Pure Fabrication</b>: у реальному світі немає "об'єкта
 * сповіщень" — це штучний клас, введений, щоб не змішувати
 * відповідальність за надсилання email/SMS з класом {@link Order}
 * (це б знижувало його High Cohesion).
 */
public class NotificationService {

    /**
     * ЗАГЛУШКА бізнес-логіки: реальна інтеграція з поштовим сервером
     * / SMS-шлюзом тут не реалізується, лише виводиться інформація
     * про виклик.
     *
     * @param order  замовлення, про яке сповіщаємо
     * @param message текст сповіщення
     */
    public void sendOrderNotification(Order order, String message) {
        System.out.println("[STUB] NotificationService.sendOrderNotification(orderId="
                + order.getId() + ", customerEmail=" + order.getCustomer().getEmail()
                + ", message=\"" + message + "\") -> імітація надсилання email/SMS");
    }
}
