package a4.commands;


import a4.GameWorldProxy;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CarCollisionCommand extends AbstractAction {//basically starts the game
    private GameWorldProxy gw;//target
      // maintain a single global reference to the spooler
      private static CarCollisionCommand theCarCollisionCommand;
      public void actionPerformed(ActionEvent e){

      }
      // insure that no one can construct a spooler directly
      private CarCollisionCommand() { }
      // provide access to the spooler, creating it if necessary
      public static CarCollisionCommand getAccelerateCommand() {
      if (theCarCollisionCommand == null)
      theCarCollisionCommand = new CarCollisionCommand();
      return theCarCollisionCommand;
      }
      // accept a Document for printing
      public void setTarget (GameWorldProxy gw) {
         this.gw = gw;      //code here to add the Document to a private queue ...
      }
      //private methods here to dequeue and print documents ...
    }

