package a4.interfaces;

/**
 * Created by Asus on 3/30/2015.
 */
public interface IObservable {
 public void addObservers(IObserver obs);
    public void notifyObservers();
}
