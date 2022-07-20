package listeners;

/**
 * A HitNotifier notifies a HitListener when an event occurs.
 */
public interface HitNotifier {
    /**
     *  Add hl as a listener to hit events.
     * @param hl a Listeners.HitListener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl a Listeners.HitListener.
     */
    void removeHitListener(HitListener hl);
}
