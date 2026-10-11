package shop.adapter;

/** Цільовий інтерфейс перевірки й резервування залишків магазину. */
public interface StockService {
    /**
     * Перевіряє доступну кількість товару.
     *
     * @param productId ідентифікатор товару
     * @param quantity потрібна кількість
     * @return {@code true}, якщо товару достатньо
     */
    boolean isAvailable(String productId, int quantity);

    /**
     * Резервує товар для оформлення замовлення.
     *
     * @param productId ідентифікатор товару
     * @param quantity кількість для резерву
     */
    void reserve(String productId, int quantity);
}
