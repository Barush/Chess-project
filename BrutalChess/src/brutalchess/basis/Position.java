/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalchess.basis;

import brutalchess.ui.Tile;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 *
 * @author Barush
 */
public class Position {

	private int row;
	private char col;
	private Figure fig;
	private final Tile tile;
	private final Desk desk;

	public Position(Desk d, char c, int r, int color) {
		this.desk = d;
		this.col = c;
		this.row = r;
		this.tile = new Tile(color, this);
	}

	public void setRow(int r) {
		this.row = r;
	}

	public int getRow() {
		return this.row;
	}

	public void setCol(char c) {
		this.col = c;
	}

	public char getCol() {
		return this.col;
	}

	public void setFigure(Figure fig, String state) {
		this.fig = fig;
		if (fig != null) {
			this.fig.paintFigure(state);
		} else {
			this.tile.removeAll();
			this.tile.revalidate();
			this.tile.repaint();
		}
	}

	public Figure getFigure() {
		return this.fig;
	}

	public Tile getTile() {
		return this.tile;
	}

	public Desk getDesk() {
		return this.desk;
	}

	/**
	 * Called on Position, where user wants to move his active Figure
	 */
	public void moveActiveFigure() {
		Figure activeFig = this.getDesk().getActive().getFigure();
		Figure kickedFig = this.getFigure();
		Position from = this.getDesk().getActive();
		//figure moving
		if (activeFig.canMove(this)) {
			activeFig.markCanMovePositions(false);
            //System.out.println("Can move from "+activeFig.getPosition().getCol()+activeFig.getPosition().getRow()+" to "+this.getCol()+this.getRow());
			//unmark the tile
			activeFig.getPosition().getTile().removeAll();
			from.getTile().repaintColor();
			//set position of figure
			activeFig.setPosition(this);
			//set figure to position
			this.setFigure(activeFig, "inactive");
			//deactivate figure
			activeFig.Activate(false);
			//delete figure from previous position
			this.getDesk().getActive().setFigure(null, "");
			this.getDesk().setActive(null);
			desk.getGame().moveMade(from, this);
			if (kickedFig != null) kickedFig.deathCallback();
		}
	}
}
