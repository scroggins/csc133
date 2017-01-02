/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4.gameObjects;

/**
 *
 * @author Randy
 */
public abstract class MovObj extends GameObjects {
	private float heading;
	private float speed;
	public MovObj(float newHeading,float newSpeed){
		heading = newHeading;
		speed = newSpeed;
	}//mutators
        public MovObj(){
        heading = (float) 0.0;
        speed = (float) 0.0;
        
        }
	public void setSpeed(float newSpeed){
		speed = newSpeed;
	}//accessors
	public float getHeading(){
		return (heading);
	}
	public float getSpeed(){
		return speed;
	}
	public void move(){
    	float deltaX = (float) Math.cos(heading) * speed;
    	float deltaY = (float) Math.sin(heading) * speed;
        setLocation(getLocationX() + deltaX, getLocationY() + deltaY ); 

	}
    
}
