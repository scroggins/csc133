/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4;

import a4.gameObjects.*;
import a4.interfaces.*;
import a4.strategies.DestructionDerbyStrategy;
import a4.strategies.ToThePylonNPC;

import javax.swing.*;
import java.applet.Applet;
import java.io.File;
import java.util.Random;
import java.util.Vector;

import java.applet.*;

/**
 *
 * @author Randy
 */

public class GameWorld implements IObservable/*, IAudioClip */{

    //ArrayList<GameObjects> gameCollection = new ArrayList<GameObjects>();//creating my arraylist hold all my gameWorld variables
    private GameCollection gameCollection = new GameCollection();
    private AudioClip looper;
    private AudioClip npcCollisionSound;
    private AudioClip fuelCollisionSound;
    private AudioClip lifeLostSound;
    private int currentLives;//keeps track of the players lives
    private int timeClock;//to keep track of the time clock
    private int lastPylon;
    private GameObjects gO;
    private ToThePylonNPC pylon ;
    private int counter;
    private DestructionDerbyStrategy derby;
    private String sound;
    private Cars car;
    private NPC npc1;
    private NPC npc2;
    private NPC npc3;
    private Iterator myIter, myIter2;//to create my
    private Vector observer= new Vector();
    private int pylonNum;
    private GameWorldProxy gP = new GameWorldProxy(this);
    private boolean currPaused = false;
    private Random random;
    private Point point;
    private boolean del = false;
    private String backRoundFileName = "gun_battle_sound-ReamProductions-1158375208.wav";
    private String CarCollisionSoundFile = "Plastic_Bottle_Crush-Simon_Craggs-140131346";
    private String fuelPickUpCommandFile = "pin_dropping-Brian_Rocca-2084700791";
    private String loseALifeSoundFile = "Slap-SoundMaster13-49669815";
    private  String soundDir = "." + File.separator + "Sounds" + File.separator ;//path to folder where sounds are found

    //private String fileName = "";
    // private String soundDir = "." + File.separator + "sounds" + File.separator ;




    public  GameWorld(){
        //system.out.println("  THe poopie");

    }
    public Iterator getIterator(){
        return gameCollection.getIterator();
    }
    public void remove(GameObjects x){
        gameCollection.remove(x);
    }
    public GameCollection getGameCollection(){
        return gameCollection;
    }
    public GameWorldProxy getGameWorldProxy(){
        return gP;
    }

    @Override
    public void addObservers(IObserver obs) {
        observer.add(obs);
    }

    public void notifyObservers(){
        for (Object o : observer) {
            ((IObserver)o).update(this);
        }
    }
    public void initLayout(){
        random = new Random();
        sound = "Off";//
        //looper.loop();
    	//Cars myCar = new Cars();//creating my car
        point = new Point();
        gameCollection.add(createCar());
        gameCollection.add(createPylon(1));
        counter = 0;
        //gameCollection.add(pylon);
        for(int j = 0 ; j<3;j++){//for loop to create my initial objects
           if(j<2){//if statement keeps the objects bird oil and fuel at 2 i make at 
               gameCollection.add(createBird());
               gameCollection.add(createOilSlick());
               gameCollection.add(createFuel());
           }
           gameCollection.add(createPylon(j + 2));//numbers the pylons }
            gameCollection.add(createNPC(j));//create an npc
    	}
    	timeClock = 0;
    	currentLives = 3;
    	lastPylon = 1;


        //
        //private String filePath = soundDir + fileName ;
        //String soundDir = "." + File.separator + "Sounds" + File.separator ;//path to folder where sounds are found
        String filePath = soundDir + backRoundFileName;//concat sound to path

        try {
            //System.out.println("ADDING looper SOUNDS");
            looper = Applet.newAudioClip(new File(filePath).toURI().toURL());
            System.out.println(new File (filePath).toURI().toURL() + "");
          //looper.loop();
        }
        catch (Exception e) {
            throw new RuntimeException("Problem with " + backRoundFileName + ": " + e);
        }
        //  observer = new Vector();
    }
    private int getTime(){
        return timeClock;
    }
    public int getCurrentLives(){
        return currentLives;
    }
    public void setCurrentLives(int newLives){
        currentLives = newLives;
    }
    public Pylon createPylon(int j){
        //Pylon pylon = new Pylon(j);
        //gameCollection.add(pylon);
        return new Pylon(j);
    }
    public void setPoint(Point p){//
        point = p;
        System.out.println("point is now "+ point.getX() + " " + point.getY());
    }
    public void add(GameObjects x){
        gameCollection.add(x);
    }
    public NPC createNPC(int i){
        //NPC npc = new NPC(i, this);
        //gameCollection.add(npc);
        return new NPC(i, gP);
    }
    public Cars createCar(){
        //Cars car = new Cars();
        //gameCollection.add(car);
        return new Cars();
    }
    public Birds createBird(){
        //Birds bird = new Birds();
        //gameCollection.add(bird);
        return new Birds();
    }
    public OilSlick createOilSlick(){
        //OilSlick oilSlick = new OilSlick();
        return new OilSlick();
        //gameCollection.add(oilSlick);
    }
    public Fuel createFuel(int x, int y){
        return new Fuel (x,y);
    }
    public Fuel createFuel(){
        //if(point.getX() ==0 && point.getY() == 0)
        return new Fuel();
        //else {

        //}
        //gameCollection.add(fuel);
    }

