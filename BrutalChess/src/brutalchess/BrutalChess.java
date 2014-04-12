/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalchess;

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
        Menu game = new Menu();
        game.setTitle("Chess project (Baruska a Ondrasek)");
    }
	
	// we should probably move this thing to some place else
	private void initDesk(){
		Desk desk = new Desk(8);
		Game game = new Game();
		
		for (Position[] poss : desk.getPositions()){
			for (Position pos : poss){
				// display every pos.Tile on JPanel?
			}
		}
	}
}
