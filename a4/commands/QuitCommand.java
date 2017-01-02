package a4.commands;


import a4.GameWorldProxy;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class QuitCommand extends AbstractAction {//basically starts the game
    private GameWorldProxy gw;//target
    // maintain a single global reference to the spooler
    private static QuitCommand theQuitCommand= null;
    public void actionPerformed(ActionEvent e){
        /*
          int selectedOption = JOptionPane.showConfirmDialog(null, 
                                  "Do you wanna close the window?", 
                                  "Choose", 
                                  JOptionPane.YES_NO_OPTION); 
    if (selectedOption == JOptionPane.YES_OPTION) {
        System.exit(0);
    }*/
        gw.quit();
            gw.notifyObservers();
      }
      // insure that no one can construct a spooler directly
      private QuitCommand(GameWorldProxy gaw) {
      gw = gaw;
      }
      // provide access to the spooler, creating it if necessary
      public static QuitCommand getQuitCommand(GameWorldProxy gaw) {
      if (theQuitCommand == null)
          theQuitCommand = new QuitCommand(gaw);
      return theQuitCommand;
      }
      // accept a Document for printing
      public void setTarget (GameWorldProxy gw) {
         this.gw = gw;      //code here to add the Document to a private queue ...
      }
      //private methods here to dequeue and print documents ...
    }

