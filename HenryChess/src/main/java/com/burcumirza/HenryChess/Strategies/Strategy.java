package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;
import com.burcumirza.HenryChess.Pieces.*;

import java.util.*;

public class Strategy {
    public Chessboard board;
    public LegalMoves legalMoves;
    public static Random rGen = new Random();
    public int reverseLevel, numLevels, valAtEndGame = 60;
    public double probability;
    public boolean opening = true, endGame = false, castled = false;
    public int minDepth = 1, maxDepth = 3, maxToSearch = 0, depth, minToBeAggressive = 9;

    public Strategy(Chessboard board, LegalMoves legalMoves, int reverseLevel, int numLevels, boolean testPlayer) {
        this.board = board;
        this.legalMoves = legalMoves;
        this.reverseLevel = reverseLevel;
        probability = 240.0 * (1.0 - Math.pow(1.2, (-2.0 * (reverseLevel - 1.0) / (numLevels - 2.0))));
        if (reverseLevel == 0) {
            minDepth = 3;
            maxDepth = 5;
            maxToSearch = 5;
        }
    }

    public Move chooseMove(ArrayList<Move> moveList) {
        int color = ((Move) moveList.get(0)).piece().color();
        if (opening)
            checkOpening(color);
        else if (!endGame)
            checkEndgame();
        int i = 0;
        if (reverseLevel > 1) {
            while (i < moveList.size() && moveList.size() > 3) {
                if (rGen.nextInt(100) < probability) {
                    moveList.remove(i);
                } else
                    i++;
            }
        }
        moveList = evaluate(moveList, color);
        if (moveList.size() > 1) {
            if (reverseLevel == 0 && !opening && moveList.size() <= maxToSearch) {
                if (endGame || moveList.size() < maxToSearch / 2) {
                    depth = maxDepth;
                } else {
                    depth = minDepth;
                }
                TreeNode root = new TreeNode(null, 0);
                buildTree(root, moveList, 1);
                traverseTree(root);
                moveList = bestMoves(root.children());
            }
            moveList = evalDevelopement(moveList, color);
        }
        return moveList.get(rGen.nextInt(moveList.size()));
    }

    public ArrayList<Move> evaluate(ArrayList<Move> moveList, int color) {
        Move aMove;
        // "playNext" && "oppNext" include defence of own pieces
        List<Move> playMoves, oppMoves, playNext;
        oppMoves = legalMoves.getMoveList((color + 1) % 2, true);
        int numOppKingMoves = evalPieceMoves(board.king[(color + 1) % 2], oppMoves);
        int numPlayKingMoves = evalPieceMoves(board.king[color], moveList);
        for (int i = 0; i < moveList.size(); i++) {
            aMove = moveList.get(i);
            int numPieceMoves = evalPieceMoves(aMove.piece(), moveList);
            board.move(aMove);
            oppMoves = legalMoves.getMoveList((color + 1) % 2, true);
            playMoves = legalMoves.getMoveList(color, true);
            aMove.setCheckingKing(legalMoves.inCheck(playMoves, (color + 1) % 2));
            if (oppMoves.size() == 0) {
                if (aMove.getCheckingKing()) {
                    moveList.clear();
                    aMove.setValue(1000);
                    moveList.add(aMove);
                }
            } else {
                aMove.setPlayKingMoves(evalPieceMoves(board.king[color], playMoves) - numPlayKingMoves);
                aMove.setOppKingMoves(evalPieceMoves(board.king[(color + 1) % 2], oppMoves) - numOppKingMoves);
                aMove.setMovingPieceMoves(evalPieceMoves(aMove.piece(), playMoves) - numPieceMoves);
                playNext = legalMoves.getMoveList(color, false);
                aMove.setValue(pieceDifference(color) + evalDefence(color, playNext, oppMoves));
                aMove.setRepeatedPosition(board.repeatedPosition(null));
                if (oppMoves.size() == 0 || aMove.getRepeatedPositions() == 3) {
                    if (aMove.value() < 0) {
                        moveList.clear();
                        aMove.setValue(500);
                        moveList.add(aMove);
                    } else if (moveList.size() > 1) {
                        moveList.remove(i);
                    } else {
                        aMove.setValue(-500);
                    }
                }
            }
            if (aMove.value() > minToBeAggressive && evalRepeat(aMove) == 0) {
                aMove.addValue(evalAttack(aMove));
            }
            board.moveBack(aMove);
        }

        return bestMoves(moveList);
    }

