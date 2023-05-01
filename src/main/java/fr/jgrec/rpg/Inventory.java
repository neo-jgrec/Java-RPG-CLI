package fr.jgrec.rpg;

public class Inventory {
    private final Item[] items;
    private final int nbSlots;

    public Inventory(int nbSlots) {
        this.nbSlots = nbSlots;
        this.items = new Item[nbSlots];
    }

    public int getNbItems() {
        int nbItems = 0;
        for (Item item : items) {
            if (item != null)
                nbItems++;
        }
        return nbItems;
    }

    public void add(Item item) {
        if (this.getNbItems() == nbSlots) {
            System.out.println("Inventory is full");
            return;
        }
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                break;
            }
        }
    }

    public void remove(String name) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getName().equals(name)) {
                items[i] = null;
                System.out.println(name + " removed from inventory");
                return;
            }
        }
        System.out.println("Item not found in inventory");
    }

    public void print() {
        System.out.print("Inventory: [");
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.print(items[i].getName());
                if (i != this.getNbItems() - 1)
                    System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public boolean contains_class(Class<? extends Item> itemClass) {
        for (Item item : items) {
            if (itemClass.isInstance(item))
                return true;
        }
        return false;
    }

    public Item[] getItems() {
        return items;
    }

    public void print_class(Class<? extends Item> itemClass) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && itemClass.isInstance(items[i])) {
                System.out.print(items[i].getName() + " ");
            }
        }
        System.out.println();
    }
}
