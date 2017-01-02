package a4.commands;


import a4.*;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class CollidePylonCommand extends AbstractAction {
    private GameWorldProxy gw;//target
   // private Iterator myIter;
    //private Cars car;
    //private int pylonNum;
      private static CollidePylonCommand theCollidePylonCommand = null;
      public void actionPerformed(ActionEvent e){
         // System.out.println("hit a pylon");
          gw.collideWithPylon();
          /*pylonNum = -1;
          String x = JOptionPane.showInputDialog("Please input a pylon number");
          try{
           pylonNum = Integer.parseInt(x);
          }
          catch (NumberFormatException q){
              System.out.println("please input a valid number");
          }
           myIter = gw.getIterator();
               while(myIter.hasNext()){//find my car
                    GameObjects k =(GameObjects) myIter.getNext();
                    if (k instanceof Cars){
                       car = ((Cars)k);
                    }
               }
          myIter = gw.getIterator();
               while(myIter.hasNext()){
                   GameObjects y =(GameObjects) myIter.getNext();
                   if (y instanceof Pylon && ((Pylon)y).getNum()==pylonNum){
                       if(pylonNum == car.getHighestPylonHit()+1)
                           car.setHighestPylonHit(pylonNum);
                    }
                }*/
            gw.notifyObservers();               
    } 
      private CollidePylonCommand(GameWorldProxy gaw) {
        gw = gaw;
      }
      public static CollidePylonCommand getCollidePylonCommand(GameWorldProxy gaw) {
        if (theCollidePylonCommand == null){
        theCollidePylonCommand = new CollidePylonCommand(gaw);
        }
        return theCollidePylonCommand;
      }
      // 
      public void setTarget (GameWorldProxy gw) {
         this.gw = gw;      //
      }
      //
    }