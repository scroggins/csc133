package a4.commands;


import a4.GameWorldProxy;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BrakeCommand extends AbstractAction {//basically starts the game
    private GameWorldProxy gw;//target
   // private Iterator myIter;
    //private GameObjects x;
    //
    private static BrakeCommand theBrakeCommand = null;
    public void actionPerformed(ActionEvent e) {
        //System.out.println("applying brakes");
       gw.brake();
       /* Iterator myIter = gw.getIterator();
        while (myIter.hasNext()) {
            {
                x = (GameObjects) myIter.getNext();

                if (x instanceof Cars) {
                    if (((Cars) x).getCurrentSpeed() > 0) {
                        float y = (((Cars) x).getCurrentSpeed() - 1);
                        ((Cars) x).setCurrentSpeed(y);
                    }
                }
            }
        }*/
        gw.notifyObservers();
    }
    //
    private BrakeCommand(GameWorldProxy gaw) {
    gw = gaw;
    }
    // provide access to the spooler, creating it if necessary
    public static BrakeCommand getBrakeCommand(GameWorldProxy gaw) {
        if (theBrakeCommand == null)
            theBrakeCommand = new BrakeCommand(gaw);
        return theBrakeCommand;
    }
    // accept a Document for printing
    public void setTarget (GameWorldProxy gw) {
        this.gw = gw;      //code here to add the Document to a private queue ...
    }
    //private methods here to dequeue and print documents ...   
} 