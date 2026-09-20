package ua.edu.shopcart.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import ua.edu.shopcart.application.CartCommands;
import ua.edu.shopcart.application.CartQueries;
import ua.edu.shopcart.domain.CartItem;
import ua.edu.shopcart.domain.Customer;
import ua.edu.shopcart.domain.Order;
import ua.edu.shopcart.domain.Product;
import ua.edu.shopcart.domain.ShoppingCart;
import ua.edu.shopcart.service.CheckoutService;

/**
 * GRASP Controller для зовнішніх подій роботи з кошиком.
 *
 * <p>Клас не містить розрахунків: він координує предметний об'єкт
 * {@link ShoppingCart} і сервіс оформлення.</p>
 */
public final class CartController implements CartCommands, CartQueries {
    /** Кошик, яким керує контролер. */
    private final ShoppingCart cart;

    /** Сервіс оформлення замовлення. */
    private final CheckoutService checkoutService;

    /**
     * Створює контролер.
     *
     * @param cart кошик користувача
     * @param checkoutService сервіс оформлення
     */
    public CartController(ShoppingCart cart, CheckoutService checkoutService) {
        this.cart = Objects.requireNonNull(cart, "cart не може бути null");
        this.checkoutService = Objects.requireNonNull(
                checkoutService, "checkoutService не може бути null");
    }

    /**
     * Передає команду додавання предметному об'єкту.
     *
     * @param product товар
     * @param quantity кількість
     */
    @Override
    public void addProduct(Product product, int quantity) {
        cart.addProduct(product, quantity);
    }

    /**
     * Передає команду видалення предметному об'єкту.
     *
     * @param productId ідентифікатор товару
     * @return результат видалення
     */
    @Override
    public boolean removeProduct(String productId) {
        return cart.removeProduct(productId);
    }

    /**
     * Делегує запит списку рядків кошику.
     *
     * @return рядки кошика
     */
    @Override
    public List<CartItem> getItems() {
        return cart.getItems();
    }

    /**
     * Делегує розрахунок суми інформаційному експерту.
     *
     * @return сума до знижки
     */
    @Override
    public BigDecimal getSubtotal() {
        return cart.getSubtotal();
    }

    /**
     * Делегує підрахунок кількості кошику.
     *
     * @return кількість одиниць товару
     */
    @Override
    public int getTotalQuantity() {
        return cart.getTotalQuantity();
    }

    /**
     * Обробляє зовнішню подію оформлення замовлення.
     *
     * @param customer покупець
     * @return створене замовлення
     */
    public Order checkout(Customer customer) {
        return checkoutService.checkout(customer, cart);
    }
}
