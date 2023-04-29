package fr.jgrec.rpg;

public class Inventory {
    private final Item[] items;

    public Inventory(int nbSlots) {
        items = new Item[nbSlots];
    }

    public int getAvailableSlots() {
        int nbSlots = 0;
        for (Item item : items) {
            if (item == null)
                nbSlots++;
        }
        return nbSlots;
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
        if (getAvailableSlots() == 0) {
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

    public boolean contains(Class<? extends Item> itemClass) {
        for (Item item : items) {
            if (item != null && item.getClass() == itemClass)
                return true;
        }
        return false;
    }

    // get inventory items
    public Item[] getItems() {
        return items;
    }
}
