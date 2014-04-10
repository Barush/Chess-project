/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.logic;

import draft.swingui.*;
import draft.basis.*;
import java.util.*;
import draft.figures.*;

/**
 * Class Logic - logic part of the game
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class Logic {

    private GameState gameState;
    private List<Figure> whiteFigures;
    private List<Figure> blackFigures;
    private List<Position> jump;
    private List<Position> move;
    private List<Turn> movePossibility = new ArrayList<Turn>();
    private List<Position> magic;

    public Logic(List<Figure> whiteFigures, List<Figure> blackFigures) {
        this.gameState = GameState.PLAYER1;
        this.whiteFigures = whiteFigures;
        this.blackFigures = blackFigures;
    }

    /**
     * @return the gameSate
     */
    public GameState getGameSate() {
        return gameState;
    }

    /**
     * @param gameState = the gameState to set
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * @return the whiteFigures
     */
    public List<Figure> getWhiteFigures() {
        return whiteFigures;
    }

    /**
     * @return the blackFigures
     */
    public List<Figure> getBlackFigures() {
        return blackFigures;
    }

    /**
     * 
     * @param color color of the draft which is going to jump
     * @return list of possible turns with jumping
     */
    private List<Turn> getDraftsJump(int color) {
        movePossibility.clear();
        if (color == 0) { // blsck figure
            for (Figure figure : this.blackFigures) {
                if (figure instanceof Draft) {
                    jump = figure.getJump();
                    if (jump.isEmpty()) {
                        continue;//return movePossibility;
                    }
                    for (Position position : jump) {
                        movePossibility.add(new Turn(figure.getPosition(), position));
                    }
                }
            }
            return movePossibility;
        }

        // white figure
        for (Figure figure : this.whiteFigures) {
            if (figure instanceof Draft) {
                jump = figure.getJump();
                System.out.println(jump);
                if (jump.isEmpty()) {
                    continue;//return movePossibility;
                }
                for (Position position : jump) {
                    movePossibility.add(new Turn(figure.getPosition(), position));
                    System.out.println(position.getColumn() + " " + position.getRow());
                }
            }
        }
        return movePossibility;
    }

    /**
     * 
     * @param color color of the pawn which is going to jump
     * @return list of possible turns with jumping
     */
    private List<Turn> getPawnsJump(int color) {
        movePossibility.clear();
        if (color == 0) { // black figure
            for (Figure figure : this.blackFigures) {
                if (figure instanceof Pawn) {
                    jump = figure.getJump();
                    if (jump.isEmpty()) {
                        continue;//return movePossibility;
                    }
                    for (Position position : jump) {
                        movePossibility.add(new Turn(figure.getPosition(), position));
                    }
                }
            }
            return movePossibility;
        }

        // white figure
        for (Figure figure : this.whiteFigures) {
            if (figure instanceof Pawn) {
                jump = figure.getJump();
                if (jump.isEmpty()) {
                    continue;
                }
                for (Position position : jump) {
                    movePossibility.add(new Turn(figure.getPosition(), position));
                }
            }
        }
        return movePossibility;
    }

    /**
     * 
     * @param color color of the figure to analyze
     * @return list of possible moves of the figure
     */
    private List<Turn> getFiguresMove(int color) {
        movePossibility.clear();
        if (color == 0) { // black figure
            for (Figure figure : this.blackFigures) {
                move = figure.getMove();
                if (move.isEmpty()) {
                    continue;
                }
                for (Position position : move) {
                    movePossibility.add(new Turn(figure.getPosition(), position));
                }
            }
            return movePossibility;
        }

        for (Figure figure : this.whiteFigures) {
            move = figure.getMove();
            if (move.isEmpty()) {
                continue;
            }

            for (Position position : move) {
                movePossibility.add(new Turn(figure.getPosition(), position));
            }
        }
        return movePossibility;
    }

    /**
     * Parent method of all get jump and get move methods
     * 
     * @param color color of the figure to analyze
     * @return list of all possible turns - works with priority of jumps and
     * draft jumping priority
     */
    public List<Turn> getMovePossibility(int color) {
        movePossibility.clear();
        if (getDraftsJump(color).isEmpty() != true) {
            return getDraftsJump(color);
        } else if (getPawnsJump(color).isEmpty() != true) {
            return getPawnsJump(color);
        } else {
            return getFiguresMove(color);
        }
    }

    /**
     * method which implements changing the position of the figure
     */
    public void changeFigures() {
        for (Figure figure : blackFigures) {
            for (int i = 0; i < 8; i++) {
                if (figure.getPosition().getRow() == 1 && (int) (figure.getPosition().getColumn()) == (int) ('a' + i) && figure instanceof Pawn) {
                    System.out.println("zmena cierneho");
                    Position p = figure.getPosition();
                    blackFigures.remove(figure);
                    figure.getPosition().removeFigure();
                    blackFigures.add(new Draft(p, 0));
                    return;
                }
            }
        }
        for (Figure figure : whiteFigures) {
            for (int i = 0; i < 8; i++) {
                if (figure.getPosition().getRow() == 8 && (int) (figure.getPosition().getColumn()) == (int) ('a' + i) && figure instanceof Pawn) {
                    Position p = figure.getPosition();
                    whiteFigures.remove(figure);
                    figure.getPosition().removeFigure();
                    whiteFigures.add(new Draft(p, 1));
                    return;
                }
            }
        }
    }

    /**
     * removes figure from the desk
     * 
     * @param thrown figure to be removed
     */
    public void removeFigureFromDesk(Figure thrown) {
        if (whiteFigures.contains(thrown)) {
            whiteFigures.remove(thrown);
        }
        if (blackFigures.contains(thrown)) {
            blackFigures.remove(thrown);
        }
    }

    /**
     * method which checks if win or loose is necessary to set
     * 
     * @return new gamestate to set after checking figure counts on each side
     */
    public GameState statusGame() {
        if (whiteFigures.isEmpty()) {
            return GameState.WINBLACK;
        } else if (blackFigures.isEmpty()) {
            return GameState.WINWHITE;
        } else if (whiteFigures.size() == 1 && blackFigures.size() == 1) {
            return GameState.DRAW;
        }
        return GameState.NOTHING;

    }
}
