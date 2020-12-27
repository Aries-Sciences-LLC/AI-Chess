package com.burcumirza.HenryChess;

import com.burcumirza.HenryChess.Logic.*;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        ChessJApplet app = new ChessJApplet();
        app.init(Integer.parseInt(args[0]));
        // app.start();

        // why app.start(); ? Deleted it and nothing changed...?
    }
}
