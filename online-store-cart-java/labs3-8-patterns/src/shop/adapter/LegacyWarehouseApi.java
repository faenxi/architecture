package shop.adapter;

/**
 * Імітація застарілого складського API з іншими назвами та одиницями.
 */
public final class LegacyWarehouseApi {
    /** Створює клієнт старого складського сервісу. */
    public LegacyWarehouseApi() { }

    /**
     * Старий API перевіряє залишок у штуках.
     *
     * @param sku складський код
     * @param units кількість одиниць
     * @return чи достатньо одиниць
     */
    public boolean hasUnits(String sku, int units) {
        System.out.println("[STUB] LegacyWarehouseApi.hasUnits(sku=" + sku + ", units=" + units + ")");
        return units > 0;
    }

    /**
     * Старий API створює резерв за складським кодом.
     *
     * @param sku складський код
     * @param units кількість одиниць
     */
    public void holdUnits(String sku, int units) {
        System.out.println("[STUB] LegacyWarehouseApi.holdUnits(sku=" + sku + ", units=" + units + ")");
    }
}
