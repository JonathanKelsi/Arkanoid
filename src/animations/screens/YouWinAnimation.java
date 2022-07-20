package animations.screens;

import animations.Animation;
import biuoop.DrawSurface;
import other.Counter;
import java.awt.Color;

/**
 * The YouWinAnimation class is in charge of letting the player know he won.
 */
public class YouWinAnimation implements Animation {
    private Counter score;

    /**
     * Constructor.
     * @param score the game's score.
     */
    public YouWinAnimation(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(1, 1, d.getWidth(), d.getHeight());

        d.setColor(new Color(40, 86, 159));
        d.drawText(d.getWidth() / 2 - 135, d.getHeight() / 3, "You Win!", 68);

        int n = ((int) (Math.log10(score.getValue() + 1) + 1) + 7) * 7;
        d.drawText(d.getWidth() / 2 - 50 - n, 5 * d.getHeight() / 12, "Your score is: " + score.getValue(), 32);

        d.drawText(d.getWidth() / 2 - 160, d.getHeight() / 2, "press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
