
package draft;

import draft.gamemode.Player;
import draft.swingui.*;

/**
 * Main class of the project Draft, it invokes the menu, class with graphical 
 * user interface, where the user does all the actions from.
 * 
 * @author xserec00@sutd.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class MainClass {

    /**
     * The main class which is invoked when the whole project is turned on.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MenuUI menu = new MenuUI();
                //menu.setVisible(true);
            }
        });
    }
}
