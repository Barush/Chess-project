/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.ai;

import java.util.*;
import draft.logic.*;
import draft.basis.*;

/**
 * Class which takes care of the automatic turns in the game player vs. artificial 
 *  intelligence. 
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class ArtificalInteligence {

    private Logic logic;
    private List<Turn> movePossibility;
    private Random generator = new Random();
    private boolean jump = false;

    /**
     * Constructor of an object of Artifical Intelligence class
     * 
     * @param logic the new set of logical informations
     */
    public ArtificalInteligence(Logic logic) {
        this.logic = logic;
    }

    /**
     * The method which is invoked when the computers turn is on and it plays as 
     * the black player. It takes all possible moves of all figures and then, randomly, 
     * chose some turn which it does. It respects the rules - if you can jump,
     * you have to jump, if can draft and the pawn jump, draft will jump.
     * 
     * @param record - the list of turns to save the next turn to
     * @param move - the moves count to work with in record
     * @param Vilo - my team collegue (text area to put the record to)
     */
    public void computerTurnBlack(Queue<Report> record, int move, javax.swing.JTextArea Vilo) {
        if (logic.getGameSate().equals(GameState.PLAYER2)) {
            movePossibility = logic.getMovePossibility(0); // computer will be only black

            if (movePossibility.size() == 0) {
                logic.setGameState(GameState.WINWHITE);
                return;
            }
            
            int randomIndex = generator.nextInt(movePossibility.size());
            
          
            Figure figure = movePossibility.get(randomIndex).getPositionFrom().getFigure().move(movePossibility.get(randomIndex).getPositionTo());

            if (figure != null) {
                logic.removeFigureFromDesk(figure);
                jump = true;
            }
            if(jump){
                Vilo.append(movePossibility.get(randomIndex).getPositionFrom().getColumn()+""+movePossibility.get(randomIndex).getPositionFrom().getRow()+"x"+movePossibility.get(randomIndex).getPositionTo().getColumn()+""+movePossibility.get(randomIndex).getPositionTo().getRow()+"\n");
            } else {
                Vilo.append(movePossibility.get(randomIndex).getPositionFrom().getColumn()+""+movePossibility.get(randomIndex).getPositionFrom().getRow()+"-"+movePossibility.get(randomIndex).getPositionTo().getColumn()+""+movePossibility.get(randomIndex).getPositionTo().getRow()+"\n");
            }
            record.add(new Report(move, movePossibility.get(randomIndex).getPositionFrom(), movePossibility.get(randomIndex).getPositionTo(), jump, 0));
           
            jump = false;
            logic.changeFigures();
            //logic.setGameState(GameState.PLAYER1);
            //movePossibility.clear();
            return;
        }
    }
    
    /**
     * The method which is invoked when the computers turn is on and it plays as 
     * the white player. It takes all possible moves of all figures and then, randomly, 
     * chose some turn which it does. It respects the rules - if you can jump,
     * you have to jump, if can draft and the pawn jump, draft will jump.
     * 
     * @param record - the list of turns to save the next turn to
     * @param move - the moves count to work with in record
     * @param Vilo - my team collegue (text area to put the record to) 
     */
    
    public void computerTurnWhite(Queue<Report> record, int move, javax.swing.JTextArea Vilo) {
        if (logic.getGameSate().equals(GameState.PLAYER1)) {
            movePossibility = logic.getMovePossibility(1); // computer will be only white
            
            if (movePossibility.size() == 0) {
                logic.setGameState(GameState.WINBLACK);
                return;
            }

            int randomIndex = generator.nextInt(movePossibility.size());
      
            Figure figure = movePossibility.get(randomIndex).getPositionFrom().getFigure().move(movePossibility.get(randomIndex).getPositionTo());

            if (figure != null) {
                logic.removeFigureFromDesk(figure);
                jump = true;
            }
            if(jump){
                Vilo.append(move+". "+movePossibility.get(randomIndex).getPositionFrom().getColumn()+""+movePossibility.get(randomIndex).getPositionFrom().getRow()+"x"+movePossibility.get(randomIndex).getPositionTo().getColumn()+""+movePossibility.get(randomIndex).getPositionTo().getRow()+" ");
            } else {
                Vilo.append(move+". "+movePossibility.get(randomIndex).getPositionFrom().getColumn()+""+movePossibility.get(randomIndex).getPositionFrom().getRow()+"-"+movePossibility.get(randomIndex).getPositionTo().getColumn()+""+movePossibility.get(randomIndex).getPositionTo().getRow()+" ");
            }
            record.add(new Report(move, movePossibility.get(randomIndex).getPositionFrom(), movePossibility.get(randomIndex).getPositionTo(), jump, 1));
            
            jump = false;
            logic.changeFigures();
            //logic.setGameState(GameState.PLAYER2);
            //movePossibility.clear();
            return;
        }
    }
}
