/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4.gameObjects;
import a4.Point;
import a4.interfaces.ICollider;
import a4.interfaces.IDrawable;

import java.awt.*;
import java.util.*;
/**
 *
 * @author Randy
 */
public abstract class GameObjects implements ICollider, IDrawable {
    private Point location;
	private Color color;
     public GameObjects(){
         setColor();
         location = new Point(random(800),random(900));
     }//accessor
     public Color getColor(){
         return color;
     }
     public Point getLocation(){
     	return location;
     }
     public double getLocationX(){
         return location.getX();
     }//mutators
     public double getLocationY(){
      return location.getY();
      }
     public void setColor(){
         color = new Color(random(254),random(254),random(254));
     }
     public void setLocation(double x, double y){
         location.setX(x);
         location.setY(y);
     }
     public int random(int y){//random
         Random num = new Random();
         int x = num.nextInt(y);
         return x;
     }
    public void draw(Graphics o){

    }

    @Override
    public void handleCollision(ICollider o) {

    }

    @Override
    public boolean CollidesWith(ICollider o) {
        return false;
    }


    abstract public int getX();
        //return 0;



    abstract public int getY();
        //return 0;



    abstract public int getWidth();
        //return 0;



    abstract public int getLength();
        //return 0;

}
