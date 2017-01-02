package a4.commands;

import a4.GameWorldProxy;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Asus on 4/27/2015.
 */
public class AddFuelCanCommand extends AbstractAction {
    private GameWorldProxy gw;//target
    private static AddFuelCanCommand theAddFuelCanCommand = null;
    private AddFuelCanCommand(GameWorldProxy gaw)
    {
        gw = gaw;
    }
    public void actionPerformed(ActionEvent e){
        gw.addFuelAt();
        gw.notifyObservers();
    }
    public static AddFuelCanCommand getAddFuelCanCommand(GameWorldProxy gaw) {
        if (theAddFuelCanCommand == null)
            theAddFuelCanCommand = new AddFuelCanCommand(gaw);
        return theAddFuelCanCommand;
    }
    // accept a Document for printing
    public void setTarget (GameWorldProxy gw) {
        this.gw = gw;
    }
}
