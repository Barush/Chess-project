
package ija.homework3.basis;

import ija.homework3.figures.*;
import java.util.ArrayList;

/**
 *
 * @author DumbEar
 */
public class Desk {
   
	public static final int BLACK = 0;
	public static final int WHITE = 1;
    protected int dim;
    public Position [][] pos;
    protected ArrayList<Figure> WhiteFigures = new ArrayList<>();
    protected ArrayList<Figure> BlackFigures = new ArrayList<>();
    
    public Desk( )
    {
        this.dim = 8;
        pos = new Position [dim][dim];
        for( int rows = 0; rows < dim; rows++ )
        {
            for( int cols = 0; cols < dim; cols++ )
            {
                pos[rows][cols] = new Position(this,(char)('a'+cols),1+rows );
            }
        }
        for( int rows = 0; rows < 3; rows++ )
        {
            for( int cols = 0; cols < dim; cols++ )
            {
                if( ( (rows+cols)%2 ) != 0 )
                {        
                    pos[rows][cols].putFigure( new Pawn(pos[rows][cols], BLACK ));
                    BlackFigures.add(pos[rows][cols].getFigure());
                }
            }
        }
        for( int rows = dim-1; rows > dim-4; rows-- )
        {
            for( int cols = 0; cols < dim; cols++ )
            {
                if( ( (rows+cols)%2 ) != 0 )
                {
                    pos[rows][cols].putFigure( new Pawn(pos[rows][cols], WHITE ));
                    WhiteFigures.add(pos[rows][cols].getFigure());
                }
            }
        }
    }
    public void initMoves()
    {
        for (Figure f : this.BlackFigures)
        {
            f.addMoves();
        }
        for (Figure f : this.WhiteFigures)
        {
            f.addMoves();
        }
    }
    
    public Position getPositionAt(char c, int r)
    {
        if( c < 'a' || c > ('a'+this.dim-1) || r < 1 || r > this.dim )
        {
            return null;
        }  
        else
        {
            
            return( this.pos[r-1][(int)(c-'a')] );
        }
    }

    public Figure getFigureAt(char c, int r) 
    {
        return getPositionAt(c, r).getFigure();
        
    }
    
    public void removefromFigurelist( int colour, Figure toRemove )
    {
        if( colour == WHITE )
        {
            this.WhiteFigures.remove( toRemove );
        }
        else
        {
            this.BlackFigures.remove( toRemove );
        }
    }
    
    public void addtoFigurelist( int colour, Figure toAdd )
    {
        if( colour == WHITE )
        {
            this.WhiteFigures.add( toAdd );
        }
        else
        {
            this.BlackFigures.add( toAdd );
        }
    }
    public ArrayList retWhitelist()
    {
        return this.WhiteFigures;
    }
    public ArrayList retBlacklist()
    {
        return this.BlackFigures;
    }
    
}
    
    
