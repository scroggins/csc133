/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4.strategies;

import a4.gameObjects.GameObjects;
import a4.GameWorldProxy;
import a4.gameObjects.NPC;
import a4.gameObjects.Pylon;
import a4.interfaces.IStrategy;
import a4.interfaces.Iterator;

/**
 *
 * @author Randy
 */
public class ToThePylonNPC implements IStrategy {
    private NPC myNPC;
    private Iterator myIter;
    private GameWorldProxy gw;
    private GameObjects gO;
    private double xn, yn, xp, yp;
    public ToThePylonNPC (NPC n, GameWorldProxy gaw){
        this.myNPC = n;
        gw = gaw;
    }
    public void apply() {
        //get need iterator
        myIter =gw.getIterator();
        xn = (int) myNPC.getLocationX();
        yn = (int) myNPC.getLocationY();
        while(myIter.hasNext()){
            gO = (GameObjects) myIter.getNext();
            if (gO instanceof Pylon){
                if (((Pylon)gO).getNum() == myNPC.getHighestPylonHit()){
                    xp = gO.getLocationX();
                    yp = gO.getLocationY();
                    //if(yn>yp)
                        myNPC.setHeading(Math.atan2((yp-yn), (xp-xn)));
                    //else
                    //    myNPC.setHeading(Math.atan((xp-xn)/ (yp-yn)));
                    //break;
                }
            }
        }
        //gw.notifyObservers();
       //System.out.println("we are using the Pylon strategy");
    }
}
    
    
