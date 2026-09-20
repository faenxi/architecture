package ua.edu.shopcart.domain;

import java.math.BigDecimal;

/**
 * Цифровий товар, який покупець отримує електронною поштою.
 */
public final class DigitalProduct extends Product {
    /** Формат файла, який отримає покупець. */
    private final String fileFormat;

    /**
     * Створює цифровий товар.
     *
     * @param id ідентифікатор товару
     * @param name назва товару
     * @param unitPrice ціна одиниці
     * @param fileFormat непорожній формат файла
     */
    public DigitalProduct(String id, String name, BigDecimal unitPrice, String fileFormat) {
        super(id, name, unitPrice);
        if (fileFormat == null || fileFormat.isBlank()) {
            throw new IllegalArgumentException("fileFormat не може бути порожнім");
        }
        this.fileFormat = fileFormat;
    }

    /**
     * Повертає формат цифрового файла.
     *
     * @return формат файла
     */
    public String getFileFormat() {
        return fileFormat;
    }

    /**
     * Повертає спосіб доставки цифрового товару.
     *
     * @return опис електронної доставки
     */
    @Override
    public String getDeliveryInfo() {
        return "Посилання на файл " + fileFormat + " буде надіслано електронною поштою";
    }
}
