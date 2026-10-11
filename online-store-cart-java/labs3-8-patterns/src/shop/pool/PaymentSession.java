package shop.pool;

import java.util.Objects;
import shop.Order;

/**
 * Дорогий повторно використовуваний об'єкт у шаблоні Object Pool.
 *
 * <p>У реальній системі така сесія могла б утримувати мережеве з'єднання
 * з платіжним шлюзом. Тут зовнішню бізнес-операцію замінено заглушкою.</p>
 */
public final class PaymentSession {

    /** Унікальний ідентифікатор фізичної сесії в пулі. */
    private final String sessionId;

    /** Ознака, що сесію зараз видано клієнту. */
    private boolean borrowed;

    /** Номер останнього обробленого замовлення. */
    private String lastOrderId;

    /**
     * Створює сесію. Викликається лише пулом у цьому пакеті.
     *
     * @param sessionId ідентифікатор сесії
     */
    PaymentSession(String sessionId) {
        this.sessionId = Objects.requireNonNull(sessionId, "sessionId не може бути null");
        System.out.println("[POOL] створено дорогий ресурс PaymentSession(id=" + sessionId + ")");
    }

    /** Активує сесію перед видачею клієнту. */
    void activate() {
        borrowed = true;
    }

    /** Очищає змінний стан перед поверненням у пул. */
    void reset() {
        borrowed = false;
        lastOrderId = null;
    }

    /**
     * Імітує оплату замовлення через зовнішній платіжний шлюз.
     *
     * @param order замовлення для оплати
     */
    public void processPayment(Order order) {
        Objects.requireNonNull(order, "order не може бути null");
        if (!borrowed) {
            throw new IllegalStateException("Сесію потрібно спочатку отримати з пулу");
        }
        lastOrderId = order.getOrderId();
        System.out.println("[STUB] PaymentSession.processPayment(sessionId=" + sessionId
                + ", orderId=" + order.getOrderId() + ", amount=" + order.getTotal() + ")");
    }

    /**
     * Повертає ідентифікатор фізичної сесії.
     *
     * @return ідентифікатор сесії
     */
    public String getSessionId() { return sessionId; }

    /**
     * Перевіряє, чи сесія зараз використовується.
     *
     * @return {@code true}, якщо сесію зараз видано клієнту
     */
    public boolean isBorrowed() { return borrowed; }

    /**
     * Повертає змінний стан для перевірки його очищення.
     *
     * @return номер останнього замовлення або {@code null} після очищення
     */
    public String getLastOrderId() { return lastOrderId; }
}
