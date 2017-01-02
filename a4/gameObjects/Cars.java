/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4.gameObjects;

import a4.commands.*;
import a4.interfaces.ICollider;
import a4.interfaces.IDrawable;
import a4.interfaces.ISteerable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 *
 * @author Randy
 */
public class Cars extends MovObj implements ISteerable, ICollider, IDrawable {
	private int width;//made cars abstract so i can override heading variable
	private int length;
	private float steeringDirection;
	private float fuelLevel;
	private float damageLevel;
	private float maxDamage;
	private float maxSpeed;
        private float heading;
        private boolean inOilSlick;
        private int currentStrategy;
    private int highestPylonHit;
    private float currentSpeed;
    private double distanceModifier;
    private FuelPickUpCommand fpc;
    private CollidePylonCommand pylon;
    private EnterOilSlickCommand oil;
    private BirdCollisionCommand birdy;
    private CollisionCommand npc;
    private AddOilSlickCommand addOil;
    private AffineTransform screenXform;
    private Random r;
    private int count;
    public boolean isInOilSlick() {
        return inOilSlick;
    }

    public Cars(){
        count =0;
            width = 20;
            length = 30;
            steeringDirection = (float) 0;
            heading = (float) (0);
            fuelLevel = (float) 1000.0;
            damageLevel = (float) 0.0;
            maxDamage = (float) 30.0;
            maxSpeed = (float) 30.0;
            currentSpeed = (float) 0.0;
            setLocation(0,0);
            inOilSlick = false;
        highestPylonHit = 1;
        currentStrategy = 0;
        distanceModifier = 1;//work on this later
        }
	public Cars(int newWidth,int newLength, float newSteeringDirection, float newMaxSpeed, float newFuelLevel, float newDamageLevel, float newMaxDamage){
		width = newWidth;
		length = newLength;
		steeringDirection = newSteeringDirection;
		maxSpeed = newMaxSpeed;
		fuelLevel = newFuelLevel;
		damageLevel = newDamageLevel;
		maxDamage = (float) 30.0;
                currentSpeed = (float) 0.0;
            highestPylonHit = 1;
            currentStrategy = 0;
	}//*********accessors**********