    public ArrayList<Move> evalDevelopement(ArrayList<Move> moveList, int color) {
        Move aMove;
        for (int i = 0; i < moveList.size(); i++) {
            aMove = moveList.get(i);
            if (opening) {
                aMove.addValue(evalOpening(aMove, color) + evalCenter(aMove, color));
            } else if (board.movesSinceCapture() > 45 && aMove.capturedPiece() != null && aMove.value() > 0
                    || (aMove.capturedPiece() != null && aMove.capturedPiece().piece() == 0
                            && evalAdvancingPawn((color + 1) % 2, aMove.piece().col()) > 1)) {
                moveList.clear();
                aMove.setValue(500);
                moveList.add(aMove);
            } else if (aMove.value() >= 0 && aMove.getRepeatedPositions() > 0) {
                aMove.addValue(-10);
            } else if (aMove.value() <= 0 && aMove.capturedPiece() != null) {
                aMove.addValue(-2);
            } else {
                if (evalRepeat(aMove) != 0) {
                    aMove.addValue(evalRepeat(aMove));
                } else {
                    aMove.addValue(evalAttack(aMove));
                    if (!endGame) {
                        aMove.addValue(evalMiddle(aMove, color));
                    } else {
                        aMove.addValue(evalEndGame(aMove, color));
                    }
                }
            }
        }
        return bestMoves(moveList);
    }

    public int evalEndGame(Move aMove, int aColor) {
        int value = 0;
        Piece piece = aMove.piece();
        int color = aColor;
        int oldRow = aMove.oldRow();
        int oldCol = aMove.oldCol();
        int newRow = aMove.newRow();
        int newCol = aMove.newCol();
        switch (piece.piece()) {
            case 0: // pawn
                value += evalPawnNeighbors(color, newRow) + evalAdvancingPawn(color, newRow);
                break;
            case 1: // knights
                value += Math.abs(oldRow - board.king[(color + 1) % 2].row())
                        + Math.abs(oldCol - board.king[(color + 1) % 2].col())
                        - (Math.abs(newRow - board.king[(color + 1) % 2].row())
                                + Math.abs(newCol - board.king[(color + 1) % 2].col()));
                break;
            case 2: // bishops
                value += evalCenter(aMove, color);
                break;
            case 3: // castles
                if (checkKnightFork(newRow, newCol, color, 4)) {
                    value -= 10;
                }
                break;
            case 4: // queen
                if (checkKnightFork(newRow, newCol, color, 3)) {
                    value -= 10;
                }
                break;
            case 5: // king
                value -= 1;
                if (checkKnightFork(newRow, newCol, color, 3)) {
                    value -= 10;
                }
                if (newRow == 0 && (newCol == 0 || newCol == 7) || newRow == 7 && (newCol == 0 || newCol == 7)) {
                    value -= 2;
                }
                if (newCol == oldCol + 1 - 2 * color) {
                    value += evalKingPawns(newRow, newCol, color) - evalKingPawns(oldRow, oldCol, color);
                }
                break;
        }
        return value;
    }

    public int evalKingPawns(int newRow, int newCol, int color) {
        int value = 0;
        for (int r = newRow - 1; r < newRow + 2; r++) {
            for (int c = newCol - 1; c < newCol + 2; c++) {
                if (r >= 0 && r < 8 && c >= 0 && c < 8) {
                    if (board.objectAt(r, c) != null && board.objectAt(r, c).color() == color
                            && board.objectAt(r, c).piece() == 0) {
                        value += 1;
                    }
                }
            }
        }
        return value;
    }

