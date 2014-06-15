package brutalchess.online;

import static brutalchess.Const.*;
import brutalchess.basis.Game;
import brutalchess.basis.Position;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Canes
 */
public abstract class Online {

	static Socket socket;
	
	static final String CONNECTMESSAGE = "Hello checkers!";
	static final String ACKMESSAGE = "Couldn't agree more!";
	static final String ERRORMESSAGE = "Ouch! Error occured.";
	static final String CLOSEMESSAGE = "Good bye!";

	protected Game game;

	public Online(Game game){
		this.game = game;
	}
	
	public void run() {
		if (game.getLocalColor() == BLACK){
			decodeMove(listenFor());
		}
	};
	
	public String listenFor() {
		InputStreamReader input;
		StringBuffer strb = new StringBuffer();
		
		try {
			input = new InputStreamReader(
					new BufferedInputStream(socket.getInputStream())
			);
			
			System.out.println("Listening...");

			int character;
			while((character = input.read()) != 13) {
				strb.append((char)character);
			}
		} catch (IOException ex) {
			Logger.getLogger(Online.class.getName()).log(Level.SEVERE, null, ex);
		}

		// check incoming message
		System.out.println("Received message " + strb);
		
		return strb.toString();
	}

	public boolean sendMsg(String message) {
		System.out.println("Sending message " + message);
		try {
			OutputStreamWriter osw = new OutputStreamWriter(
				new BufferedOutputStream(socket.getOutputStream())	
				, "US-ASCII"
			);

			message = message + (char) 13;
			osw.write(message);
			osw.flush();
			
		} catch (IOException e) {
			System.out.println("Unexpected exception on server side" +e);
			return false;
		}
		System.out.println("Message sent: " + message);
		return true;
	}
	
	public boolean sendMove(Position from, Position to){
		String move = "";
		move += from.getCol();
		move += String.valueOf(from.getRow());
		
		move += to.getCol();
		move += String.valueOf(to.getRow());
		
		return sendMsg(move);
	}
	
	public boolean decodeMove(String move){
		
		if (!move.matches("[a-h][1-8][a-h][1-8]")) {
			throw new RuntimeException("Sent wrong move!");
		}
		
		char c = move.charAt(0);
		int r = Integer.parseInt(move.substring(1, 2));
		Position from = game.getDesk().getPositionAt(c, r);
		
		System.out.println("From: "+ c +" - "+ r);
		
		c = move.charAt(2);
		r = Integer.parseInt(move.substring(3, 4));
		Position to = game.getDesk().getPositionAt(c, r);
		
		System.out.println("To: "+ c +" - "+ r);
		
		game.makeMove(from, to);
		return true;
	}

}