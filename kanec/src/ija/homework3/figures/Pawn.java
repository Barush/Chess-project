
package ija.homework3.figures;

import ija.homework3.basis.Figure;
import ija.homework3.basis.Position;
import java.util.ArrayList;

/**
 *
 * @author DumbEar
 */
public final class Pawn extends Figure {
   
    protected ArrayList<Position> Moves = new ArrayList<>();
    protected ArrayList<Position> Jumps = new ArrayList<>();
    
    public Pawn( Position p, int set_colour )
    {
        super( p, set_colour );
		this.type = "pawn";
        
        //addMoves();
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
    
    @Override
    public void addMoves()
    {
        this.Moves.clear();
        this.Jumps.clear();
        Position nextposition1;
        Position nextposition2;
        Position jumpposition1;
        Position jumpposition2;
        if( this.getColour() == 0 )
        {
            nextposition1 = this.getPosition().nextPosition(1, 1);
            jumpposition1 = this.getPosition().nextPosition(2, 2);
            nextposition2 = this.getPosition().nextPosition(-1, 1);
            jumpposition2 = this.getPosition().nextPosition(-2, 2);
        } 
        else
        {
            nextposition1 = this.getPosition().nextPosition(1, -1);
            jumpposition1 = this.getPosition().nextPosition(2, -2);
            nextposition2 = this.getPosition().nextPosition(-1, -1);
            jumpposition2 = this.getPosition().nextPosition(-2, -2);
        }
        if( nextposition1 != null )
        {
            if( this.canMove( nextposition1 ) )
            {
                this.Moves.add( nextposition1 );
            }
            if( jumpposition1 != null )
            {
                if( this.canJump( jumpposition1 ) != null )
                {
                    this.Jumps.add( jumpposition1 );
                }
            }
        }
        if( nextposition2 != null )
        {
            if( this.canMove( nextposition2 ) )
            {
                this.Moves.add( nextposition2 );
            }
            if( jumpposition2 != null )
            {
                if( this.canJump( jumpposition2 ) != null )
                {
                    this.Jumps.add( jumpposition2 );
                }
            }
        }
        
//        System.out.println( "Figure:" + this.getClass() + this.colour + this.pos.returnCol() + this.pos.returnRow() + "pohyby:" + this.Moves);
//        System.out.println( "Figure:" + this.getClass() + this.colour + this.pos.returnCol() + this.pos.returnRow() + "skoky:" + this.Jumps);
    }
    
    @Override
    public boolean canMove( Position p )
    {
        /*if( this.Jumps.isEmpty() == false )
        {
            return false;
        }*/
        Position nextposition1;
        Position nextposition2;
        if( this.getColour() == 0 )
        {
            nextposition1 = this.getPosition().nextPosition(1, 1);
            nextposition2 = this.getPosition().nextPosition(-1, 1);
        } 
        else
        {
            nextposition1 = this.getPosition().nextPosition(1, -1);
            nextposition2 = this.getPosition().nextPosition(-1, -1);
        }
        if( nextposition1 != null )
        {
            if( p.getFigure() == null )
            {
                if( p.equals( nextposition1 )) 
                {
                    return true;
                }
            }
        }
        if( nextposition2 != null )
        {
            if( p.getFigure() == null )
            {
                if( p.equals( nextposition2 ))
                {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public Position canJump( Position p )
    {
        Position nextright;
        Position nextleft;
        Position tobekickedright;
        Position tobekickedleft;
        Position kickoutpos = null;
        int thiscolour;
        thiscolour = this.getColour();
        if( thiscolour == 0 )
        {
            nextright = this.getPosition().nextPosition(2, 2);
            nextleft = this.getPosition().nextPosition(-2, 2);
            tobekickedright = this.getPosition().nextPosition(1, 1);
            tobekickedleft = this.getPosition().nextPosition(-1, 1);
        }
        else
        {
            nextright = this.getPosition().nextPosition(2, -2);
            nextleft = this.getPosition().nextPosition(-2, -2);
            tobekickedright = this.getPosition().nextPosition(1, -1);
            tobekickedleft = this.getPosition().nextPosition(-1, -1);
        }
        if( nextright != null )
        {
            if( p.getFigure() == null )
            {
                if( p.equals( nextright ))
                {
                    Figure rightkickfig = tobekickedright.getFigure();
                    if( rightkickfig != null ) 
                    {
                        if( thiscolour != rightkickfig.getColour() )
                        {
                            kickoutpos = tobekickedright;
                            //return true;
                        }
                    }
                }
            }
        }
        if( nextleft != null )
        {
            if( p.getFigure() == null )
            {
                if( p.equals( nextleft ))
                {
                    Figure leftkickfig = tobekickedleft.getFigure();
                    if( leftkickfig != null ) 
                    {
                        if( thiscolour != leftkickfig.getColour() )
                        {
                            kickoutpos = tobekickedleft;
                            //return true;
                        }
                    }
                }
            }
        }
        return kickoutpos;
    }
}
