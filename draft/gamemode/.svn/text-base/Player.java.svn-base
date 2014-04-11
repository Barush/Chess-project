/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.gamemode;

import draft.logic.GameState;
import draft.logic.*;
import draft.swingui.*;
import draft.figures.*;
import java.awt.event.MouseEvent;
import java.util.*;
import draft.basis.*;
import javax.swing.*;
import java.awt.event.*;

;

/**
 * Class Player - implements basic logic of game between two players on one
 * computer, extends GameUI where the canvas of the game window is implemented
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class Player extends GameUI {

    private Queue<Report> record = new LinkedList<Report>(); // recording all game turn by turn
    private int move = 1;
    private boolean jump = false;
    private boolean click = true;
    private TileComponent tile1;
    private List<Turn> movePossibility;
    private Logic state;

    /**
     * Constructor which is invoked, when a new game starts
     */
    public Player() {
        super();
        initDesk();
        updateDesk();
        this.setTitle("Player vs. Player");
        state = new Logic(getWhiteFigures(), getBlackFigures());
    }

    /**
     * Constructor which is invoked, when starting game is load from file
     * 
     * @param load queue of turns which were played before the loaded game was saved
     */
    public Player(Queue<TurnInNotation> load) {
        super();
        initDesk();
        state = new Logic(getWhiteFigures(), getBlackFigures());
        loadDesk(load, state, getRecord());
        System.out.println("pohol som sa");
        updateDesk();
    }
    
    /**
     * mousePressed method - implements turns logic depending on mouse clicks,
     *          it works with winning or loosing gameState and it implements all
     *          moves done with a figure
     * 
     * @param e MouseEvent cought by eventListener
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (javax.swing.SwingUtilities.isLeftMouseButton(e)) {
            //winning logic
            if (state.statusGame().equals(GameState.WINBLACK)) {
                JOptionPane.showMessageDialog(this, "Black Win !!!");
            } else if (state.statusGame().equals(GameState.WINWHITE)) {
                JOptionPane.showMessageDialog(this, "White Win !!!");
            } else if (state.statusGame().equals(GameState.DRAW)) {
                JOptionPane.showMessageDialog(this, "Draw");
            }
            //choosing a figure
            if (click) {
                tile1 = (TileComponent) e.getSource();
                // Player 1 (white player) turn
                if (state.getGameSate().equals(GameState.PLAYER1) && (getWhiteFigures().contains(tile1.getTilePosition().getFigure()))) {
                    // matched figures
                    if (tile1.getTilePosition().getFigure().getColor() == 1) { // white
                        if (tile1.getTilePosition().getFigure() instanceof Draft) { // white draft
                            tile1.setWhiteMatchedDraft();
                        } else {
                            tile1.setWhiteMatchedPawn();
                        }
                    }
                    click = false;
                }
                // Palyer 2 (blsck player) turn
                if (state.getGameSate().equals(GameState.PLAYER2) && (getBlackFigures().contains(tile1.getTilePosition().getFigure()))) {
                    // matched figures
                    if (tile1.getTilePosition().getFigure().getColor() == 0) { // black
                        if (tile1.getTilePosition().getFigure() instanceof Draft) { // black draft
                            tile1.setBlackMatchedDraft();
                        } else { // black pawn
                            tile1.setBlackMatchedPawn();
                        }
                    }
                    click = false;
                }
            //choosing an endtile of a turn
            } else {
                TileComponent tile2 = (TileComponent) e.getSource();
                //unmatching a figure
                if(tile1.equals(tile2)){
                    tile1.updateTile();
                }
                else if (tile1.getTilePosition().getFigure() != null) {
                    if (state.getGameSate().equals(GameState.PLAYER1)) {
                        whiteMove(tile2);
                    } else {
                        if (state.getGameSate().equals(GameState.PLAYER2)) {
                            blackMove(tile2);
                        }
                    }
                }
                updateDesk();
                click = true;
            }
        }
    }
    
    /**
     * whiteMove method - implements all logic of one move of a white figure including 
     * invoking methods working with GUI. 
     * 
     * To see the changes made in this method you have to invoke updateDesk() 
     * afterwards.
     * 
     * This method also shows a help message if the turn which you are trying to 
     * make is illegal.
     * 
     * @param tile2 endtile of a turn
     */
    public void whiteMove(TileComponent tile2) {

        movePossibility = state.getMovePossibility(1); // white
        if (movePossibility.isEmpty()) {
            state.setGameState(GameState.WINBLACK);
            JOptionPane.showMessageDialog(this, "Black Win !!!");
            return;
        }
        for (Turn turn : movePossibility) {
            if (turn.getPositionTo().equals(tile2.getTilePosition()) && turn.getPositionFrom().equals(tile1.getTilePosition())) {
                Figure figure = tile1.getTilePosition().getFigure().move(tile2.getTilePosition());

                if (figure != null) {
                    state.removeFigureFromDesk(figure);
                    jump = true;
                }
                if (jump) {
                    getVilorea().append(move + ". " + tile1.getTileColumn() + "" + tile1.getTileRow() + "x" + tile2.getTileColumn() + "" + tile2.getTileRow() + " ");
                } else {
                    getVilorea().append(move + ". " + tile1.getTileColumn() + "" + tile1.getTileRow() + "-" + tile2.getTileColumn() + "" + tile2.getTileRow() + " ");
                }

                // reporting game for white player
                getRecord().add(new Report(move, tile1.getTilePosition(), tile2.getTilePosition(), jump, 1)); // white figure, save turn
                jump = false;

                state.changeFigures();

                state.setGameState(GameState.PLAYER2);
                //movePossibility.clear();
                return;
            }

        }
        JOptionPane.showMessageDialog(this, "Move for white from " + movePossibility.get(0).getPositionFrom().getColumn() + "" + movePossibility.get(0).getPositionFrom().getRow() + " to " + movePossibility.get(0).getPositionTo().getColumn() + "" + movePossibility.get(0).getPositionTo().getRow());
    }
    
    /**
     * blackMove method -  implements all logic of one move of a black figure including 
     * invoking methods working with GUI. 
     * 
     * To see the changes made in this method you have to invoke updateDesk() 
     * afterwards.
     * 
     * This method also shows a help message if the turn which you are trying to 
     * make is illegal.
     * 
     * @param tile2 endtile of a turn
     */
    public void blackMove(TileComponent tile2) {
        movePossibility = state.getMovePossibility(0); // black
        if (movePossibility.isEmpty()) {
            state.setGameState(GameState.WINWHITE);
            JOptionPane.showMessageDialog(this, "White Win !!!");
            return;
        }
        for (Turn turn : movePossibility) {
            if (turn.getPositionTo().equals(tile2.getTilePosition()) && turn.getPositionFrom().equals(tile1.getTilePosition())) {
                Figure figure = tile1.getTilePosition().getFigure().move(tile2.getTilePosition());

                if (figure != null) {
                    state.removeFigureFromDesk(figure);
                    jump = true;
                }
                if (jump) {
                    getVilorea().append(tile1.getTileColumn() + "" + tile1.getTileRow() + "x" + tile2.getTileColumn() + "" + tile2.getTileRow() + "\n");
                } else {
                    getVilorea().append(tile1.getTileColumn() + "" + tile1.getTileRow() + "-" + tile2.getTileColumn() + "" + tile2.getTileRow() + "\n");
                }
                getRecord().add(new Report(move, tile1.getTilePosition(), tile2.getTilePosition(), jump, 0));
                jump = false; // default value
                move++; // incremet move

                state.changeFigures();
                state.setGameState(GameState.PLAYER1);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Move for black from " + movePossibility.get(0).getPositionFrom().getColumn() + "" + movePossibility.get(0).getPositionFrom().getRow() + " to " + movePossibility.get(0).getPositionTo().getColumn() + "" + movePossibility.get(0).getPositionTo().getRow());
    }

    @Override
    public void SaveGameActionPerformed(java.awt.event.ActionEvent evt) {
        //SaveGame save = new SaveGame(this);
        new SaveGame(getRecord());
    }

    @Override
    public void SaveNotationActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        //SaveGameInNotation game = new SaveGameInNotation();
        //LoadGame game = new LoadGame();
        //
        new SaveGame(getRecord(), 1);
    }

    /**
     * @return the record
     */
    @Override
    public Queue<Report> getRecord() {
        return record;
    }
}
