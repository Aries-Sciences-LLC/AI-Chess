/*
 * ChessJApplet.java
 * Created on October 22, 2006, 8:44 AM
 * Written 11/04-5/05
 * @author mhenry
 */

package com.burcumirza.HenryChess.Logic;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class ChessJApplet extends JApplet {
    private ChessGame game;
    private Chessboard board;
    private Frame window;

    public void init(int level) {
        Dimension bounds = new Dimension(800, 700);
        board = new Chessboard(bounds);
        window = new Frame("Level " + level + " Chess Round");
        window.add(board);
        window.setSize(bounds);
        window.setVisible(true);
        window.setResizable(false);
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((int) ((dim.width / 2) - (bounds.getWidth() / 2)),
                (int) ((dim.height / 2) - (bounds.getHeight() / 2)));

        Move.board = board;
        game = new ChessGame(board, level);
        game.makeMove();
    }
}

class RoundedBorder implements Border {

    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
