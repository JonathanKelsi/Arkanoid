package indicators;

import other.Counter;
import levels.GameLevel;
import gameObjects.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A ScoreIndicator indicates the score of a current game.
 */
public class ScoreIndicator implements Sprite {
    private static final int FONT_SIZE = 14;
    private Counter score;
    private int x;
    private int y;

    /**
     * Constructor.
     * @param x x value
     * @param y y value
     * @param score score counter
     */
    public ScoreIndicator(int x, int y, Counter score) {
        this.x = x;
        this.y = y;
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //align the text at the center - with consideration with the number of digits
        //in the score itself, and the letters in the word "Score";
        int n = ((int) (Math.log10(score.getValue() + 1) + 1) + 7) * 3;

        d.setColor(Color.WHITE);
        d.drawText(x - n, y, "Score: " + this.score.getValue(), FONT_SIZE);
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
