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
	public void run(){
		InetAddress address;
		try {
			address = InetAddress.getByName(host);
			
			try {
				socket = new Socket(address, port);
				sendMsg(CONNECTMESSAGE);
				
				System.out.println("Socket created! Sending message...");
				
			} catch (IOException ex) {
				Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
				System.out.println("BULLSHIT!");
			}
			
		} catch (UnknownHostException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("BULLSHIT!");
		}
		
		// get Color from host
		int color = Integer.parseInt( listenFor() );
		System.out.println("Got color from server: "+ (char) color);
		if (color == BLACK || color == WHITE){
			game.setLocalColor(color);
		} else {
			throw new RuntimeException("Host didnt respond which color I should be");
		}
		
		super.run();
	}
 
}