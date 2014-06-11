/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalchess.basis;

import brutalchess.ui.Tile;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Barush
 */
public abstract class Figure {
    protected Position pos;
    protected int col; // color
	
	public abstract boolean canMove(Position p);
	public abstract String getPathToPic();
    
    public Figure(Position pos, int col){
        this.pos = pos;
        this.col = col;
//		pos.setFigure(this);
    }
    
    public void setPosition(Position pos){
        this.pos = pos;
    }
    
    public Position getPosition(){
        return this.pos;
    }
    
    public void setColor(int col){
        if((col > 1) || (col < 0)){
            //ERROR
        }
        this.col = col;
    }
    
    public int getColor(){
        return this.col;
    }
	
	public void paintFigure() {
		Tile tile = this.pos.getTile();
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResource( this.getPathToPic() ));
		} catch (IOException ex) {
			Logger.getLogger(Figure.class.getName()).log(Level.SEVERE, null, ex);
			return;
		}
		Image dimg = image.getScaledInstance(12, 12, Image.SCALE_SMOOTH);
		ImageIcon imgIcon = new ImageIcon( dimg );
		JLabel picLabel = new JLabel( imgIcon );
		tile.add(picLabel);
	}

}
