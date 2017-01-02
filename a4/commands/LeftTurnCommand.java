package a4.commands;


import a4.gameObjects.GameObjects;
import a4.GameWorldProxy;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LeftTurnCommand extends AbstractAction {//basically starts the game
    private GameWorldProxy gw;//target
    private GameObjects x;
      // maintain a single global reference to the spooler

      private static LeftTurnCommand theLeftTurnCommand= null;
      public void actionPerformed(ActionEvent e){
        //System.out.println("turning left");
          /*
          Iterator myIter = gw.getIterator();
          while(myIter.hasNext()){
              GameObjects x =(GameObjects) myIter.getNext();
              if (x instanceof Cars){
                  if(((Cars)x).getSteeringDirection()>-.698131){
                      float y =((Cars)x).getSteeringDirection()-(float).08726646;
                      ((Cars)x).setSteeringDirection(y);
                  }
              }
          }*/
          gw.left();
          gw.notifyObservers();
      }




      // insure that no one can construct a spooler directly
      private LeftTurnCommand(GameWorldProxy gaw) {
      gw = gaw;
      }
      // provide access to the spooler, creating it if necessary
      public static LeftTurnCommand getLeftTurnCommand(GameWorldProxy gaw) {
      if (theLeftTurnCommand == null)
          theLeftTurnCommand = new LeftTurnCommand(gaw);
      return theLeftTurnCommand;
      }
      // accept a Document for printing
      public void setTarget (GameWorldProxy gw) {
         this.gw = gw;      //code here to add the Document to a private queue ...
      }
      //private methods here to dequeue and print documents ...
    }

