package shop;

/**
 * Клас {@code Customer} описує покупця інтернет-магазину.
 */
public class Customer {

    private String id;
    private String name;
    private String email;

    /**
     * Створює покупця.
     *
     * @param id ідентифікатор
     * @param name ім'я
     * @param email електронна адреса
     */
    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Надсилає покупцю сповіщення (наприклад, підтвердження замовлення).
     * Реальна відправка листа замінена заглушкою.
     *
     * @param message текст повідомлення
     */
    public void sendNotification(String message) {
        System.out.println("[STUB] Customer.sendNotification: лист до '" + email + "': " + message);
    }

    /**
     * Повертає ідентифікатор покупця.
     *
     * @return ідентифікатор покупця
     */
    public String getId() { return id; }

    /**
     * Повертає ім'я покупця.
     *
     * @return ім'я покупця
     */
    public String getName() { return name; }

    /**
     * Повертає електронну адресу покупця.
     *
     * @return електронна адреса покупця
     */
    public String getEmail() { return email; }
}
