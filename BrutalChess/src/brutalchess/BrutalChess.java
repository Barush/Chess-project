/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalchess;

import static brutalchess.Const.*;
import brutalchess.ui.Frame;
import brutalchess.ui.MenuPanel;
import brutalchess.ui.GamePanel;
import brutalchess.basis.Desk;
import brutalchess.basis.Position;
import javax.swing.JFrame;

/**
 *
 * @author Barush
 */
public class BrutalChess {

	static private Frame frame;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        frame = new Frame();
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setTitle("Chess project (Baruska a Ondrasek)");
		MenuPanel menu = new MenuPanel();
		frame.setContent( menu );
    }
	
	// we should probably move this thing to some place else
	static public void initDesk(){
		Desk desk = new Desk(8);
		frame.setContent( new GamePanel(desk.getPositions(), 8) );
	}
}
