package fr.jgrec.rpg;

import java.util.Scanner;

public class Player {
    private final String name;
    private int pv;
    private final Inventory inventory;

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
        if (inventory.contains_class(Sword.class)) {

            System.out.println("Choose a sword:");
            inventory.print_class(Sword.class);
            System.out.print("> ");

            String input = new Scanner(System.in).nextLine();
            for (Item item : inventory.getItems()) {
                if (item != null && item.getName().equals(input) && item instanceof Sword) {
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

    public void heal() {
        if (inventory.contains_class(Potion.class)) {

            System.out.println("Choose a potion:");
            inventory.print_class(Potion.class);
            System.out.print("> ");

            String input = new Scanner(System.in).nextLine();
            for (Item item : inventory.getItems()) {
                if (item != null && item.getName().equals(input) && item instanceof Potion) {
                    Potion potion = (Potion) item;
                    pv += potion.getHeal();
                    System.out.println("You healed " + potion.getHeal() + " pv");
                    inventory.remove(potion.getName());
                    return;
                }
            }
            System.out.println("Potion not found");
            heal();
        } else
            System.out.println("No potion in inventory");
    }

    public void searchItemAction() {
        int random = (int) (Math.random() * 3);

        if (random == 0) {
            System.out.println("You found a sword");
            inventory.add(new Sword("Sword", 10));
        } else if (random == 1) {
            System.out.println("You found a potion");
            inventory.add(new Potion("Potion", 10));
        } else
            System.out.println("You found nothing");
    }
}