    public String toString(){
        String t = "current lives: "+currentLives +"\ntime: "+ timeClock+"\nPylon: "+lastPylon+"\n";
        return t;
    }//car vroom vroom
    public void accelerate(){
        if(currPaused){

        }else {
            myIter = gameCollection.getIterator();
            //System.out.println("im accelerating");
            while (myIter.hasNext()) {
                GameObjects x = (GameObjects) myIter.getNext();
                if (x instanceof Cars) {
                    //System.out.println("im accelerating");
                    if (((Cars) x).getCurrentSpeed() < ((Cars) x).getMaxSpeed()) {
                        float y = (((Cars) x).getCurrentSpeed() + 1);
                        ((Cars) x).setCurrentSpeed(y);
                    }
                }
            }
        }
    }///screech
    public void changeStrategy(){
        myIter = getIterator();
        while(myIter.hasNext()){
            gO  =(GameObjects) myIter.getNext();
            if (gO instanceof NPC){
                pylon = new ToThePylonNPC((NPC)gO, gP);
                derby = new DestructionDerbyStrategy((NPC)gO, gP);
                if (((NPC)gO).getStrategy() instanceof DestructionDerbyStrategy){
                    ((NPC)gO).setStrategy(pylon);
                    System.out.println("pylon");
                }else{
                    ((NPC)gO).setStrategy(derby);
                    System.out.println("derby");
                }
            }
        }
    }
    public void collideWithPylon() {
       // pylonNum = -1;

        //String x = JOptionPane.showInputDialog("Please input a pylon number");
        //try{
        // pylonNum = Integer.parseInt(x);
        //}
        //catch (NumberFormatException q){
        //  System.out.println("please input a valid number");
        //}


        myIter = getIterator();
        while (myIter.hasNext()) {//find my car
            gO = (GameObjects) myIter.getNext();
            if (gO instanceof Cars) {
                car = ((Cars) gO);
                car.setHighestPylonHit(car.getHighestPylon() + 1);
                break;
            }
            //if (gO instanceof NPC) {
              //  npc1 = ((NPC) gO);
            //}

        }
        //myIter = getIterator();
        //while (myIter.hasNext()) {
          //  GameObjects y = (GameObjects) myIter.getNext();
            //if (y instanceof Pylon && ((Pylon)y).getNum()==pylonNum){
           // if (pylonNum == car.getHighestPylonHit() + 1)
              //  car.setHighestPylonHit(car.getHighestPylon() + 1);

        //}
    }
    //}
    public void about(){
        System.out.println("\n\n\n");
        System.out.println("Hello I am Randy and i fight I.D.E's all day evey day except jgrasp we are buddies like that :)");
        System.out.println("\n\n\n");
    }
    public void addOilSlick(){

        gameCollection.add(createOilSlick());
    }
   public void brake(){
       if(currPaused){

       }else {
           myIter = gameCollection.getIterator();
           while (myIter.hasNext()) {
               {
                   gO = (GameObjects) myIter.getNext();

                   if (gO instanceof Cars) {
                       if (((Cars) gO).getCurrentSpeed() > 0) {
                           float y = (((Cars) gO).getCurrentSpeed() - 1);
                           ((Cars) gO).setCurrentSpeed(y);
                       }
                   }
               }
           }
       }
    }//going left
    public void left(){
        if(currPaused){

        }else {
            myIter = gameCollection.getIterator();
            while (myIter.hasNext()) {
                gO = (GameObjects) myIter.getNext();
                if (gO instanceof Cars) {
                    if (((Cars) gO).getSteeringDirection() > -.698131) {
                        float y = ((Cars) gO).getSteeringDirection() - (float) .08726646;
                        ((Cars) gO).setSteeringDirection(y);
                    }
                }
            }
        }
    }
    public void right(){
        if(currPaused){

        }else {
            myIter = gameCollection.getIterator();
            while (myIter.hasNext()) {
                gO = (GameObjects) myIter.getNext();
                if (gO instanceof Cars) {
                    if (((Cars) gO).getSteeringDirection() < .698131) {
                        float y = ((Cars) gO).getSteeringDirection() + (float) .08726646;
                        ((Cars) gO).setSteeringDirection(y);
                    }
                }
            }
        }
    }
    public void oilSlick(){

        gameCollection.add(createOilSlick());
    }
    public void e(){
        myIter = gameCollection.getIterator();
            while(myIter.hasNext()){ 
            gO =(GameObjects) myIter.getNext();
            if (gO instanceof Cars){
               ((Cars)gO).setInOilSlick(true);
            }   
        }
    }//exit the oil slick
    public void x(){
        myIter = gameCollection.getIterator();
            while(myIter.hasNext()){ 
            gO =(GameObjects) myIter.getNext();
            if (gO instanceof Cars){
               ((Cars)gO).setInOilSlick(false);
            }   
        }
    }//colors
    public void n(){
         myIter = gameCollection.getIterator();
            while(myIter.hasNext()){ 
            gO =(GameObjects) myIter.getNext();
             gO.setColor();
         }
    }//display
    public void d(){
        String t = "";
         myIter = gameCollection.getIterator();
            while(myIter.hasNext()){ 
            gO =(GameObjects) myIter.getNext();
            if (gO instanceof Cars){
                t = "Fuel: "+ ((Cars)gO).getFuelLevel()+"\nDamage: "+ ((Cars)gO).getDamageLevel();
            }
        }
        System.out.println(toString()+ t);
    }//grabbing fuel
    public void addFuelAt(){
        if (point.getX() ==0.0 && point.getY() ==0.0){

        }
        else {
            Fuel x = new Fuel(((int) point.getX()), (int) point.getY());
            gameCollection.add(x);
            point.setX(0.0);
            point.setY(0.0);
        }
    }
    public void addPylonAt(){
        if (point.getX() ==0.0 && point.getY() ==0.0){

        }
        else {
            pylonNum = -1;

            String x = JOptionPane.showInputDialog("Please input a pylon number");
            try{
            pylonNum = Integer.parseInt(x);
            }
            catch (NumberFormatException q){
              System.out.println("please input a valid number");
            }
            Pylon q = new Pylon( pylonNum,((int) point.getX()), (int) point.getY());
            gameCollection.add(q);
            point.setX(0.0);
            point.setY(0.0);
        }
    }

