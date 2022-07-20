package levels;

import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.screens.KeyPressStoppableAnimation;
import animations.screens.PauseScreen;
import biuoop.KeyboardSensor;
import gameObjects.SpriteCollection;
import gameObjects.Sprite;
import gameObjects.GameEnvironment;
import gameObjects.Collidable;
import gameObjects.Paddle;
import gameObjects.Ball;
import gameObjects.Block;
import geometry.Point;
import geometry.Rectangle;
import indicators.NameIndicator;
import indicators.ScoreIndicator;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import other.Counter;
import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Game.Game class provides us the game - the sprites inside it, the environment. It also provides an initialization
 * function that creates the objects inside the game, and a run function that runs the game.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private LevelInformation levelInformation;
    private KeyboardSensor keyboard;
    private GUI gui;
    private boolean isInit;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;

    //game constants
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BORDER_SIZE = WIDTH / 45;
    private static final int PADDLE_HEIGHT = 5;
    public static final int FPS = 60;

    /**
     * Default constructor.
     * @param levelInformation a levelInformation.
     * @param gui the gui.
     * @param keyboard the gui's keyboard.
     * @param ar an AnimationRunner.
     * @param score the score of the entire game.
     */
    public GameLevel(LevelInformation levelInformation, GUI gui,
                     KeyboardSensor keyboard, AnimationRunner ar, Counter score) {
        this.isInit = false;
        this.levelInformation = levelInformation;
        this.gui = gui;
        this.keyboard = keyboard;
        this.runner = ar;
        this.score = score;
    }

    @Override
    public boolean shouldStop() {
        return this.ballCounter.getValue() == 0 || this.blockCounter.getValue() == 0;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // game-specific logic
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (keyboard.isPressed("p")) {
                this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen()));
        }
    }

    /**
     * Add a new collidable to the game environment.
     * @param c a collidable.
     */
    public void addCollidable(Collidable c) {
        if (environment != null) {
            environment.addCollidable(c);
        }
    }

    /**
     * Add a new sprite to the game environment.
     * @param s a sprite.
     */
    public void addSprite(Sprite s) {
        if (sprites != null) {
            sprites.addSprite(s);
        }
    }

    /**
     *  Initialize a new game: create the Blocks and Sprites.Ball (and Sprites.Paddle)
     *  and add them to the game.
     */
    public void initialize() {
        //update the game has been initialized
        isInit = true;

        //create the sprite collection and game environment
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();

        //create the background
        this.addSprite(levelInformation.getBackground());

        //create the paddle
        Paddle paddle = new Paddle(new Point(WIDTH / 2 - levelInformation.paddleWidth() / 2, HEIGHT - BORDER_SIZE),
                levelInformation.paddleWidth(), PADDLE_HEIGHT, BORDER_SIZE, WIDTH - BORDER_SIZE,
                    levelInformation.paddleSpeed(), this.keyboard);
        paddle.addToGame(this);

        //create counters
        this.blockCounter = new Counter();
        this.blockCounter.increase(levelInformation.numberOfBlocksToRemove());

        this.ballCounter = new Counter();
        this.ballCounter.increase(levelInformation.numberOfBalls());

        //create hitListeners
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);

        //create the balls
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball b = new Ball(WIDTH / 2.0, HEIGHT - BORDER_SIZE - PADDLE_HEIGHT - 25, 4, Color.WHITE);
            b.setVelocity(levelInformation.initialBallVelocities().get(i));
            b.setGameEnvironment(this.environment);
            b.setPaddle(paddle);
            b.addToGame(this);
        }

        //borders (slightly modified, so they'll look better)
        Block borderLeft = new Block(new Rectangle(new Point(0, BORDER_SIZE),
                BORDER_SIZE, HEIGHT - BORDER_SIZE), Color.GRAY, false);
        Block borderRight = new Block(new Rectangle(new Point(WIDTH - BORDER_SIZE, BORDER_SIZE),
                BORDER_SIZE, HEIGHT - BORDER_SIZE), Color.GRAY, false);
        Block borderUp = new Block(new Rectangle(new Point(0, 0),
                WIDTH, BORDER_SIZE), Color.GRAY, false);
        borderUp.addHitListener(scoreTracker);

        Block deathRegion = new Block(new Rectangle(new Point(BORDER_SIZE, HEIGHT),
                WIDTH - BORDER_SIZE * 2, BORDER_SIZE), Color.GRAY, false);
        deathRegion.addHitListener(ballRemover);

        borderUp.addToGame(this);
        deathRegion.addToGame(this);
        borderLeft.addToGame(this);
        borderRight.addToGame(this);

        //create the indicators
        ScoreIndicator scoreIndicator = new ScoreIndicator(WIDTH / 3,  3 * BORDER_SIZE / 4, score);
        NameIndicator nameIndicator = new NameIndicator(2 * WIDTH / 3,
                3 * BORDER_SIZE / 4, levelInformation.levelName());
        scoreIndicator.addToGame(this);
        nameIndicator.addToGame(this);


        //create the blocks
        for (Block b: levelInformation.blocks()) {
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTracker);
            b.addToGame(this);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        if (!isInit) {
            return;
        }

        //countdown
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, FPS));

        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * @return the number of balls in the game
     */
    public int getNumberOfBalls() {
        return this.ballCounter.getValue();
    }

    /**
     * Remove a collidable from the game environment.
     * @param c a collidable.
     */
    public void removeCollidable(Collidable c) {
        if (environment != null) {
            environment.removeCollidable(c);
        }
    }

    /**
     * Remove a sprite to the game environment.
     * @param s a sprite.
     */
    public void removeSprite(Sprite s) {
        if (sprites != null) {
            sprites.removeSprite(s);
        }
    }
}
