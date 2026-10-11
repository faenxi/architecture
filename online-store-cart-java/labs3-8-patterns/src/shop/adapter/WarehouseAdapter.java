package shop.adapter;

import java.util.Objects;

/**
 * Adapter перетворює сучасний {@link StockService} на виклики старого API.
 */
public final class WarehouseAdapter implements StockService {
    /** Старий сервіс складу, який адаптується. */
    private final LegacyWarehouseApi legacyApi;

    /**
     * Створює адаптер навколо наявного складського API.
     *
     * @param legacyApi старий API
     */
    public WarehouseAdapter(LegacyWarehouseApi legacyApi) {
        this.legacyApi = Objects.requireNonNull(legacyApi, "legacyApi не може бути null");
    }

    /** {@inheritDoc} */
    @Override
    public boolean isAvailable(String productId, int quantity) {
        return legacyApi.hasUnits(productId, quantity);
    }

    /** {@inheritDoc} */
    @Override
    public void reserve(String productId, int quantity) {
        legacyApi.holdUnits(productId, quantity);
    }
}
