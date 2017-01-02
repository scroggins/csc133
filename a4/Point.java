/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4;

/**
 *
 * @author Randy
 */
public class Point extends java.awt.Point {
    private double x,y ; //the coordinates of the point
       //overloaded constructors: blank and filled
       public Point () {
          x = 0.0 ;
          y = 0.0 ;
       }
       public Point (double xVal, double yVal) {
          x = xVal ;
          y = yVal ;
       }//the mutators to set the xVal yVal or both at once
       public void setPoint(double xVal, double yVal){
            x = xVal;
            y = yVal;
       }
       public void setX(double xVal){
           x =xVal;
       }
       public void setY(double yVal){
           y = yVal;
       }//accessors to get the values for x and y
       public double getX(){
         return x;
       }
       public double getY(){
         return y;
       }
       //overloaded methods:
       /** Returns the distance between this point and the other point */
       public double distance (Point otherPoint) {
          double dx = x - otherPoint.x ;
          double dy = y - otherPoint.y ;
          return Math.sqrt (dx*dx + dy*dy) ;
       }
       /** Returns the distance between this point and a location */
       public double distance (double xVal, double yVal) {
          double dx = x - xVal ;
          double dy = y - yVal ;
          return Math.sqrt (dx*dx + dy*dy) ;
       }
       /** Returns the distance between this point and the origin */
       public double distance () {
         return Math.sqrt (x*x + y*y) ;
    }
}