    public int evalMiddle(Move aMove, int aColor) {
        int value = 0;
        Piece piece = aMove.piece();
        int color = aColor;
        int oldRow = aMove.oldRow();
        int oldCol = aMove.oldCol();
        int newRow = aMove.newRow();
        int newCol = aMove.newCol();
        switch (piece.piece()) {
            case 0: // pawn
                value -= 1;
                if (oldCol == 1 + color * 5 && board.objectAt(oldRow, color * 7) != null
                        && board.objectAt(oldRow, color * 7).equals(board.king[color])) {
                    value -= 10;
                }
                if (newCol == 3 + color && (newRow == 3 || newRow == 4)) {
                    value += 5;
                } else if (evalPawnInFile(color, newRow, oldCol)) {
                    value -= 5;
                }
                break;
            case 1: // knights
                value += Math.abs(oldRow - board.king[(color + 1) % 2].row())
                        + Math.abs(oldCol - board.king[(color + 1) % 2].col())
                        - (Math.abs(newRow - board.king[(color + 1) % 2].row())
                                + Math.abs(newCol - board.king[(color + 1) % 2].col()));
                break;
            case 2: // bishops
                value += evalCenter(aMove, color);
                if (newCol == color * 7) {
                    value -= 10;
                }
                break;
            case 3: // castle
                value -= 2;
                if (oldCol == 7 * color && oldCol == newCol) {
                    if (oldRow == 3 || oldCol == 4 || !evalPawnInFile(color, oldRow, 7 * color)) {
                        value -= 1;
                    } else if (newRow == 3 || newRow == 4 || !evalPawnInFile(color, newRow, 7 * color)) {
                        value += 1;
                    } else {
                        value -= 1;
                    }
                } else if (newCol == 6 - 5 * color && board.king[(color + 1) % 2].col() == 7 * color
                        && Math.abs(board.king[(color + 1) % 2].row()) - newRow < 4) {
                    value += 1;
                }
                if (evalOpenKingFile(color, newRow) < 2) {
                    value += 2;
                }
                if (checkKnightFork(newRow, newCol, color, 4)) {
                    value -= 10;
                }
                break;
            case 4: // queen
                value += 2 * evalCenter(aMove, color) + aMove.getMovingPieceMoves();
                if (checkKnightFork(newRow, newCol, color, 3)) {
                    value -= 10;
                }
                break;
            case 5: // king
                /*
                 * if (newCol == oldCol + 1-2*color){
                 * value+=evalKingPawns(newRow,newCol,color)-evalKingPawns(oldRow,oldCol,color);
                 * }
                 */
                value += -5;
                if (checkKnightFork(newRow, newCol, color, 3)) {
                    value -= 10;
                }
                break;
        }
        if (piece.piece() < 3 || piece.piece() == 4) {
            value += evalCenter(aMove, color);
        }
        return value;
    }

    public int evalOpening(Move aMove, int aColor) {
        int value = 0;
        if (aMove.castleIndex() != -1) {
            value = 500;
        } else {
            Piece piece = aMove.piece();
            int color = aColor;
            int oldRow = aMove.oldRow();
            int oldCol = aMove.oldCol();
            int newRow = aMove.newRow();
            int newCol = aMove.newCol();
            switch (piece.piece()) {
                case 0: // pawn
                    if (newCol == 3 + color && (newRow == 3 || newRow == 4)) {
                        value += 6;
                    } else if (newCol == 2 + 3 * color && (newRow == 3 || newRow == 4)) {
                        value += 3;
                    } else if (oldRow == 0 || oldRow == 7) {
                        value -= 1;
                    } else if ((newRow == 2 && newCol == 2 + 3 * color && board.objectAt(1, 7 * color) != null
                            && board.objectAt(1, 7 * color).piece() == 1)
                            || (newRow == 5 && board.objectAt(6, 7 * color) != null
                                    && board.objectAt(6, 7 * color).piece() == 1)) {
                        value -= 3;
                    }
                    if (evalPawnInFile(color, newRow, oldCol)) {
                        value -= 5;
                    }
                    break;
                case 1: // knight
                    if (oldCol == 7 * color && newRow != 0 && newRow != 7) {
                        value += 4;
                    } else
                        value -= 1;
                    break;
                case 2: // bishop
                    if (oldCol == 7 * color) {
                        if (newCol == 4 - color || newCol == 1 + 5 * color && (newRow == 1 || newRow == 6))
                            value += 3;
                        else if (newCol == 3 + color || newCol == 2 + 3 * color) {
                            value += 2;
                        } else
                            value += 1;
                    } else
                        value -= 1;
                    break;
                case 3: // castle
                    value -= 1;
                    break;
                case 4: // queen
                    value += -5;
                    break;
                case 5: // king
                    value += -10;
                    break;
            }
        }
        return value;
    }

