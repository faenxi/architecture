package shop.decorator;

import java.util.Objects;

/** Базовий Decorator, який делегує операції вкладеній пропозиції. */
public abstract class OfferDecorator implements ProductOffer {
    /** Об'єкт, поведінка якого розширюється. */
    protected final ProductOffer wrapped;

    /**
     * Створює декоратор навколо іншої пропозиції.
     *
     * @param wrapped декорований компонент
     */
    protected OfferDecorator(ProductOffer wrapped) {
        this.wrapped = Objects.requireNonNull(wrapped, "wrapped не може бути null");
    }
}
