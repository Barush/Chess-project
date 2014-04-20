/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalchess;

import static brutalchess.Const.*;
import brutalchess.ui.Menu;
import brutalchess.ui.Game;
import brutalchess.basis.Desk;
import brutalchess.basis.Position;

/**
 *
 * @author Barush
 */
public class BrutalChess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Menu menu = new Menu();
        menu.setTitle("Chess project (Baruska a Ondrasek)");
    }
	
	// we should probably move this thing to some place else
	static public void initDesk(){
		Desk desk = new Desk(8);
		Game game = new Game(desk.getPositions(), 8);
//		game.setVisible(true); //???? shiiit :(
		
	}
}
