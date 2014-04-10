/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.network;

import draft.gamemode.Network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Class communication implements thread which takes care of sending and receiving 
 * packets during the network game
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class Communication extends Thread { 
    private BufferedReader reader;
    private PrintWriter writer;
    private Network parent;
    private String toSend;
    private Boolean color = false;
    
    /**
     * Constructor of the communication thread
     * 
     * @param parent parent object of the thread
     * @param name name of the thread
     * @param reader socket reader connected to the os
     * @param writer socket writer connected to the os
     * @param toSend string to send - null if not sending anything
     * @param color true if we are sending a color
     */
    public Communication(Network parent, String name, BufferedReader reader, PrintWriter writer, String toSend, Boolean color){
        super(name);
        this.reader = reader;
        this.writer = writer;
        this.parent = parent;
        this.toSend = toSend;
        this.color = color;
    }
    
    /**
     * Constructor of the communication thread
     * 
     * @param parent parent object of the thread
     * @param name name of the thread
     * @param reader socket reader connected to the os
     * @param writer socket writer connected to the os
     * @param toSend string to send - null if not sending anything 
     */
        
    public Communication(Network parent, String name, BufferedReader reader, PrintWriter writer, String toSend){
        super(name);
        this.reader = reader;
        this.writer = writer;
        this.parent = parent;
        this.toSend = toSend;
        this.color = false;
    }
    
    /**
     * Method invoked when the thread starts.
     * 
     * It sends a packet and waits form confirmation
     */
    @Override
    public void run() {
        int packNr = this.parent.getPacketNum();
        if (color) {
            while(true){
                //read string
                String tryTurn = "";   
                try {
                    tryTurn = this.reader.readLine();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this.parent, "The opponent was disconnected!");
                    this.parent.quitGame(this);
                    return; 
                }
                int index = tryTurn.indexOf('#');
                int loadIndex = tryTurn.indexOf('$');
                
                String col = tryTurn.substring(index+1,index+6);
                System.err.println(" - Try turn color: "+tryTurn+" col: "+col);
                if(col.equals("Black") || col.equals("White")){
                    System.err.println(" - I am not "+col);
                    this.writer.println(packNr+"#confirmColor");
                    incPN(packNr);
                    if (loadIndex > -1) {
                        String loadData = tryTurn.substring(loadIndex+1);
                        System.err.println(" - Loaded Data: "+ loadData);
                        // Zde interpretace nahrane hry
                        this.parent.setGameToStateAfterLoad(loadData, col);
                    }
                    this.parent.colorSetFromNetwork(col);
                    break;
                }
                
                try {
                    Communication.sleep(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
           System.out.println("Active waiting started.");
           String tryTurn = "";   
           //cyklus pro odeslani zpravy
           while(toSend != null){
               System.out.println("Sending message...");
               //sleep
               try {
                   Communication.sleep(5);
               } catch (InterruptedException ex) {
                   Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
               }
               //if(!this.parent.getWaitingForConfirm()){
               this.writer.println(this.toSend);
               incPN(packNr);
               //this.parent.setWaitingForConfirm(true);
               break;
               //}
           }

            //cyklus pro cekani na odpoved
            while(true){
                int recievedPacketNr = -1;
                System.out.println("Waiting for message...");
                //sleep
                try {
                    Communication.sleep(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                }
                //read string
                try {
                    do {
                        tryTurn = this.reader.readLine();
                        recievedPacketNr = this.parent.getNumOfString(tryTurn);
                    } while (recievedPacketNr != packNr);
                    incPN(recievedPacketNr);
                    // WE HAVE RIGHT PACKET!!! HURAAAYYY!
                    System.out.println(" - RIGHT PACKET... "+tryTurn);
                    
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this.parent, "The opponent was disconnected!");
                    this.parent.quitGame(this);
                    return;
                }
                //work with read string
                if(tryTurn.equals(recievedPacketNr+"#confirm")){
                    //this.parent.setWaitingForConfirm(false);
                    System.out.println("Got a confirm message... "+tryTurn);
                    this.parent.confirmReceived();
                    break;
                } else if(tryTurn.equals(recievedPacketNr+"#confirmColor")){
                    //this.parent.setWaitingForConfirm(false);
                    System.out.println("Got a color confirm message..."+tryTurn);
                    this.parent.colorConfirmReceived();
                    break;
                } else {
                    if(recievedPacketNr == packNr){
                        this.writer.println(recievedPacketNr+"#confirm");
                        incPN(packNr);
                        System.out.println("Got a message and sent confirm..");
                        this.parent.turnConfirmed(tryTurn);
                        break;
                    }
                }
            }//while we are reading the same packet
        }
    }    
    
    public void incPN(int num) {
        this.parent.setPacketNum(num);
    }
}