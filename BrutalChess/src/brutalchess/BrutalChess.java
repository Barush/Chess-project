package brutalchess;

import static brutalchess.Const.*;
import brutalchess.ui.Frame;
import brutalchess.ui.MenuPanel;
import brutalchess.ui.GamePanel;
import brutalchess.basis.Desk;
import brutalchess.basis.Game;
import brutalchess.basis.Position;
import brutalchess.online.Client;
import brutalchess.online.Server;
import javax.swing.JFrame;

/**
 *
 * @author Barush
 */
public class BrutalChess {

	static private Frame frame;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frame = new Frame();
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setTitle("Chess project (Baruska a Ondrasek)");
		MenuPanel menu = new MenuPanel();
		frame.setContent( menu );
    }
	
	static public Game initDesk(){
		Desk desk = new Desk(8);
		frame.setContent( new GamePanel(desk.getPositions(), 8) );
		
		return new Game(desk);
	}
	
	public static void initHost(String stringPort){
		int port = Integer.parseInt(stringPort);
		new Server(initDesk(), port);
	}
	
	public static void initClient(String address){
		new Client(initDesk(), address);
	}
}
