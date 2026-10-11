package shop.bridge;

/** ConcreteImplementor SMS-каналу у шаблоні Bridge. */
public final class SmsChannel implements MessageChannel {
    /** Створює SMS-канал. */
    public SmsChannel() { }

    /** {@inheritDoc} */
    @Override
    public void send(String recipient, String text) {
        System.out.println("[STUB] SmsChannel.send(to=" + recipient + ", text=" + text + ")");
    }
}
