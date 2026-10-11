package shop.decorator;

/** ConcreteComponent з базовою назвою й ціною товару. */
public final class BasicProductOffer implements ProductOffer {
    /** Назва товару. */
    private final String name;
    /** Базова ціна. */
    private final double price;

    /**
     * Створює базову пропозицію товару.
     *
     * @param name назва
     * @param price ціна
     */
    public BasicProductOffer(String name, double price) {
        if (name == null || name.isBlank() || price < 0) throw new IllegalArgumentException("Некоректна пропозиція");
        this.name = name;
        this.price = price;
    }

    /** @return назва товару */
    @Override public String getDescription() { return name; }
    /** @return базова ціна */
    @Override public double getPrice() { return price; }
}
