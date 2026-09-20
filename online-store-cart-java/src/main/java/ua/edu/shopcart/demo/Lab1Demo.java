package ua.edu.shopcart.demo;

import java.math.BigDecimal;
import ua.edu.shopcart.controller.CartController;
import ua.edu.shopcart.domain.Customer;
import ua.edu.shopcart.domain.DigitalProduct;
import ua.edu.shopcart.domain.Order;
import ua.edu.shopcart.domain.PhysicalProduct;
import ua.edu.shopcart.domain.Product;
import ua.edu.shopcart.domain.ShoppingCart;
import ua.edu.shopcart.payment.ConsolePaymentProcessor;
import ua.edu.shopcart.payment.ConsoleReceiptSender;
import ua.edu.shopcart.pricing.DiscountPolicy;
import ua.edu.shopcart.pricing.PercentageDiscountPolicy;
import ua.edu.shopcart.repository.InMemoryOrderRepository;
import ua.edu.shopcart.repository.OrderRepository;
import ua.edu.shopcart.service.CheckoutService;
import ua.edu.shopcart.view.ConsoleCartView;

/**
 * Демонстраційний запуск лабораторної роботи №1 з принципів GRASP.
 */
public final class Lab1Demo {
    /** Забороняє створення службового класу запуску. */
    private Lab1Demo() {
    }

    /**
     * Створює кошик, додає товари та оформлює замовлення.
     *
     * @param args аргументи командного рядка не використовуються
     */
    public static void main(String[] args) {
        System.out.println("=== ЛР1: принципи GRASP ===");

        ShoppingCart cart = new ShoppingCart();
        DiscountPolicy discount = new PercentageDiscountPolicy(10);
        OrderRepository repository = new InMemoryOrderRepository();
        CheckoutService checkoutService = new CheckoutService(
                discount,
                new ConsolePaymentProcessor(),
                repository,
                new ConsoleReceiptSender());
        CartController controller = new CartController(cart, checkoutService);

        Product keyboard = new PhysicalProduct(
                "P-1", "Клавіатура", new BigDecimal("1200.00"), 0.75);
        Product course = new DigitalProduct(
                "D-1", "Java-мінікурс", new BigDecimal("500.00"), "PDF");
        Customer customer = new Customer("C-1", "Іван Петренко", "ivan@example.com");

        controller.addProduct(keyboard, 1);
        controller.addProduct(course, 2);
        new ConsoleCartView(controller).showCart();

        System.out.println("Політика: " + checkoutService.getDiscountDescription());
        Order order = controller.checkout(customer);
        System.out.println("Створено замовлення: " + order);
        System.out.println("Збережено замовлень: " + repository.findAll().size());
    }
}
