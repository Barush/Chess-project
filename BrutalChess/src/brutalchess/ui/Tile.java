package brutalchess.ui;

import static brutalchess.Const.*;
import brutalchess.basis.Position;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

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

        @Override
        public void mouseClicked(MouseEvent e) {
            if(p.getFigure() != null){
                //figure marking
                if(p.getFigure().isActive()){
                    //figure inactivation
                    p.getFigure().Activate(false);
                    this.setBackground(this.color == BLACK ? Color.LIGHT_GRAY : Color.WHITE);
                    this.p.setFigure(this.p.getFigure(), ".");
                    p.getDesk().setActive(null);
                }
                else{
                    //figure activation
                    if(p.getDesk().getActive() == null){
                        //if no other is active
                        p.getFigure().Activate(true);
                        this.p.setFigure(this.p.getFigure(), "active");
                        this.setBackground(Color.GREEN);
                        p.getDesk().setActive(p);
                    }
                }
            }
            else if(p.getDesk().getActive() != null){
                //figure moving
                if(p.getDesk().getActive().getFigure().canMove(p)){
                    System.out.println("Can move");
                    p.getDesk().getActive().getTile().setBackground(this.color == BLACK ? Color.LIGHT_GRAY : Color.WHITE);
                    this.p.setFigure(p.getDesk().getActive().getFigure(), ".");
                    p.getDesk().getActive().getFigure().Activate(false);
                    p.getDesk().setActive(null);
                }            
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
