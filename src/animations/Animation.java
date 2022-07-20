package animations;
import biuoop.DrawSurface;

/**
 * The animation interface is responsible for drawing an animation, and deciding when
 * should an animation be drawn.
 */
public interface Animation {
    /**
     * The doOneFrame method is in charge of the logic of the animation.
     * @param d the drawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * The shouldStop method is in charge of the stopping condition of the animation.
     * @return whether the animation should stop.
     */
    boolean shouldStop();
}
