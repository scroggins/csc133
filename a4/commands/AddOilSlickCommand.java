package a4.commands;


import a4.GameWorldProxy;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddOilSlickCommand extends AbstractAction {//basically starts the game
    private GameWorldProxy gw;//target
    //private Iterator myIter;
    // private GameCollection gc;
    // maintain a single global reference to the spooler
    private static AddOilSlickCommand theAddOilSlickCommand = null;

    public void actionPerformed(ActionEvent e) {
        gw.addOilSlick();
        //System.out.println("Oil slick created");
        /*
         OilSlick oilSlick = new OilSlick(); where RVR.product_ID=RVS.Product_ID
        gc = gw.getGameCollection();
         gc.add(oilSlick);
         gw.notifyObservers();
         */
                gw.notifyObservers();
    }

    // insure that no one can construct a spooler directly
    private AddOilSlickCommand(GameWorldProxy gaw) {
        gw = gaw;
    }

    // provide access to the spooler, creating it if necessary
    public static AddOilSlickCommand getOilSlickCommand(GameWorldProxy gaw) {
        if (theAddOilSlickCommand == null)
            theAddOilSlickCommand = new AddOilSlickCommand(gaw);
        return theAddOilSlickCommand;
    }

    public void setTarget(GameWorldProxy gw) {
        this.gw = gw;
    }
}
