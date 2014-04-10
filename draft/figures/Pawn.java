/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.figures;

import draft.basis.Position;
import draft.basis.Figure;
import java.util.*;

/**
 * Class Pawn implements all actions about the pawn figure
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class Pawn extends Figure {

    private List<Position> pawnJump = new ArrayList<Position>();
    private List<Position> pawnMove = new ArrayList<Position>();

    /**
     * Constructor of the new pawn
     * @param p position of the new pawn
     * @param color color of the new pawn
     */
    public Pawn(Position p, int color) {
        super(p, color);
    }

    /**
     * adds moves into the moving list of positions
     */
    private void canPawnMove() {
        if (this.getColor() == 0) { // black pawn
            // if you can move
            if (this.getPosition().nextPosition(1, -1) != null) { // down to right
                if (canMove(this.getPosition().nextPosition(1, -1)) == true) {
                    pawnMove.add(this.getPosition().nextPosition(1, -1));
                }
            }
            if (this.getPosition().nextPosition(-1, -1) != null) { // down to left
                if (canMove(this.getPosition().nextPosition(-1, -1)) == true) {
                    pawnMove.add(this.getPosition().nextPosition(-1, -1));
                }
            }
            // if you can jump
            if (this.getPosition().nextPosition(2, -2) != null) { // 2 x down to 2 x right
                if (canMove(this.getPosition().nextPosition(2, -2)) == true) {
                    pawnJump.add(this.getPosition().nextPosition(2, -2));
                }
            }
            if (this.getPosition().nextPosition(-2, -2) != null) { // 2 x down to 2 x left
                if (canMove(this.getPosition().nextPosition(-2, -2)) == true) {
                    pawnJump.add(this.getPosition().nextPosition(-2, -2));
                }
            }
        } else { // white pawn
            // if you can move
            if (this.getPosition().nextPosition(1, 1) != null) { // up to right
                if (canMove(this.getPosition().nextPosition(1, 1)) == true) {
                    pawnMove.add(this.getPosition().nextPosition(1, 1));
                }
            }
            if (this.getPosition().nextPosition(-1, 1) != null) { // up to left
                if (canMove(this.getPosition().nextPosition(-1, 1)) == true) {
                    pawnMove.add(this.getPosition().nextPosition(-1, 1));
                }
            }
            // if you can jump
            if (this.getPosition().nextPosition(2, 2) != null) { // 2 x up to 2 x right
                if (canMove(this.getPosition().nextPosition(2, 2)) == true) {
                    pawnJump.add(this.getPosition().nextPosition(2, 2));
                }
            }
            if (this.getPosition().nextPosition(-2, 2) != null) { // 2 x up to 2 x left
                if (canMove(this.getPosition().nextPosition(-2, 2)) == true) {
                    pawnJump.add(this.getPosition().nextPosition(-2, 2));
                }
            }
        }
    }

    /**
     * 
     * @return list of positions where the pawn can jump to
     */
    @Override
    public List<Position> getJump() {
        pawnMove.clear();
        pawnJump.clear();
        canPawnMove();
        return pawnJump;
    }

    /**
     * 
     * @return list of positions where the pawn can move to
     */
    @Override
    public List<Position> getMove() {
        pawnMove.clear();
        pawnJump.clear();
        canPawnMove();
        return pawnMove;
    }

    /**
     * 
     * @param p new position of the pwan
     * @return true if pawn can move from this to p, false otherwise
     */
    @Override
    public boolean canMove(Position p) {

        // is new position valid ??
        if (this.getPosition().getDesk().getPositionAt(p.getColumn(), p.getRow()) == null) {
            return false;
        }

        if (getColor() == 0) { // Black pawn

            // test direction only down move
            if ((this.getPosition().getRow() - p.getRow()) <= 0) {
                return false;
            }

            // p.getColumn() - this.getPosition().getColumn()  get number extepted 1,-1,2,-2
            switch ((int) (p.getColumn() - this.getPosition().getColumn())) {
                case 1: // down to right
                    if (p.getFigure() == null && (this.getPosition().getRow() - p.getRow()) == 1) { // empty tile
                        // if second tile heve figure
                        if (this.getPosition().getDesk().getPositionAt((char) (this.getPosition().getColumn() - 1), this.getPosition().getRow() - 1) != null
                                && this.getPosition().getDesk().getFigureAt((char) (this.getPosition().getColumn() - 1), this.getPosition().getRow() - 1) != null) {

                            // test color of second figure (red is good)
                            if (this.getPosition().nextPosition(-1, -1).getFigure().getColor() == 0) {
                                return true;
                            }

                            // if next position from second tile have figure and if next position is in desk
                            if (this.getPosition().getDesk().getPositionAt((char) (this.getPosition().getColumn() - 2), this.getPosition().getRow() - 2) != null
                                    && this.getPosition().getDesk().getFigureAt((char) (this.getPosition().getColumn() - 2), this.getPosition().getRow() - 2) != null) {
                                return true;
                            } // position out of desk
                            else if (this.getPosition().getDesk().getPositionAt((char) (this.getPosition().getColumn() - 2), this.getPosition().getRow() - 2) == null) {
                                return true;
                            } else { // can jump figure 
                                return false;
                            }

                        } else { // second tile is empty
                            return true;
                        }
                    } else { // tile have figure
                        return false;
                    }

                case -1: // down to left
                    if (p.getFigure() == null && (this.getPosition().getRow() - p.getRow()) == 1) { // empty tile
                        // if second tile heve figure
                        if (this.getPosition().getDesk().getPositionAt((char) (this.getPosition().getColumn() + 1), this.getPosition().getRow() - 1) != null
                                && this.getPosition().getDesk().getFigureAt((char) (this.getPosition().getColumn() + 1), this.getPosition().getRow() - 1) != null) {

                            // test color of second figure (red is good)
                            if (this.getPosition().nextPosition(1, -1).getFigure().getColor() == 0) {
                                return true;
                            }

                            // if next position from second tile have figure and if next position is in desk
                            if (this.getPosition().getDesk().getPositionAt((char) (this.getPosition().getColumn() + 2), this.getPosition().getRow() - 2) != null
                                    && this.getPosition().getDesk().getFigureAt((char) (this.getPosition().getColumn() + 2), this.getPosition().getRow() - 2) != null) {
                                return true;
                            } // position out of desk
                            else if (this.getPosition().getDesk().getPositionAt((char) (this.getPosition().getColumn() + 2), this.getPosition().getRow() - 2) == null) {
                                return true;
                            } else { // can jump figure 
                                return false;
                            }

                        } else { // second tile is empty
                            return true;
                        }
                    } else { // tile have figure
                        return false;
                    }

                case 2: // 2 x down 2 x right
                    if (p.getFigure() == null && (this.getPosition().getRow() - p.getRow()) == 2) { // empty tile
                        // test if tile have figure on previous position
                        if (this.getPosition().nextPosition(1, -1).getFigure() != null) {
                            // test color of figure
                            if (this.getPosition().nextPosition(1, -1).getFigure().getColor() == 0) { // Black figure
                                return false;
                            }
                            return true;
                        } else { // empty tile
                            return false;
                        }
                    } else {
                        return false;
                    }
                case -2: // 2 x down 2 x left
                    if (p.getFigure() == null && (this.getPosition().getRow() - p.getRow()) == 2) { // empty tile
                        // test if tile have figure on previous position
                        if (this.getPosition().nextPosition(-1, -1).getFigure() != null) {
                            // test color of figure
                            if (this.getPosition().nextPosition(-1, -1).getFigure().getColor() == 0) { // Black figure
                                return false;
                            }
                            return true;
                        } else { // empty tile
                            return false;
                        }
                    } else {
                        return false;
                    }
            }
        } else { // White pawn
            if ((this.getPosition().getRow() - p.getRow()) >= 0) {
                return false;
            }

            switch ((int) (p.getColumn() - this.getPosition().getColumn())) {
                case 1: // up to right
                    if (p.getFigure() == null && (p.getRow() - this.getPosition().getRow()) == 1) { // empty tile
                        // if second tile heve figure
                        if (this.getPosition().getDesk().getPositionAt((char) (this.getPosition().getColumn() - 1), this.getPosition().getRow() + 1) != null
                                && this.getPosition().getDesk().getFigureAt((char) (this.getPosition().getColumn() - 1), this.getPosition().getRow() + 1) != null) {

                            // test color of second figure (white is good)
                            if (this.getPosition().nextPosition(-1, 1).getFigure().getColor() == 1) {
                                return true;
                            }

                            // if next position from second tile have figure and if next position is in desk
                            if (this.getPosition().getDesk().getPositionAt((char) (this.getPosition().getColumn() - 2), this.getPosition().getRow() + 2) != null
                                    && this.getPosition().getDesk().getFigureAt((char) (this.getPosition().getColumn() - 2), this.getPosition().getRow() + 2) != null) {
                                return true;
                            } // position out of desk
                            else if (this.getPosition().getDesk().getPositionAt((char) (this.getPosition().getColumn() - 2), this.getPosition().getRow() + 2) == null) {
                                return true;
                            } else { // can jump figure 
                                return false;
                            }

                        } else { // second tile is empty
                            return true;
                        }
                    } else { // tile have figure
                        return false;
                    }

                case -1: // up to left
                    if (p.getFigure() == null && (p.getRow() - this.getPosition().getRow()) == 1) { // empty tile
                        // if second tile heve figure
                        if (this.getPosition().getDesk().getPositionAt((char) (this.getPosition().getColumn() + 1), this.getPosition().getRow() + 1) != null
                                && this.getPosition().getDesk().getFigureAt((char) (this.getPosition().getColumn() + 1), this.getPosition().getRow() + 1) != null) {

                            // test color of second figure (white is good)
                            if (this.getPosition().nextPosition(1, 1).getFigure().getColor() == 1) {
                                return true;
                            }

                            // if next position from second tile have figure and if next position is in desk
                            if (this.getPosition().getDesk().getPositionAt((char) (this.getPosition().getColumn() + 2), this.getPosition().getRow() + 2) != null
                                    && this.getPosition().getDesk().getFigureAt((char) (this.getPosition().getColumn() + 2), this.getPosition().getRow() + 2) != null) {
                                return true;
                            } // position out of desk
                            else if (this.getPosition().getDesk().getPositionAt((char) (this.getPosition().getColumn() + 2), this.getPosition().getRow() + 2) == null) {
                                return true;
                            } else { // can jump figure 
                                return false;
                            }

                        } else { // second tile is empty
                            return true;
                        }
                    } else { // tile have figure
                        return false;
                    }

                case 2: // 2 x up to 2 x right
                    if (p.getFigure() == null && (p.getRow() - this.getPosition().getRow()) == 2) { // empty tile
                        // test if tile have figure on previous position
                        if (this.getPosition().nextPosition(1, 1).getFigure() != null) {
                            // test color of figure
                            if (this.getPosition().nextPosition(1, 1).getFigure().getColor() == 1) { // White figure
                                return false;
                            }
                            return true;
                        } else { // empty tile
                            return false;
                        }
                    } else {
                        return false;
                    }

                case -2: // 2 x up to 2 x left
                    if (p.getFigure() == null && (p.getRow() - this.getPosition().getRow()) == 2) { // empty tile
                        // test if tile have figure on previous position
                        if (this.getPosition().nextPosition(-1, 1).getFigure() != null) {
                            // test color of figure
                            if (this.getPosition().nextPosition(-1, 1).getFigure().getColor() == 1) { // White figure
                                return false;
                            }
                            return true;
                        } else { // empty tile
                            return false;
                        }
                    } else {
                        return false;
                    }
            }
        }
        return false;
    }

    /**
     * throwns out the figures which are in the way from this to p
     * @param p new position of the pawn
     * @return figure if there was some figure thrown out during the move from 
     * this to p, null otherwise
     */
    @Override
    public Figure killFigure(Position p) {

        if (this.getColor() == 0) { // black
            if ((int) (p.getColumn() - this.getPosition().getColumn()) > 0) {
                return this.getPosition().nextPosition(1, -1).removeFigure();
            } else {
                return this.getPosition().nextPosition(-1, -1).removeFigure();
            }
        } else { // white
            if ((int) (p.getColumn() - this.getPosition().getColumn()) > 0) {
                return this.getPosition().nextPosition(1, 1).removeFigure();
            } else {
                return this.getPosition().nextPosition(-1, 1).removeFigure();
            }
        }
    }
}
