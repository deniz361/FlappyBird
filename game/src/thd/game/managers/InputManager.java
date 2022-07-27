package thd.game.managers;

import thd.gameView.GameView;
import thd.gameobjects.movable.Bird;

import java.awt.event.KeyEvent;

public class InputManager {

    private final GameView gameView;
    private final Bird bird;
    public static boolean ALLOW_KEYPRESS = true;


    InputManager(GameView gameView, Bird bird) {
        this.gameView = gameView;
        this.bird = bird;
    }

    void updateUserInputs() {
        if (ALLOW_KEYPRESS) {
            Integer[] pressedKeys = gameView.getKeyCodesOfCurrentlyPressedKeys();

            for (int keyCode : pressedKeys) {
                if (keyCode == KeyEvent.VK_SPACE) {
                    bird.jump();
                }
            }
        }
    }
}
