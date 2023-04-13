import java.util.Scanner;

import application.UI;
import chess.ChessMatch;

public class App {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);

    ChessMatch chessMatch = new ChessMatch();
    UI.printBoard(chessMatch.getPieces());

    scanner.close();
  }
}
