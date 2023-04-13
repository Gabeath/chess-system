import java.util.Scanner;

import boardgame.Position;

public class App {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Hello, World!");
    Position position = new Position(3, 5);
    System.out.println(position);

    scanner.close();
  }
}
