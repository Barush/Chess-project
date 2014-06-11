/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brutalchess.ui;

import static brutalchess.Const.*;
import brutalchess.basis.Position;
import java.awt.GridLayout;
import javax.swing.JLabel;

/**
 *
 * @author Canes
 */
public class GamePanel extends javax.swing.JPanel {

	/**
	 * Creates new form Game
	 * @param desk
	 * @param dim
	 */
	public GamePanel(Position[][] desk, int dim) {
		initComponents();
		
		this.Desk.setLayout( new GridLayout(dim + 2, dim + 2) );
		
		// add empty label and numbers 1 to dim
		this.Desk.add( new JLabel() );
                char j = 'a';
		for (int i = 1; i <= dim; i++){
			this.Desk.add( new JLabel( String.valueOf(j++), JLabel.CENTER ) );
		}
		
		int k = 1;
		for (Position[] row: desk) {
			// add letter to every row
			this.Desk.add( new JLabel( String.valueOf(k++), JLabel.CENTER ) );
			
			for (Position pos: row) {
				this.Desk.add(pos.getTile());
			}
		}
		
		setVisible(true);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desk = new javax.swing.JPanel();

        Desk.setBackground(new java.awt.Color(227, 224, 224));
        Desk.setBorder(null);

        javax.swing.GroupLayout DeskLayout = new javax.swing.GroupLayout(Desk);
        Desk.setLayout(DeskLayout);
        DeskLayout.setHorizontalGroup(
            DeskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        DeskLayout.setVerticalGroup(
            DeskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Desk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Desk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Desk.getAccessibleContext().setAccessibleName("");
        Desk.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Desk;
    // End of variables declaration//GEN-END:variables
}
