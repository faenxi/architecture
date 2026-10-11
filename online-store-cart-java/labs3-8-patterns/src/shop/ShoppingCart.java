package shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Клас {@code ShoppingCart} реалізує шаблон <b>Singleton</b>: у межах
 * однієї сесії покупця (в даній навчальній реалізації — в межах усього
 * застосунку) повинен існувати рівно один екземпляр кошика, до якого
 * звертаються всі частини системи (сторінка каталогу, сторінка
 * оформлення замовлення тощо), щоб уникнути неузгодженості стану
 * (наприклад, двох різних "кошиків" з різним вмістом одночасно).
 * <p>
 * Реалізація використовує <i>double-checked locking</i>, щоб гарантувати
 * потокобезпечне лінивe створення єдиного екземпляра без зайвої
 * синхронізації при кожному зверненні.
 */
public final class ShoppingCart {

    /** Єдиний екземпляр класу (volatile — для коректної видимості між потоками). */
    private static volatile ShoppingCart instance;

    /** Список позицій у кошику. */
    private final List<CartItem> items = new ArrayList<>();

    /** Застосована знижка (якщо є). */
    private Discount appliedDiscount;

    /**
     * Приватний конструктор — ключовий елемент Singleton: забороняє
     * створення екземплярів класу ззовні через {@code new ShoppingCart()}.
     */
    private ShoppingCart() {
        System.out.println("[STUB] ShoppingCart: створено єдиний екземпляр кошика (hash="
                + System.identityHashCode(this) + ")");
    }

    /**
     * Повертає єдиний екземпляр кошика, створюючи його за потреби.
     * Метод потокобезпечний завдяки double-checked locking.
     *
     * @return єдиний екземпляр {@link ShoppingCart}
     */
    public static ShoppingCart getInstance() {
        ShoppingCart result = instance;
        if (result == null) {
            synchronized (ShoppingCart.class) {
                result = instance;
                if (result == null) {
                    instance = result = new ShoppingCart();
                }
            }
        }
        return result;
    }

    /**
     * Додає товар у кошик. Якщо товар уже є в кошику — збільшує кількість.
     *
     * @param product  товар
     * @param quantity кількість
     */
    public void addProduct(Product product, int quantity) {
        Optional<CartItem> existing = items.stream()
                .filter(i -> i.getProduct().equals(product))
                .findFirst();
        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + quantity);
        } else {
            items.add(new CartItem(product, quantity));
        }
        System.out.println("[STUB] ShoppingCart.addProduct: додано '" + product.getName()
                + "' x" + quantity);
    }

    /**
     * Видаляє товар із кошика.
     *
     * @param product товар для видалення
     */
    public void removeProduct(Product product) {
        items.removeIf(i -> i.getProduct().equals(product));
        System.out.println("[STUB] ShoppingCart.removeProduct: видалено '" + product.getName() + "'");
    }

    /**
     * Застосовує знижку до кошика.
     *
     * @param discount знижка
     */
    public void applyDiscount(Discount discount) {
        this.appliedDiscount = discount;
        System.out.println("[STUB] ShoppingCart.applyDiscount: застосовано промокод '" + discount.getCode() + "'");
    }

    /**
     * Обчислює підсумкову вартість кошика з урахуванням знижки.
     *
     * @return підсумкова сума до сплати
     */
    public double getTotal() {
        double total = items.stream().mapToDouble(CartItem::getSubtotal).sum();
        if (appliedDiscount != null) {
            total -= appliedDiscount.applyTo(total);
        }
        return total;
    }

    /**
     * Формує замовлення на основі поточного вмісту кошика та очищає кошик.
     * Бізнес-логіка обробки платежу замінена заглушкою.
     *
     * @param customer покупець, що оформлює замовлення
     * @return сформоване замовлення {@link Order}
     */
    public Order checkout(Customer customer) {
        double total = getTotal();
        System.out.println("[STUB] ShoppingCart.checkout: обробка платежу на суму " + total
                + " для покупця '" + customer.getName() + "'");
        Order order = new Order(customer, new ArrayList<>(items), total);
        clear();
        return order;
    }

    /** Повністю очищає кошик (позиції та знижку). */
    public void clear() {
        items.clear();
        appliedDiscount = null;
        System.out.println("[STUB] ShoppingCart.clear: кошик очищено");
    }

    /**
     * Повертає незмінний знімок позицій кошика.
     *
     * @return позиції кошика
     */
    public List<CartItem> getItems() {
        return List.copyOf(items);
    }
}
