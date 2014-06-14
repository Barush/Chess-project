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
public class Online {

	static Socket socket;
	
	static final String CONNECTMESSAGE = "Hello checkers!";
	static final String ACKMESSAGE = "Couldn't agree more!";
	static final String ERRORMESSAGE = "Ouch! Error occured.";
	static final String CLOSEMESSAGE = "Good bye!";

	protected Game game;

	StringBuffer strb = new StringBuffer();

	public Online(Game game){
		this.game = game;
	}
	
	public String listenFor(){
		
		InputStreamReader input;
		try {
			input = new InputStreamReader(
					new BufferedInputStream(socket.getInputStream())
			);

			int character;
			while((character = input.read()) != 13) {
				strb.append((char)character);
			}
		} catch (IOException ex) {
			Logger.getLogger(Online.class.getName()).log(Level.SEVERE, null, ex);
		}

		// check incoming message
		System.out.println(strb);
		
		return strb.toString();
	}

	public boolean sendMsg(String move) {
		System.out.println("Sending move "+move);
		try {
			OutputStreamWriter osw = new OutputStreamWriter(
				new BufferedOutputStream(socket.getOutputStream())	
				, "US-ASCII"
			);

			String message = move + (char) 13;
			osw.write(message);
			osw.flush();
			
		} catch (IOException e) {
			System.out.println("Unexpected exception on server side" +e);
			return false;
		}
		return true;
	}

}