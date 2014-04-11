/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.homework3.basis;

import ija.homework3.figures.*;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author DumbEar
 */
public class Position {

    /**
     *
     */
    protected int row;
    protected char col;  
    protected Desk desk;
    protected Figure figure_on_pos = null;
    public JPanel square;

    public Position(Desk d, char c, int r) {
        this.desk = d;
        this.col = c;
        this.row = r;
        this.square = new JPanel(new BorderLayout());
    }

    public boolean equals(Position p) {
        if ((p.col == this.col) && (p.row == this.row)) {
            return true;
        } else {
            return false;
        }
    }

    public Position nextPosition(int dC, int dR) {
        return (this.desk.getPositionAt((char) (this.col + dC), this.row + dR));
    }

    public Figure getFigure() {
        return (this.figure_on_pos);
    }

    public Figure putFigure(Figure f) {
        Figure temp = this.getFigure();
        // pokud existuje figurka, smazu ji z jeji puvodni pozice
        if (f.piece != null) {
            JPanel tempSquare = (JPanel) f.piece.getParent();
            tempSquare.remove(f.piece);
            tempSquare.repaint();
        }
        // a vytvorim novou figurku
        f.piece = new JLabel(new ImageIcon("lib/" + f.type + f.colour + ".png"));
        // kterou vlozim na nove misto
        this.square.add(f.piece);
        this.figure_on_pos = f;
        return temp;
    }

    public Figure removeFigure() {
        Figure temp;
        Position temp2;
        temp2 = this.desk.getPositionAt(this.col, this.row);

        if (temp2.figure_on_pos == null) {
            return null;
        } else {
            temp = temp2.figure_on_pos;
            if (temp.piece != null && temp2.square != null) {
                temp2.square.remove(temp.piece);
            }
            temp2.figure_on_pos = null;
            return temp;
        }
    }

    public boolean sameRow(Position p) {
        return (this.row == p.row);
    }

    public boolean sameColumn(Position p) {
        return (this.col == p.col);
    }

    public boolean sameDiagonal(Position p) {
        if (p.col < 'a' || p.col > ('a' + 7) || p.row < 1 || p.row > 8) {
            return false;
        }
        int temprow = this.row - p.row;
        int tempcol = this.col - p.col;
        if (Math.abs(temprow) == Math.abs(tempcol)) {
            return true;
        }
        return false;
    }

    public int returnRow() {
        return this.row;
    }

    public char returnCol() {
        return this.col;
    }

    public boolean transform() {
        Figure temp = this.getFigure();
        if (temp.getColour() == desk.WHITE) //white figure
        {
            if (this.returnRow() == 1) //make it queen
            {
                this.desk.removefromFigurelist(desk.WHITE, this.figure_on_pos);
                //this.removeFigure();
                this.putFigure(new Queen(this, desk.WHITE));
                this.desk.addtoFigurelist(desk.WHITE, this.getFigure());

                this.getFigure().addMoves();
                return true;
            }
        } else {
            if (this.returnRow() == 8) {
                this.desk.removefromFigurelist(desk.BLACK, this.figure_on_pos);
                //this.removeFigure();
                this.putFigure(new Queen(this, desk.BLACK));
                this.desk.addtoFigurelist(desk.BLACK, this.getFigure());

                this.getFigure().addMoves();
                return true;
            }
        }
        return false;
    }
}
