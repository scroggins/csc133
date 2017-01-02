package a4.interfaces;

/**
 * Created by Asus on 4/23/2015.
 */
public interface ICollider {
    void handleCollision(ICollider o);
    boolean CollidesWith(ICollider o);
    int getX();
    int getY();
    int getWidth();
    int getLength();
}
