package fr.jgrec.rpg;
import java.util.Scanner;

public class Game {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private enum inputType {
        ATTACK, INVENTORY, QUIT, UNKNOWN
    }
    public Game(String player1Name, String player2Name, int player1Pv, int player2Pv) {
        player1 = new Player(player1Name, player1Pv);
        player2 = new Player(player2Name, player2Pv);
        currentPlayer = player1;
    }

    private inputType getInput() {
        System.out.print("> ");
        String input = new Scanner(System.in).nextLine();
        switch (input) {
            case "exit":
                return inputType.QUIT;
            case "attack":
                return inputType.ATTACK;
            case "print":
                return inputType.INVENTORY;
            default:
                return inputType.UNKNOWN;
        }
    }

    public void start() {
        while (true) {
            if (currentPlayer.getPv() <= 0) {
                System.out.println(currentPlayer.getName() + " is dead");
                currentPlayer = currentPlayer == player1 ? player2 : player1;
                System.out.println(currentPlayer.getName() + " won");
                break;
            }

            System.out.println("Current player: " + currentPlayer.getName());
            System.out.println("Player1 pv: " + player1.getPv());
            System.out.println("Player2 pv: " + player2.getPv());
            System.out.println(currentPlayer.getName() + "'s turn");

            switch (getInput()) {
                case QUIT:
                    return;
                case ATTACK:
                    currentPlayer.attack(currentPlayer == player1 ? player2 : player1);
                    currentPlayer = currentPlayer == player1 ? player2 : player1;
                    break;
                case INVENTORY:
                    player1.getInventory().print();
                    break;
                default:
                    System.out.println("Unknown command");
            }

            System.out.println("--------------------");
        }
    }
}
