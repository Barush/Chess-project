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
import draft.swingui.*;
import draft.ai.*;
import javax.swing.JOptionPane;

/**
 * Class computer implements the organization of a game player vs computer
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class Computer extends GameUI {

    private TileComponent tile1;
    private List<Turn> movePossibility;
    private Logic state;
    private boolean click = true;
    private ArtificalInteligence ai;
    private Queue<Report> record = new LinkedList<Report>(); // recording all game turn by turn
    private boolean jump = false;
    private int move = 1;

    /**
     * Constructor of the computer object when the game starts standard way.
     * 
     * @param color which color will the computer play
     */
    public Computer(int color) {
        super();
        initDesk();
        updateDesk();
        this.setTitle("Player vs. Computer");
        this.state = new Logic(getWhiteFigures(), getBlackFigures());


        if (color == 0) {
            //this.state.setGameState(GameState.PLAYER2);
            ai = new ArtificalInteligence(this.state);
            state.setGameState(GameState.PLAYER1);
            ai.computerTurnWhite(record, move, getVilorea());
            this.state.setGameState(GameState.PLAYER2);

        } else {
            ai = new ArtificalInteligence(this.state);
            this.state.setGameState(GameState.PLAYER1);
        }
        updateDesk();
    }

    /**
     * Constructor of the object computer for the game which loads game state
     * from external file
     * 
     * @param load queue of turns made in a previous instance of this game
     * @param color color which the computer will play for
     */
    public Computer(Queue<TurnInNotation> load, int color) {
        super();
        initDesk();
        this.setTitle("Player vs. Computer");
        this.state = new Logic(getWhiteFigures(), getBlackFigures());
        loadDesk(load, state, getRecord());
        // computer must play his turn    
        ai = new ArtificalInteligence(this.state);
        if (color == 0) { // black
            if (state.getGameSate().equals(GameState.PLAYER1)) {
                //ai = new ArtificalInteligence(this.state);
                state.setGameState(GameState.PLAYER1);
                ai.computerTurnWhite(record, move, getVilorea());
                this.state.setGameState(GameState.PLAYER2);
            }
        } else { // i m white
            System.out.println("tu lala");
            if (state.getGameSate().equals(GameState.PLAYER2)) {
                state.setGameState(GameState.PLAYER2);
                ai.computerTurnBlack(record, move, getVilorea());
                this.state.setGameState(GameState.PLAYER1);
            }
        }
        updateDesk();
    }

    /**
     * method implements the logic of the players turn - matching the figure and 
     * checking if the move is legal and moves the figure
     * 
     * @param e mouse event from event listener
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (javax.swing.SwingUtilities.isLeftMouseButton(e)) {

            if (state.statusGame().equals(GameState.WINBLACK)) {
                JOptionPane.showMessageDialog(this, "Black Win !!!");
            } else if (state.statusGame().equals(GameState.WINWHITE)) {
                JOptionPane.showMessageDialog(this, "White Win !!!");
            } else if (state.statusGame().equals(GameState.DRAW)) {
                JOptionPane.showMessageDialog(this, "Draw");
            }

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
                } else if (state.getGameSate().equals(GameState.PLAYER2) && (getBlackFigures().contains(tile1.getTilePosition().getFigure()))) {
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


            } else {
                TileComponent tile2 = (TileComponent) e.getSource();
                if(tile1.equals(tile2)){
                    tile1.updateTile();
                }
                else if (tile1.getTilePosition().getFigure() != null) {
                    // move white and computer
                    if (state.getGameSate().equals(GameState.PLAYER1)) {
                        whiteMove(tile2);
                        ai.computerTurnBlack(record, move, getVilorea());
                        state.setGameState(GameState.PLAYER1);
                        move++;
                    }
                    if (state.getGameSate().equals(GameState.PLAYER2)) {
                        blackMove(tile2);
                        move++;
                        ai.computerTurnWhite(record, move, getVilorea());
                        state.setGameState(GameState.PLAYER2);
                    }
                }
                updateDesk();
                click = true;
            }
        }
    }

    /**
     * whiteMove method -  implements all logic of one move of a white figure including 
     * invoking methods working with GUI. 
     * 
     * To see the changes made in this method you have to invoke updateDesk() 
     * afterwards.
     * 
     * This method also shows a help message if the turn which you are trying to 
     * make is illegal.
     * 
     * @param tile2 endtile of the move
     */
    public void whiteMove(TileComponent tile2) {

        movePossibility = state.getMovePossibility(1); // white
        if (movePossibility.isEmpty()) {
            state.setGameState(GameState.WINBLACK);
            return;
        }
        for (Turn turn : movePossibility) {
            if (turn.getPositionTo().equals(tile2.getTilePosition()) && turn.getPositionFrom().equals(tile1.getTilePosition())) {
                Figure figure = tile1.getTilePosition().getFigure().move(tile2.getTilePosition());

                if (figure != null) {
                    state.removeFigureFromDesk(figure);
                    jump = true;
                }
                if(jump){
                    getVilorea().append(move+". "+tile1.getTileColumn()+""+tile1.getTileRow()+"x"+tile2.getTileColumn()+""+tile2.getTileRow()+" ");
                } else {
                    getVilorea().append(move+". "+tile1.getTileColumn()+""+tile1.getTileRow()+"-"+tile2.getTileColumn()+""+tile2.getTileRow()+" ");
                }
                // reporting game for white player
                getRecord().add(new Report(move, tile1.getTilePosition(), tile2.getTilePosition(), jump, 1)); // white figure, save turn
                jump = false;
                state.setGameState(GameState.PLAYER2);
                state.changeFigures();
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
     * @param tile2 endtile of the move
     */
    public void blackMove(TileComponent tile2) {
        movePossibility = state.getMovePossibility(0); // black
        if (movePossibility.isEmpty()) {
            state.setGameState(GameState.WINWHITE);
            return;
        }
        for (Turn turn : movePossibility) {
            if (turn.getPositionTo().equals(tile2.getTilePosition()) && turn.getPositionFrom().equals(tile1.getTilePosition())) {
                Figure figure = tile1.getTilePosition().getFigure().move(tile2.getTilePosition());

                if (figure != null) {
                    state.removeFigureFromDesk(figure);
                    jump = true;
                }
                if(jump){
                    getVilorea().append(tile1.getTileColumn()+""+tile1.getTileRow()+"x"+tile2.getTileColumn()+""+tile2.getTileRow()+"\n");
                } else {
                    getVilorea().append(tile1.getTileColumn()+""+tile1.getTileRow()+"-"+tile2.getTileColumn()+""+tile2.getTileRow()+"\n");
                }
                // reporting game for white player
                getRecord().add(new Report(move, tile1.getTilePosition(), tile2.getTilePosition(), jump, 0)); // white figure, save turn
                jump = false;

                state.setGameState(GameState.PLAYER1);
                state.changeFigures();
                //movePossibility.clear();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Move for black from " + movePossibility.get(0).getPositionFrom().getColumn() + "" + movePossibility.get(0).getPositionFrom().getRow() + " to " + movePossibility.get(0).getPositionTo().getColumn() + "" + movePossibility.get(0).getPositionTo().getRow());
    }

    /**
     * method for saving game as the actual state of game turned into xml
     * 
     * @param evt GUI action performed event
     */
    @Override
    public void SaveGameActionPerformed(java.awt.event.ActionEvent evt) {
        //SaveGame save = new SaveGame(this);
        new SaveGame(getRecord());
    }

    /**
     * method for saving game as the queue of the turns in defined notation
     * 
     * @param evt GUI action performed event
     */
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
