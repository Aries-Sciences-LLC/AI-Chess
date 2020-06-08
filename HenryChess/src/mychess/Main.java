package mychess;

/**
 * 
 * @author MHenry
 */
public class Main {

	/** Creates a new instance of Main */
	public Main() {
	}


	public static void main(String[] args) {
		ChessJApplet app = new ChessJApplet();
		app.init(Integer.parseInt(args[0]));
//		app.start();

// why app.start(); ? Deleted it and nothing changed...?
	}
}
