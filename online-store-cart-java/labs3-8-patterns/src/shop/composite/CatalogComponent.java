package shop.composite;

/** Component спільного дерева каталогу: категорія або товар. */
public interface CatalogComponent {
    /**
     * Показує елемент дерева з відступом.
     *
     * @param indent глибина рівня дерева
     */
    void display(int indent);

    /**
     * Обчислює суму цін усіх товарів у цьому елементі.
     *
     * @return сума цін
     */
    double getTotalPrice();
}
