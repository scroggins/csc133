/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4.gameObjects;

import a4.Point;
import a4.interfaces.ICollider;
import a4.interfaces.ISelectable;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Randy
 */
public class Pylon extends FixedObj implements ICollider, ISelectable {
      private int radius;//the private variables one for radius
    private int sequenceNumber; // this private variable is for the number of the pylon
    private Color otherColor;
    private boolean selected;
    private AffineTransform screenXform;
    public Pylon(int i){//blank constructor
    	radius = 20;
    	sequenceNumber = i;
        super.setColor();
        otherColor = Color.BLACK;
        selected = false;


    }//the filled constructor
    public Pylon(int number,double  x, double  y){
        radius = 20;//the costructor for pylon with radius newRadius
        sequenceNumber = number;// and with sequenceNumber number
        this.setLocation(x, y);
        super.setColor();
        otherColor = Color.BLACK;
        selected = false;
        //Pylon.Point.setX(x);
        // Pylon.Point.setY(y);
    }
    //mutators
    public void setRadius(int newRadius){
        radius = newRadius;
    }
    public void setNumber(int newNum){
        sequenceNumber = newNum;
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

        return (2*this.getRadius());
    }

    @Override
    public int getLength() {
        return (2*this.getRadius());
    }

    //acessors
    public int getRadius(){
        return radius;
    }
    public int getNum(){
        return sequenceNumber;
    }
    public String toString(){
    	String x = ("Pylon: loc= ["+getLocationX()+", "+ getLocationY()+"] color= "+getColor()+" radius= "+getRadius()+" seqNum= "+getNum());
        return x;
    }

    @Override//for the ISelectable
    public void setSelected(boolean yesNo) {
        selected = yesNo;
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean contains(Point p) {
        if(((this.getX()<= p.getX())&&(this.getX()+this.getWidth())>=p.getX())
            &&((this.getY()<= p.getY())&&(this.getY()+this.getWidth())>=p.getY()))
            return true;
        else
            return false;
    }

    public void draw(Graphics g){
        if(this.isSelected()){
            g.setColor(otherColor);
            g.fillOval((int) (this.getLocationX()), (int) (this.getLocationY()), this.getWidth(), this.getLength());
            g.setColor(Color.BLACK);
            g.drawString(getNum() + "", (int) (getLocationX() + 17), (int) getLocationY() + 25);
        }else {
            g.setColor(this.getColor());
            g.fillOval((int) (this.getLocationX()), (int) (this.getLocationY()), this.getWidth(), this.getLength());
            g.setColor(Color.BLACK);
            g.drawString(getNum() + "", (int) (getLocationX() + 17), (int) getLocationY() + 25);
        }

    }
}
