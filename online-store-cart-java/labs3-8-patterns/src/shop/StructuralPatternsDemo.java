package shop;

import shop.adapter.LegacyWarehouseApi;
import shop.adapter.StockService;
import shop.adapter.WarehouseAdapter;
import shop.bridge.EmailChannel;
import shop.bridge.OrderPlacedNotification;
import shop.bridge.OrderShippedNotification;
import shop.bridge.SmsChannel;
import shop.composite.CatalogCategory;
import shop.composite.CatalogProduct;
import shop.decorator.BasicProductOffer;
import shop.decorator.ExpressDeliveryDecorator;
import shop.decorator.GiftWrapDecorator;
import shop.decorator.ProductOffer;
import shop.flyweight.ProductCard;
import shop.flyweight.ProductStyle;
import shop.flyweight.ProductStyleFactory;
import shop.proxy.ProductImageProxy;

/** Демонструє шість структурних шаблонів лабораторних робіт №6-8. */
public final class StructuralPatternsDemo {
    /** Забороняє створення службового класу демонстрації. */
    private StructuralPatternsDemo() { }

    /** Запускає приклади Adapter, Bridge, Composite, Flyweight, Decorator і Proxy. */
    public static void run() {
        System.out.println("\n=== 8. Adapter: сумісність із старим складським API ===");
        StockService stock = new WarehouseAdapter(new LegacyWarehouseApi());
        System.out.println("Товар доступний: " + stock.isAvailable("P-101", 2));
        stock.reserve("P-101", 2);

        System.out.println("\n=== 9. Bridge: тип сповіщення окремо від каналу ===");
        new OrderPlacedNotification(new EmailChannel()).notify("olena@example.com", "ORD-100");
        new OrderShippedNotification(new SmsChannel()).notify("+380501112233", "ORD-100");

        System.out.println("\n=== 10. Composite: дерево каталогу ===");
        CatalogCategory root = new CatalogCategory("Каталог");
        CatalogCategory electronics = new CatalogCategory("Електроніка");
        electronics.add(new CatalogProduct(new Product("P-201", "Навушники", "Електроніка",
                1599.0, "Bluetooth", 12)));
        electronics.add(new CatalogProduct(new Product("P-202", "Клавіатура", "Електроніка",
                1200.0, "Механічна", 8)));
        root.add(electronics);
        root.display(0);
        System.out.println("Сума каталогу: " + root.getTotalPrice() + " грн");

        System.out.println("\n=== 11. Flyweight: спільний стиль карток товарів ===");
        ProductStyleFactory styleFactory = new ProductStyleFactory();
        ProductStyle styleA = styleFactory.getStyle("Електроніка");
        ProductStyle styleB = styleFactory.getStyle("Електроніка");
        new ProductCard("P-201", "Навушники", 1599.0, styleA).display();
        new ProductCard("P-202", "Клавіатура", 1200.0, styleB).display();
        System.out.println("Стиль спільний: " + (styleA == styleB));

        System.out.println("\n=== 12. Decorator: додаткові послуги до товару ===");
        ProductOffer offer = new BasicProductOffer("Подарунковий набір", 1000.0);
        offer = new GiftWrapDecorator(offer, 80.0);
        offer = new ExpressDeliveryDecorator(offer, 120.0);
        System.out.println(offer.getDescription() + " = " + offer.getPrice() + " грн");

        System.out.println("\n=== 13. Proxy: ліниве завантаження фото товару ===");
        ProductImageProxy image = new ProductImageProxy("products/P-201.jpg");
        System.out.println("Фото завантажене до показу: " + image.isLoaded());
        image.display();
        image.display();
    }
}
