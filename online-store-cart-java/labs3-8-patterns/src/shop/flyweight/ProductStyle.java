package shop.flyweight;

/** Intrinsic незмінний стан товарної картки, спільний між багатьма товарами. */
public final class ProductStyle {
    /** Колірна тема картки. */
    private final String theme;
    /** Назва іконки категорії. */
    private final String icon;

    /**
     * Створює спільний стиль карток.
     *
     * @param theme назва теми
     * @param icon іконка категорії
     */
    ProductStyle(String theme, String icon) {
        this.theme = theme;
        this.icon = icon;
    }

    /**
     * Повертає тему оформлення картки.
     *
     * @return тема оформлення
     */
    public String getTheme() { return theme; }
    /**
     * Повертає іконку категорії.
     *
     * @return іконка категорії
     */
    public String getIcon() { return icon; }
}
