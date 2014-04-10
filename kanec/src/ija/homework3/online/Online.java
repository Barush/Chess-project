/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.homework3.online;
import java.net.*;
import java.io.*;
import ija.homework3.basis.*;

/**
 *
 * @author Canes
 */
public class Online {
	
	static ServerSocket socket1;
	static Socket connection;
	static final String CONNECTMESSAGE = "Hello checkers!";
	static final String ACKMESSAGE = "Couldn't agree more!";
	static final String ERRORMESSAGE = "Ouch! Error occured.";
	static final String CLOSEMESSAGE = "Good bye!";

	static boolean first;
	protected String host;
	protected int port;
	protected Game game;
	
	private BufferedOutputStream bos;
	OutputStreamWriter osw;
	BufferedInputStream bis;
	InputStreamReader isr;
	StringBuffer strb = new StringBuffer();
	
	public Online(String params, Game hra){
		game = hra;
		String[] info = params.split(":");
		
		if (info.length == 2){
			
			// this is client
			host = info[0];
			port = Integer.parseInt(info[1]);
			
			// lets try to connect to the server
			if (!connect()) throw new RuntimeException();
			System.out.println("Connecting to: "+ host +":"+ port);
			
		} else if (info.length == 1){
			
			// this is server
			port = Integer.parseInt(info[0]);
			game.player = game.desk.BLACK; // server is always black and this style of access is wrong :(
			
			// lets listen on some port
			if (!listen()) throw new RuntimeException();
			System.out.println("Listening on port: "+ port);
			
			// start listenning on port - catching moves
			game.switchPlayers();
			listen4Play();
			
		} else {
			System.out.println("Nevalidni parametry pro online hru "+ params);
			throw new RuntimeException();
		}
	}
 
	public boolean connect(){

		try {
			InetAddress address = InetAddress.getByName(host);
			connection = new Socket(address, port);

			bos = new BufferedOutputStream(connection.getOutputStream());
			osw = new OutputStreamWriter(bos, "US-ASCII");

			String message = CONNECTMESSAGE + (char) 13;
			osw.write(message);
			osw.flush();

			bis = new BufferedInputStream(connection.getInputStream());
			isr = new InputStreamReader(bis, "US-ASCII");

			int c;
			while ( (c = isr.read()) != 13){
				strb.append( (char) c);
			}
			
			if (strb.toString().equals(ACKMESSAGE)){
				System.out.println("Connected to: "+ host +":"+ port);
			}
			connection.close();

		}
		catch (IOException f) {
			System.out.println("IOException: " + f);
		}
		catch (Exception g) {
			System.out.println("Exception: " + g);
		}

		return true;
	}
	
	public boolean listen(){
		int character;
		boolean status = false;
		String returnCode = ERRORMESSAGE;
		
		try{
			socket1 = new ServerSocket(port);
			System.out.println("Server waiting for message");
			
			connection = socket1.accept();

			bis = new BufferedInputStream(connection.getInputStream());
			isr = new InputStreamReader(bis);
			bos = new BufferedOutputStream(connection.getOutputStream());
			osw = new OutputStreamWriter(bos, "US-ASCII");

			// read incoming message
			while((character = isr.read()) != 13) {
				strb.append((char)character);
			}
			
			// check incoming message
			System.out.println(strb);
			if (strb.toString().equals(CONNECTMESSAGE)){
				status = true;
				returnCode = ACKMESSAGE;
			}

			// respond to client
			osw.write(returnCode + (char) 13);
			osw.flush();
			connection.close();
			socket1.close();
			
		} catch (IOException e) {
			System.out.println("Unexpected exception on server side" +e);
		}

		return status;
	}
	
	public boolean sendMove(String move){
		System.out.println("Sending move "+move);
		try {
			InetAddress address = InetAddress.getByName(host);
			connection = new Socket(address, port);

			bos = new BufferedOutputStream(connection.getOutputStream());
			osw = new OutputStreamWriter(bos, "US-ASCII");
			
			String message = move + (char) 13;
			osw.write(message);
			osw.flush();
		} catch (IOException e) {
			System.out.println("Unexpected exception on server side" +e);
		}
		return true;
	}
	
	public void listen4Play(){
		Runnable runnable = new Listener(this.game, this.port);
		Thread thread = new Thread(runnable);
		thread.start();
	}
	
}
