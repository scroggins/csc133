package a4;

import a4.gameObjects.GameObjects;
import a4.interfaces.ICollection;
import a4.interfaces.Iterator;

import java.util.ArrayList;

/**
 * Created by Randy on 3/17/2015.
 */
public class GameCollection implements ICollection {
    private ArrayList theCollection ;
    private int length;
    private GameArrayListIterator myIter;
    public GameCollection() {
        theCollection = new ArrayList<GameObjects>();
        setLength(0);
        myIter= new GameArrayListIterator();
    }
    public void add(GameObjects newObject) {
        theCollection.add(newObject);
    }
    public void remove(GameObjects newObject){
        theCollection.remove(newObject);
    }
            
    private void setLength(int x){
        length = x;
    }
    public int getLength(){
        return length;
    }
    public Iterator getIterator() {
        return new GameArrayListIterator( );
    }
    private class GameArrayListIterator implements Iterator {
        private int currElementIndex;
        public GameArrayListIterator() {
            currElementIndex = -1;
        }
        public boolean hasNext() {
            if (theCollection.size ( ) <= 0)
                return false;
            if (currElementIndex == theCollection.size() -1)
                return false;
            return true;
        }
        public Object getNext ( ) {
            currElementIndex ++ ;
            return(theCollection.get(currElementIndex));
        }
        public void remove(){
            theCollection.remove(currElementIndex);
        }
    }     //end private iterator class
}
