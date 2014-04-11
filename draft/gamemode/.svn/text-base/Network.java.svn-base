/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.gamemode;


import draft.logic.Logic;
import draft.logic.Turn;
import draft.swingui.GameUI;
import draft.swingui.TileComponent;
import java.util.List;
import draft.logic.GameState;
import draft.logic.*;
import draft.swingui.*;
import draft.figures.*;
import java.awt.event.MouseEvent;
import java.util.*;
import draft.basis.*;
import draft.network.Communication;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Class implements network game between 2 players. 
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public final class Network extends GameUI{
    private Queue<Report> record = new LinkedList<Report>(); // recording all game turn by turn
    private int wrongMove = 0;
    private boolean figureSelected = false;
    private TileComponent tile1;
    private TileComponent tile2;
    private List<Turn> movePossibility;
    private Logic state;
    private ServerSocket server;
    private Socket connection;
    private int mycolor = -1;
    private PrintWriter writer;
    private BufferedReader reader;
    private Communication activeWaiting;
    private Communication colorWaiting;
    private Communication confirmWaiting;
    private char figureThrownOut;
    private int packetNum = 0;
    private int move = 1;
    //private Boolean waitingForConfirm = false;
    
    static private int BLACK = 0;
    static private int WHITE = 1;
    
    public void d(String s) {
      System.err.println(s);
    }
    
     /**
     * Constructor of the network game from load
     * 
     * @param ipAdress address for the connection
     * @param portNr the number of port for connection
     * @param color Object represents players color
     * @param creator If true connect like the creator, otherwise like the connector
     */
    public Network(String ipAddress, int portNr, Object color, String loadData) {
        super();
        initDesk();
        updateDesk();
        loadData = "@"+loadData;
        this.setTitle("Player vs. Player on Network");
        state = new Logic(getWhiteFigures(), getBlackFigures());
        d("### I am load creator");
        connect(ipAddress, portNr);
        initConnectionCreator();
        sendMyColorWithLoad(color, loadData);
        setGameToStateAfterLoad(loadData, color.toString());
    }
    
    /**
     * Constructor of the network game
     * 
     * @param ipAdress address for the connection
     * @param portNr the number of port for connection
     * @param color Object represents players color
     * @param creator If true connect like the creator, otherwise like the connector
     */
    public Network(String ipAddress, int portNr, Object color, Boolean creator) {
        super();
        initDesk();
        updateDesk();
        this.setTitle("Player vs. Player on Network");
        state = new Logic(getWhiteFigures(), getBlackFigures());
        if(creator){
            d("### I am creator");
            connect(ipAddress, portNr);
            initConnectionCreator();
            sendMyColor(color);
        } else {
            d("### I am connector");
            serverConnect(portNr);
            initConnectionConnector();
            wait4ColorOrLoad();
        }
    }

    /**
     * Packet number setter
     */
    public void setPacketNum(int num){
        this.packetNum = num;
    }
    
    /**
     * Packet number getter
     */
    public int getPacketNum(){
        return this.packetNum;
    }
    
    /**
     * Method for create client socket connection.
     * 
     * @param ipAddress IP Address
     * @param portNr port
     */
    public void connect(String ipAddress, int portNr){
        try {
            this.connection = new Socket(ipAddress, portNr);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Wrong argument in the input!", "Connection Failed", 0);
        }
    }

     /**
     * Method for create server socket connection.
     * 
     * @param portNr port
     */
    public void serverConnect(int portNr){
        try {
            this.server = new ServerSocket(portNr);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Wrong argument in the input!", "Connection Failed", 0);
        }
        try {
            this.connection = this.server.accept();
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Initialization of the creator (client)
     */
    public void initConnectionCreator(){
      d(" - init Network: creator");
      try {
          this.writer = new PrintWriter(this.connection.getOutputStream(), true);
            this.reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(this, "Unknown host.", "Connection failed", 0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Connection to the host failed.", "Connection failed", 0);
        }
    }
    
    /**
     * Initialization of the connector (server)
     */
    public void initConnectionConnector() {
        d(" - init Network: connector");
        try {
            this.writer = new PrintWriter(this.connection.getOutputStream(), true);
            this.reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host exception in joinPartition");
        } catch (IOException e) {
            System.err.println("IO exception in joinPartition");
        }
    }
    
    /**
     * Sends color capsuled as string in the packet.
     * 
     * @param color players color
     */
    public void sendMyColor(Object color){        
        //nacteni vybrane barvy
        String col = Integer.toString(this.packetNum) + "#" + color.toString();
        setColor(color.toString());
        
        sendPacket(col);
    }
    
    /**
     * Sends color capsuled as string in the packet.
     * 
     * @param color players color
     */
    public void sendMyColorWithLoad(Object color, String load){        
        //nacteni vybrane barvy
        String col = Integer.toString(this.packetNum) + "#" +color.toString()+"$"+load;
        setColor(color.toString());
        
        sendPacket(col);
    }
    
    /**
     * Method starts thread of waiting to recieve the color.
     */
    public void wait4ColorOrLoad(){
        colorWaiting = new Communication(this, "colorWaiting", this.reader, this.writer, null, true);
        colorWaiting.start();
    }
    
    /**
     * Overrided method of the mouse click.
     */
    @Override
    public void mousePressed(MouseEvent e){
        if (javax.swing.SwingUtilities.isLeftMouseButton(e) && state.getGameSate().equals(GameState.PLAYER1)) {
            if(!figureSelected){
                tile1 = (TileComponent) e.getSource();
                if((this.mycolor == BLACK) && getBlackFigures().contains(tile1.getTilePosition().getFigure())){
                    if(tile1.getTilePosition().getFigure() instanceof Draft){
                        tile1.setBlackMatchedDraft();
                        figureSelected = true;
                    }//i have chosen a draft
                    else {
                        tile1.setBlackMatchedPawn();
                        figureSelected = true;
                    }//i have chosen a pawn
                }//I am black player and I chose a black figure
                
                if((this.mycolor == WHITE) && getWhiteFigures().contains(tile1.getTilePosition().getFigure())){
                    if(tile1.getTilePosition().getFigure() instanceof Draft){
                        tile1.setWhiteMatchedDraft();
                        figureSelected = true;
                    }//i have chosen a draft
                    else {
                        tile1.setWhiteMatchedPawn();
                        figureSelected = true;
                    }//i have chosen a pawn
                }//I am white player and I chose a white figure
            }//figure not selected
            else { //SECOND CLICK - MOVE
                tile2 = (TileComponent) e.getSource();
                if(tile1.equals(tile2)){
                    //unselecting figure
                    tile1.updateTile();
                    figureSelected = false;
                } else {
                    Boolean isPossible = false;
                    if(this.mycolor == BLACK){
                        isPossible = blackMove(tile2);
                        updateDesk();
                        figureSelected = false;
                    }//I am the black player
                    else { // WHITE
                        isPossible = whiteMove(tile2);
                        updateDesk();
                        figureSelected = false;
                    }//I am the white player
                    if (isPossible) {
                        String toSend = posToString(tile1.getTilePosition(), tile2.getTilePosition());
                        writeReport(toSend);
                        sendPacket(toSend);
                    }
                }
            }//figureSelected
        }//isLeftMouseButton && PLAYER1 plays
        checkWinOrLoose();
    }
    
     /**
     * whiteMove method -  implements all logic of my one move of a black figure including 
     * invoking methods working with GUI. 
     * 
     * To see the changes made in this method you have to invoke updateDesk() 
     * afterwards.
     * 
     * This method also shows a help message if the turn which you are trying to 
     * make is illegal.
     * 
     * @param tile2 Endtile of a turn.
     * @return True, if move was processed, false otherwise.
     */
    public Boolean whiteMove(TileComponent tile2) {
        movePossibility = state.getMovePossibility(1); // white
        this.figureThrownOut = '-';

        for (Turn turn : movePossibility) {
            if (turn.getPositionTo().equals(tile2.getTilePosition()) && turn.getPositionFrom().equals(tile1.getTilePosition())) {
                Figure figure = tile1.getTilePosition().getFigure().move(tile2.getTilePosition());
                
                if (figure != null) {
                    state.removeFigureFromDesk(figure);
                    this.figureThrownOut = 'x';
                }
                state.changeFigures();
                return true;
            }
        }
        JOptionPane.showMessageDialog(this, "Move for white from " + movePossibility.get(0).getPositionFrom().getColumn() + "" + movePossibility.get(0).getPositionFrom().getRow() + " to " + movePossibility.get(0).getPositionTo().getColumn() + "" + movePossibility.get(0).getPositionTo().getRow());        
        return false;
    }
    
    /**
     * blackMove method -  implements all logic of my one move of a black figure including 
     * invoking methods working with GUI. 
     * 
     * To see the changes made in this method you have to invoke updateDesk() 
     * afterwards.
     * 
     * This method also shows a help message if the turn which you are trying to 
     * make is illegal.
     * 
     * @param tile2 Endtile of a turn.
     * @return True, if move was processed, false otherwise.
     */
    public Boolean blackMove(TileComponent tile2) {
        movePossibility = state.getMovePossibility(0); // black
        this.figureThrownOut = '-';

        for (Turn turn : movePossibility) {
            if (turn.getPositionTo().equals(tile2.getTilePosition()) && turn.getPositionFrom().equals(tile1.getTilePosition())) {
                Figure figure = tile1.getTilePosition().getFigure().move(tile2.getTilePosition());
                
                if (figure != null) {
                    this.figureThrownOut = 'x';
                    state.removeFigureFromDesk(figure);
                }//vyhozeni preskocene figurky
                state.changeFigures();
                return true;
            }
        }
        JOptionPane.showMessageDialog(this, "Move for black from " + movePossibility.get(0).getPositionFrom().getColumn() + "" + movePossibility.get(0).getPositionFrom().getRow() + " to " + movePossibility.get(0).getPositionTo().getColumn() + "" + movePossibility.get(0).getPositionTo().getRow());
        return false;
    }
    
    /**
     * Moves figure of the opponent player.
     * Is called after recieved move from opponent player.
     * 
     * To see the changes made in this method you have to invoke updateDesk() 
     * afterwards.
     * 
     * This method also shows a help message if the turn which you are trying to 
     * make is illegal.
     * 
     * @param tile2 Endtile of a turn.
     * @return True, if move was processed, false otherwise.
     */
    public void opponentMove(String inMsg){
        TileComponent [] turnTiles = {null, null};
        d(" *** opponentTurn: "+inMsg);
        turnTiles = stringToTiles(inMsg);   
        Figure figure = turnTiles[0].getTilePosition().getFigure().move(turnTiles[1].getTilePosition());
        if(figure != null){
           state.removeFigureFromDesk(figure);
        }
        updateDesk();
        writeReportFromNetwork(inMsg);
        checkWinOrLoose();
        activePlayerPlays();
    }
    
    /**
     * Parses and writes report from network (The turn of opponent player).
     * 
     * @param msg report.
     */
    public void writeReportFromNetwork(String msg){
        String turn = msg.substring(msg.indexOf('#')+1);
        if(this.mycolor == BLACK){
            getVilorea().append(move + ". " + turn + " ");
            move++;
        }
        else {
            getVilorea().append(turn + "\n");

        }
        writeTurnToRecord(turn);
    }
    
    /**
     * Parses and writes report from network (The turn of opponent player).
     * 
     * @param msg report.
     */
    public void writeReport(String msg){
        String turn = msg.substring(msg.indexOf('#')+1);
        if(this.mycolor == WHITE){
            getVilorea().append(move + ". " + turn + " ");
            move++;
        }
        else {
            getVilorea().append(turn + "\n");

        }
        writeTurnToRecord(turn);
    }
    
        /**
     * Parses and writes report from network (The turn of opponent player).
     * 
     * @param msg report.
     * @param isWhite Is white move?
     */
    public void writeReport(String msg, Boolean isWhite){
        String turn = msg.substring(msg.indexOf('#')+1);
        if(isWhite){
            getVilorea().append(move + ". " + turn + " ");
            move++;
        }
        else {
            getVilorea().append(turn + "\n");
        }
        writeTurnToRecord(turn);
    }
        
    /**
     * Sets player color after recived packet with the color from the opponent player.
     * 
     * @param color Players color.
     */
    public void colorSetFromNetwork(String color){
        colorWaiting.interrupt();
        // On the other side are the colors inverted.
        if (color.toString().equals("Black")) {
            setColor("White");
        } else {
            setColor("Black");
        }
    }
    
    /**
     * Converts turn string to the tiles and add formated text to the end of defined record. 
     * 
     * @param color Players color.
     */
    private void writeTurnToRecord(String turn){
        TileComponent []tiles = {null, null};
        tiles = stringToTiles(turn);
        Boolean thrown;
        
        if(turn.charAt(2) == '-') {
            thrown = false;
        }
        else {
            thrown = true;
        }
        getRecord().add(new Report(move, tiles[0].getTilePosition(), tiles[1].getTilePosition(), thrown, this.mycolor));
    } 
    
    /**
     * Color setter.
     * 
     * @param color players color.
     */
    private void setColor(String color) {
        //color of the second player is black
        if (color.equals("Black")) {
            d(" - Color set: BLACK");
            this.mycolor = BLACK; //I am black
            opponentPlays();
        } else {
            //color of the second player is white
            d(" - Color set: WHITE");
            this.mycolor = WHITE; //I am white
            activePlayerPlays();
        }
    }
    
    /**
     * Called after the color confirmation message/packet recieved.
     */
    public void colorConfirmReceived() {
        confirmWaiting.interrupt();
        if (this.mycolor == WHITE) {
            activePlayerPlays();
        }
    }
    
    /**
     * Called after the confirmation message/packet recieved.
     */
    public void confirmReceived() {
        confirmWaiting.interrupt();
        opponentPlays();
    }
    
    /**
     * Called after the turn message/packet recieved.
     */
    public void turnConfirmed(String inMsg){
        activeWaiting.interrupt();
        System.out.println("Message confirmed: " + inMsg);
        
        if((inMsg != null) && inMsg.indexOf('-') == -1 && inMsg.indexOf('x') == -1 && inMsg.length() == 7){
            System.err.println("ERROR: Message is not a turn.");
            return;
        }//message is not a turn
        ///this.inMsg = inMsg;
        opponentMove(inMsg);
    }
    
    /**
     * Sends packet with text to opponent player.
     * 
     * @param toSend Messages text.
     */
    public void sendPacket(String toSend){
        this.state.setGameState(GameState.PLAYER2);
        System.err.println(" *** sending... "+toSend);
        this.confirmWaiting = new Communication(this, "confirmWaiting", this.reader, this.writer, toSend);
        confirmWaiting.start();    
    }   
    
    /**
     * Parses number from (packet) message.
     * 
     * @param turnMsg Parsed message.
     * @return Number of packet or -1 if the parsing failed.
     */
    public int getNumOfString(String turnMsg){
        int index = turnMsg.indexOf('#');
        if(index != -1 ){
            return Integer.parseInt(turnMsg.substring(0, index));
        }
        return -1;
    }
    
    /**
     * Converts the possition to the message (used in packets).
     * 
     * @param posFrom Turns from position.
     * @param posTo Turns to position.
     * @return the message
     */
    public String posToString(Position posFrom, Position posTo){
        String turn = "";
        
        turn += Integer.toString(this.packetNum);
        turn += '#';
        turn += posFrom.getColumn();
        turn += posFrom.getRow();
        turn += this.figureThrownOut;
        turn += posTo.getColumn();
        turn += posTo.getRow();
        System.err.println("Po prevodu pos to string: " + turn);
        return turn;
    }

    /**
     * Parses massage and converts to tiles objects.
     * 
     * @param turnMsg mesasage
     * @return Array of tiles.
     */
    public TileComponent [] stringToTiles(String turnMsg){
        char colFrom, rowFrom, colTo, rowTo;
        TileComponent [] retTiles = {null, null};
        
        String turn = turnMsg.substring(turnMsg.indexOf('#')+1);
        System.out.println("Substring: "+turn);
        colFrom = turn.charAt(0);
        rowFrom = turn.charAt(1);
        colTo = turn.charAt(3);
        rowTo = turn.charAt(4);
        
        if(colFrom < 'a' || colFrom > 'h' || colTo < 'a' || colTo > 'h' ||
                rowFrom < '1' || rowFrom > '8' || rowTo < '1' || rowTo > '8'){
            System.out.println("Tohle musi generovat nullPtrException!");
            return null;
        }
        
        retTiles[0] = getTile((int)(rowFrom - '0'), colFrom);
        retTiles[1] = getTile((int)(rowTo - '0'), colTo);
        
        System.out.println("Po prevodu string to pos: " + retTiles[1]);
        return retTiles;
    }
    
    /**
     * Set game state on: "The opponent plays".
     */
    public void opponentPlays() {
       this.state.setGameState(GameState.PLAYER2);
       d(" - opponent plays");
       this.activeWaiting = new Communication(this, "activeWaiting", this.reader, this.writer, null);
       activeWaiting.start();
    }
    
    /**
     * Set game state on: "I play".
     */
    public void activePlayerPlays() {
       this.state.setGameState(GameState.PLAYER1);
       d(" - I play.");
    }
    
    /**
     * Checks if game ended.
     */
    public void checkWinOrLoose() {
        int NO = 0;
        int WIN = 1;
        int LOOSE = 2;
        int barushka = NO;
        if (this.state.getBlackFigures().isEmpty()) {
            if (this.mycolor == BLACK) {
                barushka = LOOSE;
            } else {
                barushka = WIN;
            }
        }
        if (this.state.getWhiteFigures().isEmpty()) {
            if (this.mycolor == WHITE) {
                barushka = LOOSE;
            } else {
                barushka = WIN;
            }
        }
        if (barushka!=NO) {
            if (barushka == LOOSE) {
                JOptionPane.showMessageDialog(this, "You are looser!!!");
            } else {
                JOptionPane.showMessageDialog(this, "You are winner!!!");
            }
            opponentPlays();
            disconnect();
        }
    }
    
    /**
     * Overrided method for quit game action.
     */
    @Override
    protected void QuitGameActionPerformed(java.awt.event.ActionEvent evt){
        this.dispose();
        disconnect();
    }
    
    /**
     * Disconnects from network.
     */
    public void disconnect(){
         try {
                this.writer.close();
                this.reader.close();
                this.connection.close();
                this.server.close();
            } catch (IOException ex) {
                Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * Ends game and kills thread.
     * Called from thread;
     * 
     * @param victim Thread, which will be killed.
     */
    public void quitGame(Communication victim) {
        victim.stop();
        this.dispose();
    }
    
    /**
     * Overrided save game action method.
     */
    @Override
    public void SaveGameActionPerformed(java.awt.event.ActionEvent evt) {
        //SaveGame save = new SaveGame(this);
        new SaveGame(getRecord());
    }

    /**
     * Overrided save game notation action method.
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
     * Method to set the desk into start state after load from file
     * 
     * @param loadData string containing content of the file
     * @param color color choosed by creator
     */
    public void setGameToStateAfterLoad(String loadData, String color) {
        Boolean whiteOnTurn = false;
        if (loadData.charAt(loadData.length()-1)=='@') {
            whiteOnTurn = true;
            loadData = loadData.substring(0, loadData.length()-1);
        }
        // Implementace presunu figurek
       int indxAt = -1;
       whiteOnTurn = true;
       while ((indxAt = loadData.indexOf('@'))!=-1) {
            String turn = loadData.substring(indxAt+1, indxAt+6);
            loadData = loadData.substring(indxAt+6);
            TileComponent [] turnTiles = {null, null};
            d(" *** loaded parsed turn: "+turn);
            turnTiles = stringToTiles(turn);   
            Figure figure = turnTiles[0].getTilePosition().getFigure().move(turnTiles[1].getTilePosition());
            if(figure != null){
               state.removeFigureFromDesk(figure);
            }
            
            writeReport(turn, whiteOnTurn);
            whiteOnTurn = !whiteOnTurn;
        }
        updateDesk();
        if (whiteOnTurn) {
            if (color.equals("White")) {
                activePlayerPlays();
            } else {
                opponentPlays();
            }
        } else {
            if (color.equals("Black")) {
                activePlayerPlays();
            } else {
                opponentPlays();
            }
        }
    }
    
    /**
     * Record getter.
     * 
     * @return the record
     */
    @Override
    public Queue<Report> getRecord() {
        return record;
    }
}
