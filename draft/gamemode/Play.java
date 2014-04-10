/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.gamemode;

import draft.swingui.TileComponent;
import java.util.Queue;
import java.util.Timer;import java.util.TimerTask;

 /**
 * Class Play - implements some more stuff about playing
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
import java.util.logging.Level;
import java.util.logging.Logger;

public class Play extends TimerTask {

    private int turn;
    private Queue<TurnInNotation> load;
    Replay repl;
    private int vilo;
    private int times = this.vilo;
    
    /**
     * Constructor of the play object
     * 
     * @param vilo viliams variable
     * @param turn count fo turns made
     * @param load queue of turns to load
     * @param repl replaying object
     */
    public Play(int vilo, int turn, Queue<TurnInNotation> load, Replay repl) {
        super();
        this.load = load;
        this.turn = turn;
        this.vilo = vilo;
        this.repl = repl;

    }

    /**
     * Method move - implements one move of a figure on the desk
     * 
     * @param load - Queue<TurnInNotation>, indexes of the two tiles between which we
     *              are moving written in basic notation like a1-b2 or a1xb2 if some figure
     *              is thrown during the move
     * @param vilo - some magic
     */
    private void move(Queue<TurnInNotation> load, int vilo) {
        int turn = 1;

        if (load.isEmpty()) {
            return;
        }
        //System.out.println(load.peek().getCfrom()+""+load.peek().getRfrom());
        for (TurnInNotation note : load) {
            repl.getTileDesk()[note.getRfrom() - 1][note.getCfrom() - 'a'].getTilePosition().getFigure().move(repl.getTileDesk()[note.getRto() - 1][note.getCto() - 'a'].getTilePosition());
            if (turn >= vilo) {
                return;
            }
            turn++;

        }
    }
    
    /**
     * 
     * @return count of turns which were made
     */
    public int getVilo() {
        return times;
    }

    /**
     * method invoked when the object is started
     */
    public void run() {
        times++;
        if (times <= (this.turn * 2)) {
            repl.removeAllFigures();
            repl.initDesk();
            move(load, times);
            repl.updateDesk();
        } else {
            //Stop Timer.
            this.cancel();
        }
    }
}