    public int evalAttack(Move aMove) {
        int value = 0;
        if (aMove.getCheckingKing()) {
            value += 1;
        }
        return value - aMove.getOppKingMoves();
    }

    public int evalRepeat(Move aMove) {
        Move lastMove = board.getLastMove();
        int value = 0;
        if (lastMove.piece().equals(aMove.piece())) {
            value -= 1;
            if (lastMove.oldCol() == aMove.newCol() && lastMove.oldRow() == aMove.newRow()) {
                value -= 1;
            }
        }
        return value;
    }

    /*
     * Returns evalPieces (playercolor) - evalPieces(oppColor) Seems that this could
     * be done in another method.
     */
    public int pieceDifference(int color) {
        return evalPieces(color) - evalPieces((color + 1) % 2);
    }

    /*
     * This method added to pieceDifference takes into account the potential trade
     * of pieces. One thing that is tweakable is the value of pieces. For example,
     * are two bishops as valuable as two knights? What's the value of a king?
     */
    public int evalDefence(int color, List<Move> playNext, List<Move> oppNext) {
        int valueAdded = 0;
        Move aMove;
        Iterator<Piece> iter = board.pieceList(color).iterator();
        while (iter.hasNext()) {
            Piece piece = (Piece) iter.next();
            ArrayList<Integer> attackerList = new ArrayList<Integer>();
            for (int i = 0; i < oppNext.size(); i++) {
                aMove = (Move) oppNext.get(i);
                if (aMove.newRow() == piece.row() && aMove.newCol() == piece.col()) {
                    attackerList.add(new Integer(aMove.piece().value()));
                }
            }
            if (attackerList.size() > 0) {
                ArrayList<Integer> defenderList = new ArrayList<Integer>();
                for (int i = 0; i < playNext.size(); i++) {
                    aMove = (Move) playNext.get(i);
                    if (aMove.newRow() == piece.row() && aMove.newCol() == piece.col()) {
                        defenderList.add(new Integer(aMove.piece().value()));
                    }
                }
                Iterator<Integer> iterA = insertionSort(attackerList).iterator();
                Iterator<Integer> iterD = insertionSort(defenderList).iterator();
                ArrayList<Integer> mergedList = new ArrayList<Integer>();
                mergedList.add(new Integer(piece.value()));
                while (iterA.hasNext() && mergedList.size() % 2 == 1) {
                    mergedList.add(iterA.next());
                    if (iterD.hasNext()) {
                        mergedList.add(iterD.next());
                    }
                }
                int i = 0;
                while (i + 1 < mergedList.size()
                        && (((Integer) mergedList.get(i)).intValue() >= ((Integer) mergedList.get(i + 1)).intValue()
                                || mergedList.size() == i + 2)) {
                    valueAdded += (2 * (i % 2) - 1) * ((Integer) mergedList.get(i)).intValue();
                    i++;
                }
            }
        }
        return valueAdded;
    }

    public int evalPawnNeighbors(int color, int row) {
        int numNeighbors = 0;
        if (row - 1 >= 0 && evalPawnInFile(color, row - 1, color * 7)) {
            numNeighbors += 1;
        }
        if (row + 1 <= 7 && evalPawnInFile(color, row + 1, color * 7)) {
            numNeighbors += 1;
        }
        return numNeighbors;
    }

    /*
     * Returns increasingly larger numbers (up to 3) as a pawn advances. Returns 100
     * if the row is the final row;
     */
    public int evalAdvancingPawn(int color, int col) {
        int value = 0;
        if (col > 3 - 3 * color && col < 7 - 3 * color) {
            value = Math.abs(col - 7 * color) - 3;
        } else if (col == 7 - 7 * color) {
            value = 100;
        }
        return value;
    }

