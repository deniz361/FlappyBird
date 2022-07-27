package thd.game.managers;

import thd.gameView.GameView;

public class GameLoopManager {


    private final GameView gameView;
    private final InputManager inputManager;
    private final GameObjectManager gameObjectManager;

    public GameLoopManager() {
        gameView = new GameView();
        gameView.setWindowTitle("Flappy Bird");
        gameView.setStatusText("Deniz Adigüzel");
        gameView.setWindowIcon("bird1.png");
        gameObjectManager = new GameObjectManager(gameView);
        inputManager = new InputManager(gameView, gameObjectManager.bird);
    }

    public void startGame() {
        while (!gameObjectManager.gameOver) {
            gameObjectManager.updateGameObjects();
            inputManager.updateUserInputs();
            gameView.printCanvas();
        }
    }
}
