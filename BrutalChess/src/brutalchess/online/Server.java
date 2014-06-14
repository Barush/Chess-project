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
public class Server extends Online{

	protected int port;

	public Server(Game game, int port){
		super(game);
		ServerSocket serverSocket;
		try {
			
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			
			if (listenFor().equals(CONNECTMESSAGE)){
				sendMsg(ACKMESSAGE);
			} else {
				throw new RuntimeException("Client is speaking language I don't understand!");
			}
			
		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}

}