    // Returns true if none of players own pawns are in the row.
    public boolean evalPawnInFile(int color, int row, int ignoreCol) {
        boolean pawnInFile = false;
        int col = 1;
        while (col < 7 && pawnInFile == false) {
            if (col != ignoreCol && board.objectAt(row, col) != null && board.objectAt(row, col).color() == color
                    && board.objectAt(row, col).piece() == 0) {
                pawnInFile = true;
            }
            col++;
        }
        return pawnInFile;
    }

    // returns # pieces in king file

    public int evalOpenKingFile(int color, int row) {
        int numPieces = 0;
        for (int col = 0; col < 8; col++) {
            if (board.objectAt(row, col) != null) {
                numPieces++;
            }
        }
        return numPieces;
    }

    public boolean checkKnightFork(int row, int col, int color, int minValue) {
        boolean forkable = false;
        if (board.checkIfPiece(1, color) && (board.getPieceValue(row, col + 2, color) >= minValue
                || board.getPieceValue(row, col - 2, color) >= minValue
                || board.getPieceValue(row, col + 4, color) >= minValue
                || board.getPieceValue(row, col - 4, color) >= minValue
                || board.getPieceValue(row + 1, col + 1, color) >= minValue
                || board.getPieceValue(row + 1, col - 1, color) >= minValue
                || board.getPieceValue(row - 1, col + 1, color) >= minValue
                || board.getPieceValue(row - 1, col - 1, color) >= minValue
                || board.getPieceValue(row + 1, col + 3, color) >= minValue
                || board.getPieceValue(row + 1, col - 3, color) >= minValue
                || board.getPieceValue(row - 1, col + 3, color) >= minValue
                || board.getPieceValue(row - 1, col - 3, color) >= minValue
                || board.getPieceValue(row + 2, col, color) >= minValue
                || board.getPieceValue(row - 2, col, color) >= minValue
                || board.getPieceValue(row + 2, col + 4, color) >= minValue
                || board.getPieceValue(row + 2, col - 4, color) >= minValue
                || board.getPieceValue(row - 2, col + 4, color) >= minValue
                || board.getPieceValue(row - 2, col - 4, color) >= minValue
                || board.getPieceValue(row + 3, col + 1, color) >= minValue
                || board.getPieceValue(row + 3, col - 1, color) >= minValue
                || board.getPieceValue(row - 3, col + 1, color) >= minValue
                || board.getPieceValue(row - 3, col - 1, color) >= minValue
                || board.getPieceValue(row + 3, col + 3, color) >= minValue
                || board.getPieceValue(row + 3, col - 3, color) >= minValue
                || board.getPieceValue(row - 3, col + 3, color) >= minValue
                || board.getPieceValue(row - 3, col - 3, color) >= minValue
                || board.getPieceValue(row + 4, col, color) >= minValue
                || board.getPieceValue(row - 4, col, color) >= minValue
                || board.getPieceValue(row + 4, col + 2, color) >= minValue
                || board.getPieceValue(row + 4, col - 2, color) >= minValue
                || board.getPieceValue(row - 4, col + 2, color) >= minValue
                || board.getPieceValue(row - 4, col - 2, color) >= minValue)) {
            forkable = true;
        }
        return forkable;
    }

    // returns 1,0 or -1 depending on whether it is moving toward, -- , or away from
    // center

    public int evalCenter(Move aMove, int color) {
        int oldRow = aMove.oldRow();
        int oldCol = aMove.oldCol();
        int newRow = aMove.newRow();
        int newCol = aMove.newCol();
        int change = Math.abs(2 * newRow - 7) + Math.abs(newCol - 4 + color)
                - (Math.abs(2 * oldRow - 7) + Math.abs(oldCol - 4 + color));
        if (change < 0)
            return 1;
        else if (change == 0)
            return 0;
        else
            return -1;
    }

    // returns the number of moves a piece has

    public int evalPieceMoves(Piece piece, List<Move> nextMoves) {
        int pieceMoves = 0;
        for (int i = 0; i < nextMoves.size(); i++) {
            if (((Move) nextMoves.get(i)).piece() == piece) {
                pieceMoves++;
            }
        }
        return pieceMoves;
    }

