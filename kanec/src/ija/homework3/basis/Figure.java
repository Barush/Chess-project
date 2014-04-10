
package ija.homework3.basis;

import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author DumbEar
 */
public abstract class Figure {
    
    protected Position pos;
    protected int colour;
	public JLabel piece;
	public String type;
    
    public Figure( Position p, int set_colour )
    {
        colour = set_colour;
        
        this.pos = p;
        p.figure_on_pos = this;
        
    }
    public int getColour()
    {
        return this.colour;
    }
    public Position getPosition()
    {
        return this.pos;
    }
    public boolean isAtPosition(Position p) {
        
        return p.equals( this.pos );
    }
    public abstract boolean canMove(Position p);
    public abstract Position canJump(Position p);
    public abstract void addMoves();
    public abstract ArrayList returnJumps();
    public abstract ArrayList returnMoves();
    
    public boolean move(Position p)
    {
        if( this.canMove(p) )
        {
            Position temp = pos;
            pos = p;
            pos.putFigure(this);
            
            temp.removeFigure();
            
            p.transform();
            /*if( !p.transform( ))
            {
                this.addMoves();
            }*/
            return true;
        }
        return false;
    }
    public boolean jump(Position p)
    {
        Position kickoutpos = null;
        
        kickoutpos = this.canJump(p);
        if( kickoutpos != null )
        {
            Position temp = pos;
            pos = p;
            pos.putFigure(this);
                
            kickoutpos.desk.removefromFigurelist( kickoutpos.figure_on_pos.getColour(), kickoutpos.figure_on_pos );
                
            kickoutpos.removeFigure();
            temp.removeFigure();
                
            p.transform();
            /*if( !p.transform( ))
            {
                this.addMoves();
            }*/
                return true;
        }
        return false;
    }
}

