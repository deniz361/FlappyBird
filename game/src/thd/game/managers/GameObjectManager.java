package thd.game.managers;

import thd.game.utilities.Sorter;
import thd.game.utilities.TooManyGameObjectsException;
import thd.gameView.GameView;
import thd.gameobjects.base.AutoMovable;
import thd.gameobjects.base.CollidableGameObject;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.Bird;
import thd.gameobjects.unmovable.Background;
import thd.gameobjects.unmovable.Overlay;
import thd.gameobjects.unmovable.Pillar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameObjectManager {

    private final GameView gameView;
    private final ArrayList<GameObject> gameObjects;
    private final ArrayList<GameObject> toAdd;
    private final ArrayList<GameObject> toRemove;
    private final Random random;
    Bird bird;
    private final int upperPillar;
    private final int lowerPillar;
    private final int gap;
    public boolean gameOver;
    public double score;
    public Overlay overlay;

    GameObjectManager(GameView gameView) {
        this.gameView = gameView;
        gameOver = false;
        gameObjects = new ArrayList<>();
        toAdd = new ArrayList<>();
        toRemove = new ArrayList<>();
        bird = new Bird(gameView, this);
        overlay = new Overlay(gameView, this);
        gameObjects.add(bird);
        startBackGround();
        random = new Random();

        upperPillar = -180;
        lowerPillar = 0;
        gap = 430;
        gameObjects.add(new Pillar(gameView, GameView.WIDTH, 340, lowerPillar));
        gameObjects.add(new Pillar(gameView, GameView.WIDTH, 340 - gap, upperPillar));
        gameObjects.add(overlay);
    }

    void updateGameObjects() {
        moveWorld(2);
        buildBackground();
        modifyGameObjectsList();
        ArrayList<CollidableGameObject> collidables = new ArrayList<>(gameObjects.size());
        for (GameObject gameObject : gameObjects) {
            gameObject.updateStatus();
            if (gameObject instanceof AutoMovable) {
                ((AutoMovable) gameObject).updatePosition();
            }
            gameObject.addToCanvas();
            if (gameObject instanceof CollidableGameObject) {
                collidables.add((CollidableGameObject) gameObject);
            }
        }
        detectCollisionsAndNotifyGameObjects(collidables);
    }

    private void detectCollisionsAndNotifyGameObjects(ArrayList<CollidableGameObject> collidables) {
        for (int index = 0; index < collidables.size(); index++) {
            for (int other = index + 1; other < collidables.size(); other++) {
                if (collidables.get(index).collidesWith(collidables.get(other))) {
                    collidables.get(index).reactToCollision(collidables.get(other));
                    collidables.get(other).reactToCollision(collidables.get(index));
                }
            }
        }
    }


    private void addGameObject(GameObject gameObject) {
        toAdd.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        toRemove.add(gameObject);
    }

    /**
     * Moves the background
     *
     * @param shiftX shift in x
     */
    void moveWorld(double shiftX) {
        for (GameObject g : gameObjects) {
            if (!(g instanceof Bird)) {
                g.worldHasMoved(shiftX);
            }
        }
    }

    private void moveWorldToLeft(double pixels) {
        moveWorld(pixels);
    }

    private void modifyGameObjectsList() {

        gameObjects.sort(new Sorter());

        gameObjects.removeAll(toRemove);
        gameObjects.addAll(toAdd);
        toAdd.clear();
        toRemove.clear();

        if (gameObjects.size() > 300) {
            throw new TooManyGameObjectsException("Too many game Objects");
        }
    }

    private void buildBackground() {
        for (GameObject g : gameObjects) {
            if (g instanceof Background && g.position.x < -288) {
                addGameObject(new Background(gameView, GameView.WIDTH - 1));
                removeGameObject(g);
            } else if (g instanceof Pillar) {
                if (g.position.x == GameView.WIDTH / 2d) {
                    spawnPillar();
                    return;
                } else if (g.position.x < -40) {
                    removeGameObject(g);
                } else if (g.position.x - bird.position.x <= 0 && !((Pillar) g).scoreCounted) {
                    score += 0.5;
                    ((Pillar) g).scoreCounted = true;
                }
            }
        }
    }

    private void spawnPillar() {
        int randomNumber = random.nextInt(210, 380);
        addGameObject(new Pillar(gameView, GameView.WIDTH, randomNumber, lowerPillar));
        addGameObject(new Pillar(gameView, GameView.WIDTH, randomNumber - gap, upperPillar));
    }

    private void startBackGround() {
        gameObjects.add(new Background(gameView, 0));
        gameObjects.add(new Background(gameView, 288));
        gameObjects.add(new Background(gameView, 2 * 288));
    }
}
