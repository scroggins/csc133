/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4;
import a4.commands.*;
import a4.views.MapView;
import a4.views.ScoreView;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*; 
import java.awt.event.*;

/**
 *
 * @author Randy
 */
public class Game extends JFrame implements ActionListener, MouseListener{
    private GameWorldProxy gw;
    private GameWorld gaw;
    private MapView mv; // new in A2
    private ScoreView sv; // new in A2
    private Timer timer;//this is my timer
    private int delay = 20;

	public Game(){


        JFrame frame = new JFrame();
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu("file");
        JMenu comandsMenu = new JMenu("Commands");



        JPanel topPanel = new JPanel();
        gaw = new GameWorld();
        //gaw.initLayout();

        gw = gaw.getGameWorldProxy();
        mv = new MapView(gw); // create an “Observer” for the map
        sv = new ScoreView(topPanel ,gw); // create an “Observer” for the game state data
        gw.addObservers(mv); // register the map Observer
        gw.addObservers(sv); // register the score observer
        timer = new Timer (delay, this);
        timer.start();
        //
        //timer.setActionCommand("tick");


        TickCommand tick = TickCommand.getTickCommand(gw);
        CollisionCommand NPCColission = CollisionCommand.getCollisionCommand(gw);
	    CollidePylonCommand hitPylon = CollidePylonCommand.getCollidePylonCommand(gw);
        BirdCollisionCommand hitBird = BirdCollisionCommand.getBirdCollisionCommand(gw);
        FuelPickUpCommand fuelPickUp = FuelPickUpCommand.getFuelPickUpCommand(gw);
        EnterOilSlickCommand EnterOil = EnterOilSlickCommand.getEnterOilSlickCommand(gw);
        ExitOilSlickCommand exitOil = ExitOilSlickCommand.getExitOilSlickCommand(gw);
        ChangeStrategiesCommand newStrategy = ChangeStrategiesCommand.getChangeStrategiesCommand(gw);
        QuitCommand quit = QuitCommand.getQuitCommand(gw);
        AddOilSlickCommand AddOil = AddOilSlickCommand.getOilSlickCommand(gw);
        NewColorsCommand newColor = NewColorsCommand.getColorsCommand(gw);
        PauseCommand pause = PauseCommand.getPauseCommand(gw);
        BrakeCommand brakes = BrakeCommand.getBrakeCommand(gw);
        AccelerateCommand accelerate = AccelerateCommand.getAccelerateCommand(gw);
        LeftTurnCommand leftTurn = LeftTurnCommand.getLeftTurnCommand(gw);
        RightTurnCommand rightTurn = RightTurnCommand.getRightTurnCommand(gw);
        About about = About.getAbout(gw);
        AddFuelCanCommand addFuel = AddFuelCanCommand.getAddFuelCanCommand(gw);
        AddPylonCommand addPylon = AddPylonCommand.getAddPylonCommand(gw);
        SoundCommand addSound = SoundCommand.getSoundCommand(gw);


        timer.addActionListener(tick);

        gw.initLayout();
		//play has been moved blow gui so i could work on the iterator

        //gw.loop();

        frame.add(mv,BorderLayout.CENTER);

        //Graphics g = mv.getGraphics();
        //g.drawLine(50,50, 250,250);

        int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
        InputMap imap = mv.getInputMap(mapName);

        KeyStroke aKey = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        KeyStroke bKey = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0);
        KeyStroke lKey = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
        KeyStroke rKey = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0);

        imap.put(aKey, "accelerate");
        imap.put(bKey, "brakes");
        imap.put(lKey, "left");
        imap.put(rKey, "right");

        ActionMap amap = mv.getActionMap();

        amap.put("accelerate", accelerate);
        amap.put("brakes", brakes);
        amap.put("left", leftTurn);
        amap.put("right", rightTurn);

        this.requestFocus();


    JMenuItem newOilSlick = new JMenuItem("make a new oil slick");
    comandsMenu.add(newOilSlick);
    newOilSlick.setMnemonic ('o');
    newOilSlick.addActionListener(AddOil);
    JMenuItem fuelPick = new JMenuItem("pick up fuel can"); //
    comandsMenu.add(fuelPick);
    fuelPick.setMnemonic ('f');//fuelPickUp
    fuelPick.addActionListener(fuelPickUp);
    JMenuItem newColors = new JMenuItem("give new colors"); //
    comandsMenu.add(newColors);
    newColors.setMnemonic ('N');//newColor
    newColors.addActionListener(newColor);

    // create each of the Edit menu items and add each item to the menu
    JMenuItem newOption = new JMenuItem("New"); // edit->cut
    fileMenu.add(newOption);

    JMenuItem saveOption = new JMenuItem("Save"); // edit->copy
    fileMenu.add(saveOption);
    saveOption.setMnemonic ('S');
    JCheckBoxMenuItem soundMenu = new JCheckBoxMenuItem( "Sound" );
        soundMenu.addActionListener(addSound);
    fileMenu.add(soundMenu);

    JMenuItem aboutOption = new JMenuItem("About");
    fileMenu.add(aboutOption);
    aboutOption.setMnemonic ('A');
    aboutOption.addActionListener(about);
    //fileMenu.add(aboutItem);
    fileMenu.addSeparator(); // adds a separator
    JMenuItem quitOption = new JMenuItem("Quit"); // edit->find
    fileMenu.add(quitOption);
    quitOption.addActionListener(quit);

    bar.add(fileMenu);
    bar.add(comandsMenu);
        frame.setJMenuBar(bar);

       // frame.pack();
      frame.setTitle("My Race Game");
      //JPanel topPanel = new JPanel(); //////////////////////////////////////////////////////
      topPanel.setBorder(new LineBorder(Color.black, 2));


      frame.add(topPanel,BorderLayout.NORTH);

      JLabel myLabel = new JLabel ("Time:") ;
      topPanel.add(myLabel);
		JLabel myOtherLabel = new JLabel(String.valueOf(0));
		topPanel.add(myOtherLabel);
      JLabel spacer = new JLabel ("         ") ;
      topPanel.add(spacer);

      JLabel myLabel2 = new JLabel ("\tLives Left:") ;
      topPanel.add(myLabel2);
      JLabel myOtherLabel2 = new JLabel(String.valueOf(3));
		topPanel.add(myOtherLabel2);
      JLabel spacer2 = new JLabel ("         ") ;
      topPanel.add(spacer2);

      JLabel myLabel3 = new JLabel ("Highest Player Pylon:") ;
      topPanel.add(myLabel3);
      JLabel myOtherLabel3 = new JLabel(String.valueOf(1));
		topPanel.add(myOtherLabel3);
      JLabel spacer3 = new JLabel ("         ") ;
      topPanel.add(spacer3);

      JLabel myLabel4 = new JLabel ("Player Fuel Remaining:") ;
      topPanel.add(myLabel4);
      JLabel myOtherLabel4 = new JLabel(String.valueOf(10));
		topPanel.add(myOtherLabel4);
      JLabel spacer4 = new JLabel ("         ") ;
      topPanel.add(spacer4);


      JLabel myLabel5 = new JLabel ("PlayerDamageLevel:") ;
      topPanel.add(myLabel5);
      JLabel myOtherLabel5 = new JLabel(String.valueOf(0));
		topPanel.add(myOtherLabel5);
      JLabel spacer5 = new JLabel ("         ") ;
      topPanel.add(spacer5);


      JLabel myLabel6 = new JLabel ("Sound:") ;
      topPanel.add(myLabel6);
      JLabel myOtherLabel6 = new JLabel("Off");
		topPanel.add(myOtherLabel6);
      JLabel spacer6 = new JLabel ("         ") ;
      topPanel.add(spacer6);

      //JButton mytopButton = new JButton ("push harder");
      //topPanel.add(mytopButton);
      // add a bordered panel with a 10x1 Grid Layout at the left of this frame
      JPanel leftPanel = new JPanel();
      leftPanel.setBorder(new TitledBorder(" Commands: "));
      leftPanel.setLayout(new GridLayout(20, 1));
      frame.add(leftPanel,BorderLayout.WEST);
      // add two buttons to the left panel; they will be in the top two grid cells
