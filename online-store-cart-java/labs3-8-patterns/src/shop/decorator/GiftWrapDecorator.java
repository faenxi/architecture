package shop.decorator;

/** Decorator додає подарункове пакування до пропозиції товару. */
public final class GiftWrapDecorator extends OfferDecorator {
    /** Вартість подарункового пакування. */
    private final double wrapPrice;

    /**
     * Додає послугу пакування.
     *
     * @param wrapped початкова пропозиція
     * @param wrapPrice вартість пакування
     */
    public GiftWrapDecorator(ProductOffer wrapped, double wrapPrice) {
        super(wrapped);
        if (wrapPrice < 0) throw new IllegalArgumentException("Вартість пакування не може бути від'ємною");
        this.wrapPrice = wrapPrice;
    }

    /** {@inheritDoc} */
    @Override public String getDescription() { return wrapped.getDescription() + " + подарункове пакування"; }
    /** {@inheritDoc} */
    @Override public double getPrice() {
        double result = wrapped.getPrice() + wrapPrice;
        System.out.println("[STUB] GiftWrapDecorator.getPrice(base=" + wrapped.getPrice()
                + ", wrap=" + wrapPrice + ", total=" + result + ")");
        return result;
    }
}
