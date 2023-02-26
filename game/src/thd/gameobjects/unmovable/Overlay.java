package thd.gameobjects.unmovable;

import thd.game.managers.GameObjectManager;
import thd.gameView.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

public class Overlay extends GameObject {

    private String text;
    private GameObjectManager gameObjectManager;
    private int secondsToShow;
    private int fpsCounter;
    private int fps;
    private long time;
    private long time2;
    private long timeDiff;

    /**
     * Mindestanforderung, das jedes GameObject haben muss.
     *
     * @param gameView to interact with game view.
     */
    public Overlay(GameView gameView, GameObjectManager gameObjectManager) {
        super(gameView);
        positionInSort = 101;
        this.gameObjectManager = gameObjectManager;
        text = " ";
        fpsCounter = 0;
        fps = 30;
        time = System.currentTimeMillis();
    }

    public void showMessage(String message, int secondsToShow) {
        text = message;
        this.secondsToShow = secondsToShow;
        gameView.activateTimer(text, this, secondsToShow * 1000L);
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("SCORE:" + (int) gameObjectManager.score, 10, 10, 15, Color.BLACK, 0);
        gameView.addTextToCanvas("FPS: " + fps, 420, 10, 15, Color.BLACK, 0);
        time2 = System.currentTimeMillis();
        fpsCounter += 1;
        timeDiff = time2 - time;
        if ((timeDiff) >= 1000) {
            time = System.currentTimeMillis();
            fps = fpsCounter;
            fpsCounter = 0;

        }

        if (gameView.timerIsActive(text, this)) {
            gameView.addTextToCanvas(text, GameView.WIDTH / 2d - 150, GameView.HEIGHT / 2d, 30, Color.BLACK, 0);
        }
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public int compareTo(GameObject o) {
        return Integer.compare(positionInSort, o.positionInSort);
    }
}
