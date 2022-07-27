package thd.game.utilities;

import thd.gameobjects.base.GameObject;

import java.util.Comparator;

public class Sorter implements Comparator<GameObject> {

    @Override
    public int compare(GameObject a, GameObject b) {
        return Integer.compare(a.positionInSort, b.positionInSort);
    }
}
