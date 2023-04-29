package fr.jgrec.rpg;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Player1", 100);
        player1.getInventory().add(new Sword("Excalibur", 5));
        player1.getInventory().add(new Sword("oui", 105));
        Player player2 = new Player("Player2", 100);

        Player currentPlayer = player1;

        for (;;) {
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
            System.out.print("> ");

            String input = new Scanner(System.in).nextLine();
            if (input.equals("exit"))
                break;
            else if (input.equals("attack")) {
                currentPlayer.attack(currentPlayer == player1 ? player2 : player1);
                currentPlayer = currentPlayer == player1 ? player2 : player1;
            } else if (input.equals("print"))
                player1.getInventory().print();

            System.out.println("--------------------");
        }
    }
}
