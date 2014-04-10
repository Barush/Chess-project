/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.network;

import draft.gamemode.Network;

/**
 * Class implements thread which takes care of creating a connection between two 
 * peers in the beginning of the network game
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class Connection extends Thread { 
    private int port;
    
    public Connection(String name, int inPort){
        super(name);
        this.port = inPort;
    }
    
    @Override
    public void run() {    
        Network game = new Network(null, port, null, false);
        game.setVisible(true);                                   
    }    
    
}