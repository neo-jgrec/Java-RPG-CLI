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

    public void performAction(Player player, Class<? extends Item> itemClass, String action) {
        if (inventory.contains_class(itemClass)) {
            System.out.println("Choose an item:");
            inventory.print_class(itemClass);
            System.out.print("> ");

            String input = new Scanner(System.in).nextLine();
            for (Item item : inventory.getItems()) {
                if (item != null && item.getName().equals(input) && itemClass.isInstance(item)) {
                    if (action.equals("attack") && item instanceof Sword) {
                        Sword sword = (Sword) item;
                        player.takeDamage(sword.getDamage());
                        System.out.println(player.getName() + " took " + sword.getDamage() + " damage");
                    } else if (action.equals("heal") && item instanceof Potion) {
                        Potion potion = (Potion) item;
                        pv += potion.getHeal();
                        System.out.println("You healed " + potion.getHeal() + " pv");
                        inventory.remove(potion.getName());
                    }
                    return;
                }
            }
            System.out.println("Item not found");
            performAction(player, itemClass, action);
        } else {
            if (action.equals("attack")) {
                System.out.println("No sword in inventory");
            } else if (action.equals("heal")) {
                System.out.println("No potion in inventory");
            }
        }
    }
    public void attack(Player player) {
        performAction(player, Sword.class, "attack");
    }

    public void heal() {
        performAction(this, Potion.class, "heal");
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
