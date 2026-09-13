package shop;

/**
 * Демонстраційний клас, що показує роботу кошика інтернет-магазину
 * та застосування принципів GRASP у зібраній системі.
 */
public class Main {

    public static void main(String[] args) {
        // --- Підготовка предметної області ---
        Product laptop = new Product("P1", "Ноутбук Lenovo", 25000.0, "Електроніка");
        Product mouse = new Product("P2", "Бездротова миша", 450.0, "Електроніка");
        Product book = new Product("P3", "Книга \"Чисті архітектури\"", 380.0, "Книги");

        Customer customer = new Customer("C1", "Олена Ковальчук",
                "olena@example.com", "м. Чернівці, вул. Головна, 1");

        PriceCalculator priceCalculator = new PriceCalculator(); // Pure Fabrication / Indirection
        ShoppingCart cart = new ShoppingCart(priceCalculator);
        NotificationService notificationService = new NotificationService(); // Pure Fabrication

        CartController controller = new CartController(cart, customer, notificationService); // Controller

        // --- Сценарій використання ---
        controller.handleAddToCart(laptop, 1);
        controller.handleAddToCart(mouse, 2);
        controller.handleAddToCart(book, 1);
        controller.handleApplyPromoCode("STUDENT10");

        System.out.println("Сума кошика до оформлення: " + cart.calculateTotal() + " грн");

        // Polymorphism + Protected Variations: підставляємо будь-яку реалізацію
        PaymentProcessor payment = new CardPayment("**** **** **** 1234");
        Order order = controller.handleCheckout(payment);

        System.out.println("Результат: " + order);

        // Демонстрація іншого способу оплати без змін у Controller/Order:
        ShoppingCart secondCart = new ShoppingCart(priceCalculator);
        CartController secondController = new CartController(secondCart, customer, notificationService);
        secondController.handleAddToCart(mouse, 3);
        Order secondOrder = secondController.handleCheckout(new PayPalPayment("olena@example.com"));
        System.out.println("Результат: " + secondOrder);
    }
}
