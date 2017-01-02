package a4;

import a4.interfaces.IGameWorld;
import a4.interfaces.IObserver;
import a4.interfaces.Iterator;

/**
 * Created by Asus on 4/21/2015.
 */
public class GameWorldProxy implements IGameWorld/*, IAudioClip*/ {
    private GameWorld gw;

    public GameWorldProxy(GameWorld gaw){
        gw = gaw;
    }

    public Iterator getIterator() {
        return gw.getIterator();
    }
    public void about(){
        gw.about();
    }



    public void accelerate() {
        gw.accelerate();
    }

    public void pause(){
        gw.pause();
    }

    public void newPoint(Point e){
        gw.setPoint(e);
    }


    public void addOilSlick() {
        gw.addOilSlick();
    }


    public void hitBird() {
        gw.hitBird();
    }


    public void brake() {
        gw.brake();
    }


    public void changeStrategy() {
        gw.changeStrategy();
    }


    public void collideWithPylon() {
        gw.collideWithPylon();
    }


    public void collision() {
        gw.collision();
    }


    public void e() {
        gw.e();
    }

    public void addFuel(){//--------------------------work in progress---------------------
        //System.out.println("in the fuel  proxy");
        gw.add(gw.createFuel());
    }
    public void addPylon(){
        //System.out.println("in the Pylon  proxy");
        gw.addPylonAt();
    }


    public void x() {
        gw.x();
    }

//    public int getPX(){
//        gw.pointgetX();
//    }

//    public int getPY


    public void fuel() {
        gw.fuel();
    }


    public void left() {
        gw.left();
    }


    public void n() {
        gw.n();
    }


    public void quit() {
        gw.quit();
    }


    public void right() {
        gw.right();
    }


    public void t() {
        gw.t();
    }


    public void notifyObservers() {
        gw.notifyObservers();
    }

    public void addObservers(IObserver x) {
        gw.addObservers(x);
    }


    public int getLives() {
        return gw.getLives();
    }


    public int getTimeClock() {
        return gw.getTimeClock();
    }


    public void initLayout() {
        gw.initLayout();
    }

    @Override
    public void addFuelAt() {
        gw.addFuelAt();
    }

    @Override
    public void changeSound() {
        gw.changeSound();
    }

    public String getSound() {
        return gw.getSound();
    }

//    @Override
//    public void play() {
//        gw.play();
//    }
//
//    @Override
//    public void loop() {
//        gw.loop();
//    }
//
//    @Override
//    public void stop() {
//        gw.stop();
//    }
}
