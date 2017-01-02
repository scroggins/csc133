package a4.commands;


import a4.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangeStrategiesCommand extends AbstractAction {//basically starts the game
    private GameWorldProxy gw;//target
    //private Iterator myIter;
    //private ToThePylonNPC pylon ;
    //private DestructionDerbyStrategy derby;
      // maintain a single global reference to the spooler
      private static ChangeStrategiesCommand theChangeStrategiesCommand = null;
      public void actionPerformed(ActionEvent e){
         
        //System.out.println("Strategy button hit");
          /*
         myIter = gw.getIterator();
          while(myIter.hasNext()){
              GameObjects k =(GameObjects) myIter.getNext();
                    if (k instanceof NPC){
                         pylon = new ToThePylonNPC((NPC)k, gw);
                         derby = new DestructionDerbyStrategy((NPC)k, gw);
                       if (((NPC)k).getStrategy() instanceof DestructionDerbyStrategy){
                           ((NPC)k).setStrategy(pylon);
                           System.out.println("pylon");
                       }else{
                           ((NPC)k).setStrategy(derby);
                            System.out.println("derby");
                       }
                    }
          }
          */
          gw.changeStrategy();
          gw.notifyObservers();  
      }
         
      // insure that no one can construct a spooler directly
      private ChangeStrategiesCommand(GameWorldProxy gaw) {
      gw = gaw;
      }
      // provide access to the spooler, creating it if necessary
      public static ChangeStrategiesCommand getChangeStrategiesCommand(GameWorldProxy gaw) {
      if (theChangeStrategiesCommand == null)
          theChangeStrategiesCommand = new ChangeStrategiesCommand(gaw);
      return theChangeStrategiesCommand;
      }
      // accept a Document for printing
      public void setTarget (GameWorldProxy gw) {
         this.gw = gw;      //code here to add the Document to a private queue ...
      }
      //private methods here to dequeue and print documents ...
    }
