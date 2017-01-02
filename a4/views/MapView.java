package a4.views;

import a4.gameObjects.GameObjects;
import a4.GameWorldProxy;
import a4.gameObjects.*;
import a4.interfaces.IObservable;
import a4.interfaces.IObserver;
import a4.interfaces.ISelectable;
import a4.interfaces.Iterator;
import a4.Point;
import a4.gameObjects.NPC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

/**
 * Created by Asus on 3/17/2015.
 */
public class MapView extends JPanel implements IObserver, MouseListener {//need to extend jPanel
    private GameWorldProxy gw;
    private Iterator myIter;
    private Iterator myIter2;

    public MapView(GameWorldProxy gaw) {
        gw = gaw;
    }

    public void update(IObservable o) {
        String y = "";
        myIter = gw.getIterator();
        while (myIter.hasNext()) {
            GameObjects x = (GameObjects) myIter.getNext();
            if (x instanceof Cars) {
                // y = y + (x.toString()) + "\n";
                // System.out.println("");
            }
            // if (x instanceof Birds)
            //y = y + x.toString() + "\n";
            //else if (x instanceof Pylon)
            //y = y + x.toString() + "\n";
            //else if (x instanceof OilSlick)
            //y = y + x.toString() + "\n";
            //else if (x instanceof Fuel)
            // y = y + x.toString() + "\n";
            //else
            //y = y + x.toString() + "\n";
        }
       // System.out.print(y + "\n\n");
        repaint();
    }

    public void paintComponent(Graphics g) {
        //System.out.println("painting");
        super.paintComponents(g);
        // g.drawRect(10,10,100,100);

        myIter = gw.getIterator();
        while (myIter.hasNext()) {
            Graphics2D g2d = (Graphics2D) g ;
            //todo get affine transfermation to work
            AffineTransform screenXform = new AffineTransform();
            screenXform.translate (0, this.getHeight());
            screenXform.scale (1, -1);


            GameObjects x = (GameObjects) myIter.getNext();
            if (x instanceof Cars) {
                (x).draw(g2d);
                //g.setColor(Color.RED);
                //g.drawRect((int)(x.getLocationX()-x.getWidth()/2),(int)(x.getLocationY() - x.getLength()/2),((Cars)x).getWidth(),((Cars)x).getLength());
                //g.fillRect((int)(x.getLocationX()),(int)(x.getLocationY()),((Cars)x).getWidth(),((Cars)x).getLength());
                //g.fillRect((int) (x.getLocationX()), (int) (x.getLocationY()), ((Cars) x).getWidth(), ((Cars) x).getLength());
            }
            if (x instanceof Birds) {
                x.draw(g2d);
                //y = y + ((Birds) x).toString() + "\n";
                //g.setColor((x).getColor());
                //g.drawRect((int)(x.getLocationX()), (int)(x.getLocationY()),(((Birds)x).getSize()),(((Birds)x).getSize()));
                //g.setColor(Color.CYAN);
                //g.drawOval((int)(x.getLocationX()), (int)(x.getLocationY()),((Birds)x).getSize(),((Birds)x).getSize());
            }
            if (x instanceof Pylon) {
                x.draw(g2d);
                //g.setColor(Color.orange);
                //y = y + ((Pylon) x).toString() + "\n";

                //g.drawOval((int)(x.getLocationX()), (int)(x.getLocationY()),((Pylon)x).getRadius(),((Pylon)x).getRadius());
                //g.fillOval((int) (x.getLocationX()), (int) (x.getLocationY()), ((Pylon) x).getRadius(), ((Pylon) x).getRadius());
                //g.setColor(Color.blue);
                //g.drawString((((Pylon) x).getNum()+""), ((int)(x.getLocationX())+17), ((int)(x.getLocationY()))+25);
            }
            if (x instanceof OilSlick) {
                x.draw(g2d);
                //g.setColor(Color.black);
                //g.drawOval((int) (x.getLocationX()), (int) (x.getLocationY()), ((OilSlick) x).getWidth(), ((OilSlick) x).getLength());
                //g.fillOval((int) (x.getLocationX()), (int) (x.getLocationY()), (x).getWidth(), ( x).getLength());
                //y = y + ((OilSlick) x).toString() + "\n";
            }
            if (x instanceof Fuel) {
                x.draw(g2d);
                //g.setColor(Color.blue);

                //y = y + ((Fuel) x).toString() + "\n";
                //g.fillRect((int) x.getLocationX() - ((Fuel) x).getWidth() / 2, (int) x.getLocationY() - ((Fuel) x).getLength() / 2, ((Fuel) x).getWidth(), ((Fuel) x).getLength());
                //g.fillRect((int)x.getLocationX(),(int)x.getLocationY(), ((Fuel)x).getSize(), ((Fuel)x).getSize());
            }
            if (x instanceof NPC) {
                x.draw(g2d);
                //g.setColor(Color.RED);
                //g.drawRect((int) (x.getLocationX()), (int) (x.getLocationY()), ((NPC) x).getWidth(), ((NPC) x).getLength());

                //if(((NPC)x).getStrategy() instanceof DestructionDerbyStrategy){
                //GameObjects t;
                //myIter2 = gw.getIterator();
                //while (myIter2.hasNext()) {
                // t = (GameObjects) myIter2.getNext();
                // if (t instanceof Cars) {
                //     g.drawLine((int) (x.getLocationX()) , (int) (x.getLocationY()), (int)(t.getLocationX()),(int)(t.getLocationY()));
                //       //break;
                //     }
                //   }
                //} else {
                // GameObjects t;
                // myIter2 = gw.getIterator();
                //while(myIter2.hasNext()){
                // t = (GameObjects) myIter2.getNext();
                // if (t instanceof Pylon) {
                // if (((Pylon) t).getNum() == (1)) {//fix this later
                //       g.drawLine((int) (x.getLocationX()), (int) (x.getLocationY()), (int) t.getLocationX(), (int) t.getLocationY());
                //         //break;
                //       }
                //     }
                //   }
                // }
                // y = y + ((NPC) x).toString() + "\n";
            }
        }

    }

    @Override//------------------------------for the mouse listener----------
    public void mouseClicked(MouseEvent e) {
        Point p = (Point) e.getPoint();
        System.out.println("point "+ p);
        myIter = gw.getIterator();
        while (myIter.hasNext()) {
            GameObjects x = (GameObjects) myIter.getNext();
            if (x instanceof ISelectable) {
                if (x instanceof Pylon) {
                    if (((Pylon) x).contains(p))
                        ((ISelectable) x).setSelected(true);
                    else
                        ((ISelectable) x).setSelected(false);
                }
                if (x instanceof Fuel) {
                    if (((Fuel) x).contains(p))
                        ((ISelectable) x).setSelected(true);
                    else
                        ((ISelectable) x).setSelected(false);
                }
            }
            this.repaint();
        }
    }


    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }


    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}


