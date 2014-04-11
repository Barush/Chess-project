/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.gamemode;

import draft.swingui.*;
import java.util.Queue;
import java.util.Timer;
import javax.swing.JOptionPane;

/**
 * CLass replay implements all replaying logic
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class Replay extends GameUI {

    private int vilo = 0;
    private Queue<TurnInNotation> load;
    private Timer timer = new Timer();
    private Play play;
    private boolean press = false;

    /**
     * Constructor of the replay object
     * 
     * @param load queue of turns to replay
     */
    public Replay(Queue<TurnInNotation> load) {
        super();
        this.load = load;
        initDesk();
        updateDesk();
        setVisible(true);
        this.setTitle("Replaying game");
    }

    /**
     *  
     * @param evt GUI action performed event
     */
    @Override
    public void PlayActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        //
        press = true;
        play = null;
        if (getTurn() <= vilo) {
            JOptionPane.showMessageDialog(this, "Wrong parameter turn");
            return;
        }
        play = new Play(vilo, getTurn(), load, this);

        timer.schedule(play, 0, getTime() * 1000);
        System.out.println(play.getVilo());

    }

    /**
     * 
     * @param evt GUI action performed event
     */
    @Override
    public void StopActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        //
        if (press) {
            timer.cancel();
            vilo = play.getVilo();
            timer = null;
            timer = new Timer();
            press = false;
        }
    }

    /**
     * 
     * @param evt GUI action performed event
     */
    @Override
    public void NextActionPerformed(java.awt.event.ActionEvent evt) {


        if (press) {
            vilo = play.getVilo() - 1;
            press = false;
        }

        if (vilo == this.load.size()) {
            return;
        }

        vilo++;
        removeAllFigures();
        initDesk();
        if (vilo < 0) {
            return;
        } else if (vilo > this.load.size()) {
            return;
        } else if (vilo == 0) {
            removeAllFigures();
            initDesk();
            updateDesk();
            return;
        }

        next(this.load, vilo);
        updateDesk();
    }

    /**
     * 
     * @param evt GUI action performed event
     */
    @Override
    public void PrevActionPerformed(java.awt.event.ActionEvent evt) {
        if (press) {
            vilo = play.getVilo() - 1;
            press = false;
        }

        if (vilo == 0) {
            return;
        }

        vilo--;

        removeAllFigures();
        initDesk();
        if (vilo <= 0) {
            removeAllFigures();
            initDesk();
            updateDesk();
            return;
        } else if (vilo > this.load.size()) {
            return;
        }
        removeAllFigures();
        initDesk();


        next(this.load, vilo);
        updateDesk();
    }

    /**
     * Method which extracts next turn on a queue of turns in load param
     * 
     * @param load queue of turns loaded from file
     * @param vilo count of turn
     */
    public void next(Queue<TurnInNotation> load, int vilo) {
        int turn = 1;

        if (load.isEmpty()) {
            return;
        }
        //System.out.println(load.peek().getCfrom()+""+load.peek().getRfrom());
        for (TurnInNotation note : load) {
            getTileDesk()[note.getRfrom() - 1][note.getCfrom() - 'a'].getTilePosition().getFigure().move(getTileDesk()[note.getRto() - 1][note.getCto() - 'a'].getTilePosition());
            if (turn >= vilo) {
                return;
            }
            turn++;

        }
    }
}
