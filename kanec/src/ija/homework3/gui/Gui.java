package ija.homework3.gui;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import ija.homework3.basis.*;
import ija.homework3.figures.*;


/**
 *
 * @author xhenek02
 */
public class Gui extends JFrame implements MouseListener, MouseMotionListener{
	
	private JFrame frame = new JFrame("Über broken checkers"); //create Frame
	private JPanel pButtons = new JPanel();
	private JPanel pDesk = new JPanel();

	private JButton bSingle = new JButton("Single player");
	private JButton bLocal = new JButton("Local multiplayer");
	private JButton bOnlineServer = new JButton("Host a game");
	private JButton bOnlineClient = new JButton("Connect to host");
	private JButton bExit = new JButton("Exit");

	Dimension boardSize = new Dimension(300, 300);

	JLayeredPane layeredPane;
	JLabel piece;
	Container pane = frame.getContentPane();
	int xAdjustment;
	int yAdjustment;
	int xOriginal, yOriginal;
	private Position draggedPos;
	private Desk desk;
	private Game game;
	private static Gui self;
	
	private boolean locked = false;
	
	/** Constructor for the GUI */
	public Gui(){
		
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
		
		// prepare layer for desk
		layeredPane = new JLayeredPane();
		frame.add(layeredPane);
		layeredPane.setVisible(false);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);
		
		// Add Buttons
		pButtons.add(bSingle);
		pButtons.add(bLocal);
		pButtons.add(bOnlineClient);
		pButtons.add(bOnlineServer);
		pButtons.add(bExit);
		pButtons.setLayout(new BoxLayout(pButtons, BoxLayout.Y_AXIS));
		pane.add(pButtons, BorderLayout.NORTH);
		
		// listeners
		bSingle.addActionListener(new SingleButtonHandler());
		bLocal.addActionListener(new LocalButtonHandler());
		bOnlineClient.addActionListener(new OnlineClientButtonHandler());
		bOnlineServer.addActionListener(new OnlineServerButtonHandler());
		bExit.addActionListener(new ExitButtonHandler());
	}
	
	public void launchFrame(){
		// Display Frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
//		frame.pack(); //Adjusts panel to components for display
		frame.setVisible(true);
	}
	
	private Position getPos(JPanel square){
		for (int i = 0; i < desk.pos.length; i++) {
			for (int j = 0; j < desk.pos[i].length; j++) {
				if (desk.pos[i][j].square == square){
					return desk.pos[i][j];
				}
			}
		}
		return null;
	}
	
	public void mousePressed(MouseEvent e){
		if (locked) return;
		piece = null;
		Component c = pDesk.findComponentAt(e.getX(), e.getY());
 
		if (c instanceof JLabel){
			JPanel parent = (JPanel)c.getParent();
			Point parentLocation = parent.getLocation();
			draggedPos = getPos((JPanel) parent);
			yOriginal = parentLocation.y;
			xOriginal = parentLocation.x;
			xAdjustment = parentLocation.x - e.getX();
			yAdjustment = parentLocation.y - e.getY();
			piece = (JLabel)c;
			piece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
			piece.setSize(piece.getWidth(), piece.getHeight());
			layeredPane.add(piece, JLayeredPane.DRAG_LAYER);
		}
	}
   
	//Move the chess piece around
	
	public void mouseDragged(MouseEvent me) {
		if (piece == null) return;
		 piece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
	 }
	 
	//Drop the piece back onto the chess board
	public void mouseReleased(MouseEvent me) {
		if(piece == null) return;

		piece.setVisible(false);
		Component c =  pDesk.findComponentAt(me.getX(), me.getY());
		
		Component original =  pDesk.findComponentAt(xOriginal, yOriginal);
		Container originalParent = (Container)original;
		originalParent.add(piece);
		piece.setLocation(xOriginal, yOriginal);

		if (c instanceof JPanel){
			Figure fig = draggedPos.getFigure();
			Container dest = (Container)c;
			
			if (game.playerPlay(fig, getPos((JPanel) dest))){
				piece.setVisible(true);
				return;
			}
			
//			if (fig.jump(getPos((JPanel) dest))){
//				System.out.println("jumped");
//			} else if (fig.move(getPos((JPanel) dest))){
//				System.out.println("moved");
//			} else {
//			}
		}
		System.out.println("Can't move!");
		piece.setVisible(true);

	}

	
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e){
	}
	public void mouseExited(MouseEvent e) {
	}
	
	

	// button listeners
	public class SingleButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("Start single player mode");
			game = new Game(self, desk.WHITE, game.SINGLE, null);
			layeredPane.setVisible(true);
		}
	}
	public class LocalButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("Start local multiplayer game");
			game = new Game(self, desk.WHITE, game.LOCAL, null);
			layeredPane.setVisible(true);
		}
	}
	public class OnlineClientButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent actionEvent) {
			Component source = (Component) actionEvent.getSource();
//			String response = JOptionPane.showInputDialog(source, "Zadejte IP adresu a port hostujícího hráče (např 192.168.1.10:9205");
			String response = "192.168.1.103:42345";
			if (response != null){
				System.out.println("adresa: " + response);
				layeredPane.setVisible(true);
				game = new Game(self, desk.WHITE, game.ONLINE, response);
			}
		}
	}
	public class OnlineServerButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent actionEvent) {
			Component source = (Component) actionEvent.getSource();
//			String response = JOptionPane.showInputDialog(source, "Zadejte port, na kterém naslouchat (číslo od 1024 do 65535)");
			String response = "42345";
			if (response != null){
				game = new Game(self, desk.WHITE, game.ONLINE, response);
				layeredPane.setVisible(true);
			}
		}
	}
	public class ExitButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}

	public void initDesk(Desk desk){
		
		this.desk = desk;
		
		layeredPane.removeAll();
		// prepare desk
		layeredPane.add(pDesk, JLayeredPane.DEFAULT_LAYER);
		pDesk.setLayout( new GridLayout(8, 8) );
		pDesk.setPreferredSize( boardSize );
		pDesk.setBounds(0, 0, boardSize.width, boardSize.height);
		
		// add squares to desk
		for (int i = 0; i < desk.pos.length; i++) {
			for (int j = 0; j < desk.pos[i].length; j++) {
				
				pDesk.add( desk.pos[i][j].square );

				int row = i % 2;
				if (row == 0){
					desk.pos[i][j].square.setBackground( j % 2 == 0 ? new Color(200, 200, 200) : Color.white );
				} else {
					desk.pos[i][j].square.setBackground( j % 2 == 0 ? Color.white : new Color(200, 200, 200) );
				}
			}
		}

	}

	public static void main(String args[]){
		//onlineGame.listen();
		self = new Gui();
		self.launchFrame();
	}
	
	public void lock(String message){
		this.locked = true;
		System.out.println("GUI Locked: "+ message);
	}
	public void unlock(){
		this.locked = false;
		System.out.println("GUI Unlocked");
	}
}
