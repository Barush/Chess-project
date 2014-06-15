package brutalchess.ui;

import static brutalchess.Const.*;
import brutalchess.basis.Figure;
import brutalchess.basis.Position;
import brutalchess.figures.Pawn;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Canes
 */
public class Tile extends JPanel implements MouseListener{
	final private Position p;
	final private int color;
	
	public Tile(int color, Position p){
            this.color = color;
            this.p = p;

            this.repaintColor();
            
            this.addMouseListener(this);
	}
		
	final public void repaintColor(){
		this.setBackground(this.color == BLACK ? Color.LIGHT_GRAY : Color.WHITE);
	}
        
        public void markTile(){
            this.setBackground(Color.decode(this.color == BLACK ? "#88b178" : "#c2e8b3"));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(((p.getDesk().getActive() == null) || (p.getDesk().getActive().getTile() == this)) && (p.getFigure() != null)){
                p.getFigure().markFigure();
            }
            else if(p.getDesk().getActive() != null){
                p.moveActiveFigure();           
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

}
