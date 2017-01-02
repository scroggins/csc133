package a4.commands;


import a4.GameWorldProxy;
import a4.interfaces.Iterator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewColorsCommand extends AbstractAction {//basically starts the game
    private Iterator myIter;//to create my iterator
    private GameWorldProxy gw;//target
      // maintain a single global reference to the spooler
      private static NewColorsCommand theNewColorsCommand = null;
      public void actionPerformed(ActionEvent e){
          //System.out.println("new Colors all Around ");
          /*
            myIter = gw.getIterator();
            while(myIter.hasNext()){ 
                GameObjects x =(GameObjects) myIter.getNext();
                x.setColor();
         }
         */
          gw.n();//new colors
            gw.notifyObservers();
      }
      // insure that no one can construct a spooler directly
      private NewColorsCommand(GameWorldProxy gaw) {
      gw = gaw;
      }
      // provide access to the spooler, creating it if necessary
      public static NewColorsCommand getColorsCommand(GameWorldProxy gaw) {
      if (theNewColorsCommand == null)
          theNewColorsCommand = new NewColorsCommand(gaw);
      return theNewColorsCommand;
      }
      // accept a Document for printing
      public void setTarget (GameWorldProxy gw) {
         this.gw = gw;      //code here to add the Document to a private queue ...
      }
      
      //private methods here to dequeue and print documents ...
    }
