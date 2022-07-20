package indicators;

import levels.GameLevel;
import gameObjects.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A ScoreIndicator indicates the score of a current game.
 */
public class NameIndicator implements Sprite {
    private static final int FONT_SIZE = 14;
    private String name;
    private int x;
    private int y;

    /**
     * Constructor.
     * @param x x value
     * @param y y value
     * @param name a name
     */
    public NameIndicator(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //align the text at the center
        int n = 12 * 3;

        d.setColor(Color.WHITE);
        d.drawText(x - n, y, "Level name: " + this.name, FONT_SIZE);
    }

    @Override
    public void timePassed() {
        //does nothing
    }

    /**
     * Add the scoreIndicator to a game.
     * @param g a game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
