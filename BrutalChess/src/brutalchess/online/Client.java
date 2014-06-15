package brutalchess.online;

import static brutalchess.Const.*;
import brutalchess.basis.Game;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Canes
 */
public class Client extends Online{

	protected String host;
	protected int port;

	public Client(Game game, String addressString){
		super(game);
		
		String[] info = addressString.split(":");

		// this is client
		host = info[0];
		port = Integer.parseInt(info[1]);
	}
	
	@Override
	public void init(){
		InetAddress address;
		try {
			address = InetAddress.getByName(host);
			
			try {
				socket = new Socket(address, port);
				sendMsg(CONNECTMESSAGE);
				
				dbg("Socket created! Sending message...");
				
			} catch (IOException ex) {
				Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
				dbg("BULLSHIT!");
			}
			
		} catch (UnknownHostException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
			dbg("BULLSHIT!");
		}
		
		// get Color from host
		int color = Integer.parseInt( listenFor() );
		dbg("Got color from server: "+ (char) color);
		if (color == BLACK || color == WHITE){
			game.setLocalColor(color);
		} else {
			throw new RuntimeException("Host didnt respond which color I should be");
		}
		
		super.init();
	}
 
}