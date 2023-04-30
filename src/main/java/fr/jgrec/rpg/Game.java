package fr.jgrec.rpg;
import java.util.Scanner;

public class Game {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private enum inputType {
        ATTACK, INVENTORY, QUIT, SEARCH, HEAL, UNKNOWN
    }

    public Game(String player1Name, String player2Name, int player1Pv, int player2Pv) {
        player1 = new Player(player1Name, player1Pv);
        player2 = new Player(player2Name, player2Pv);
        player1.getInventory().add(new Sword("Sword1", 10));
        player1.getInventory().add(new Item("Item1"));
        player2.getInventory().add(new Sword("Sword2", 30));
        player2.getInventory().add(new Item("Item11"));
        player2.getInventory().add(new Potion("Potion1", 10));
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
            case "search":
                return inputType.SEARCH;
            case "heal":
                return inputType.HEAL;
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
                    currentPlayer.getInventory().print();
                    break;
                case HEAL:
                    currentPlayer.heal();
                    currentPlayer = currentPlayer == player1 ? player2 : player1;
                    break;
                case SEARCH:
                    currentPlayer.searchItemAction();
                    currentPlayer = currentPlayer == player1 ? player2 : player1;
                    break;
                default:
                    System.out.println("Unknown command");
            }

            System.out.println("--------------------");
        }
    }
}
