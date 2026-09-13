package shop;

/**
 * Клієнт інтернет-магазину.
 * <p>
 * Реалізує принцип <b>Creator</b>: саме Customer ініціює створення
 * замовлення {@link Order}, оскільки клієнт логічно "володіє" своїми
 * замовленнями (агрегує їх) і містить дані, необхідні для їх створення
 * (ім'я, адресу доставки).
 */
public class Customer {

    private final String id;
    private final String name;
    private final String email;
    private final String deliveryAddress;

    /**
     * Створює клієнта.
     *
     * @param id              ідентифікатор клієнта
     * @param name            ім'я клієнта
     * @param email           електронна пошта клієнта
     * @param deliveryAddress адреса доставки за замовчуванням
     */
    public Customer(String id, String name, String email, String deliveryAddress) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * Створює нове замовлення на основі кошика.
     * Реалізує GRASP Creator: Customer створює Order, бо саме
     * клієнт ініціює оформлення і "агрегує" свої замовлення.
     *
     * @param cart кошик, на основі якого формується замовлення
     * @return нове замовлення у стані NEW
     */
    public Order createOrder(ShoppingCart cart) {
        String orderId = "ORD-" + System.currentTimeMillis();
        return new Order(orderId, this, cart.getItemsSnapshot(), cart.calculateTotal());
    }

    @Override
    public String toString() {
        return name + " <" + email + ">";
    }
}
