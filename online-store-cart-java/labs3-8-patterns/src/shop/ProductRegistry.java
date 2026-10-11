package shop;

import java.util.HashMap;
import java.util.Map;

/**
 * Клас {@code ProductRegistry} реалізує варіацію шаблону <b>Prototype</b>,
 * відому як <i>Prototype Registry (реєстр прототипів)</i>.
 * <p>
 * Роль у структурі шаблону:
 * <ul>
 *     <li>зберігає набір готових "еталонних" товарів (прототипів) для
 *     кожної категорії каталогу;</li>
 *     <li>надає клієнтському коду єдину точку доступу — метод
 *     {@link #createProduct(String, String, String, double)} — який
 *     повертає <b>клон</b> прототипу, а не посилання на сам прототип,
 *     завдяки чому зміни в одному клоні не впливають на еталон і на
 *     інші клони.</li>
 * </ul>
 * Це дозволяє швидко "штампувати" нові товари на основі шаблону
 * категорії (наприклад, шаблон "Смартфон" з типовими характеристиками),
 * не описуючи щоразу всі поля вручну.
 */
public class ProductRegistry {

    /** Створює порожній реєстр прототипів товарів. */
    public ProductRegistry() {
    }

    /** Мапа "категорія товару" -> "прототип товару цієї категорії". */
    private final Map<String, Product> prototypes = new HashMap<>();

    /**
     * Реєструє новий прототип товару для певної категорії.
     *
     * @param category      категорія
     * @param prototype     екземпляр-прототип
     */
    public void registerPrototype(String category, Product prototype) {
        prototypes.put(category, prototype);
        System.out.println("[STUB] ProductRegistry.registerPrototype: зареєстровано прототип для категорії '"
                + category + "'");
    }

    /**
     * Створює новий товар шляхом клонування зареєстрованого прототипу
     * відповідної категорії та підстановки індивідуальних значень.
     *
     * @param category категорія, прототип якої слід клонувати
     * @param id       ідентифікатор нового товару
     * @param name     назва нового товару
     * @param price    ціна нового товару
     * @return новий незалежний об'єкт {@link Product}
     * @throws IllegalArgumentException якщо прототип для категорії не зареєстровано
     */
    public Product createProduct(String category, String id, String name, double price) {
        Product prototype = prototypes.get(category);
        if (prototype == null) {
            throw new IllegalArgumentException("Прототип для категорії '" + category + "' не знайдено");
        }
        Product clone = prototype.clone();
        clone.setId(id);
        clone.setName(name);
        clone.setPrice(price);
        return clone;
    }
}
