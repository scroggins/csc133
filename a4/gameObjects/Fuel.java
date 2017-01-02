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
public class Fuel extends FixedObj implements ICollider, ISelectable {
    private int size;//the private variables one for the size of the fual can
    private Color otherColor;
    private boolean selected;
    private AffineTransform screenXform;
    
    public Fuel(int newSize){
        size = newSize;//the costructor for fuel with a certain size
    }
    public Fuel(){
        size = (random(10)+15);
    }

    public Fuel(int x, int y){
        size = (random(10)+15);
        this.setLocation((double) x, (double) y);
    }
    //mutators
    public void setSize(int newSize){
        size = newSize;
    }
    //acessors
    public int getSize(){
        return size;
    }
    public String toString(){
    	String x =("FuelCan: loc= [" +getLocationX()+", "+ getLocationY()+"] color=" + getColor()+" size=" +getSize() );
        return x;
    }

    @Override
    public void handleCollision(ICollider o) {

    }

    @Override
    public boolean CollidesWith(ICollider o) {
        if ((o.getX() < this.getX() + this.getWidth() && this.getX() + this.getWidth() < o.getX() + o.getWidth()) ||
                (o.getX() < this.getX() && this.getX() < o.getX() + o.getWidth())) {
            if ((o.getY() < this.getY() + this.getLength() && this.getY() + this.getLength() < o.getY() + o.getLength()) ||
                    (o.getY() < this.getY() && this.getY() < o.getY() + o.getLength())) {
                //this.getY() + this.getLength() < o.getY() || (this.getY()) > o.getY()+o.getLength()){
                //System.out.println("Car has not collided with other object");
                return true;
            }
        }
        return false;
    }

    @Override
    public int getX() {
        return (int) this.getLocationX() - this.getSize()/2;
    }

    @Override
    public int getY() {
        return (int) this.getLocationY() - this.getSize()/2;
    }

    @Override
    public int getWidth() {
        return this.getSize();
    }

    @Override
    public int getLength() {
        return this.getSize();
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
            g.fillRect((int) (this.getLocationX()), (int) (this.getLocationY()), this.getWidth(), this.getLength());
            //g.setColor(Color.BLACK);
            //g.drawString(getNum() + "", (int) (getLocationX() + 17), (int) getLocationY() + 25);
        }else {
            g.setColor(this.getColor());
            g.fillRect((int) (this.getLocationX()), (int) (this.getLocationY()), this.getWidth(), this.getLength());
            //g.setColor(Color.BLACK);
            //g.drawString(getNum() + "", (int) (getLocationX() + 17), (int) getLocationY() + 25);
        }

    }
}
