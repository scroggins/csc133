package a4.commands;


import a4.*;
import a4.interfaces.Iterator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CollisionCommand extends AbstractAction {
    private GameWorldProxy gw;//target
     private Iterator myIter;
     private GameCollection gc;
      private static CollisionCommand theCollisionCommand = null;
      private CollisionCommand(GameWorldProxy gaw){
          gw = gaw;
      }
      public void actionPerformed(ActionEvent e){
           // System.out.println("collide with npc");
          /*
        myIter = gw.getIterator();
             while(myIter.hasNext()){ 
                GameObjects x =(GameObjects) myIter.getNext();
            if (x instanceof Cars){
               //  System.out.println("collide with npc");
                ((Cars)x).getDamageLevel();
                ((Cars)x).setDamageLevel(((Cars)x).getDamageLevel()+3);
                float g =((Cars)x).getMaxSpeed()-3;
                ((Cars)x).setMaxSpeed(g);
                if((((Cars)x).getMaxSpeed())< ((Cars)x).getCurrentSpeed())
                   ((Cars)x).setCurrentSpeed(((Cars)x).getMaxSpeed());
                if ((((Cars)x).getMaxDamage())<=(((Cars)x).getDamageLevel())){
                    System.out.println("life lost");
                    gc = gw.getGameCollection();
                    gc.remove(x);
                    gw.setCurrentLives(gw.getCurrentLives()-1) ;
                    if(gw.getCurrentLives()==0){
                        System.out.println("game Over");
                        System.exit(0);//the player has run out of lives there fore the game ends
                    }
                    else{
                        gw.createCar();
                        break;
                    }
                }
            }   
        }
        */
          gw.collision();
             gw.notifyObservers();
      }
    
      private CollisionCommand() { }//empty constructor
      //singleton 
      public static CollisionCommand getCollisionCommand(GameWorldProxy gw) {
      if (theCollisionCommand == null)
          theCollisionCommand = new CollisionCommand(gw);
      return theCollisionCommand;
      }
      // seting the target
      public void setTarget (GameWorldProxy gw) {
         this.gw = gw;   
      } 
}


