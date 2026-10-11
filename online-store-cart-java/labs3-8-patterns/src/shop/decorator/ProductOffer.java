package shop.decorator;

/** Component, що описує комерційну пропозицію для товару. */
public interface ProductOffer {
    /**
     * Повертає опис усіх послуг і товару.
     *
     * @return опис пропозиції
     */
    String getDescription();
    /**
     * Обчислює загальну вартість пропозиції.
     *
     * @return повна ціна пропозиції
     */
    double getPrice();
}
