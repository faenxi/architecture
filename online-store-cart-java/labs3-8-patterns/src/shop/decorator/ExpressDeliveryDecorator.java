package shop.decorator;

/** Decorator незалежно додає до пропозиції послугу експрес-доставки. */
public final class ExpressDeliveryDecorator extends OfferDecorator {
    /** Вартість експрес-доставки. */
    private final double deliveryPrice;

    /**
     * Додає послугу швидкої доставки.
     *
     * @param wrapped початкова пропозиція
     * @param deliveryPrice вартість доставки
     */
    public ExpressDeliveryDecorator(ProductOffer wrapped, double deliveryPrice) {
        super(wrapped);
        if (deliveryPrice < 0) throw new IllegalArgumentException("Вартість доставки не може бути від'ємною");
        this.deliveryPrice = deliveryPrice;
    }

    /** {@inheritDoc} */
    @Override public String getDescription() { return wrapped.getDescription() + " + експрес-доставка"; }
    /** {@inheritDoc} */
    @Override public double getPrice() {
        double result = wrapped.getPrice() + deliveryPrice;
        System.out.println("[STUB] ExpressDeliveryDecorator.getPrice(base=" + wrapped.getPrice()
                + ", delivery=" + deliveryPrice + ", total=" + result + ")");
        return result;
    }
}