    /*
     * Adds up the value of all the pieces of a color.
     */
    public int evalPieces(int color) {
        int valueAdded = 0;
        Iterator<Piece> iter = board.pieceList(color).iterator();
        while (iter.hasNext()) {
            valueAdded += iter.next().value();
        }
        return valueAdded;
    }

    /*
     * Determines whether it's still the opening checking whether the player has
     * castled & the and bishops knigh
     *
     * This can definitely be improved. Returns boolean "false" if it determines
     * that it is no longer the opening.
     */
    public void checkOpening(int color) {
        if ((board.objectAt(2, 7 * color) == null || board.objectAt(2, 7 * color).piece() != 2)
                && (board.objectAt(5, 7 * color) == null || board.objectAt(5, 7 * color).piece() != 2)
                && (board.objectAt(1, 7 * color) == null || board.objectAt(1, 7 * color).piece() != 1)
                && (board.objectAt(6, 7 * color) == null || board.objectAt(6, 7 * color).piece() != 1)) {
            opening = false;
        }
    }

    /*
     * Determines whether it's end game by counting the value of the pieces on the
     * board. This can definitely be improved. Returns boolean "true" if it
     * determines that it is the endgame.
     */

    public void checkEndgame() {
        if (evalPieces(0) + evalPieces(1) < valAtEndGame) {
            endGame = true;
        }
    }

    /*
     * Takes list and orders it in order of increasing value.
     */

    public ArrayList<Integer> insertionSort(ArrayList<Integer> list) {
        int temp, index;
        for (int i = 1; i < list.size(); i++) {
            temp = ((Integer) list.get(i)).intValue();
            index = i;
            while (index > 0 && temp < ((Integer) list.get(index - 1)).intValue()) {
                list.set(index, list.get(index - 1));
                index--;
            }
            list.set(index, new Integer(temp));
        }
        return list;
    }

    /*
     * This finds the extreme value of the moveList (either the largest or
     * smallest). If max = true, it finds the max, of course.
     */
    public int findExtreme(List<Move> moveList, boolean max) {
        int extreme = moveList.get(0).value();
        for (int i = 1; i < moveList.size(); i++) {
            if (max && moveList.get(i).value() > extreme || !max && moveList.get(i).value() < extreme) {
                extreme = moveList.get(i).value();
            }
        }
        return extreme;
    }

    /*
     * Eliminates from moveList moves that aren't valued the greatest within the
     * given width Is sent the max.
     */

    public ArrayList<Move> bestMoves(ArrayList<Move> moveList) {
        int i = 0;
        Move aMove;
        int max = findExtreme(moveList, true);
        while (i < moveList.size()) {
            aMove = moveList.get(i);
            if (aMove.value() < max) {
                moveList.remove(i);
            } else {
                i++;
            }
        }
        return moveList;
    }

    public void traverseTree(TreeNode node) {
        for (int i = 0; i < node.numChildren(); i++) {
            if (node.child(i).numChildren() > 0) {
                traverseTree(node.child(i));
            }
        }
        if (node.level() != 0) {
            int extreme = findExtreme(node.children(), node.level() % 2 == 0);
            node.value().setValue(extreme);
        }
    }

    public void buildTree(TreeNode node, ArrayList<Move> moveList, int level) {
        Move aMove = moveList.get(0);
        int color = aMove.piece().color();
        TreeNode child;
        ArrayList<Move> newList;
        for (int i = 0; i < moveList.size(); i++) {
            aMove = moveList.get(i);
            child = new TreeNode(aMove, level);
            node.setChild(child);
            newList = new ArrayList<Move>();
            if (level < depth) {
                board.move(aMove);
                newList = legalMoves.getMoveList((color + 1) % 2, true);
                if (newList.size() == 0) {
                    if (level % 2 == 0) {
                        aMove.setValue(-1 * aMove.value());
                    }
                } else {
                    newList = evaluate(newList, (color + 1) % 2);
                    buildTree(child, newList, level + 1);
                }
                board.moveBack(aMove);
            }
        }
    }
}
