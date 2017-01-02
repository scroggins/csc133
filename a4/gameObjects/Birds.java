/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4.gameObjects;

import a4.interfaces.ICollider;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Randy
 */
public class Birds  extends MovObj implements ICollider {
    private int size;//the private variables one for radius
    private AffineTransform screenXform;
    
    public Birds(int newSize){
        size = newSize;//the costructor for size with newSize
        super.setColor();
        //location = 
    }
    public Birds(){
        int x = random(10)+15;
        size = x ;
        super.setColor();
        setSpeed(random(10));
    }
    //mutators
    public void setSize(int newSize){
        size = newSize;
    }
    //acessors
    public int getSize(){
        return size;
    }
    public void setColor(){
     
     }

    @Override
    public int getX() {

        return (int) this.getLocationX();
    }

    @Override
    public int getY() {

        return (int) this.getLocationY();
    }

    @Override
    public int getWidth() {

        return (this.getSize());
    }

    @Override
    public int getLength() {
        return (this.getSize());
    }
    public void draw(Graphics x){
        AffineTransform screenXform = new AffineTransform();
        //screenXform.translate (0, panel.getHeight());
        screenXform.scale (1, -1);
        x.setColor(this.getColor());
        x.drawOval((int)(getLocationX()), (int)(getLocationY()),getSize(),getSize());

    }

    public String toString(){
    	String x =("Bird: loc= ["+ getLocationX()+", " + getLocationY()+"] color= "+getColor()+" heading= " +getHeading()+" speed= "+getSpeed()+" size= " +getSize());
        return x;
    
    
    }
}
