package shop;

import java.util.List;
import shop.builder.OnlineOrderBuilder;
import shop.builder.OrderBuilder;
import shop.builder.OrderDirector;
import shop.pool.PaymentSession;
import shop.pool.PaymentSessionPool;

/**
 * Автоматизовані перевірки Builder та Object Pool без сторонніх бібліотек.
 */
public final class Lab5Test {

    /** Забороняє створення службового тестового класу. */
    private Lab5Test() {
    }

    /**
     * Запускає всі перевірки ЛР №5.
     *
     * @param args аргументи командного рядка не використовуються
     */
    public static void main(String[] args) {
        Order order = testBuilder();
        testObjectPool(order);
        System.out.println("Усі перевірки ЛР №5 пройдено успішно.");
    }

    /**
     * Перевіряє покрокове створення двох конфігурацій замовлення.
     *
     * @return замовлення для наступного тесту
     */
    private static Order testBuilder() {
        Customer customer = new Customer("TEST-1", "Тестовий покупець", "test@example.com");
        Product product = new Product("TEST-P", "Миша", "Електроніка",
                500.0, "Тестовий товар", 10);
        List<CartItem> items = List.of(new CartItem(product, 2));

        OrderBuilder builder = new OnlineOrderBuilder();
        OrderDirector director = new OrderDirector();
        Order standard = director.buildStandardOrder(
                builder, customer, items, 1000.0, "НП №1, Чернівці");
        Order gift = director.buildGiftOrder(
                builder, customer, items, 1000.0, "вул. Головна, 1, Чернівці");

        check(!standard.isGiftWrap(), "Стандартне замовлення не повинно мати пакування");
        check(gift.isGiftWrap(), "Подарункове замовлення повинно мати пакування");
        check("LiqPay".equals(gift.getPaymentMethod()), "Подарункове замовлення має LiqPay");
        check(gift.getItems().size() == 1, "Builder повинен перенести позиції замовлення");
        return gift;
    }

    /**
     * Перевіряє обмеження, повернення, очищення та повторне використання ресурсу.
     *
     * @param order замовлення для імітації оплати
     */
    private static void testObjectPool(Order order) {
        PaymentSessionPool pool = new PaymentSessionPool(2);
        PaymentSession first = pool.acquire();
        PaymentSession second = pool.acquire();
        check(pool.getAvailableCount() == 0, "Після двох acquire пул має бути порожнім");

        boolean exhausted = false;
        try {
            pool.acquire();
        } catch (IllegalStateException expected) {
            exhausted = true;
        }
        check(exhausted, "Вичерпаний пул має повідомляти про помилку");

        first.processPayment(order);
        pool.release(first);
        check(first.getLastOrderId() == null, "release повинен очищати стан сесії");

        PaymentSession reused = pool.acquire();
        check(reused == first, "Пул повинен повторно використати повернений об'єкт");
        pool.release(second);
        pool.release(reused);
        check(pool.getAvailableCount() == 2, "Усі ресурси мають повернутися до пулу");
    }

    /**
     * Перевіряє умову тесту.
     *
     * @param condition очікувана умова
     * @param message повідомлення при помилці
     */
    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
