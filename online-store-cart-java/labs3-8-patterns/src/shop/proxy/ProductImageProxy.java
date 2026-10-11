package shop.proxy;

import java.util.Objects;

/**
 * Virtual Proxy створює реальне зображення лише при першому показі.
 */
public final class ProductImageProxy implements ProductImage {
    /** Шлях ресурсу, відомий до завантаження. */
    private final String imagePath;
    /** Ліниво створюваний RealSubject. */
    private RealProductImage realImage;

    /**
     * Створює легкий проксі без завантаження зображення.
     *
     * @param imagePath шлях або ключ зображення
     */
    public ProductImageProxy(String imagePath) {
        if (imagePath == null || imagePath.isBlank()) throw new IllegalArgumentException("Шлях зображення обов'язковий");
        this.imagePath = imagePath;
    }

    /**
     * Завантажує реальне зображення один раз і делегує показ.
     */
    @Override
    public synchronized void display() {
        if (realImage == null) {
            System.out.println("[PROXY] lazy load image=" + imagePath);
            realImage = new RealProductImage(imagePath);
        }
        realImage.display();
    }

    /**
     * Перевіряє, чи створене й завантажене реальне зображення.
     *
     * @return чи було реальне зображення вже створено
     */
    public synchronized boolean isLoaded() { return realImage != null; }
}
