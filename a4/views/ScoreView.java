package a4.views;

import a4.gameObjects.GameObjects;
import a4.GameWorldProxy;
import a4.gameObjects.Cars;
import a4.interfaces.IObservable;
import a4.interfaces.IObserver;
import a4.interfaces.Iterator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Asus on 3/30/2015.
 */
public class ScoreView implements IObserver {
    private JPanel scoreView;
    private GameWorldProxy gw;
    private int time;
    private int livesLeft;
    private int highestPylon;
    private float fuelRemaining;
    private float damage;
    private String sound;
    //private JPanel topPanel = new JPanel();

    public ScoreView(JPanel score, GameWorldProxy gaw){
        scoreView = score;
        gw = gaw;

    }
    public void update(IObservable observable){
        scoreView.removeAll();
        scoreView.setVisible(false);
        sound = gw.getSound();
        Iterator iter = gw.getIterator();

            while(iter.hasNext()){//itorating through to get my car
                GameObjects x =(GameObjects) iter.getNext();
                if (x instanceof Cars){
                    fuelRemaining  =(((Cars)x).getFuelLevel());
                    damage = (((Cars)x).getDamageLevel());
                    highestPylon = (((Cars)x).getHighestPylon());
                }

            }
        livesLeft = gw.getLives();
        time = gw.getTimeClock();
        //highestPylon = gw.getHighestPylon();
        scoreView.setBorder(new LineBorder(Color.blue, 2));

            //label.setText(field.getText());
        JLabel myLabel = new JLabel("Time:"+ time);
        scoreView.add(myLabel);
        //JLabel myOtherLabel = new JLabel((String) time);
        // topPanel.add(myOtherLabel);
        JLabel spacer = new JLabel ("         ") ;
        scoreView.add(spacer);

        JLabel myLabel2 = new JLabel ("\tLives Left:"+ livesLeft) ;
        scoreView.add(myLabel2);
       // JLabel myOtherLabel2 = new JLabel(String.valueOf(3));
        //topPanel.add(myOtherLabel2);
        JLabel spacer2 = new JLabel ("         ") ;
        scoreView.add(spacer2);

        JLabel myLabel3 = new JLabel ("Highest Player Pylon:"+ highestPylon) ;
        scoreView.add(myLabel3);
        //JLabel myOtherLabel3 = new JLabel(String.valueOf(1));
        //topPanel.add(myOtherLabel3);
        JLabel spacer3 = new JLabel ("         ") ;
        scoreView.add(spacer3);

        JLabel myLabel4 = new JLabel ("Player Fuel Remaining:" + fuelRemaining) ;
        scoreView.add(myLabel4);
        //JLabel myOtherLabel4 = new JLabel(String.valueOf(10));
        //topPanel.add(myOtherLabel4);
        JLabel spacer4 = new JLabel ("         ") ;
        scoreView.add(spacer4);


        JLabel myLabel5 = new JLabel ("PlayerDamageLevel:"+ damage) ;
        scoreView.add(myLabel5);
        //JLabel myOtherLabel5 = new JLabel(String.valueOf(0));
        //topPanel.add(myOtherLabel5);
        JLabel spacer5 = new JLabel ("         ") ;
        scoreView.add(spacer5);


        JLabel myLabel6 = new JLabel ("Sound:") ;
        scoreView.add(myLabel6);
        JLabel myOtherLabel6 = new JLabel(sound);
        scoreView.add(myOtherLabel6);
        JLabel spacer6 = new JLabel ("         ") ;
        scoreView.add(spacer6);
        scoreView.setVisible(true);
    }
}