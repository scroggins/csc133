package a4.commands;


import a4.GameWorldProxy;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TickCommand extends AbstractAction {
    private GameWorldProxy gw;//target
    private static TickCommand theTickCommand = null;
    private TickCommand(GameWorldProxy gaw)
    {
    gw = gaw;
    }
    public void actionPerformed(ActionEvent e){
        gw.t();
        gw.notifyObservers();
    }
    public static TickCommand getTickCommand(GameWorldProxy gaw) {
        if (theTickCommand == null)
            theTickCommand = new TickCommand(gaw);
        return theTickCommand;
    }
    // accept a Document for printing
    public void setTarget (GameWorldProxy gw) {
    this.gw = gw;
    }
    }

