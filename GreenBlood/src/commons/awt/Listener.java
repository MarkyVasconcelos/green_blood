package commons.awt;

/**
 * Generic Listener class, consumer should define which events this will fire on(T)
 * Implementer should consume the event and notify if it dids (trough boolean return)
 * 
 * @author marcos.vasconcelos
 * @param <T> The Object type that will be fired
 */
public interface Listener<T> {
    /**
     * @param t the Object of the event itself
     */
    public void on(T t);
}
