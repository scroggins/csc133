package a4.commands;

import a4.GameWorldProxy;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Asus on 4/27/2015.
 */
public class AddPylonCommand extends AbstractAction {
    private GameWorldProxy gw;//target
    private static AddPylonCommand theAddPylonCommand = null;
    private AddPylonCommand(GameWorldProxy gaw)
    {
        gw = gaw;
    }
    public void actionPerformed(ActionEvent e){
        System.out.println("action performed");
        gw.addPylon();
        gw.notifyObservers();
    }
    public static AddPylonCommand getAddPylonCommand(GameWorldProxy gaw) {
        if (theAddPylonCommand == null)
            theAddPylonCommand = new AddPylonCommand(gaw);
        return theAddPylonCommand;
    }
    // accept a Document for printing
    public void setTarget (GameWorldProxy gw) {
        this.gw = gw;
    }
}

