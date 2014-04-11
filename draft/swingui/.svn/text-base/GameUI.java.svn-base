/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.swingui;

import javax.swing.*;
import draft.basis.*;
import draft.figures.*;
import draft.gamemode.*;
import draft.logic.GameState;
import draft.logic.Logic;
import draft.logic.Report;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Class which implements GUI of the game - swing generated
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class GameUI extends JFrame implements MouseListener {

    private TileComponent[][] desk;
    //private boolean click = true;
    //private TileComponent tile1;
    private List<Figure> white = new ArrayList<Figure>();
    private List<Figure> black = new ArrayList<Figure>();
    //private Logic state = new Logic();

    public enum TipeFigure {

        DRAFT_WHITE,
        DRAFT_BLACK,
        PAWN_WHITE,
        PAWN_BLACK
    };

    /**
     * Creates new form GameGUI
     */
    public GameUI() {
        initComponents();
        DeskPanel.setLayout(new GridLayout(8, 8));
        createDesk();
    }

    public Queue<Report> getRecord() {
        return null;
    }

    public List<Figure> getWhiteFigures() {
        return this.white;
    }

    public List<Figure> getBlackFigures() {
        return this.black;
    }

    private void createDesk() {
        this.desk = new TileComponent[8][8];
        Desk d = new Desk(8);
        for (int i = 8; i != 0; i--) {
            for (int j = 0; j < 8; j++) {
                if ((i % 2) == 0) {
                    if ((j % 2) == 0) {
                        this.desk[(i - 1)][j] = new TileComponent(d.getDeskPositions()[i - 1][j], Color.WHITE);
                        this.desk[i - 1][j].addMouseListener(this);
                        DeskPanel.add(desk[i - 1][j]);
                    } else {
                        this.desk[i - 1][j] = new TileComponent(d.getDeskPositions()[i - 1][j], Color.BLACK);
                        this.desk[i - 1][j].addMouseListener(this);
                        DeskPanel.add(desk[i - 1][j]);
                    }
                } else {
                    if ((j % 2) == 0) {
                        this.desk[i - 1][j] = new TileComponent(d.getDeskPositions()[i - 1][j], Color.BLACK);
                        this.desk[i - 1][j].addMouseListener(this);
                        DeskPanel.add(desk[i - 1][j]);
                    } else {
                        this.desk[i - 1][j] = new TileComponent(d.getDeskPositions()[i - 1][j], Color.WHITE);
                        this.desk[i - 1][j].addMouseListener(this);
                        DeskPanel.add(desk[i - 1][j]);
                    }
                }
            }
        }
    }

    public void updateDesk() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.desk[i][j].updateTile();
            }
        }
    }

    public TileComponent[][] getTileDesk() {
        return this.desk;
    }

    public TileComponent getTile(int row, char column) {
        return this.desk[row - 1][(int) (column - 'a')];
    }

    public TileComponent[][] initDesk() {
        // put white and black figures to desk
        for (int i = 0; i < 3; i++) { // cycle for rows
            for (int j = 0; j < 8; j++) { // cycle for column
                if ((i % 2) == 0) { // firs and third rows, seventh row
                    if ((j % 2) == 0) {
                        white.add(new Pawn(this.desk[i][j].getTilePosition(), 1)); // white pawn
                        if (i == 2) // seventh row
                        {
                            black.add(new Pawn(this.desk[i + 4][j].getTilePosition(), 0)); // black pawn
                        }
                    }
                } else {  // second row, eighth and sixth rows
                    if ((j % 2) != 0) {
                        white.add(new Pawn(this.desk[i][j].getTilePosition(), 1));     // white Pawn
                        black.add(new Pawn(this.desk[i + 4][j].getTilePosition(), 0));   // black pawn
                        black.add(new Pawn(this.desk[i + 6][j].getTilePosition(), 0));   // black pawn
                    }
                }
            }
        }
        return desk;
    }

    public void loadDesk(Queue<TurnInNotation> load, Logic state, Queue<Report> record) {
        boolean color = true;
        if (load.isEmpty() == false) {
            for (TurnInNotation note : load) {
                System.out.println(note.getRfrom() + " " + note.getCfrom() + " " + note.getRto() + " " + note.getCto());
                Figure figure = getTile(note.getRfrom(), note.getCfrom()).getTilePosition().getFigure().move(getTile(note.getRto(), note.getCto()).getTilePosition());

                if (figure != null) {
                    state.removeFigureFromDesk(figure);
                }

                if (color) {
                    record.add(new Report(note.getTurn(), getTile(note.getRfrom(), note.getCfrom()).getTilePosition(), getTile(note.getRto(), note.getCto()).getTilePosition(), note.isJump(), 1));
                    state.changeFigures();
                    state.setGameState(GameState.PLAYER2);
                    color = false;
                } else {
                    record.add(new Report(note.getTurn(), getTile(note.getRfrom(), note.getCfrom()).getTilePosition(), getTile(note.getRto(), note.getCto()).getTilePosition(), note.isJump(), 0));
                    state.changeFigures();
                    state.setGameState(GameState.PLAYER1);
                    color = true;
                }
            }
        }
    }

    private void removeWhite() {
        if (white.isEmpty()) {
            return;
        }
        for (Figure figure : white) {
            figure.getPosition().removeFigure();
        }
    }

    private void removeBlack() {
        if (black.isEmpty()) {
            return;
        }
        for (Figure figure : black) {
            figure.getPosition().removeFigure();
        }
    }

    public void removeAllFigures() {
        removeWhite();
        removeBlack();
    }

    public int getTime() {
        int t = Integer.parseInt(time.getValue().toString());
        return t;
    }

    public int getTurn() {
        int t =  Integer.parseInt(turn.getValue().toString());
        return t;
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public javax.swing.JTextArea getVilorea() {
        return Vilorea;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        InfoPanelClomns = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Vilorea = new javax.swing.JTextArea();
        Play = new javax.swing.JButton();
        Stop = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        Prev = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        time = new javax.swing.JSpinner();
        turn = new javax.swing.JSpinner();
        InfoPanelRows = new javax.swing.JPanel();
        DeskPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        GameMenu = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        SaveGame = new javax.swing.JMenuItem();
        SaveNotation = new javax.swing.JMenuItem();
        QuitGame = new javax.swing.JMenuItem();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(646, 521));
        setMinimumSize(new java.awt.Dimension(646, 521));
        setPreferredSize(new java.awt.Dimension(646, 521));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(89, 73, 43));
        jPanel1.setMinimumSize(new java.awt.Dimension(30, 30));
        jPanel1.setPreferredSize(new java.awt.Dimension(30, 30));
        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        InfoPanelClomns.setBackground(new java.awt.Color(89, 73, 43));
        InfoPanelClomns.setMinimumSize(new java.awt.Dimension(30, 30));
        InfoPanelClomns.setPreferredSize(new java.awt.Dimension(30, 30));
        getContentPane().add(InfoPanelClomns, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(89, 73, 43));
        jPanel3.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(89, 73, 43));
        jPanel2.setMinimumSize(new java.awt.Dimension(30, 30));
        jPanel2.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel4.setBackground(new java.awt.Color(89, 73, 43));
        jPanel4.setMinimumSize(new java.awt.Dimension(186, 186));
        jPanel4.setPreferredSize(new java.awt.Dimension(186, 186));
        jPanel4.setLayout(null);

        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setDoubleBuffered(true);
        jScrollPane2.setFocusCycleRoot(true);
        jScrollPane2.setFocusTraversalPolicyProvider(true);
        jScrollPane2.setInheritsPopupMenu(true);

        Vilorea.setBackground(new java.awt.Color(153, 0, 0));
        Vilorea.setColumns(20);
        Vilorea.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Vilorea.setForeground(new java.awt.Color(255, 255, 255));
        Vilorea.setLineWrap(true);
        Vilorea.setRows(500);
        Vilorea.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED))));
        Vilorea.setEnabled(false);
        Vilorea.setFocusCycleRoot(true);
        Vilorea.setFocusTraversalPolicyProvider(true);
        Vilorea.setInheritsPopupMenu(true);
        Vilorea.setMinimumSize(new java.awt.Dimension(100, 100));
        jScrollPane2.setViewportView(Vilorea);

        jPanel4.add(jScrollPane2);
        jScrollPane2.setBounds(0, 170, 170, 240);

        Play.setBackground(new java.awt.Color(153, 0, 0));
        Play.setForeground(new java.awt.Color(255, 255, 255));
        Play.setText("Play");
        Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayActionPerformed(evt);
            }
        });
        jPanel4.add(Play);
        Play.setBounds(10, 0, 90, 23);

        Stop.setBackground(new java.awt.Color(153, 0, 0));
        Stop.setForeground(new java.awt.Color(255, 255, 255));
        Stop.setText("Stop");
        Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopActionPerformed(evt);
            }
        });
        jPanel4.add(Stop);
        Stop.setBounds(10, 30, 90, 23);

        Next.setBackground(new java.awt.Color(153, 0, 0));
        Next.setForeground(new java.awt.Color(255, 255, 255));
        Next.setText("Next");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });
        jPanel4.add(Next);
        Next.setBounds(10, 60, 90, 23);

        Prev.setBackground(new java.awt.Color(153, 0, 0));
        Prev.setForeground(new java.awt.Color(255, 255, 255));
        Prev.setText("Previous");
        Prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrevActionPerformed(evt);
            }
        });
        jPanel4.add(Prev);
        Prev.setBounds(10, 90, 90, 23);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Turn:");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(10, 140, 70, 14);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Time:");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(10, 120, 60, 14);
        jPanel4.add(time);
        time.setBounds(60, 120, 50, 20);
        jPanel4.add(turn);
        turn.setBounds(60, 140, 50, 20);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.LINE_END);

        InfoPanelRows.setBackground(new java.awt.Color(89, 73, 43));
        InfoPanelRows.setMinimumSize(new java.awt.Dimension(30, 30));
        InfoPanelRows.setPreferredSize(new java.awt.Dimension(30, 30));
        getContentPane().add(InfoPanelRows, java.awt.BorderLayout.LINE_START);

        DeskPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        DeskPanel.setMinimumSize(new java.awt.Dimension(412, 412));
        DeskPanel.setPreferredSize(new java.awt.Dimension(412, 412));

        javax.swing.GroupLayout DeskPanelLayout = new javax.swing.GroupLayout(DeskPanel);
        DeskPanel.setLayout(DeskPanelLayout);
        DeskPanelLayout.setHorizontalGroup(
            DeskPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );
        DeskPanelLayout.setVerticalGroup(
            DeskPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );

        getContentPane().add(DeskPanel, java.awt.BorderLayout.CENTER);

        GameMenu.setText("Game Menu");

        jMenu1.setText("Save As...");

        SaveGame.setText("Save Game");
        SaveGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveGameMouseClicked(evt);
            }
        });
        SaveGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveGameActionPerformed(evt);
            }
        });
        jMenu1.add(SaveGame);

        SaveNotation.setText("Save Game In Notation");
        SaveNotation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveNotationActionPerformed(evt);
            }
        });
        jMenu1.add(SaveNotation);

        GameMenu.add(jMenu1);

        QuitGame.setText("Quit Game");
        QuitGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitGameActionPerformed(evt);
            }
        });
        GameMenu.add(QuitGame);

        menuBar.add(GameMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    protected void QuitGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitGameActionPerformed
        this.dispose();
    }//GEN-LAST:event_QuitGameActionPerformed

    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
        String about = "Hra Dama.\n\nAutori: \nBarbora Skrivankova, xskriv01@stud.fit"
                + ".vutbr.cz\nUzasny Vilo (tos uhodl), xserec00@stud.fit.vutbr.cz.\n\n"
                + "Vytvoreno jako projekt do predmetu IJA.\n duben 2013";
        JOptionPane.showMessageDialog(this, about, "About game", 0x1);
    }//GEN-LAST:event_AboutActionPerformed

    private void HowToPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HowToPlayActionPerformed
        String howto = "Hra Dama.\nLze hrat ve trech variantach - single player,"
                + "multiplayer na jednom pocitaci a multiplayer po siti.\n\n"
                + "Pri hre jednoho hrace hraje hrac proti umele inteligenci na "
                + "pocitaci. Tuto hru hrac vytvori kliknutim na New Game Player vs."
                + "Computer.\n\n"
                + "P�i h�e v�ce hr��� na jednom po��ta�i....n�pov�da jak se to d�l�.\n\n"
                + "P�i h�e v�ce hr��� poi s�ti....n�pov�da jak to nakonfigurovat.";
        JOptionPane.showMessageDialog(this, howto, "How to play", 0x1);
    }//GEN-LAST:event_HowToPlayActionPerformed

    public void SaveNotationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveNotationActionPerformed
        // TODO add your handling code here:
        //SaveGameInNotation game = new SaveGameInNotation();
    }//GEN-LAST:event_SaveNotationActionPerformed

    public void SaveGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveGameActionPerformed
        // TODO add your handling code here:
        //SaveGame save = new SaveGame(this);
    }//GEN-LAST:event_SaveGameActionPerformed

    private void SaveGameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveGameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SaveGameMouseClicked

    public void PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayActionPerformed
        // TODO add your handling code here:
        //
    }//GEN-LAST:event_PlayActionPerformed

    public void StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopActionPerformed
        // TODO add your handling code here:
        //
    }//GEN-LAST:event_StopActionPerformed

    public void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        // TODO add your handling code here:
        //
    }//GEN-LAST:event_NextActionPerformed

    public void PrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrevActionPerformed
        // TODO add your handling code here:
        //
    }//GEN-LAST:event_PrevActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DeskPanel;
    private javax.swing.JMenu GameMenu;
    private javax.swing.JPanel InfoPanelClomns;
    private javax.swing.JPanel InfoPanelRows;
    private javax.swing.JButton Next;
    private javax.swing.JButton Play;
    private javax.swing.JButton Prev;
    private javax.swing.JMenuItem QuitGame;
    private javax.swing.JMenuItem SaveGame;
    private javax.swing.JMenuItem SaveNotation;
    private javax.swing.JButton Stop;
    private javax.swing.JTextArea Vilorea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JSpinner time;
    private javax.swing.JSpinner turn;
    // End of variables declaration//GEN-END:variables
}
