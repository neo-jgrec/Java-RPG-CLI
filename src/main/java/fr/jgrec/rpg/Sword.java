package fr.jgrec.rpg;

public class Sword extends Item {
    private final int damage;

    public Sword(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
