package fr.jgrec.rpg;

public class Potion extends Item {
    private final int heal;

    public Potion(String name, int heal) {
        super(name);
        this.heal = heal;
    }

    public int getHeal() {
        return heal;
    }
}