    public void fuel() {
        //float z = (float) 0.0;

        String filePath = soundDir +  fuelPickUpCommandFile;//concat sound to path
        //String filePath = soundDir +  fuelPickUpCommandFile;//concat sound to path

        try {
            System.out.println("ADDING SOUNDS");
            fuelCollisionSound = Applet.newAudioClip(
                    new File(filePath).toURI().toURL());
            System.out.println(new File (filePath).toURI().toURL() + "");
            if(sound.equalsIgnoreCase("On")) {
                looper.stop();
                fuelCollisionSound.play();
                looper.loop();
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Problem with " +  fuelPickUpCommandFile + ": " + e);
        }
        myIter = gameCollection.getIterator();
        while (myIter.hasNext()) {//this loop returns my car
            gO = (GameObjects) myIter.getNext();
            if (gO instanceof Cars) {
                car = (Cars) gO;
            }
        }
        myIter = gameCollection.getIterator();
        while (myIter.hasNext()) {
            gO = (GameObjects) myIter.getNext();
            if (gO instanceof Fuel) {
                if (gO.CollidesWith((ICollider) car)) {
                    car.setFuelLevel(car.getFuelLevel() + ((Fuel) gO).getSize());
                    gameCollection.remove(gO);
                    gameCollection.add(createFuel());
                    break;
                }
            }
        }
    }/*
            myIter = gameCollection.getIterator();
            while (myIter.hasNext()) {
                gO = (GameObjects) myIter.getNext();
                if (gO instanceof Cars) {
                    ((Cars) gO).setFuelLevel(((Cars) gO).getFuelLevel() + z);
                    break;
                }
            }
        } */ //hit a bird




    public void hitBird(){
        myIter = gameCollection.getIterator();
        while(myIter.hasNext()){
            gO =(GameObjects) myIter.getNext();
            if (gO instanceof Cars){
                //System.out.println("collide with Bird");
                ((Cars)gO).getDamageLevel();
                ((Cars)gO).setDamageLevel(((Cars)gO).getDamageLevel()+1);
                float g =((Cars)gO).getMaxSpeed()-1;
                ((Cars)gO).setMaxSpeed(g);
                if((((Cars)gO).getMaxSpeed())< ((Cars)gO).getCurrentSpeed())
                    ((Cars)gO).setCurrentSpeed(((Cars)gO).getMaxSpeed());
                if ((((Cars)gO).getMaxDamage())<=(((Cars)gO).getDamageLevel())){
                    System.out.println("life lost");
                    remove(gO);
                    setCurrentLives(getCurrentLives() - 1) ;
                    if(getCurrentLives()==0){
                        System.out.println("game Over");
                        System.exit(0);//the player has run out of lives there fore the game ends
                    }
                    else{
                        gameCollection.add(createCar());
                        break;
                    }
                }
            }
        }
    }
    public void collision(){


        String filePath = soundDir +  CarCollisionSoundFile;//concat sound to path
        //String filePath = soundDir +  fuelPickUpCommandFile;//concat sound to path

        try {
            //System.out.println("ADDING SOUNDS");
            npcCollisionSound = Applet.newAudioClip(new File(filePath).toURI().toURL());
            System.out.println(new File (filePath).toURI().toURL() + "");
            if(sound.equalsIgnoreCase("On")) {
                looper.stop();
                npcCollisionSound.play();
                looper.loop();
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Problem with " +  fuelPickUpCommandFile + ": " + e);
        }
             myIter = gameCollection.getIterator();
             while(myIter.hasNext()){ 
                gO =(GameObjects) myIter.getNext();
            if (gO instanceof Cars){
                float y =((Cars)gO).getDamageLevel()+3;
                ((Cars)gO).setDamageLevel(y);
                float g =((Cars)gO).getMaxSpeed()-3;
                ((Cars)gO).setMaxSpeed(g);
                if((((Cars)gO).getMaxSpeed())< ((Cars)gO).getCurrentSpeed())
                   ((Cars)gO).setCurrentSpeed(((Cars)gO).getMaxSpeed());
                if ((((Cars)gO).getMaxDamage())<=(((Cars)gO).getDamageLevel())){
                    System.out.println("life lost");
                    gameCollection.remove(gO);
                    currentLives--;
                       filePath = soundDir +  loseALifeSoundFile;//concat sound to path
                    try {
                        //System.out.println("ADDING SOUNDS");
                        lifeLostSound = Applet.newAudioClip(new File(filePath).toURI().toURL());
                        System.out.println(new File(filePath).toURI().toURL() + "");
                        if (sound.equalsIgnoreCase("On")) {
                            looper.stop();
                            lifeLostSound.play();
                            looper.loop();
                        }
                    }
                    catch (Exception e) {
                        throw new RuntimeException("Problem with " +  fuelPickUpCommandFile + ": " + e);
                    }
                    if(currentLives==0){
                        System.out.println("game Over");
                        System.exit(0);//the player has run out of lives there fore the game ends
                    }
                    else{
                        gameCollection.add(createCar());
                        break;
                    }
                }
            }   
        }
    }
    public void quit(){
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Do you wanna close the window?",
                "Choose",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    /*
	 public void pylon(String x){
	 boolean pylonFound = false;
	 	if(x.length()==3){//the three character case
	 		String s = x.charAt(1)+"";
				if (s.equals("0")){
               String w =x.charAt(2)+"";
               p = Integer.parseInt(w);
               for(GameObjects k : gameCollection){
                   if (k instanceof Pylon && ((Pylon)k).getNum()==p){
                       pylonFound = true;
                       if(p == lastPylon+1)
                           lastPylon++;
                   }
               }
           }
				else{// if p is not followed by 0 and has 3 chars
                  p = Integer.parseInt(x.substring(1,3));
                  for(GameObjects k : gameCollection){
                      if (k instanceof Pylon && ((Pylon)k).getNum()==p){
                          pylonFound = true;
                          if(p == lastPylon+1)
                              lastPylon++;
                      }
                  }
				}
			}else{//the two character case
				String w =x.charAt(1)+"";
				p = Integer.parseInt(w);
				for(GameObjects k : gameCollection){
					if (k instanceof Pylon && ((Pylon)k).getNum()==p){
                                            pylonFound = true;
						if (p ==lastPylon+1){
                                                        lastPylon++;
						}
					}
				}
			}
			if(pylonFound == false)
				System.out.println("pylon does not exist");
	 }*/
    public void pause(){
        if(currPaused){
            currPaused = false;
            del = false;
            looper.loop();
        }else{
            looper.stop();
            currPaused = true;
            del = true;
        }
    }
    public void createPylon(){
        myIter = gameCollection.getIterator();
        System.out.println("in the Pylon  gameWorld");
        int count = 0;
        while(myIter.hasNext()){
            gO = (GameObjects) myIter.getNext();
            if(gO instanceof Pylon){
                count++;
            }
        }
        gameCollection.add(createPylon((count+1)));
    }
	 public void t() {
         //System.out.println("Hello");
         if (currPaused) {
                ///working on this

         } else {
             counter++;
             if ((counter % 50) == 0) {
                 timeClock = timeClock + 1;
             }
             myIter = gameCollection.getIterator();
             while (myIter.hasNext()) {
                 gO = (GameObjects) myIter.getNext();

                 if (gO instanceof MovObj) {
                     MovObj mObj = (MovObj) gO;
                        if(mObj instanceof NPC)
                        {
                            if(random.nextInt(300)%300 == 0){//todo get the npc to change stratagies
                                pylon = new ToThePylonNPC((NPC)mObj, gP);
                                derby = new DestructionDerbyStrategy((NPC)mObj, gP);
                                if (((NPC)mObj).getStrategy() instanceof DestructionDerbyStrategy){
                                    ((NPC)mObj).setStrategy(pylon);
                                    System.out.println("pylon");
                                }else{
                                    ((NPC)mObj).setStrategy(derby);
                                    System.out.println("derby");
                                }
                            }
                        }
                     mObj.move();
                 }

             }
             myIter = gameCollection.getIterator();//----------------------------------------------------------------collisions-----------------\\
             while (myIter.hasNext()) {
                 ICollider g2 = (ICollider) myIter.getNext();
                 Iterator myIter2 = gameCollection.getIterator();
                 while (myIter2.hasNext()) {
                     ICollider g1 = (ICollider) myIter2.getNext();


                     //System.out.println(g2.toString());
                     //System.out.println(g1.toString());
                     if (g1 != g2) {
                         if (g2.CollidesWith(g1)) {

                             g2.handleCollision(g1);
                         }
                     }
                 }
             }


             // notifyObservers();
         }
     }
    public int getTimeClock(){
        return timeClock;
    }
    public int getLives(){
        return currentLives;
    }
    public int getHighestPylon(){
        return lastPylon;
    }
    public String getSound(){
        return sound;
    }
    public void setSound(){
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public String map() {
        String y = "";
        myIter = gameCollection.getIterator();
        while (myIter.hasNext()) {
            gO = (GameObjects) myIter.getNext();
            if (gO instanceof Cars)
                y = y + (gO.toString()) + "";
            if (gO instanceof Birds)
                y = y + gO.toString() + "\n";
            if (gO instanceof Pylon)
                y = y + gO.toString() + "\n";
            if (gO instanceof OilSlick)
                y = y + gO.toString() + "\n";
            if (gO instanceof Fuel)
                y = y + gO.toString() + "\n";
            if (gO instanceof NPC)
                y = y + gO.toString() + "\n";

        }
        return y;
    }

    void setTimeClock(int i) {
        timeClock = i;
    }

    public void changeSound() {
        if(sound.equalsIgnoreCase("off")){
            sound = "On";
            looper.loop();
        }else {
            sound = "Off";
            looper.stop();
        }
    }
//
//    @Override
//    public void play() {
//        //looper.play();
//        System.out.println("play sound");
//        //this.play();
//    }
//
//    @Override
//    public void loop() {
//        System.out.println("looping sound");
//        looper.loop();
//    }
//
//    @Override
//    public void stop() {
//
//    }
}