
package ija.homework3.figures;

import ija.homework3.basis.Figure;
import ija.homework3.basis.Position;
import java.util.ArrayList;
/**
 *
 * @author DumbEar
 */
public class Queen extends Figure {
    
    protected ArrayList<Position> Moves = new ArrayList<>();
    protected ArrayList<Position> Jumps = new ArrayList<>();
    
    public Queen( Position p, int set_colour )
    {
        super( p, set_colour );
        this.type = "queen";
    }
    
    @Override
    public ArrayList returnJumps()
    {
        return( this.Jumps );
    }
    @Override
    public ArrayList returnMoves()
    {
        return( this.Moves );
    }
    public void addMoves()
    {
        this.Moves.clear();
        this.Jumps.clear();
        Position RD = this.getPosition().nextPosition(1, 1);
        Position LD = this.getPosition().nextPosition(-1, 1);
        Position RU = this.getPosition().nextPosition(1, -1);
        Position LU = this.getPosition().nextPosition(-1, -1);
        int csRD = 1;
        int rsRD = 1;
        int csLD = -1;
        int rsLD = 1;
        int csRU = 1;
        int rsRU = -1;
        int csLU = -1;
        int rsLU = -1;
        
        while( RD != null )
        {
            if( this.canMove( RD ) )
            {
                this.Moves.add( RD );
            }
            else if( this.canJump( RD ) != null )
            {
                this.Jumps.add( RD );
            }
            csRD++;
            rsRD++;
            RD = this.getPosition().nextPosition(csRD, rsRD);
        }
        while( LD != null )
        {
            if( this.canMove( LD ) )
            {
                this.Moves.add( LD );
            }
            else if( this.canJump( LD ) != null )
            {
                this.Jumps.add( LD );
            }
            csLD--;
            rsLD++;
            LD = this.getPosition().nextPosition(csLD, rsLD);
        }
        while( RU != null )
        {
            if( this.canMove( RU ) )
            {
                this.Moves.add( RU );
            }
            else if( this.canJump( RU ) != null )
            {
                this.Jumps.add( RU );
            }
            csRU++;
            rsRU--;
            RU = this.getPosition().nextPosition(csRU, rsRU);
        }
        while( LU != null )
        {
            if( this.canMove( LU ) )
            {
                this.Moves.add( LU );
            }
            else if( this.canJump( LU ) != null )
            {
                this.Jumps.add( LU );
            }
            csLU--;
            rsLU--;
            LU = this.getPosition().nextPosition(csLU, rsLU);
        }
        
        System.out.println( "Figure:" + this.getClass() + this.colour + this.pos.returnCol() + this.pos.returnRow() + "pohyby:" + this.Moves);
        System.out.println( "Figure:" + this.getClass() + this.colour + this.pos.returnCol() + this.pos.returnRow() + "skoky:" + this.Moves);
    }
    
