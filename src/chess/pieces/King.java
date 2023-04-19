package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

//REI
public class King extends ChessPiece {

  private ChessMatch chessMatch;

  public King(Board board, Color color, ChessMatch chessMatch) {
    super(board, color);
    this.chessMatch = chessMatch;
  }

  @Override
  public String toString() {
    return "K";
  }

  private boolean canMove(Position position) {
    ChessPiece chessPiece = (ChessPiece) getBoard().piece(position);
    return chessPiece == null || chessPiece.getColor() != getColor();
  }

  private boolean testRookCastling(Position position) {
    ChessPiece chessPiece = (ChessPiece) getBoard().piece(position);
    return chessPiece != null
        && chessPiece instanceof Rook
        && chessPiece.getColor() == getColor()
        && chessPiece.getMoveCount() == 0;
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] possibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position p = new Position(0, 0);

    // above
    p.setValues(position.getRow() - 1, position.getColumn());
    if (getBoard().positionExists(p) && canMove(p)) {
      possibleMoves[p.getRow()][p.getColumn()] = true;
    }

    // below
    p.setValues(position.getRow() + 1, position.getColumn());
    if (getBoard().positionExists(p) && canMove(p)) {
      possibleMoves[p.getRow()][p.getColumn()] = true;
    }

    // left
    p.setValues(position.getRow(), position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      possibleMoves[p.getRow()][p.getColumn()] = true;
    }

    // right
    p.setValues(position.getRow(), position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      possibleMoves[p.getRow()][p.getColumn()] = true;
    }

    // north west
    p.setValues(position.getRow() - 1, position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      possibleMoves[p.getRow()][p.getColumn()] = true;
    }

    // north east
    p.setValues(position.getRow() - 1, position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      possibleMoves[p.getRow()][p.getColumn()] = true;
    }

    // south west
    p.setValues(position.getRow() + 1, position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      possibleMoves[p.getRow()][p.getColumn()] = true;
    }

    // south east
    p.setValues(position.getRow() + 1, position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      possibleMoves[p.getRow()][p.getColumn()] = true;
    }

    // special move castling
    if (getMoveCount() == 0 && !chessMatch.isCheck()) {
      // special move castling king side rook
      Position rook1Position = new Position(position.getRow(), position.getColumn() + 3);
      if (testRookCastling(rook1Position)) {
        Position p1 = new Position(position.getRow(), position.getColumn() + 1);
        Position p2 = new Position(position.getRow(), position.getColumn() + 2);

        if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
          possibleMoves[position.getRow()][position.getColumn() + 2] = true;
        }
      }

      // special move castling queen side rook
      Position rook2Position = new Position(position.getRow(), position.getColumn() - 4);
      if (testRookCastling(rook2Position)) {
        Position p1 = new Position(position.getRow(), position.getColumn() - 1);
        Position p2 = new Position(position.getRow(), position.getColumn() - 2);
        Position p3 = new Position(position.getRow(), position.getColumn() - 3);

        if (getBoard().piece(p1) == null
            && getBoard().piece(p2) == null
            && getBoard().piece(p3) == null) {
          possibleMoves[position.getRow()][position.getColumn() - 2] = true;
        }
      }
    }

    return possibleMoves;
  }
}
