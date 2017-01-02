package a4.interfaces;

import a4.gameObjects.GameObjects;

/**
 * Created by Asus on 3/17/2015.
 */
public interface ICollection {
    public void add(GameObjects newObject);
    public Iterator getIterator();
}
