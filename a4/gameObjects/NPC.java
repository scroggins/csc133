/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4.gameObjects;

import a4.GameWorldProxy;
import a4.interfaces.ICollider;
import a4.strategies.ToThePylonNPC;
import a4.interfaces.IStrategy;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 *
 * @author Randy
 */
public class NPC extends MovObj implements IStrategy, ICollider {
	private int width;//made cars abstract so i can override heading variable
	private int length;
	private float steeringDirection;
	private float fuelLevel;
	private float damageLevel;
	private float maxDamage;
	private float maxSpeed;
    private float heading;
    private ToThePylonNPC pylon;
    private int highestPylonHit;
    private boolean inOilSlick;
    private IStrategy strategy;
    private float currentSpeed;
    private Random random;
    private AffineTransform screenXform;
    //private ChangeStrategiesCommand change;
    private Random r;
	public NPC(int i, GameWorldProxy gaw){

            strategy =new ToThePylonNPC(this, gaw);
            width = 20;
            length = 30;
            steeringDirection = (float) 0;
            heading = (float) (0);
            fuelLevel = (float) 20.0;
            damageLevel = (float) 0.0;
            maxDamage = (float) 50.0;
            maxSpeed = (float) 10.0;
            currentSpeed = (float) 3.0;
            highestPylonHit = 1;
            setLocation(40,i*40);
            inOilSlick = false;
            //change = new ChangeStrategiesCommand(null);
            
        }
    public void apply(){
        strategy.apply();
    }
	public NPC(int newWidth,int newLength, float newSteeringDirection, float newMaxSpeed, float newFuelLevel, float newDamageLevel, float newMaxDamage){
		width = newWidth;
		length = newLength;
		steeringDirection = newSteeringDirection;
		maxSpeed = newMaxSpeed;
		fuelLevel = newFuelLevel;
		damageLevel = newDamageLevel;
		maxDamage = (float) 30.0;
        currentSpeed = (float) 0.0;
        highestPylonHit = 0;
                
	}//*********accessors**********

    @Override
    public int getX() {
        return (int) this.getLocationX();
    }

    @Override
    public int getY() {
        return (int)this.getLocationY();
    }

    public int getWidth(){
		return width;
	}
    public int getHighestPylonHit(){
        return highestPylonHit;
    }
    public IStrategy getStrategy(){
            return strategy;
        }
	public int getLength(){
		return length;
	}
    public float getCurrentSpeed(){
         return currentSpeed;
     }
    //public float getSteeringDirection(){
    	//return steeringDirection;
    //}
    public float getMaxSpeed(){
    	return maxSpeed;
    }
    public float getFuelLevel(){
    	return fuelLevel;
    }
    public float getDamageLevel(){
    	return damageLevel;
    }
    public float getHeading(){
        return heading;
    }
    public float getMaxDamage(){
    	return maxDamage;
    }//*******mutators*******
    public void setWidth(int newWidth){
    	width = newWidth;
    }
    public void setLength(int newLength){
    	length = newLength;
    }
    public void setHeading(double x){//took the float x out of the setheading
          heading = (float) x;
    }
    public void setHighestPylonHit(int pylonNum ){
        highestPylonHit = pylonNum;
    }
    public void setInOilSlick(boolean x){
        inOilSlick = x;
    }
    public void setSteeringDirection(float newSteeringDirection){   
            steeringDirection = newSteeringDirection;
    }
    public void setMaxSpeed(float newMaxSpeed){
    	maxSpeed = newMaxSpeed;
    }
    public void setFuelLevel(float newFuelLevel){
    	fuelLevel = newFuelLevel;
    }
    public void setDamageLevel(float newDamageLevel){
    	damageLevel = newDamageLevel;
    }
    public void setCurrentSpeed(float newSpeed){
        currentSpeed = newSpeed;
    }///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setStrategy(IStrategy s) {
        strategy= s; 
   }
    public void invokeStrategy() 
    {
        strategy.apply();
   }///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void move(){//move had to be overridden from the parent so cars could change direction


        invokeStrategy();
        double theta =(double) (/*getSteeringDirection() +*/ getHeading());
        float deltaX = (float) Math.cos(theta) * getCurrentSpeed();
    	float deltaY = (float) Math.sin(theta) * getCurrentSpeed();

        setLocation(getLocationX() + deltaX, getLocationY() + deltaY );
        this.setFuelLevel(this.getFuelLevel()-((float).01));
        r = new Random();
        int x = r.nextInt(30);
        if (x < 7){

        }
    }
    public String toString(){
    	String x =("NPC: loc = ["+this.getLocationX()+", "+this.getLocationY()+"] "+ " color = "+this.getColor()+" heading ="+heading+" speed="+ this.getCurrentSpeed()+" width= "+this.getWidth()+" length=" +this.getLength()+" maxSpeed="+this.getMaxSpeed()+ " steeringDirection= "+0.0/*this.getSteeringDirection() */+ " fuelLevel= "+this.getFuelLevel()+ " damage= "+this.getDamageLevel());
        return x;
    }
    public boolean CollidesWith(ICollider o) {
        if ((o.getX() < this.getX() + this.getWidth() && this.getX() + this.getWidth() < o.getX() + o.getWidth()) ||
                (o.getX() < this.getX() && this.getX() < o.getX() + o.getWidth())) {
            if ((o.getY() < this.getY() + this.getLength() && this.getY() + this.getLength() < o.getY() + o.getLength()) ||
                    (o.getY() < this.getY() && this.getY() < o.getY() + o.getLength())) {
                //this.getY() + this.getLength() < o.getY() || (this.getY()) > o.getY()+o.getLength()){
                //System.out.println("Car has not collided with other object");
                if (o instanceof Pylon) {
                    return true;
                }
                return false;//set this up so the npc wont be hitting the car when the car hits the npc

            }
            //System.out.println("CAR HAS COLLIDED WITH OTHER OBJECT");


        }
        return false;
    }
        @Override
        public void handleCollision(ICollider o) {
           /* if(o instanceof Fuel){
                System.out.println("Car has hit Fuel Can");
                fpc = fpc.getFuelPickUpCommand(null);
                System.out.println("\nthis.getX = "+this.getX() + "to this.getX()+this.getWidth() = " + (this.getX()+this.getWidth()));
                System.out.println("o.getX() = "+o.getX() + "to o.getX()+o.getX()+o.getWidth() = " + (o.getX()+o.getWidth())+"\n");
                System.out.println("this.getY() = "+this.getY() + "to this.getY()+this.getLength() = " + (this.getY()+this.getLength()));
                System.out.println("o.getY() = "+o.getY() + "to o.getY()+o.getLength() = " + (o.getY()+o.getLength()));
                fpc.actionPerformed(null);
                //gw.fuel();
            } else*/ if (o instanceof Pylon)
            {
                //System.out.println("NPC has hit Pylon");
            }/* else if (o instanceof Birds)
            {
                System.out.println("Car has hit a bird");
            } else if (o instanceof OilSlick)
            {
                System.out.println("Car has hit a oil slick");
            }*/
        }
    public void draw(Graphics g){
        g.setColor(this.getColor());
        g.drawRect((int) (this.getLocationX()), (int) (this.getLocationY()), this.getWidth(), this.getLength());
    }
}