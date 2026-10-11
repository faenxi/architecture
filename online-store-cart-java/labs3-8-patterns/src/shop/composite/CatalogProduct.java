package shop.composite;

import java.util.Objects;
import shop.Product;

/** Leaf дерева Composite, що представляє товар каталогу. */
public final class CatalogProduct implements CatalogComponent {
    /** Предметний товар. */
    private final Product product;

    /**
     * Створює лист дерева з товару.
     *
     * @param product товар магазину
     */
    public CatalogProduct(Product product) {
        this.product = Objects.requireNonNull(product, "product не може бути null");
    }

    /** {@inheritDoc} */
    @Override
    public void display(int indent) {
        System.out.println(" ".repeat(Math.max(0, indent)) + "- "
                + product.getName() + ": " + product.getPrice() + " грн");
    }

    /** {@inheritDoc} */
    @Override
    public double getTotalPrice() { return product.getPrice(); }
}
