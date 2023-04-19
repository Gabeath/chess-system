package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

//PEÃO
public class Pawn extends ChessPiece {

  private ChessMatch chessMatch;

  public Pawn(Board board, Color color, ChessMatch chessMatch) {
    super(board, color);
    this.chessMatch = chessMatch;
  }

  @Override
  public String toString() {
    return "P";
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] possibleMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position p = new Position(0, 0);

    if (getColor() == Color.WHITE) {
      // above
      p.setValues(position.getRow() - 1, position.getColumn());
      if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
        possibleMoves[p.getRow()][p.getColumn()] = true;
      }

      // double above
      p.setValues(position.getRow() - 2, position.getColumn());
      Position aux = new Position(position.getRow() - 1, position.getColumn());
      if (getBoard().positionExists(p)
          && !getBoard().thereIsAPiece(p)
          && getBoard().positionExists(aux)
          && !getBoard().thereIsAPiece(aux)
          && getMoveCount() == 0) {
        possibleMoves[p.getRow()][p.getColumn()] = true;
      }

      // north west
      p.setValues(position.getRow() - 1, position.getColumn() - 1);
      if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        possibleMoves[p.getRow()][p.getColumn()] = true;
      }

      // north east
      p.setValues(position.getRow() - 1, position.getColumn() + 1);
      if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        possibleMoves[p.getRow()][p.getColumn()] = true;
      }

      // special move en passant white
      if (position.getRow() == 3) {
        Position left = new Position(position.getRow(), position.getColumn() - 1);

        if (getBoard().positionExists(left)
            && isThereOpponentPiece(left)
            && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
          possibleMoves[left.getRow() - 1][left.getColumn()] = true;
        }

        Position right = new Position(position.getRow(), position.getColumn() + 1);

        if (getBoard().positionExists(right)
            && isThereOpponentPiece(right)
            && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
          possibleMoves[right.getRow() - 1][right.getColumn()] = true;
        }
      }
    } else {
      // below
      p.setValues(position.getRow() + 1, position.getColumn());
      if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
        possibleMoves[p.getRow()][p.getColumn()] = true;
      }

      // double below
      p.setValues(position.getRow() + 2, position.getColumn());
      Position aux = new Position(position.getRow() + 1, position.getColumn());
      if (getBoard().positionExists(p)
          && !getBoard().thereIsAPiece(p)
          && getBoard().positionExists(aux)
          && !getBoard().thereIsAPiece(aux)
          && getMoveCount() == 0) {
        possibleMoves[p.getRow()][p.getColumn()] = true;
      }

      // south west
      p.setValues(position.getRow() + 1, position.getColumn() - 1);
      if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        possibleMoves[p.getRow()][p.getColumn()] = true;
      }

      // south east
      p.setValues(position.getRow() + 1, position.getColumn() + 1);
      if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        possibleMoves[p.getRow()][p.getColumn()] = true;
      }

      // special move en passant black
      if (position.getRow() == 4) {
        Position left = new Position(position.getRow(), position.getColumn() - 1);

        if (getBoard().positionExists(left)
            && isThereOpponentPiece(left)
            && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
          possibleMoves[left.getRow() + 1][left.getColumn()] = true;
        }

        Position right = new Position(position.getRow(), position.getColumn() + 1);

        if (getBoard().positionExists(right)
            && isThereOpponentPiece(right)
            && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
          possibleMoves[right.getRow() + 1][right.getColumn()] = true;
        }
      }
    }

    return possibleMoves;
  }

}
