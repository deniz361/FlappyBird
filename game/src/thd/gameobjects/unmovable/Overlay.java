package thd.gameobjects.unmovable;

import thd.game.managers.GameObjectManager;
import thd.gameView.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

public class Overlay extends GameObject {

    private String text;
    private GameObjectManager gameObjectManager;
    private int secondsToShow;
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
    }

    public void showMessage(String message, int secondsToShow) {
        text = message;
        this.secondsToShow = secondsToShow;
        gameView.activateTimer(text, this, secondsToShow * 1000L);
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("SCORE:" + gameObjectManager.score, 10, 10, 15, Color.BLACK, 0);

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
