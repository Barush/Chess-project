/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.homework3.online;
import java.net.*;
import java.io.*;
import ija.homework3.basis.*;
import ija.homework3.gui.Gui;

/**
 *
 * @author Canes
 */
public class Listener implements Runnable{
	
	static ServerSocket socket1;
	static Socket connection;

	protected int port;
	protected Game game;
	
	BufferedInputStream bis;
	InputStreamReader isr;
	StringBuffer strb = new StringBuffer();

	public Listener(Game newgame, int newport){
		game = newgame;
		port = newport;
	}
	
	@Override
	public void run(){
		System.out.println("Starting Listener");
		int character;
		try {
			socket1 = new ServerSocket(port);
			game.gui.lock("Waiting for other player");
			connection = socket1.accept();

			bis = new BufferedInputStream(connection.getInputStream());
			isr = new InputStreamReader(bis);

			while((character = isr.read()) != 13) {
				strb.append((char)character);
			}

			// if received CLOSEMESSAGE -> end listening and do something
			if (strb.toString().equals(Online.CLOSEMESSAGE)){
				System.out.println("Looks like we have ended...");
			}

			// check incoming message
			System.out.println(strb);

			Figure f = game.parseFigure(strb.toString());
			Position p = game.parsePosition(strb.toString());
			game.playerPlay(f, p);

			game.switchPlayers();
			game.gui.unlock();
			connection.close();
			System.out.println("moved, your turn!");
			socket1.close();
			
		} catch (IOException e) {
			System.out.println("Unexpected exception on server side" +e);
		}
	}
	

}