//      JButton myLeftButton1 = new JButton ("Collide with NPC");
//      leftPanel.add(myLeftButton1);
//      myLeftButton1.setAction(NPCColission);
//       myLeftButton1.setText("Collide with NPC");
     // myLeftButton1.setAction(gw.collision(5));//collision from a car cuases 5 damage);
      //myLeftButton1.addActionListener(this);
      //extend abstract action

//      JButton myLeftButton2 = new JButton ("Collide with pylon");
//      leftPanel.add(myLeftButton2);
//      myLeftButton2.setAction(hitPylon);
//      myLeftButton2.setText("Collide with pylon");
      //myLeftButton2.addActionListener(AL);

//
//      JButton myLeftButton3 = new JButton("Collide with Bird");
//      leftPanel.add(myLeftButton3);
//      myLeftButton3.setAction(hitBird);
//      myLeftButton3.setText("Collide with Bird");
      //myLeftButton3.addActionListener(AL);


//      JButton myLeftButton4 = new JButton ("Pick up fuel can");
//      leftPanel.add(myLeftButton4);
//      myLeftButton4.setAction(fuelPickUp);
//      myLeftButton4.setText("Pick up fuel can");
      //myLeftButton4.addActionListener(AL);


//      JButton myLeftButton5 = new JButton ("Entered oil slick");
//      leftPanel.add(myLeftButton5);
//      myLeftButton5.setAction(EnterOil);
//      myLeftButton5.setText("Entered Oil Slick");
      //myLeftButton5.addActionListener(AL);


//      JButton myLeftButton6 = new JButton("Exited Oil Slick");
//      leftPanel.add(myLeftButton6);
//      myLeftButton6.setAction(exitOil);
//      myLeftButton6.setText("Exited Oil Slick");
      //myLeftButton6.addActionListener(AL);


