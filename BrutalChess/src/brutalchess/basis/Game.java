package brutalchess.basis;

import static brutalchess.Const.*;
import brutalchess.online.Online;
import brutalchess.ui.GamePanel;

public class Game {

	private final Desk desk;
	private int localColor;
	private Online online;
	private Player whiteP;
	private Player blackP;
	private GamePanel panel;

	public Game(int localColor) {
		this.desk = new Desk(8, this);
		this.localColor = localColor;
		
		this.whiteP = new Player(WHITE);
		this.blackP = new Player(BLACK);
	}
	
	public boolean canPlayWithColor(int color){
		return getActivePlayerColor() == color && (localColor == color || localColor == LOCALGAME);
	}
	
	public void makeMove(Position from, Position to){
		desk.setActive(from);
		to.moveActiveFigure();
		
		System.out.println("move is done...");
	} 
	
	public void moveMade(Position from, Position to) {
		if (getActivePlayerColor() == localColor) {
			if (online != null) {
				online.sendMove(from, to);
				System.out.println("sening move and listening for another");
				online.decodeMove(online.listenFor());
			}
		}
		changePlayers();
	}

	public void changePlayers() {
		if (this.getActivePlayerColor() == WHITE) {
			this.whiteP.deactivate();
			this.blackP.activate();
			panel.setActivePlayer(BLACK);
		} else {
			this.blackP.deactivate();
			this.whiteP.activate();
			panel.setActivePlayer(WHITE);
		}
	}
	
	public void setLocalColor(int color){
		localColor = color;
	}
	
	public int getLocalColor(){
		return localColor;
	}

	public Desk getDesk() {
		return this.desk;
	}

	public int getActivePlayerColor() {
		return this.whiteP.getState() ? WHITE : BLACK;
	}
	
	public void setNetworkHandler(Online networkHandler){
		this.online = networkHandler;
	}
	
	public void setGamePanel(GamePanel panel){
		this.panel = panel;
	}

}
