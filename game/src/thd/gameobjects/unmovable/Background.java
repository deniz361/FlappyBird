package thd.gameobjects.unmovable;

import thd.gameView.GameView;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.GameObject;

public class Background extends GameObject implements AutoMovable {


    /**
     * Mindestanforderung, das jedes GameObject haben muss.
     *
     * @param gameView to interact with game view.
     */
    public Background(GameView gameView, int positionX) {
        super(gameView);
        positionInSort = 0;
        width = 288;
        height = 512;
        position.x = positionX;
    }

    @Override
    public void updatePosition() {

    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("bg.png", position.x, position.y, 1, 0);
        gameView.addImageToCanvas("base.png", position.x, GameView.HEIGHT - 112, 1, 0);
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public int compareTo(GameObject o) {
        return Integer.compare(positionInSort, o.positionInSort);
    }
}
