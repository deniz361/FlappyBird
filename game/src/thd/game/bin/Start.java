package thd.game.bin;

import thd.game.managers.GameLoopManager;

public class Start {

    public static void main(String[] args) {
        GameLoopManager gameLoopManager = new GameLoopManager();
        gameLoopManager.startGame();
    }
}
