package shop.pool;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

/**
 * Object Pool для обмеженої кількості дорогих платіжних сесій.
 *
 * <p>Пул створює ресурси один раз, видає їх через {@link #acquire()},
 * контролює належність і повертає очищені ресурси через
 * {@link #release(PaymentSession)}. Синхронізовані методи роблять просту
 * навчальну реалізацію безпечною для одночасних звернень.</p>
 */
public final class PaymentSessionPool {

    /** Вільні сесії, готові до повторного використання. */
    private final Queue<PaymentSession> available = new ArrayDeque<>();

    /** Сесії, які зараз видано клієнтам; порівняння виконується за посиланням. */
    private final Set<PaymentSession> borrowed =
            Collections.newSetFromMap(new IdentityHashMap<>());

    /** Загальний розмір пулу. */
    private final int capacity;

    /**
     * Створює й наповнює пул фіксованого розміру.
     *
     * @param capacity кількість сесій; має бути більшою за нуль
     */
    public PaymentSessionPool(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity має бути більшою за нуль");
        }
        this.capacity = capacity;
        for (int i = 1; i <= capacity; i++) {
            available.add(new PaymentSession("PAY-" + i));
        }
    }

    /**
     * Видає вільну сесію з пулу.
     *
     * @return активована платіжна сесія
     * @throws IllegalStateException якщо всі сесії вже зайняті
     */
    public synchronized PaymentSession acquire() {
        PaymentSession session = available.poll();
        if (session == null) {
            throw new IllegalStateException("Пул вичерпано: усі платіжні сесії зайняті");
        }
        session.activate();
        borrowed.add(session);
        System.out.println("[POOL] acquire(sessionId=" + session.getSessionId()
                + ", available=" + available.size() + ")");
        return session;
    }

    /**
     * Повертає сесію, очищає її стан і робить доступною повторно.
     *
     * @param session сесія, раніше отримана з цього пулу
     * @throws IllegalArgumentException якщо сесія не належить до виданих
     */
    public synchronized void release(PaymentSession session) {
        Objects.requireNonNull(session, "session не може бути null");
        if (!borrowed.remove(session)) {
            throw new IllegalArgumentException("Сесія не була видана цим пулом або вже повернена");
        }
        session.reset();
        available.offer(session);
        System.out.println("[POOL] release(sessionId=" + session.getSessionId()
                + ", available=" + available.size() + ")");
    }

    /**
     * Повертає фіксований розмір пулу.
     *
     * @return максимальна кількість сесій
     */
    public int getCapacity() { return capacity; }

    /**
     * Повертає кількість ресурсів, готових до видачі.
     *
     * @return поточна кількість вільних сесій
     */
    public synchronized int getAvailableCount() { return available.size(); }

    /**
     * Повертає кількість ресурсів, які використовують клієнти.
     *
     * @return поточна кількість виданих сесій
     */
    public synchronized int getBorrowedCount() { return borrowed.size(); }
}