	public int getWidth(){
		return width;
	}
	public int getLength(){
		return length;
	}
    public float getCurrentSpeed(){
         return currentSpeed;
     }
    public float getSteeringDirection(){
    	return steeringDirection;
    }
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
    }
    public int getHighestPylonHit(){
        return highestPylonHit;
    }
    public int getStrategy(){
        return currentStrategy;
    }//*******mutators*******

    public void setWidth(int newWidth){
    	width = newWidth;
    }
    public void setStrategy(int k){
        currentStrategy = k;
    }
    public void setHighestPylonHit(int pylonNum ){
        highestPylonHit = pylonNum;
    }
    public void setLength(int newLength){
    	length = newLength;
    }
    public void setHeading(double x){//took the float x out of the setheading
          heading = (float) x;
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
    }
    public void move(){//move had to be overridden from the parent so cars could change directio
        this.setFuelLevel(this.getFuelLevel()-(float).5);
        double theta;
        if(inOilSlick ){
         theta = getHeading();
            count++;
            if(count == 200){
                inOilSlick = false;
                count = 0;
            }
        }else{
            double otherHeading = (double) getHeading();
            theta =(double) (getSteeringDirection() + getHeading());
            setHeading(theta);
        }
        
    	float deltaX = ((float) Math.cos(theta) * getCurrentSpeed());
    	float deltaY = (float) Math.sin(theta) * getCurrentSpeed();
        setLocation((int) distanceModifier*(getLocationX() + deltaX),(int) distanceModifier*( getLocationY() + deltaY ));
    }
    public int getHighestPylon(){
        return highestPylonHit;
    }
    public String toString(){
    	String x =("Car: loc = ["+this.getLocationX()+", "+this.getLocationY()+"] "+ "Last Pylon hit = " +this.getHighestPylon() +" color = "+this.getColor()+" heading ="+heading+" speed="+ this.getCurrentSpeed()+" width= "+this.getWidth()+" length=" +this.getLength()+" maxSpeed="+this.getMaxSpeed()+ " steeringDirection= "+this.getSteeringDirection() + " fuelLevel= "+this.getFuelLevel()+ " damage= "+this.getDamageLevel());
        return x;
    }

    @Override
    public void handleCollision(ICollider o) {
        if(o instanceof Fuel){
            System.out.println("Car has hit Fuel Can");
            fpc = fpc.getFuelPickUpCommand(null);
            //System.out.println("\nthis.getX = "+this.getX() + "to this.getX()+this.getWidth() = " + (this.getX()+this.getWidth()));
            //System.out.println("o.getX() = "+o.getX() + "to o.getX()+o.getX()+o.getWidth() = " + (o.getX()+o.getWidth())+"\n");
            //System.out.println("this.getY() = "+this.getY() + "to this.getY()+this.getLength() = " + (this.getY()+this.getLength()));
            //System.out.println("o.getY() = "+o.getY() + "to o.getY()+o.getLength() = " + (o.getY()+o.getLength()));
            fpc.actionPerformed(null);
            //gw.fuel();
        } else if (o instanceof Pylon)
        {
            pylon = pylon.getCollidePylonCommand(null);
            if(((Pylon) o).getNum() == this.getHighestPylonHit()+1) {
                pylon.actionPerformed(null);
                System.out.println("next pylon hit");
            }
            System.out.println("Car has hit Pylon");
        } else if (o instanceof Birds)
        {
            birdy = birdy.getBirdCollisionCommand(null);
            birdy.actionPerformed(null);
            System.out.println("Car has hit a bird");
        } else if (o instanceof OilSlick)
        {
            oil = oil.getEnterOilSlickCommand(null);
            oil.actionPerformed(null);
            System.out.println("Car has hit a oil slick");
            count = 0;
        }
        else if (o instanceof NPC)
        {
            npc = npc.getCollisionCommand(null);
            npc.actionPerformed(null);
            r = new Random();
            int x = r.nextInt(5)+1;
            if(x == 1){
                addOil = addOil.getOilSlickCommand(null);
                addOil.actionPerformed(null);
            }

            System.out.println("Car has hit an npc");
        }
    }

    @Override
    public boolean CollidesWith(ICollider o) {
        if((o.getX() < this.getX() + this.getWidth() && this.getX()+this.getWidth() < o.getX()+o.getWidth()) ||
                (o.getX() < this.getX()  && this.getX() < o.getX()+o.getWidth())) {
            if((o.getY() < this.getY() + this.getLength() && this.getY()+this.getLength() < o.getY()+o.getLength()) ||
                    (o.getY() < this.getY()  && this.getY() < o.getY()+o.getLength())){
                    //this.getY() + this.getLength() < o.getY() || (this.getY()) > o.getY()+o.getLength()){
                //System.out.println("Car has not collided with other object");
                return true;
            }
        }
        //System.out.println("CAR HAS COLLIDED WITH OTHER OBJECT");

        return false;
    }

    @Override
    public int getX() {
        return (int) this.getLocationX() /*- this.getWidth()/2*/;
    }

    @Override
    public int getY() {
        return (int) this.getLocationY() /*- this.getLength()/2*/;
    }
    public void draw(Graphics g){
//        //todo get this affine transfermation to work
        AffineTransform screenXform = new AffineTransform();
        screenXform.scale (1, -1);
        screenXform.translate((double) getX(), (double) getY());
//        screenXform.translate (0, panel.getHeight());
       //screenXform.scale (1, -1);

        g.setColor(this.getColor());
        g.fillRect((int)(this.getLocationX()),(int) (this.getLocationY()),this.getWidth(),this.getLength());
    }
}
