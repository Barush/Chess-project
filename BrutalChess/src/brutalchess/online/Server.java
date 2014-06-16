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
public class Server extends Online implements Runnable{

	protected int port;
	ServerSocket serverSocket;

	public Server(Game game, int port){
		super(game);
		this.port = port;
	}
	
	@Override
	public void run(){
		dbg("Running server ");
		
		try {
			serverSocket = new ServerSocket(port);
			socket = serverSocket.accept();
			
			dbg("Catching " + CONNECTMESSAGE);
			if (listenFor().equals(CONNECTMESSAGE)){
				
				// send color
				if (game.getLocalColor() == WHITE){
					sendMsg(String.valueOf(BLACK));
				} else {
					sendMsg(String.valueOf(WHITE));
				}
				
			} else {
				throw new RuntimeException("Client is speaking language I don't understand!");
			}
			
		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}

		super.init();
	}

}