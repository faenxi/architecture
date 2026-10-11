package shop;

import shop.builder.OnlineOrderBuilder;
import shop.builder.OrderBuilder;
import shop.builder.OrderDirector;
import shop.checkout.CheckoutService;
import shop.checkout.CheckoutServiceFactory;
import shop.checkout.DomesticCheckoutFactory;
import shop.checkout.InternationalCheckoutFactory;
import shop.notification.EmailNotificationCreator;
import shop.notification.NotificationCreator;
import shop.notification.SmsNotificationCreator;
import shop.pool.PaymentSession;
import shop.pool.PaymentSessionPool;

/**
 * Клас {@code Main} демонструє роботу шаблонів <b>Singleton</b>,
 * <b>Prototype</b> (ЛР №3), <b>Factory Method</b> і
 * <b>Abstract Factory</b> (ЛР №4), а також <b>Builder</b> і
 * <b>Object Pool</b> (ЛР №5), а також структурні шаблони ЛР №6-8
 * у межах єдиної предметної області
 * "Кошик інтернет-магазину".
 */
public class Main {

    /** Забороняє створення демонстраційного службового класу. */
    private Main() {
    }

    /**
     * Запускає послідовну демонстрацію шаблонів лабораторних робіт №3-5.
     *
     * @param args аргументи командного рядка не використовуються
     */
    public static void main(String[] args) {

        System.out.println("=== 1. Демонстрація Singleton ===");
        ShoppingCart cart1 = ShoppingCart.getInstance();
        ShoppingCart cart2 = ShoppingCart.getInstance();
        System.out.println("cart1 == cart2: " + (cart1 == cart2));

        System.out.println("\n=== 2. Демонстрація Prototype (реєстр прототипів товарів) ===");
        ProductRegistry registry = new ProductRegistry();

        Product phoneTemplate = new Product("P-000", "Базовий смартфон", "Електроніка",
                0.0, "Стандартний опис смартфону, 6.1'', 128GB", 0);
        registry.registerPrototype("Електроніка", phoneTemplate);

        Product tshirtTemplate = new Product("T-000", "Базова футболка", "Одяг",
                0.0, "Бавовна 100%, унісекс", 0);
        registry.registerPrototype("Одяг", tshirtTemplate);

        Product phone1 = registry.createProduct("Електроніка", "P-101", "Смартфон Galaxy A55", 12999.0);
        Product tshirt1 = registry.createProduct("Одяг", "T-101", "Футболка Nike чорна", 899.0);

        System.out.println("Клоновано з прототипу: " + phone1);
        System.out.println("Клоновано з прототипу: " + tshirt1);

        Discount baseDiscount = new Discount("SEASON10", 10.0, "2026-12-31");
        Discount personalDiscount = baseDiscount.clone();
        personalDiscount.setCode("VIP-CUSTOMER-15");
        personalDiscount.setPercentage(15.0);
        System.out.println("Клонована й змінена знижка: " + personalDiscount);

        System.out.println("\n=== 3. Наповнення кошика й оформлення замовлення (checkout) ===");
        cart1.addProduct(phone1, 1);
        cart1.addProduct(tshirt1, 2);
        cart1.applyDiscount(personalDiscount);
        System.out.println("Разом до сплати: " + cart1.getTotal());

        Customer ua = new Customer("C-1", "Олена Коваль", "olena@example.com");
        Order uaOrder = cart1.checkout(ua);

        System.out.println("\n=== 4. Демонстрація Factory Method (сповіщення покупця) ===");
        // Клієнтський код обирає ConcreteCreator (наприклад, за налаштуваннями
        // покупця), але далі працює лише з абстрактним NotificationCreator.
        NotificationCreator emailChannel = new EmailNotificationCreator();
        emailChannel.notifyCustomer(ua.getEmail(), "Ваше замовлення " + uaOrder.getOrderId()
                + " оформлено на суму " + uaOrder.getTotal());

        NotificationCreator smsChannel = new SmsNotificationCreator();
        smsChannel.notifyCustomer("+380501112233", "Замовлення " + uaOrder.getOrderId()
                + " прийнято в обробку");

        System.out.println("\n=== 5. Демонстрація Abstract Factory (родина сервісів checkout) ===");
        System.out.println("-- Родина 'Україна' --");
        CheckoutServiceFactory domesticFactory = new DomesticCheckoutFactory();
        CheckoutService domesticCheckout = new CheckoutService(domesticFactory);
        domesticCheckout.finalizeOrder(uaOrder);

        System.out.println("\n-- Родина 'Міжнародна доставка' --");
        Customer foreign = new Customer("C-2", "John Smith", "john.smith@example.com");
        cart1.addProduct(phone1, 1);
        Order intlOrder = cart1.checkout(foreign);

        CheckoutServiceFactory internationalFactory = new InternationalCheckoutFactory();
        CheckoutService internationalCheckout = new CheckoutService(internationalFactory);
        internationalCheckout.finalizeOrder(intlOrder);

        System.out.println("\nПеревірка, що cart2 (той самий Singleton) бачить порожній кошик:");
        System.out.println("Кількість позицій у cart2 після checkout: " + cart2.getItems().size());

        System.out.println("\n=== 6. Демонстрація Builder (складне замовлення) ===");
        cart1.addProduct(phone1, 1);
        cart1.addProduct(tshirt1, 1);

        OrderBuilder orderBuilder = new OnlineOrderBuilder();
        OrderDirector orderDirector = new OrderDirector();
        Order giftOrder = orderDirector.buildGiftOrder(
                orderBuilder,
                ua,
                cart1.getItems(),
                cart1.getTotal(),
                "вул. Головна, 101, Чернівці");
        cart1.clear();
        System.out.println("Builder створив: " + giftOrder);

        System.out.println("\n=== 7. Демонстрація Object Pool (платіжні сесії) ===");
        PaymentSessionPool sessionPool = new PaymentSessionPool(1);
        PaymentSession firstSession = sessionPool.acquire();
        try {
            firstSession.processPayment(giftOrder);
        } finally {
            sessionPool.release(firstSession);
        }

        PaymentSession reusedSession = sessionPool.acquire();
        System.out.println("Повторно використано той самий об'єкт: "
                + (reusedSession == firstSession));
        sessionPool.release(reusedSession);
        System.out.println("Вільних сесій після повернення: "
                + sessionPool.getAvailableCount() + "/" + sessionPool.getCapacity());

        StructuralPatternsDemo.run();
    }
}
