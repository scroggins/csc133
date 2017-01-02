package a4.commands;

import a4.GameWorldProxy;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Asus on 4/27/2015.
 */
    public class PauseCommand extends AbstractAction {
        private GameWorldProxy gw;//target
        private static PauseCommand thePauseCommand = null;
        private PauseCommand(GameWorldProxy gaw)
        {
            gw = gaw;
        }
        public void actionPerformed(ActionEvent e){
            gw.pause();
            gw.notifyObservers();
        }
        public static PauseCommand getPauseCommand(GameWorldProxy gaw) {
            if (thePauseCommand == null)
                thePauseCommand = new PauseCommand(gaw);
            return thePauseCommand;
        }
        // accept a Document for printing
        public void setTarget (GameWorldProxy gw) {
            this.gw = gw;
        }
    }

