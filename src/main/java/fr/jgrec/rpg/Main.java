package fr.jgrec.rpg;

public class Main {
    public static void main(String[] args) {
        Game game = new Game("Player1", "Player2", 100, 100);
        game.start();
    }
}
