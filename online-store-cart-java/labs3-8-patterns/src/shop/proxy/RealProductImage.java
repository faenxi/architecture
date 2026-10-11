package shop.proxy;

/** RealSubject імітує завантаження зображення з віддаленого сховища. */
public final class RealProductImage implements ProductImage {
    /** Ідентифікатор ресурсу зображення. */
    private final String imagePath;

    /**
     * Створює реальний об'єкт зображення.
     *
     * @param imagePath шлях або ключ зображення
     */
    public RealProductImage(String imagePath) { this.imagePath = imagePath; }

    /** Імітує дороге завантаження та показ ресурсу. */
    @Override
    public void display() {
        System.out.println("[STUB] RealProductImage.display(loadFromStorage=" + imagePath + ")");
    }
}
