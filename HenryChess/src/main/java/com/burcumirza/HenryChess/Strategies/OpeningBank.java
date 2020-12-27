package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;

import java.util.*;

public class OpeningBank {
    private Chessboard board;

    // private Opening [] opening = new Opening [1];
    private static Random rGen = new Random();
    private static String[] openingArray = { "Scotch Game", "Giuoco Piano", "Sicilian Defence (Schevingen)",
            "Sicilian Defence (Dragon)", "Queen Gambit Declined", "Ruy Lopez", "Evans Gambit Declined", "Max Lange",
            "Danish Gambit Accepted", "Benoni Counter Gambit", "Philidor's Defence", "Colle System", "Bishop Opening",
            "KingsGambit" };
    private Opening[] opening = new Opening[openingArray.length];
    private String openingString = "";

    public OpeningBank(Chessboard board, LegalMoves legalMoves) {
        this.board = board;
        opening[0] = new ScotchGame(board);
        opening[1] = new GiuocoPiano(board);
        opening[2] = new Scheveningen(board, legalMoves);
        opening[3] = new Dragon(board, legalMoves);
        opening[4] = new QueenGambitDeclined(board);
        opening[5] = new RuyLopez(board);
        opening[6] = new EvansGambitDeclined(board);
        opening[7] = new MaxLange(board);
        opening[8] = new DanishGambitAccepted(board);
        opening[9] = new BenoniCounterGambit(board);
        opening[10] = new PhilidorsDefence(board);
        opening[11] = new ColleSystem(board);
        opening[12] = new BishopOpening(board);
        opening[13] = new KingsGambit(board);
    }

    public Move getOpeningMove(int m) {
        ArrayList<Move> moveList = new ArrayList<Move>();
        Move lastMove;
        for (int i = 0; i < opening.length; i++) {
            if (m == 0) {
                moveList.add(opening[i].getMove(0));
            } else if (opening[i].getBoolean() && m < opening[i].getSize()) {
                openingString = openingArray[i];
                lastMove = opening[i].getMove(m - 1);
                if (!(i == 3) && lastMove.piece().equals(board.objectAt(lastMove.newRow(), lastMove.newCol()))) {
                    opening[i].checkVariations(m);
                    moveList.add(opening[i].getMove(m));

                } else if (opening[i].checkAlterations(m)) {
                    moveList.add(opening[i].getMove(m));
                } else {
                    opening[i].changeBoolean();
                    openingString = "";
                }
            }
        }
        Move aMove = null;
        if (moveList.size() > 0) {
            aMove = (Move) moveList.get(rGen.nextInt(moveList.size()));
            for (int i = 0; i < opening.length; i++) {
                if (opening[i].getBoolean() && !aMove.equals(opening[i].getMove(m))) {
                    opening[i].changeBoolean();
                }
            }
        }
        return aMove;
    }

    public String getOpeningString() {
        return openingString;
    }
}
