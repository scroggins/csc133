package a4.commands;


import a4.GameWorldProxy;
import a4.interfaces.Iterator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BirdCollisionCommand extends AbstractAction {//
    private GameWorldProxy gw;//
     private Iterator myIter;
      // 
      private static BirdCollisionCommand theBirdCollisionCommand = null;
      public void actionPerformed(ActionEvent e){
            // System.out.println("collide with Bird");
          gw.hitBird();
          /*
             myIter = gw.getIterator();
             while(myIter.hasNext()){ 
                GameObjects x =(GameObjects) myIter.getNext();
                if (x instanceof Cars){
                //System.out.println("collide with Bird");
                ((Cars)x).getDamageLevel();
                ((Cars)x).setDamageLevel(((Cars)x).getDamageLevel()+1);
                float g =((Cars)x).getMaxSpeed()-1;
                ((Cars)x).setMaxSpeed(g);
                if((((Cars)x).getMaxSpeed())< ((Cars)x).getCurrentSpeed())
                   ((Cars)x).setCurrentSpeed(((Cars)x).getMaxSpeed());
                if ((((Cars)x).getMaxDamage())<=(((Cars)x).getDamageLevel())){
                    System.out.println("life lost");
                    gw.remove(x);
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
        }*/
             gw.notifyObservers();
      }
      // 
      private BirdCollisionCommand(GameWorldProxy gaw) {
        gw = gaw;
      }
      // 
      public static BirdCollisionCommand getBirdCollisionCommand(GameWorldProxy gaw) {
      if (theBirdCollisionCommand == null)
          theBirdCollisionCommand = new BirdCollisionCommand(gaw);
      return theBirdCollisionCommand;
      }
    
      public void setTarget (GameWorldProxy gw) {
         this.gw = gw;      //
      }
      //
    }

