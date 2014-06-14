package brutalchess.basis;

import static brutalchess.Const.*;

public class Game {
	
	private final Desk desk;
	private Player whiteP;
	private Player blackP;
	
	public Game(Desk desk){
		this.desk = desk;
	}
	
	public void initLocal() {
		this.whiteP = new Player(WHITE);
		this.blackP = new Player(BLACK);
	}
	
	public boolean initOnline() {
		this.whiteP = new Player(WHITE);
		this.blackP = new Player(BLACK);
		
		return true;
	}

	public int getActivePlayerColor() {
		return this.whiteP.getState() ? WHITE : BLACK;
	}
	
	public void changePlayers() {
		if (this.getActivePlayerColor() == WHITE) {
			this.whiteP.deactivate();
			this.blackP.activate();
		} else {
			this.blackP.deactivate();
			this.whiteP.activate();
		}
	}
	
}
