package ua.edu.shopcart.demo;

import java.math.BigDecimal;
import ua.edu.shopcart.application.CartCommands;
import ua.edu.shopcart.application.CartQueries;
import ua.edu.shopcart.controller.CartController;
import ua.edu.shopcart.domain.Customer;
import ua.edu.shopcart.domain.DigitalProduct;
import ua.edu.shopcart.domain.Order;
import ua.edu.shopcart.domain.Product;
import ua.edu.shopcart.domain.ShoppingCart;
import ua.edu.shopcart.payment.ConsolePaymentProcessor;
import ua.edu.shopcart.payment.ConsoleReceiptSender;
import ua.edu.shopcart.payment.PaymentProcessor;
import ua.edu.shopcart.payment.ReceiptSender;
import ua.edu.shopcart.pricing.DiscountPolicy;
import ua.edu.shopcart.pricing.NoDiscountPolicy;
import ua.edu.shopcart.pricing.PercentageDiscountPolicy;
import ua.edu.shopcart.repository.InMemoryOrderRepository;
import ua.edu.shopcart.repository.OrderRepository;
import ua.edu.shopcart.service.CheckoutService;
import ua.edu.shopcart.view.ConsoleCartView;

/**
 * Демонстраційний запуск лабораторної роботи №2 з принципів SOLID.
 */
public final class Lab2Demo {
    /** Забороняє створення службового класу запуску. */
    private Lab2Demo() {
    }

    /**
     * Показує взаємозамінність політик та ін'єкцію абстракцій.
     *
     * @param args аргументи командного рядка не використовуються
     */
    public static void main(String[] args) {
        System.out.println("=== ЛР2: принципи SOLID ===");

        BigDecimal exampleSubtotal = new BigDecimal("1000.00");
        printPolicyResult(new NoDiscountPolicy(), exampleSubtotal);
        printPolicyResult(new PercentageDiscountPolicy(15), exampleSubtotal);

        DiscountPolicy policy = new PercentageDiscountPolicy(15);
        PaymentProcessor payment = new ConsolePaymentProcessor();
        ReceiptSender receiptSender = new ConsoleReceiptSender();
        OrderRepository repository = new InMemoryOrderRepository();
        CheckoutService checkoutService = new CheckoutService(
                policy, payment, repository, receiptSender);
        CartController controller = new CartController(new ShoppingCart(), checkoutService);

        // ISP: різні клієнти бачать лише необхідну частину API.
        CartCommands commands = controller;
        CartQueries queries = controller;
        Product book = new DigitalProduct(
                "D-2", "Електронна книга", new BigDecimal("300.00"), "EPUB");
        commands.addProduct(book, 3);
        new ConsoleCartView(queries).showCart();

        Customer customer = new Customer("C-2", "Олена Коваль", "olena@example.com");
        Order order = controller.checkout(customer);
        System.out.println("Результат: " + order);
    }

    /**
     * Працює з будь-якою реалізацією контракту {@link DiscountPolicy}.
     *
     * @param policy взаємозамінна політика
     * @param subtotal тестова сума
     */
    private static void printPolicyResult(DiscountPolicy policy, BigDecimal subtotal) {
        System.out.printf("%s: %s -> %s грн%n",
                policy.getDescription(), subtotal, policy.apply(subtotal));
    }
}
