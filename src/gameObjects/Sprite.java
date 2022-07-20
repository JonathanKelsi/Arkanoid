package gameObjects;

import biuoop.DrawSurface;

/**
 * The Sprites.Sprite Interface will be used by sprites - in game objects.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d a drawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
