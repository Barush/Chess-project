
package ija.homework3.basis;

import ija.homework3.online.Online;
import ija.homework3.gui.Gui;
import ija.homework3.basis.*;
import ija.homework3.figures.*;
import ija.homework3.*;
import java.util.ArrayList;

/**
 * Creates new game
 * initializes Desk
 * @author DumbEar
 */
public class Game {
   
    public static final int SINGLE = 0;
    public static final int LOCAL = 1;
    public static final int ONLINE = 2;
    public int player;
    public Desk desk;
    public Gui gui;
    Online online;
    int gameType;
    /**
     * initializes game and player on move
     */
    public Game(Gui newgui, int newplayer, int newgameType, String connection)
    {
        desk = new Desk();
        newgui.initDesk(desk);
		gui = newgui;
        player = newplayer;
        gameType = newgameType;
		
        if (gameType == ONLINE){
            online = new Online(connection, this);
        }
		
        System.out.println("type: "+gameType+ " player: "+player);
    }
    /**
     * returns player on move
     * @return
     */
    public int Player()
    {
        return this.player;
    }
    public boolean playerPlay( Figure f, Position p )
    {
        // player should play with his own figures...
        if (f.getColour() != player)
        {
            System.out.println("Cheater! Play with your own color!");
            return false;
        }
		
		boolean jumped = false;
		Position previousPos = f.getPosition();
		
        if( (jumped = this.JumpFigure( f, p )) || this.MoveFigure( f, p ) )
        {
			String move = encodeMove(previousPos, p, jumped);
            if( desk.BlackFigures.isEmpty() )
            {
                //biely vyhral
                System.out.println( "WHITE WINS" );
            }
            else if( desk.WhiteFigures.isEmpty() ) {
                //cierny vyhral
                System.out.println( "BLACK WINS" );
            }

            // zkontrolovat, jestli ten druhý může táhnout
			
            if (gameType == LOCAL)
            {
                switchPlayers();
            } 
            else if (gameType == ONLINE)
            {
                online.sendMove(move);
				online.listen4Play();
            } 
            else 
            {
                this.UAI();
            }	
            return true;
        }
        return false;
    }
    
    public boolean MoveFigure( Figure fmove, Position p ) // parametre figurka a pozicia
    {
        boolean temp;
        if( player == desk.WHITE )
        {
            for( Figure f : this.desk.WhiteFigures )
            {
                ArrayList<Position> Jumps = f.returnJumps();
                if( Jumps.isEmpty() )
                {
                    //tu musi byt vybrana figurka a kam..nieco ako figurka.move(kam);
                    //return( fmove.move( p ) );
                    continue;
                }
                else
                {
                    return false;
                }
            }
            temp = fmove.move( p ); 
            this.desk.initMoves();
            return temp;
        }
        else
        {
            for( Figure f : this.desk.BlackFigures )
            {
                ArrayList<Position> Jumps = f.returnJumps();
                if( Jumps.isEmpty() )
                {
                    //tu musi byt vybrana figurka a kam..nieco ako figurka.move(kam); 
                    continue;
                }
                else
                {
                    return false;
                }
            }
            temp = fmove.move( p );
            this.desk.initMoves();
            return temp;
        }
    }
    public boolean JumpFigure( Figure f, Position p) // parametre figurka a pozicia
    {
        boolean temp;
        temp = f.jump( p );
        if( temp ) 
        {
            this.desk.initMoves();
        }
        return temp;
    }
    public void UAI()
    {
		//this.desk.initMoves();
        for( Figure f : this.desk.BlackFigures )
        {
            ArrayList<Position> Jumps = f.returnJumps();
            for( Position pj : Jumps )
            {
                if( this.JumpFigure( f, pj ) ) //parametre chybaju
                {
                    return;
                }
            }
        }
        for( Figure f : this.desk.BlackFigures )
        {
            ArrayList<Position> Moves = f.returnMoves();
            for( Position pm : Moves )
            {
                if( this.MoveFigure( f, pm ) ) //parametre chybaju
                {
                    return;
                }
            }
        }
        //return false; //WHITE WIN
    }
	
    public void switchPlayers()
    {
        if (this.player == desk.BLACK)
        {
            this.player = desk.WHITE;
        }
        else 
        {
            this.player = desk.BLACK;
        }
    }
	
	public String encodeMove(Position start, Position dest, boolean jumped){
		String move = "";
		move += start.returnCol();
		move += start.returnRow();
		move += jumped ? 'x' : '-';
		move += dest.returnCol();
		move += dest.returnRow();
		return move;
	}
	
	public Figure parseFigure(String move){
		char c = move.charAt(0);
		int r = move.charAt(1) - '0';
		Position p = desk.getPositionAt(c, r);
		return p.getFigure();
	}
	public Position parsePosition(String move){
		char c = move.charAt(3);
		int r = move.charAt(4) - '0';
		Position p = desk.getPositionAt(c, r);
		return p;
	}
}
