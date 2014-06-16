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
public class Listener extends Online implements Runnable{

	public Listener(Game game) {
		super(game);
	}

	@Override
	public void run() {
		decodeMove(listenFor());
	}


}