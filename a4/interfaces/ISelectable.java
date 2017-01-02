package a4.interfaces;

import a4.Point;

import java.awt.*;

/**
 * Created by Randy on 4/28/2015.
 */
public interface ISelectable {
    // a way to mark an object as “selected” or not
    public void setSelected(boolean yesNo);
    // a way to test whether an object is selected
    public boolean isSelected();
    // a way to determine if a mouse point is “in” an object
    public boolean contains(Point p);
    // a way to “draw” the object that knows about drawing
// different ways depending on “isSelected”
    public void draw(Graphics g);
}
