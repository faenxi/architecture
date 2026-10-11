package shop.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Composite дерева каталогу, що містить товари або вкладені категорії. */
public final class CatalogCategory implements CatalogComponent {
    /** Назва категорії. */
    private final String name;

    /** Дочірні категорії та товари. */
    private final List<CatalogComponent> children = new ArrayList<>();

    /**
     * Створює порожню категорію.
     *
     * @param name назва категорії
     */
    public CatalogCategory(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Назва не може бути порожньою");
        this.name = name;
    }

    /**
     * Додає товар або вкладену категорію.
     *
     * @param child компонент каталогу
     */
    public void add(CatalogComponent child) {
        children.add(Objects.requireNonNull(child, "child не може бути null"));
        System.out.println("[COMPOSITE] " + name + ".add(" + child.getClass().getSimpleName() + ")");
    }

    /**
     * Видаляє компонент каталогу.
     *
     * @param child компонент для видалення
     * @return чи було його знайдено й видалено
     */
    public boolean remove(CatalogComponent child) { return children.remove(child); }

    /**
     * Повертає кількість безпосередньо вкладених товарів і категорій.
     *
     * @return кількість дочірніх елементів
     */
    public int getChildCount() { return children.size(); }

    /** {@inheritDoc} */
    @Override
    public void display(int indent) {
        System.out.println(" ".repeat(Math.max(0, indent)) + "+ " + name);
        for (CatalogComponent child : children) child.display(indent + 2);
    }

    /** {@inheritDoc} */
    @Override
    public double getTotalPrice() {
        return children.stream().mapToDouble(CatalogComponent::getTotalPrice).sum();
    }
}