    @Override
    public boolean canMove( Position p )
    {
        int colshift = 0;
        int rowshift = 0;
        Position nextpos = null;
        Figure tempfig;
        Position thispos = this.getPosition();
        
        int distrow = p.returnRow() - thispos.returnRow();
        int distcol = p.returnCol() - thispos.returnCol();
        if( p.getFigure() == null )
        {
            if( p.sameDiagonal(this.getPosition()) )
            {
                if( (distcol < 0) && (distrow > 0) )
                {    
                    for( int i = 0; i < (Math.abs(distrow)-1); i++ )
                    {
                        colshift--;
                        rowshift++;
                        nextpos = this.pos.nextPosition(colshift, rowshift);
                        if( nextpos != null )
                        {
                            tempfig = nextpos.getFigure();
                            if( tempfig != null ) 
                            {
                                return false;
                            }
                        }
                        else
                            return false;
                    }
                }
                if( (distcol > 0) && (distrow > 0) )
                {    
                    for( int i = 0; i < (Math.abs(distrow)-1); i++ )
                    {
                        colshift++;
                        rowshift++;
                        nextpos = this.pos.nextPosition(colshift, rowshift);
                        if( nextpos != null )
                        {
                            tempfig = nextpos.getFigure();
                            if( tempfig != null ) 
                            {
                                return false;
                            }
                        }
                        else
                            return false;
                    }
                }
                if( (distcol < 0) && (distrow < 0) )
                {    
                    for( int i = 0; i < (Math.abs(distrow)-1); i++ )
                    {
                        colshift--;
                        rowshift--;
                        nextpos = this.pos.nextPosition(colshift, rowshift);
                        if( nextpos != null )
                        {
                            tempfig = nextpos.getFigure();
                            if( tempfig != null ) 
                            {
                                return false;
                            }
                        }
                        else
                            return false;
                    }
                }
                if( (distcol > 0) && (distrow < 0) )
                {    
                    for( int i = 0; i < (Math.abs(distrow)-1); i++ )
                    {
                        colshift++;
                        rowshift--;
                        nextpos = this.pos.nextPosition(colshift, rowshift);
                        if( nextpos != null )
                        {
                            tempfig = nextpos.getFigure();
                            if( tempfig != null ) 
                            {
                                return false;
                            }
                        }
                        else
                            return false;
                    }
                }
            }
            else
                return false;
        }
        else
            return false;
        
        return true;
    }
    //god help me for i will die here
    //this shall be my final resting place
    @Override
    public Position canJump( Position p )
    {
        Figure tempfig;
        Position kickoutpos = null;
        boolean kicked = false;
        int colshift = 0;
        int rowshift = 0;
        if( p.sameDiagonal(this.getPosition()) )
        {
            int distrow = p.returnRow() - this.pos.returnRow();
            int distcol = p.returnCol() - this.pos.returnCol();
            if( (distcol < 0) && (distrow > 0) )
            {    
                for( int i = 0; i < (Math.abs(distrow)-1); i++ )
                {
                    colshift--;
                    rowshift++;
                    kickoutpos = this.pos.nextPosition(colshift, rowshift);
                    if( kickoutpos != null )
                    {
                        tempfig = kickoutpos.getFigure();
                        if( tempfig != null ) 
                        {
                            if( tempfig.getColour() != this.colour )
                            {
                                if( kicked == false )
                                {    
                                    kicked = true;
                                }
                                else
                                    kicked = false;
                            }
                            else
                                kicked = false;
                        }
                    }
                }
            }
            if( (distcol > 0) && (distrow > 0) )
            {    
                for( int i = 0; i < (Math.abs(distrow)-1); i++ )
                {
                    colshift++;
                    rowshift++;
                    kickoutpos = this.pos.nextPosition(colshift, rowshift);
                    if( kickoutpos != null )
                    {
                        tempfig = kickoutpos.getFigure();
                        if( tempfig != null )
                        {
                            if( tempfig.getColour() != this.colour )
                            {
                                if( kicked == false )
                                {
                                    kicked = true;
                                }
                                else
                                    kicked = false;
                            }
                            else
                                kicked = false;
                        }
                    }
                }
            }
            if( (distcol < 0) && (distrow < 0) )
            {    
                for( int i = 0; i < (Math.abs(distrow)-1); i++ )
                {
                    colshift--;
                    rowshift--;
                    kickoutpos = this.pos.nextPosition(colshift, rowshift);
                    if( kickoutpos != null )
                    {
                        tempfig = kickoutpos.getFigure();
                        if( tempfig != null )
                        {
                            if( tempfig.getColour() != this.colour )
                            {
                                if( kicked == false )
                                {
                                    kicked = true;
                                }
                                else
                                    kicked = false;
                            }
                            else
                                kicked = false;
                        }
                    }
                }
            }
            if( (distcol > 0) && (distrow < 0) )
            {    
                for( int i = 0; i < (Math.abs(distrow)-1); i++ )
                {
                    colshift++;
                    rowshift--;
                    kickoutpos = this.pos.nextPosition(colshift, rowshift);
                    if( kickoutpos != null )
                    {
                        tempfig = kickoutpos.getFigure();
                        if( tempfig != null )
                        {
                            if( tempfig.getColour() != this.colour )
                            {
                                if( kicked == false )
                                {
                                    kicked = true;
                                }
                                else
                                    kicked = false;
                            }
                            else
                                kicked = false;
                        }
                    }
                }
            }
        }
        if( kicked )
        {
            return kickoutpos;
        }
        return null;
    }
}
