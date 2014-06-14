/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalchess.basis;

import brutalchess.ui.Tile;
import java.awt.Color;
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
    protected boolean active;
    protected JLabel picLabel;
    protected JLabel actLabel;
	
	public abstract boolean canMove(Position p);
	public abstract String getPathToPic(String state);
    
    public Figure(Position pos, int col){
        this.pos = pos;
        this.col = col;
        this.active = false;
        
        BufferedImage image;
        try {
                image = ImageIO.read(getClass().getResource( this.getPathToPic("inactive") ));
        } catch (IOException ex) {
                Logger.getLogger(Figure.class.getName()).log(Level.SEVERE, null, ex);
                return;
        }
        Image dimg = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon( dimg );
        this.picLabel = new JLabel( imgIcon );
        
        try {
                image = ImageIO.read(getClass().getResource( this.getPathToPic("active") ));
        } catch (IOException ex) {
                Logger.getLogger(Figure.class.getName()).log(Level.SEVERE, null, ex);
                return;
        }
        dimg = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon( dimg );
        this.actLabel = new JLabel( imgIcon );
        
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
    
    public boolean isActive(){
        if(this.active){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void Activate(boolean state){
        this.active = state;
    }
	
    public void markFigure(){
        if(this.isActive()){
            //figure inactivation
            this.Activate(false);
            this.pos.getTile().repaintColor();
            this.pos.setFigure(this, "inactive");
            this.pos.getDesk().setActive(null);
        }
        else{
            //figure activation
            if(this.pos.getDesk().getActive() == null){
                //if no other is active
                this.Activate(true);
                //this.pos.setFigure(this, "active");
                this.pos.getTile().setBackground(Color.GREEN);
                this.pos.getDesk().setActive(this.pos);
            }
        }
    }
    
    public void paintFigure(String state) {       
        Tile tile = this.pos.getTile();
        System.out.println("painting");
        
        if(state == "active"){
            tile.add(this.actLabel);
        }
        else{
            tile.add(this.picLabel);
        }
    }

        
}
