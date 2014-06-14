package brutalchess.basis;

import static brutalchess.Const.*;

/**
 *
 * @author Canes
 */
public class Player {
	
	// array with kicked out figures so you can exchange Pawn with his dead comrades?
	private final int color;
	private boolean active;
	
	public Player(int color){
		this.color = color;
		this.active = (color == WHITE);
	}
	
	public void activate(){
		this.active = true;
	}
	
	public void deactivate(){
		this.active = false;
	}
	
	public boolean getState(){
		return this.active;
	}
	
}
