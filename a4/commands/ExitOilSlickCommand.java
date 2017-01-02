package a4.commands;


import a4.GameWorldProxy;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitOilSlickCommand extends AbstractAction {//basically starts the game
    private GameWorldProxy gw;//target
    //private Iterator myIter;
      // maintain a single global reference to the spooler
      private static ExitOilSlickCommand theExitOilSlickCommand = null;
      public void actionPerformed(ActionEvent e){
        //System.out.println("Exiting an oil Slick");
          /*
        myIter = gw.getIterator();
            while(myIter.hasNext()){ 
            GameObjects x =(GameObjects) myIter.getNext();
            if (x instanceof Cars){
               ((Cars)x).setInOilSlick(false);
            }   
        }*/
        gw.x();//exit oil slick
        gw.notifyObservers();
      }
      // insure that no one can construct a spooler directly
      private ExitOilSlickCommand(GameWorldProxy gaw) {
      gw = gaw;
      }
      // provide access to the spooler, creating it if necessary
      public static ExitOilSlickCommand getExitOilSlickCommand(GameWorldProxy gaw) {
      if (theExitOilSlickCommand == null)
          theExitOilSlickCommand = new ExitOilSlickCommand(gaw);
      return theExitOilSlickCommand;
      }
      // accept a Document for printing
      public void setTarget (GameWorldProxy gw) {
         this.gw = gw;      //code here to add the Document to a private queue ...
      }
      //private methods here to dequeue and print documents ...
    }
