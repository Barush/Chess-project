/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brutalchess.basis;

import static brutalchess.Const.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Canes
 */
public class DeskTest {
	
	public DeskTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of getPositionAt method, of class Desk.
	 */
	@org.junit.Test
	public void testGetPositionAt() {
		System.out.println("getPositionAt");
		Game game = new Game(WHITE);
		
		char c = 'd';
		int r = 7;
		Position position = game.getDesk().getPositionAt(c, r);
		System.out.println(c +" - "+ r);
		
		c = position.getCol();
		r = position.getRow();
		Position result = game.getDesk().getPositionAt(c, r);
		System.out.println(c +" - "+ r);
		
		assertEquals(position, result);
	}
	
}
