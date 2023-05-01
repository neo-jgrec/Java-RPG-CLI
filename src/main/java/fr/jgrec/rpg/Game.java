package fr.jgrec.rpg;
import java.util.Scanner;

public class Game {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    public Game(String player1Name, String player2Name, int player1Pv, int player2Pv) {
        player1 = new Player(player1Name, player1Pv);
        player2 = new Player(player2Name, player2Pv);
        player1.getInventory().add(new Sword("Sword1", 10));
        player2.getInventory().add(new Sword("Sword2", 30));
        player2.getInventory().add(new Potion("Potion1", 10));
        currentPlayer = player1;
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

            System.out.println("Choose an action:");
            System.out.println("1. Attack (attack), 2. Heal (heal), 3. Search for an item (search), 4. Print inventory (print), 5. Quit (exit)");
            switch (new Scanner(System.in).nextLine()) {
                case "exit":
                    return;
                case "attack":
                    currentPlayer.attack(currentPlayer == player1 ? player2 : player1);
                    currentPlayer = currentPlayer == player1 ? player2 : player1;
                    break;
                case "print":
                    currentPlayer.getInventory().print();
                    break;
                case "heal":
                    currentPlayer.heal();
                    currentPlayer = currentPlayer == player1 ? player2 : player1;
                    break;
                case "search":
                    currentPlayer.searchItemAction();
                    currentPlayer = currentPlayer == player1 ? player2 : player1;
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
            System.out.println("--------------------");
        }
    }
}
