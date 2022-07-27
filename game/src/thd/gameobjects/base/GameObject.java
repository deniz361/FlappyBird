package thd.gameobjects.base;

import thd.gameView.GameView;

/**Jedes Gameobject erbt von dieser Klasse.*/
public abstract class GameObject implements Comparable<GameObject> {

    protected final GameView gameView;
    public final Position position;
    public int positionInSort;
    protected double speedInPixel;
    protected double size;
    protected double rotation;
    protected double width;
    protected double height;

    /**
     * Mindestanforderung, das jedes GameObject haben muss.
     *
     * @param gameView to interact with game view.
     */
    public GameObject(GameView gameView) {
        this.gameView = gameView;
        position = new Position();
    }


    /**
     * Verschiebung der Spielwelt.
     *
     * @param shiftX Verschiebung in X-Richtung
     */
    public void worldHasMoved(double shiftX) {
        position.x -= shiftX;
    }

    /**
     * Um herauszufinden, ob ein GameObject noch im Spielfenster ist.
     * @return gibt "true" zurück, wenn das GameObject nicht mehr im Spiel ist
     */
    public boolean outOfGame() {
        return position.x > GameView.WIDTH || position.x < 0 || position.y < 0 || position.y > GameView.HEIGHT;
    }



    /**Fügt das Spielobject in GameView hinzu.*/
    public abstract void addToCanvas();

    /**
     * Statusupdate.
     */
    public abstract void updateStatus();


    /**
     * Changes the variable flyFromLeftToRight.
     *
     * @param direction input left or right
     */
    //public abstract void changeDirectionTo(String direction);

    /**Gibt die Position zurück.
     * @return gibt die Position zurück.*/
    Position getPosition() {
        return position;
    }

}
