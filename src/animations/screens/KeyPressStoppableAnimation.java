package animations.screens;

import animations.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Decorator-class that wraps an existing animation and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param sensor a keyboard sensor.
     * @param key a key to press for stopping the animation.
     * @param animation the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.key = key;
        this.keyboard = sensor;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);

        if (this.keyboard.isPressed(key) && !isAlreadyPressed) {
            this.stop = true;
        } else if (this.keyboard.isPressed(key)) {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