//      JButton myLeftButton7 = new JButton("Switch Strategy");
//      leftPanel.add(myLeftButton7);//newStrategy
//      myLeftButton7.setAction(newStrategy);
//      myLeftButton7.setText("Switch Strategy");
      //myLeftButton7.setAction(myLeftButton7);//////////////////////////////////////////////////////////////////////////


      JButton myLeftButton8 = new JButton("Pause");
      leftPanel.add(myLeftButton8);
      myLeftButton8.setAction(pause);///changing this button to to be the pause button
        myLeftButton8.setText("Pause");

        JButton myLeftButton10 = new JButton ("Add Fuel Can");
        leftPanel.add(myLeftButton10);
        myLeftButton10.setAction(addFuel);
        myLeftButton10.setText("Add Fuel Can");

        JButton myLeftButton11 = new JButton ("Add Pylon");
        leftPanel.add(myLeftButton11);
        myLeftButton11.setAction(addPylon);
        myLeftButton11.setText("Add Pylon");


      JButton myLeftButton9 = new JButton("Quit");
      leftPanel.add(myLeftButton9);
      myLeftButton9.setAction(quit);
        myLeftButton9.setText("Quit");
      //myLeftButton9.addActionListener("quit");

      // add a "combo box" (drop-down list) with a list of fruits to the left panel
      //String [] fruitList = new String [] {"Apple", "Orange", "Banana"} ;
      //JComboBox fruitComboBox = new JComboBox (fruitList);
      //leftPanel.add(fruitComboBox);
      // add a "check box" to the left panel
      //JCheckBox myCheckBox = new JCheckBox ("Enable Printing");
      //leftPanel.add(myCheckBox);
      // add a colored bordered panel at the center of this frame
      //JPanel mv = new JPanel();
      mv.setBorder (new EtchedBorder());
      mv.setBackground (Color.white);
        mv.addMouseListener(this);
      frame.add(mv,BorderLayout.CENTER);
      frame.setResizable(false);// stops user from resizing the window
      frame.setSize(new Dimension(1100,1100));//sets the size of the window
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ends the program when the window is closed


     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      //play();
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    @Override//------------------------------for the mouse listener----------
    public void mouseClicked(MouseEvent e) {//

        Point p = new Point(e.getX(), e.getY());
        gw.newPoint(p);
//        System.out.println("mouse Clicked");
//        System.out.println("point "+ p.getX()+" " +p.getY());
//        gaw.getGameCollection().add(gaw.createFuel((int)p.getX(),(int) p.getY()));
//        myIter = gw.getIterator();
//        while (myIter.hasNext()) {
//            GameObjects x = (GameObjects) myIter.getNext();
//            if (x instanceof ISelectable) {
//                if (x instanceof Pylon) {
//                    if (((Pylon) x).contains(p))
//                        ((ISelectable) x).setSelected(true);
//                    else
//                        ((ISelectable) x).setSelected(false);
//                }
//                if (x instanceof Fuel) {
//                    if (((Fuel) x).contains(p))
//                        ((ISelectable) x).setSelected(true);
//                    else
//                        ((ISelectable) x).setSelected(false);
//                }
//            }
//            this.repaint();
//        }
    }


    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }


    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Point p = (Point) e.getPoint();
//        System.out.println("mouse Clicked");



    }

        
        
        

        
        
        

        
	/*private void play(){
      boolean t = true;
      while(t){
		 Scanner console = new Scanner(System.in);
       System.out.println("please input a command");
		String input = console.nextLine();
                    

		switch (input) {
			case "a":
                            //System.out.println("the car accelerates");
                            gw.accelerate();
			        break;
			/*case "b":
                           //System.out.println("the car brakes");
                            gw.brake();
			        break;
			case "l":
                           System.out.println("the car turns left");
                           gw.left();
			        break;
			case "r":
                           System.out.println("the car turns right");
                            gw.right();
			        break;
                        case "o":
                           System.out.println("another oil slick");
                            gw.oilSlick();
			        break;

			
			case "f":
                            System.out.println("fuel can grabbed");
                            gw.fuel();
			        break;
			case "g":
                            gw.collision((float) 2.5);//collision from a bird cuases 2.5 damage
			        break;
			case "e":
                            System.out.println("entered an oilSlick");
                gw.e();
			        break;
			case "x": 
                            System.out.println("exiting an oilSlick");
                            gw.x();
			        break;
			case "n":
                            System.out.println("COLOR CHANGE");
                            gw.n();
				break;
                        case "c":
                       //System.out.println("collision");
                        gw.collision(5);//collision from a car cuases 5 damage
                            break;
			
			case "d":
                            //System.out.println("DISPLAY");
                            gw.d();
			        break;
			case "m":
                            System.out.println("the map");				
                          System.out.print( gw.map());
			        break;
                        case "t":
                            System.out.println("the game clock has moved up by one");
                            gw.t();
			        break;
			case "q":
         System.out.println("I quit");
         t = false;
                                //gw.quit();
				break;/*
                            default : 
                                if(input.charAt(0) !='p')
                                    System.out.println("bad input");
                                else
                                    gw.pylon(input);
				break;*/
                }



