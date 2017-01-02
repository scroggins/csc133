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
public class OilSlick extends FixedObj implements ICollider {
    private int length;//variable to delare the width and the lenth
    private int width;//of the oil slick
    private AffineTransform screenXform;

    public OilSlick() {
        length = 40;
        width = 15;
    }

    @Override
    public int getX() {

        return (int) this.getLocationX();
    }

    @Override
    public int getY() {

        return (int) this.getLocationY();
    }


    public OilSlick(int newLength, int newWidth) {
        length = newLength;// constructor to make the oil slick
        width = newWidth;//with a certain width and length
    }

    public void setlength(int newLength) {//????????????should i put the width and length together
        length = newLength;//mutator to set the length of the oil slick
    }

    public void setWidth(int newWidth) {//////?????????????? same qeustion??
        width = newWidth;//mutator to set the width of the 
    }

    public int getLength() {
        return length;//accessor to return to get the length of the oil slick
    }

    public int getWidth() {
        return width;// the accessor to return the width of the oil slick
    }

    public String toString() {
        String y = ("OilSlick: loc= [" + getLocationX() + ", " + getLocationY() + "] color=" + getColor() + " width= " + getWidth() + " length= " + getLength());
        return y;
    }

    public void draw(Graphics g) {


    g.setColor(getColor());
    //g.drawOval((int) (x.getLocationX()), (int) (x.getLocationY()), ((OilSlick) x).getWidth(), ((OilSlick) x).getLength());
    g.fillOval((int)(getLocationX()),(int)(getLocationY()),getWidth(),getLength());


    }
}
