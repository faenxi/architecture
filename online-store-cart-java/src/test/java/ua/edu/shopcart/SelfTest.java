package ua.edu.shopcart;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;
import ua.edu.shopcart.domain.Customer;
import ua.edu.shopcart.domain.Order;
import ua.edu.shopcart.domain.PhysicalProduct;
import ua.edu.shopcart.domain.Product;
import ua.edu.shopcart.domain.ShoppingCart;
import ua.edu.shopcart.pricing.DiscountPolicy;
import ua.edu.shopcart.pricing.NoDiscountPolicy;
import ua.edu.shopcart.pricing.PercentageDiscountPolicy;
import ua.edu.shopcart.repository.InMemoryOrderRepository;
import ua.edu.shopcart.service.CheckoutService;

/**
 * Мінімальні автоматизовані перевірки без сторонніх бібліотек.
 */
public final class SelfTest {
    /** Забороняє створення службового тестового класу. */
    private SelfTest() {
    }

    /**
     * Запускає всі перевірки та завершується помилкою при невідповідності.
     *
     * @param args аргументи командного рядка не використовуються
     */
    public static void main(String[] args) {
        testCartCalculations();
        testDiscountSubstitution();
        testCheckoutWithInjectedDependencies();
        System.out.println("Усі перевірки пройдено успішно.");
    }

    /** Перевіряє Creator та Information Expert у кошику. */
    private static void testCartCalculations() {
        ShoppingCart cart = new ShoppingCart();
        Product mouse = new PhysicalProduct(
                "P-TEST", "Миша", new BigDecimal("250.00"), 0.1);
        cart.addProduct(mouse, 2);
        cart.addProduct(mouse, 1);

        check(cart.getItems().size() == 1, "Однакові товари мають об'єднуватись");
        check(cart.getTotalQuantity() == 3, "Кількість має дорівнювати 3");
        check(cart.getSubtotal().compareTo(new BigDecimal("750.00")) == 0,
                "Сума кошика має дорівнювати 750.00");
    }

    /** Перевіряє взаємозамінність реалізацій політики знижок. */
    private static void testDiscountSubstitution() {
        BigDecimal subtotal = new BigDecimal("200.00");
        DiscountPolicy noDiscount = new NoDiscountPolicy();
        DiscountPolicy tenPercent = new PercentageDiscountPolicy(10);

        check(noDiscount.apply(subtotal).compareTo(new BigDecimal("200.00")) == 0,
                "Політика без знижки не повинна змінювати суму");
        check(tenPercent.apply(subtotal).compareTo(new BigDecimal("180.00")) == 0,
                "Знижка 10% має дати 180.00");
    }

    /** Перевіряє оформлення із залежностями, переданими через інтерфейси. */
    private static void testCheckoutWithInjectedDependencies() {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new PhysicalProduct(
                "P-CHECK", "Навушники", new BigDecimal("1000.00"), 0.3), 1);
        Customer customer = new Customer("C-CHECK", "Тест", "test@example.com");
        AtomicReference<BigDecimal> paidAmount = new AtomicReference<>();
        InMemoryOrderRepository repository = new InMemoryOrderRepository();

        CheckoutService service = new CheckoutService(
                new PercentageDiscountPolicy(20),
                (buyer, amount) -> paidAmount.set(amount),
                repository,
                (buyer, order) -> { });
        Order order = service.checkout(customer, cart);

        check(order.getTotal().compareTo(new BigDecimal("800.00")) == 0,
                "Остаточна сума має дорівнювати 800.00");
        check(paidAmount.get().compareTo(order.getTotal()) == 0,
                "До оплати має передаватись остаточна сума");
        check(repository.findAll().size() == 1, "Замовлення має бути збережене");
        check(cart.isEmpty(), "Після оформлення кошик має бути порожнім");
    }

    /**
     * Перевіряє умову та кидає помилку з поясненням.
     *
     * @param condition умова успіху
     * @param message повідомлення при помилці
     */
    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
