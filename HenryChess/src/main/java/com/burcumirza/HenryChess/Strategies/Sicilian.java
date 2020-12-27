/*
 * Created on March 13, 2008, 7:44 PM by MHenry
 *
 */

package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;

import java.util.*;

public class Sicilian extends Opening {
    private Strategy strategy;
    private LegalMoves legalMoves;

    public Sicilian(Chessboard board, int size, LegalMoves legalMoves) {
        super(size);
        this.legalMoves = legalMoves;
        strategy = new Strategy(board, legalMoves, 0, 1000, false);
    }

    public boolean checkAlterations(int m) {
        boolean alteration = false;
        ArrayList<Move> moveList = strategy.evaluate(legalMoves.getMoveList(m % 2, true), m % 2);
        boolean found = false;
        int n = m;
        Move tempMove;
        while (!found && n < size) {
            tempMove = openingMove[n];
            openingMove[n] = tempMove;
            int i = 0;
            while (!found && i < moveList.size()) {
                if (openingMove[n].equals((Move) moveList.get(i))) {
                    openingMove[m] = (Move) moveList.get(i);
                    found = true;
                    alteration = true;
                } else {
                    i++;
                }
            }
            n = n + 2;
        }
        return alteration;
    }

}
