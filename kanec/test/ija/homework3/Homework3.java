
package ija.homework3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ija.homework3.basis.*;
import ija.homework3.figures.*;
import junit.framework.TestCase;

/**
 * Homework2: uloha c. 3 z IJA
 * Trida testujici implementaci zadani 3. ukolu.
 */
public class Homework3 extends TestCase {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }
    public void printDesk( Desk d1) {
        Figure temp;
        for( int rows = 0; rows < 8; rows++ )
        {
            for( int cols = 0; cols < 8; cols++ )
            {
                if ( ( temp = d1.getFigureAt(( char)(cols + 'a'), rows+1 )) == null )
                {
                    System.out.print( "| -- |" );
                }
                else 
                {
                    if( temp.getColour() == 0)
                    {
                        if( temp instanceof Pawn )
                            System.out.print( "| BP |" );
                        else
                            System.out.print( "| BQ |" );
                    }
                    else
                    {
                        if( temp instanceof Pawn )
                            System.out.print( "| WP |" );
                        else
                            System.out.print( "| WQ |" );
                    }
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /** Test inicializacie dosky */
    @Test
    public void test01() {
        Desk d1 = new Desk();
        System.out.println( "TEST 1 - test inicializacie dosky");
        
        d1.initMoves();
        
        this.printDesk(d1);  
        //System.exit(1);
    }

    /** Test pohybov - PAWN */
    @Test
    public void test02() {
        Desk d1 = new Desk();
        System.out.println( "TEST 2 - test pohybov pawna");
        Figure figure = d1.getFigureAt('b', 3);
        Figure temp = new Pawn(d1.getPositionAt('a', 2), 1);
        
        
        Position p1 = d1.getPositionAt('a', 4);
        Position p2 = d1.getPositionAt('b', 5);
        Position p3 = d1.getPositionAt('c', 6);
        Position p4 = d1.getPositionAt('b', 6);
        
        Position p5 = d1.getPositionAt('a', 2);
        p5.removeFigure();
        p5.putFigure(temp);
        Position p6 = d1.getPositionAt('b', 1);
        p6.removeFigure();
        //Position p7 = d1.getPositionAt('c', 8);
        //p7.removeFigure();
        
        assertTrue("pohyb z b3 -> a4 ",figure.move(p1) );
        System.out.println("pobyb z b3 -> a4");
        this.printDesk( d1 );
        
        assertTrue("pohyb z a4 -> b5",figure.move(p2) );
        System.out.println("pohyb z a4 -> b5");
        this.printDesk( d1 );
        
        assertFalse("pohyb z b5 -> c6 -failed(c6 nieje prazdne)", figure.move(p3));
        System.out.println("pohyb z b5 -> c6 -failed(c6 nieje prazdne)");
        this.printDesk( d1 );
        
        assertFalse("pohyb z b5 -> a4 -failed(pohyb vzad)", figure.move(p1) );
        System.out.println("pohyb z b5 -> a4 -failed(pohyb vzad)");
        this.printDesk( d1 );
    
        assertFalse("pohyb z b5 -> b6 -failed(nevalidny pohyb)", figure.move(p4));
        System.out.println("pohyb z b5 -> b6 -failed(nevalidny pohyb)");
        this.printDesk( d1 );
        
        //figure.move(p5);
        System.out.println("PRE-TRANSFORMUS TOTALUS!!");
        this.printDesk( d1 );
        temp.move(p6);
        //figure.jump(p7);
        System.out.println("TRANSFORMUS TOTALUS!!");
        this.printDesk( d1 );
        
        //System.out.println( d1.WhiteFigures );
    }


    /** Test pohybov - QUEEN */
    @Test
    public void test03() {
        Desk d1 = new Desk();
        System.out.println( "TEST 3 - test pohybov queen");
        //make room for queen movements
        Figure fpawn = d1.getFigureAt('d', 3);
        Position ppawn = d1.getPositionAt('c', 4);
        Figure fwpawn = d1.getFigureAt('g', 6);
        Position pwpawn = d1.getPositionAt('h', 5);
        fwpawn.move(pwpawn);
        fpawn.move(ppawn);
        
        Figure f1 = new Queen(d1.getPositionAt('d', 3), 0);
        Position p1 = d1.getPositionAt('g', 6);
        Position p2 = d1.getPositionAt('d', 3);
        Position p3 = d1.getPositionAt('b', 5);
        Position p4 = d1.getPositionAt('h', 7);
        Position p5 = d1.getPositionAt('e', 3);
        
        System.out.println( "Inicializacia ");
        this.printDesk(d1);
        
        assertTrue("pohyb z d3 -> g6", f1.move(p1) );
        System.out.println( "pohyb z d3 -> g6" );
        this.printDesk(d1);
        
        assertTrue("pohyb z g6 -> d3", f1.move(p2));
        System.out.println( "pohyb z g6 -> d3" );
        this.printDesk(d1);
        
        assertFalse("pohyb z d3 -> b5 -failed(cesta je neprazdna)", f1.move(p3) );
        System.out.println( "pohyb z d3 -> b5 -failed(cesta je neprazdna)");
        this.printDesk(d1);
        
        assertFalse("pohyb z d3 -> h7 -failed(pozicia je neprazdna)", f1.move(p4) );
        System.out.println( "pohyb z d3 -> h7 -failed(pozicia je neprazdna)");
        this.printDesk(d1);
        
        assertFalse("pohyb z d3 -> e3 -failed(nevalidny pohyb)", f1.move(p5) );
        System.out.println( "pohyb z d3 -> e3 -failed(nevalidny pohyb)");
        this.printDesk(d1);
    }

    /** Test vyhadzovania - PAWN */
    @Test
    public void test04() {
        Desk d1 = new Desk();
        System.out.println( "TEST 4 -vyhadzovanie - PAWN");
      
        Figure f4 = d1.getFigureAt('a', 2 );
        Position p6 = d1.getPositionAt('c', 4);
        
        assertFalse("skok z a2 x c4 -failed(vyhodenie vlastneho)", f4.jump(p6) );
        System.out.println( "skok z a2 x c4 -failed(vyhodenie vlastneho)");
        this.printDesk(d1);
        
        //prepare for jump
        Figure f1 = d1.getFigureAt('b', 3);
        Position p1 = d1.getPositionAt('c', 4);
        Position p2 = d1.getPositionAt('b', 5);
        Figure f10 = d1.getFigureAt('e', 6);
        Position p10 = d1.getPositionAt('d',5);
        Position p11 = d1.getPositionAt('e',6);
        f10.move(p10);
        
        assertFalse("skok z b3 x e6 -failed(daleky skok)", f1.jump(p11) );
        System.out.println( "skok z b3 x e6 -failed(daleky skok)");
        this.printDesk(d1);
        
        //move to get jumped
        f1.move(p1);  
        f1.move(p2);
        
        assertFalse("skok z a2 x c4 -failed(vyhodenie prazdneho miesta)", f4.jump(p6) );
        System.out.println( "skok z a2 x c4 -failed(vyhodenie prazdneho miesta)");
        this.printDesk(d1);
      
        Figure f2 = d1.getFigureAt('a', 6 );
        Position p3 = d1.getPositionAt('c', 4);
        
        //System.out.println( d1.WhiteFigures );
        
        assertTrue("skok z a6 x c4", f2.jump(p3) );
        System.out.println( "skok z a6 x c4");
        this.printDesk(d1);
        
        Figure f3 = d1.getFigureAt('d', 3 );
        Position p4 = d1.getPositionAt('b', 5);
        
        assertTrue("skok z d3 x b5", f3.jump(p4) );
        System.out.println( "skok z d3 x b5"); 
        this.printDesk(d1);
        
        //System.out.println( d1.WhiteFigures );
    }
    
    /**Test vyhadzovania - Queen*/
    @Test
    public void test05() {
        Desk d1 = new Desk();
        System.out.println( "TEST 5 -vyhadzovanie - QUEEN");
        
        //make room for queen movements
        Figure fpawn = d1.getFigureAt('d', 3);
        Position ppawn = d1.getPositionAt('c', 4);
        Figure fwpawn = d1.getFigureAt('g', 6);
        Position pwpawn = d1.getPositionAt('f', 5);
        Figure blackpawn1 = d1.getFigureAt('f', 3);
        Position blackpmw = d1.getPositionAt('e', 4);
        Position blackpmw2 = d1.getPositionAt('d', 5);
        Figure whitepawn1 = d1.getFigureAt('c', 6);
        Figure whitepawn2 = d1.getFigureAt('e',6);
        
        fwpawn.move(pwpawn);
        fpawn.move(ppawn);
        
        Figure f1 = new Queen(d1.getPositionAt('d', 3), 0);
        Position p1 = d1.getPositionAt('g', 6);
        Position p2 = d1.getPositionAt('d', 3);
        
        System.out.println("inicializacia");
        this.printDesk(d1);
        
        assertTrue("skok z d3 x g6", f1.jump(p1) );
        System.out.println( "skok z d3 x g6");
        this.printDesk(d1);
        
        assertFalse("skok z g6 x d3 -failed(nieje co vyhodit)", f1.jump(p2));
        System.out.println( "skok z g6 x d3 -failed(nieje co vyhodit)");
        this.printDesk(d1);
        
        assertTrue("pohyb z f3 -> e4(priprava skoku)", blackpawn1.move(blackpmw) );
        System.out.println( "pohyb z f3 -> e4(priprava skoku)");
        this.printDesk(d1);
        
        assertFalse("skok z g6 x d3 -failed(nemoze vyhodit vlastneho)", f1.jump(p2));
        System.out.println( "skok z g6 x d3 -failed(nemoze vyhodit vlastneho)");
        this.printDesk(d1);
            
        blackpawn1.move(blackpmw2);
        whitepawn1.jump(blackpmw);
        whitepawn2.move(pwpawn);
        
        assertFalse("skok z g6 x d3 -failed(nemoze skakat cez dvoch)", f1.jump(p2) );
        System.out.println( "skok z g6 x d3 -failed(nemoze skakat cez dvoch)");
        this.printDesk(d1);    
    }
}
