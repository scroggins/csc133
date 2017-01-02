/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4.commands;

import a4.GameWorldProxy;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Randy
 */
public class About extends AbstractAction {
private GameWorldProxy gw;//target
      // maintain a single global reference to the spooler
      private static About about = null;
      public void actionPerformed(ActionEvent e){
          gw.about();
          /*
            System.out.println("\n\n\n");
            System.out.println("Hello I am Randy and i fight ides all day evey day except jgrasp we are buddies like that :)");
             System.out.println("\n\n\n");
             */
      }
      
      // insure that no one can construct a spooler directly
      private About(GameWorldProxy gaw) {
          gw = gaw;
      }
      // provide access to the spooler, creating it if necessary
      public static About getAbout(GameWorldProxy gaw) {
      if (about == null)
        about = new About(gaw);
      return about;
      }
      // accept a Document for printing
      public void setTarget (GameWorldProxy gw) {
         this.gw = gw;      //code here to add the Document to a private queue ...
      }
      //private methods here to dequeue and print documents ...
    }