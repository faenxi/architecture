package shop;

import java.util.List;

/**
 * Замовлення, сформоване клієнтом на основі кошика.
 * <p>
 * Реалізує <b>Information Expert</b>: Order має всі дані (клієнт,
 * рядки, сума), потрібні для власного підтвердження/скасування,
 * тому саме він відповідає за зміну свого стану.
 */
public class Order {

    private final String id;
    private final Customer customer;
    private final List<CartItem> items;
    private final double totalAmount;
    private OrderStatus status;
    private PaymentProcessor paymentProcessor;

    public Order(String id, Customer customer, List<CartItem> items, double totalAmount) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = OrderStatus.NEW;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Призначає спосіб оплати. Order працює лише з інтерфейсом
     * {@link PaymentProcessor}, не знаючи про конкретну реалізацію
     * (Protected Variations, Low Coupling).
     */
    public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    /**
     * Підтверджує замовлення: викликає оплату через призначений
     * {@link PaymentProcessor} і, у разі успіху, переводить
     * замовлення у стан CONFIRMED.
     *
     * @return true, якщо замовлення підтверджено
     */
    public boolean confirm() {
        if (paymentProcessor == null) {
            throw new IllegalStateException("Спосіб оплати не призначено");
        }
        boolean paid = paymentProcessor.pay(totalAmount);
        if (paid) {
            this.status = OrderStatus.CONFIRMED;
        }
        return paid;
    }

    /** Скасовує замовлення. */
    public void cancel() {
        this.status = OrderStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return "Order{" + id + ", клієнт=" + customer.getName()
                + ", сума=" + totalAmount + ", статус=" + status + "}";
    }
}
