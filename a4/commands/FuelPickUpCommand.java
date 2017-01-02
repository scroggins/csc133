package a4.commands;


import a4.*;
import a4.interfaces.Iterator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FuelPickUpCommand extends AbstractAction {//basically starts the game
    private GameWorldProxy gw;//target
    private Iterator myIter;
    //private GameCollection gc;
    private float fuelSize;
      // maintain a single global reference to the spooler
      private static FuelPickUpCommand theFuelPickUpCommand= null;
      public void actionPerformed(ActionEvent e){
          //System.out.println("fuel grabbed");
          /*
          myIter = gw.getIterator();
             while(myIter.hasNext()){ 
                GameObjects x =(GameObjects) myIter.getNext();
            if (x instanceof Fuel){
                fuelSize = ((Fuel)x).getSize();
                gw.remove(x);
                gw.createFuel();
                break;
            }
        }
        myIter = gw.getIterator();
        while(myIter.hasNext()){ 
            GameObjects x =(GameObjects) myIter.getNext();
            if (x instanceof Cars){
                ((Cars)x).setFuelLevel(((Cars)x).getFuelLevel()+fuelSize);
                break;
            }  
        }
        */
          gw.fuel();
          gw.notifyObservers();
      }
      // insure that no one can construct a spooler directly
      private FuelPickUpCommand(GameWorldProxy gaw) {
          gw = gaw;
      }
      // provide access to the spooler, creating it if necessary
      public static FuelPickUpCommand getFuelPickUpCommand(GameWorldProxy gaw) {
      if (theFuelPickUpCommand == null)
          theFuelPickUpCommand = new FuelPickUpCommand(gaw);
      return theFuelPickUpCommand;
      }
      // accept a Document for printing
      public void setTarget (GameWorldProxy gw) {
         this.gw = gw;      //code here to add the Document to a private queue ...
      }
      //private methods here to dequeue and print documents ...
    }

