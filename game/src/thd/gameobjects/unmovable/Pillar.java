package thd.gameobjects.unmovable;

import thd.gameView.GameView;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.GameObject;

public class Pillar extends CollidableGameObject {

    private int gap;
    /**
     * Crates a new GameObject.
     *
     * @param gameView Window to show the GameObject on.
     */
    public Pillar(GameView gameView, int positionX, int positionY, int rotation) {
        super(gameView);
        positionInSort = 50;
        position.x = positionX;
        position.y = positionY;
        gap = 470;
        this.rotation = rotation;

        height = 320;
        width = 52;
        hitBoxHeight = height;
        hitBoxWidth = width;

    }


    @Override
    public void reactToCollision(CollidableGameObject other) {

    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("pipe.png", position.x, position.y, 1, rotation);
    }

    @Override
    public void updateStatus() {

    }

    @Override
    public int compareTo(GameObject o) {
        return Integer.compare(positionInSort, o.positionInSort);
    }
}
