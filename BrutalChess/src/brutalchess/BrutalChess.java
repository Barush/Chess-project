package brutalchess;

import static brutalchess.Const.*;
import brutalchess.ui.Frame;
import brutalchess.ui.MenuPanel;
import brutalchess.ui.GamePanel;
import brutalchess.basis.Desk;
import brutalchess.basis.Game;
import brutalchess.basis.Position;
import brutalchess.online.Client;
import brutalchess.online.Online;
import brutalchess.online.Server;
import javax.swing.JFrame;

/**
 *
 * @author Barush
 */
public class BrutalChess {

	private static Frame frame;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		frame = new Frame();
		frame.setSize(800, 800);
		frame.setResizable(false);
		frame.setTitle("Chess project (Baruska a Ondrasek)");
		MenuPanel menu = new MenuPanel();
		frame.setContent(menu);
	}

	public static Game initGame(int localColor) {
		Game game = new Game(localColor);
		GamePanel panel = new GamePanel(game.getDesk().getPositions(), 8);
		frame.setContent(panel);
		game.setGamePanel(panel);
		return game;
	}

	public static void initLocal() {
		initGame(LOCALGAME);
	}

	public static void initHost(String stringPort) {
		int port = Integer.parseInt(stringPort);
		Game game = initGame(WHITE);
		Online online = new Server(game, port);
		game.setNetworkHandler(online);
		online.run();
	}

	public static void initClient(String address) {
		Game game = initGame(NONE);
		Online online = new Client(game, address);
		game.setNetworkHandler(online);
		online.run();
	}
}
