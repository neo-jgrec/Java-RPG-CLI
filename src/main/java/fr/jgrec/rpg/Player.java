package fr.jgrec.rpg;

import java.util.Scanner;

public class Player {
    private final String name;
    private int pv;
    private Inventory inventory;

    public Player(String name, int pv) {
        this.name = name;
        this.pv = pv;
        inventory = new Inventory(10);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getPv() {
        return pv;
    }

    public String getName() {
        return name;
    }

    public void takeDamage(int damage) {
        pv -= damage;
    }

    public void attack(Player player) {
        if (inventory.contains(Sword.class)) {

            System.out.println("Choose a sword:");
            inventory.print_class(Sword.class);
            System.out.print("> ");

            String input = new Scanner(System.in).nextLine();
            for (Item item : inventory.getItems()) {
                if (item != null && item.getName().equals(input)) {
                    Sword sword = (Sword) item;
                    player.takeDamage(sword.getDamage());
                    System.out.println(player.getName() + " took " + sword.getDamage() + " damage");
                    return;
                }
            }
            System.out.println("Sword not found");
            attack(player);
        } else
            System.out.println("No sword in inventory");
    }
}
