package shop.flyweight;

import java.util.HashMap;
import java.util.Map;

/** FlyweightFactory зберігає й повторно видає спільні стилі карток. */
public final class ProductStyleFactory {
    /** Кеш стилів за категорією. */
    private final Map<String, ProductStyle> styles = new HashMap<>();

    /** Створює порожню фабрику спільних стилів. */
    public ProductStyleFactory() { }

    /**
     * Повертає наявний стиль або створює його для категорії.
     *
     * @param category категорія товару
     * @return спільний Flyweight
     */
    public synchronized ProductStyle getStyle(String category) {
        if (category == null || category.isBlank()) throw new IllegalArgumentException("Категорія обов'язкова");
        ProductStyle style = styles.computeIfAbsent(category,
                key -> new ProductStyle(themeFor(key), iconFor(key)));
        System.out.println("[FLYWEIGHT] style(category=" + category + ", cachedStyles=" + styles.size() + ")");
        return style;
    }

    /**
     * Повертає кількість уже створених внутрішніх стилів.
     *
     * @return кількість спільних стилів у кеші
     */
    public synchronized int getStyleCount() { return styles.size(); }

    /** Визначає назву теми категорії. @param category назва категорії @return тема */
    private static String themeFor(String category) {
        return switch (category.toLowerCase()) {
            case "електроніка" -> "blue";
            case "одяг" -> "green";
            default -> "neutral";
        };
    }

    /** Визначає символ-іконку категорії. @param category назва категорії @return іконка */
    private static String iconFor(String category) {
        return switch (category.toLowerCase()) {
            case "електроніка" -> "[E]";
            case "одяг" -> "[C]";
            default -> "[P]";
        };
    }
}
