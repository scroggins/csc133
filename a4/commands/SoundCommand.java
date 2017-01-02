package a4.commands;

import a4.GameWorldProxy;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Randy on 5/5/2015.
 */
public class SoundCommand extends AbstractAction {//basically starts the game
    private GameWorldProxy gw;//target
    // maintain a single global reference to the spooler
    private static SoundCommand theSoundCommand = null;
    public void actionPerformed(ActionEvent e) {
        //System.out.println("accelerating ");
         /*Iterator myIter = gw.getIterator();
          while(myIter.hasNext()){ {
              GameObjects x =(GameObjects) myIter.getNext();

              if (x instanceof Cars) {
                if(((Cars)x ).getCurrentSpeed() < ((Cars)x).getMaxSpeed()){
                    float y =(((Cars)x).getCurrentSpeed()+5);
                    ((Cars)x).setCurrentSpeed(y);
                }
              }
          }
      }*/
        gw.changeSound();
        gw.notifyObservers();
    }
    // insure that no one can construct a spooler directly
    private SoundCommand(GameWorldProxy gaw) {
        gw = gaw;
    }
    // provide access to the spooler, creating it if necessary
    public static SoundCommand getSoundCommand(GameWorldProxy gaw) {
        if (theSoundCommand == null)
            theSoundCommand = new SoundCommand(gaw);
        return theSoundCommand;
    }
    // accept a Document for printing
    public void setTarget (GameWorldProxy gw) {
        this.gw = gw;      //code here to add the Document to a private queue ...
    }
    //private methods here to dequeue and print documents ...
}
