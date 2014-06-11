package brutalchess.ui;

import static brutalchess.Const.*;
import brutalchess.basis.Position;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Canes
 */
public class Tile extends JPanel{
	final private Position p;
	final private int color;
	
	public Tile(int color, Position p){
		this.color = color;
		this.p = p;
		
		this.repaintColor();
	}
		
	final public void repaintColor(){
		this.setBackground(this.color == BLACK ? Color.LIGHT_GRAY : Color.WHITE);
	}
}
