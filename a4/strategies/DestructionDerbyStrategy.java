/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4.strategies;

import a4.gameObjects.GameObjects;
import a4.GameWorldProxy;
import a4.gameObjects.Cars;
import a4.gameObjects.NPC;
import a4.interfaces.IStrategy;
import a4.interfaces.Iterator;

/**
 *
 * @author Randy
 */
public class DestructionDerbyStrategy implements IStrategy {
    private NPC myNPC;
    private GameWorldProxy gw;
    private Iterator myIter;
    private GameObjects gO;
    private GameObjects car;
    private int  xn, yn, xc, yc;
    public DestructionDerbyStrategy (NPC n, GameWorldProxy gaw){
        gw = gaw;
        this.myNPC = n;
    }
    public void apply() {
        myIter = gw.getIterator();
        while(myIter.hasNext()){
            car =(GameObjects) myIter.getNext();
            if(car instanceof Cars){
                xc = (int) car.getLocationX();
                yc = (int) car.getLocationY();
            }
        }
        xn = (int) myNPC.getLocationX();
        yn = (int) myNPC.getLocationY();
        //xp = gO.getLocationX();
        //yp = gO.getLocationY();
        //if(yn>yp)
        myNPC.setHeading(Math.atan2((yc-yn), (xc-xn)));
        /*if(yc>yn) {
            myNPC.setHeading(Math.atan((yc - yn) / (xc - xn)));
        }else
            myNPC.setHeading(Math.atan((xc - xn) / (yc - yn)));
            */
        //gw.notifyObservers();
       //System.out.println("we are using the Destruction derby");
    }
}