package shop.bridge;

/** ConcreteImplementor каналу email у шаблоні Bridge. */
public final class EmailChannel implements MessageChannel {
    /** Створює канал електронної пошти. */
    public EmailChannel() { }

    /** {@inheritDoc} */
    @Override
    public void send(String recipient, String text) {
        System.out.println("[STUB] EmailChannel.send(to=" + recipient + ", text=" + text + ")");
    }
}
