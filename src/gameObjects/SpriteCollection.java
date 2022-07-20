package gameObjects;

import java.util.ArrayList;

import biuoop.DrawSurface;

/**
 * The class Sprites.SpriteCollection provides an arrayList of sprites,
 * as well as a few constructors and methods for handling it.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites = new ArrayList<>();

    /**
     * add the given sprite to the list.
     * @param s a new sprite
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * remove the given sprite from the list.
     * @param s a new sprite
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        if (sprites.isEmpty()) {
            return;
        }

        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d
     */
    public void drawAllOn(DrawSurface d) {
        if (sprites.isEmpty()) {
            return;
        }

        for (Sprite s: sprites) {
            s.drawOn(d);
        }
    }
}
