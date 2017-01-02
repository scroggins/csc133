package a4.commands;


import a4.GameWorldProxy;
import a4.interfaces.Iterator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EnterOilSlickCommand extends AbstractAction {//basically starts the game
    private GameWorldProxy gw;//target
    private Iterator myIter;
      // maintain a single global reference to the spooler
      private static EnterOilSlickCommand theEnterOilSlickCommand = null;
      public void actionPerformed(ActionEvent e){
          //System.out.println("Entering an oil Slick");
          gw.e();//enter oil slick
       /*
        myIter = gw.getIterator();
        while(myIter.hasNext()){ 
            GameObjects x =(GameObjects) myIter.getNext();
            if (x instanceof Cars){
               ((Cars)x).setInOilSlick(true);
            }   
        }
        */
        gw.notifyObservers();
      }
      // insure that no one can construct a spooler directly
      private EnterOilSlickCommand(GameWorldProxy gaw) {
      gw = gaw;
      }
      // provide access to the spooler, creating it if necessary
      public static EnterOilSlickCommand getEnterOilSlickCommand(GameWorldProxy gaw) {
      if (theEnterOilSlickCommand == null)
      theEnterOilSlickCommand = new EnterOilSlickCommand(gaw);
      return theEnterOilSlickCommand;
      }
      // accept a Document for printing
      public void setTarget (GameWorldProxy gw) {
         this.gw = gw;      //code here to add the Document to a private queue ...
      }
      //private methods here to dequeue and print documents ...
    }
