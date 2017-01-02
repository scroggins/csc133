package a4.commands;
  

import a4.gameObjects.GameObjects;
import a4.GameWorldProxy;
import a4.interfaces.Iterator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RightTurnCommand extends AbstractAction {
    private GameWorldProxy gw;
    private Iterator myIter;
    private GameObjects x;
    private static RightTurnCommand theRightTurnCommand = null;

    public void actionPerformed(ActionEvent e) {
        //System.out.println("turning right");
        /*
        myIter = gw.getIterator();
        while(myIter.hasNext()){
            x =(GameObjects) myIter.getNext();
            if (x instanceof Cars){
                if(((Cars)x).getSteeringDirection()>-.698131){
                    float y =((Cars)x).getSteeringDirection()-(float).08726646;
                    ((Cars)x).setSteeringDirection(y);
                }
            }
        }*/
        gw.right();
        gw.notifyObservers();
    }

    private RightTurnCommand(GameWorldProxy gaw) {
        gw = gaw;
    }

    public static RightTurnCommand getRightTurnCommand(GameWorldProxy gaw) {
        if (theRightTurnCommand == null)
            theRightTurnCommand = new RightTurnCommand(gaw);
        return theRightTurnCommand;
    }

    public void setTarget(GameWorldProxy gw) {
        this.gw = gw;
    }
}
