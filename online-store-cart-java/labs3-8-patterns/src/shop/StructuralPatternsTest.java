package shop;

import shop.adapter.LegacyWarehouseApi;
import shop.adapter.StockService;
import shop.adapter.WarehouseAdapter;
import shop.composite.CatalogCategory;
import shop.composite.CatalogProduct;
import shop.decorator.BasicProductOffer;
import shop.decorator.ExpressDeliveryDecorator;
import shop.decorator.GiftWrapDecorator;
import shop.decorator.ProductOffer;
import shop.flyweight.ProductStyleFactory;
import shop.proxy.ProductImageProxy;

/** Автоматизовані перевірки структурних шаблонів ЛР №6-8. */
public final class StructuralPatternsTest {
    /** Забороняє створення службового тестового класу. */
    private StructuralPatternsTest() { }

    /**
     * Запускає перевірки всіх шести структурних шаблонів.
     *
     * @param args аргументи командного рядка не використовуються
     */
    public static void main(String[] args) {
        testAdapter();
        testComposite();
        testFlyweight();
        testDecorator();
        testProxy();
        System.out.println("Усі перевірки структурних шаблонів ЛР №6-8 пройдено успішно.");
    }

    /** Перевіряє, що Adapter відповідає цільовому інтерфейсу. */
    private static void testAdapter() {
        StockService adapter = new WarehouseAdapter(new LegacyWarehouseApi());
        check(adapter.isAvailable("SKU-1", 1), "Adapter має передати перевірку старому API");
        adapter.reserve("SKU-1", 1);
    }

    /** Перевіряє підрахунок ціни в Composite дереві. */
    private static void testComposite() {
        CatalogCategory category = new CatalogCategory("Тестова категорія");
        category.add(new CatalogProduct(new Product("T1", "Товар 1", "Тест", 10.0, "", 1)));
        category.add(new CatalogProduct(new Product("T2", "Товар 2", "Тест", 15.0, "", 1)));
        check(category.getChildCount() == 2, "Composite має містити два компоненти");
        check(category.getTotalPrice() == 25.0, "Composite має скласти ціни листків");
    }

    /** Перевіряє повторне використання Flyweight для однієї категорії. */
    private static void testFlyweight() {
        ProductStyleFactory factory = new ProductStyleFactory();
        check(factory.getStyle("Електроніка") == factory.getStyle("Електроніка"),
                "Flyweight для категорії має бути спільним");
        check(factory.getStyleCount() == 1, "Для категорії створюється один стиль");
    }

    /** Перевіряє складання вартості кількох Decorator. */
    private static void testDecorator() {
        ProductOffer offer = new ExpressDeliveryDecorator(
                new GiftWrapDecorator(new BasicProductOffer("Товар", 100.0), 10.0), 20.0);
        check(offer.getPrice() == 130.0, "Декоратори мають послідовно додати послуги");
        check(offer.getDescription().contains("подарункове пакування"), "Опис має містити пакування");
        check(offer.getDescription().contains("експрес-доставка"), "Опис має містити доставку");
    }

    /** Перевіряє ліниве створення та одноразове завантаження Proxy. */
    private static void testProxy() {
        ProductImageProxy proxy = new ProductImageProxy("test-image.jpg");
        check(!proxy.isLoaded(), "Proxy не має завантажувати ресурс під час створення");
        proxy.display();
        check(proxy.isLoaded(), "Proxy має завантажити ресурс під час першого показу");
        proxy.display();
        check(proxy.isLoaded(), "Повторний показ має використати завантажений ресурс");
    }

    /** Перевіряє умову й кидає AssertionError із поясненням. */
    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
