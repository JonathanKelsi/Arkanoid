package animations.screens;

import animations.Animation;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The PauseScreen class is in charge of pausing the animation until a certain key is pressed.
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(1, 1, d.getWidth(), d.getHeight());

        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 2 - 110, d.getHeight() / 3, "Paused", 68);
        d.drawText(d.getWidth() / 2 - 160, d.getHeight() / 2, "press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
