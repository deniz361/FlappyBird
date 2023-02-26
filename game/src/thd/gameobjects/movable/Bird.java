package thd.gameobjects.movable;

import thd.game.managers.GameObjectManager;
import thd.gameView.GameView;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.unmovable.Pillar;

import java.nio.channels.Pipe;

import static thd.game.managers.InputManager.ALLOW_KEYPRESS;

public class Bird extends CollidableGameObject {




    private enum BasicAnimation {
        BIRD1("bird1.png"), BIRD2("bird2.png"), BIRD3("bird3.png");

        private String imageFile;

        BasicAnimation(String imageFile) {
            this.imageFile = imageFile;
        }

    }

    private BasicAnimation basicAnimation;
    private boolean jumping;
    private boolean jumpNow;
    private int duration;
    private GameObjectManager gameObjectManager;


    /**
     * Crates a new GameObject.
     *
     * @param gameView Window to show the GameObject on.
     */
    public Bird(GameView gameView, GameObjectManager gameObjectManager) {
        super(gameView);
        this.gameObjectManager = gameObjectManager;
        position.x = 100;
        position.y = GameView.HEIGHT / 2d;
        basicAnimation = BasicAnimation.BIRD1;
        positionInSort = 100;
        duration = 50;

        width = 25;
        height = 20;

        hitBoxHeight = height;
        hitBoxWidth = width;
        hitBoxOffsetY = 2;
        hitBoxOffsetX = 3;
    }

    public void animation() {
        if (!gameView.alarmIsSet("basicAnimation", this)) {
            gameView.setAlarm("basicAnimation", this, 300);
        } else if (gameView.alarm("basicAnimation", this)) {
            switch (basicAnimation) {
                case BIRD1 -> basicAnimation = BasicAnimation.BIRD2;
                case BIRD2 -> basicAnimation = BasicAnimation.BIRD3;
                case BIRD3 -> basicAnimation = BasicAnimation.BIRD1;
            }
        }
    }

    public void jump() {
        jumping = true;
    }

    @Override
    public void reactToCollision(CollidableGameObject other) {
        if (other.getClass() == Pillar.class) {
            //gameObjectManager.gameOver = true;
            initializeGameOver();
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(basicAnimation.imageFile, position.x, position.y, 1, rotation);
    }

    @Override
    public void updateStatus() {
        animation();

        if (jumping) {
            duration -= 1.5;
            if (rotation > -90) {
                rotation -= 1;
            }
            if (!gameView.alarmIsSet("jumping", this)) {
                gameView.setAlarm("jumping", this, 200);
                jumpNow = true;
                rotation = 0;

            } else if (gameView.alarm("jumping", this)) {
                jumpNow = false;
                jumping = false;
                duration = 50;
            }

            if (jumpNow && duration > 0) {
                position.y -= 0.1 * duration;

            }
        } else {
            if (rotation < 90) {
                rotation += 0.50;
            }
            position.y += 1.4;

        }
    }

    private void initializeGameOver() {
        if (!gameView.alarmIsSet("gameOver", this)) {
            gameView.setAlarm("gameOver", this, 1000);
            ALLOW_KEYPRESS = false;
            //gameObjectManager.overlay.showMessage("GameOver", 3);

        } else if (gameView.alarm("gameOver", this)) {
            gameObjectManager.gameOver = true;
        }
    }

    @Override
    public int compareTo(GameObject o) {
        return Integer.compare(positionInSort, o.positionInSort);
    }
}
