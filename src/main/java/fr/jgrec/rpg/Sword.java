package fr.jgrec.rpg;

public class Sword extends Item implements Weapon {
    private final int damage;

    public Sword(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    @Override
    public int getDamage() {
        return damage;
    }
}
