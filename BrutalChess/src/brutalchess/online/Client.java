package brutalchess.online;

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
		
		InetAddress address;
		try {
			address = InetAddress.getByName(host);
			
			try {
				socket = new Socket(address, port);
			} catch (IOException ex) {
				Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
			}
			
		} catch (UnknownHostException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}

		// lets try to connect to the server
		sendMsg(CONNECTMESSAGE);
		if (!listenFor().equals(ACKMESSAGE)){
			throw new RuntimeException("Host didnt respond :(");
		}
	}
 
}