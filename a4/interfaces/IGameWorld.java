package a4.interfaces;

/**
 * Created by Randy on 4/21/2015.
 */
public interface IGameWorld {
    Iterator getIterator();
    public void about();
    public void accelerate();
    public void addOilSlick();
    public void hitBird();
    public void brake();
    public void changeStrategy();
    public void collideWithPylon();
    public void collision();
    public void e();//enter oil slick
    public void x();//exit oil slick
    public void fuel();
    public void left();
    public void n();
    public void quit();
    public void right();
    public void t();
    public void notifyObservers();
    public void addObservers(IObserver x);
    public int getLives();
    public int getTimeClock();
    public void initLayout();
    public void addFuelAt();

    public void changeSound();
}
