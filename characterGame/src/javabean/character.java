package javabean;

public class character {
    private int HP;
    private int MaxHp;
    private int defense;
    private String name;
    private int attract;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        if (HP >getMaxHp()) {
            HP = getMaxHp();
        }
        this.HP = HP;
    }

    public int getMaxHp() {
        return MaxHp;
    }

    public void setMaxHp(int maxHp) {
        MaxHp = maxHp;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttract() {
        return attract;
    }

    public void setAttract(int attract) {
        this.attract = attract;
    }

    public character() {
    }

    public character(int HP, int defense, String name, int attract) {
        this.HP = HP;
        MaxHp = HP;
        this.defense = defense;
        this.name = name;
        this.attract = attract;
    }

    public void heal(int h) {
        HP += h;
        if (HP > MaxHp) {
            HP = MaxHp;
        }
    }

    public boolean Alive() {
        return HP > 0;
    }

    public int getdamage(int hurt) {
        int damage = hurt - defense;
        if (damage < 0) {
            damage = 0;
        } else if (damage > 0 & damage < 1) {
            damage = 1;
        }
        return damage;
    }

    public int takeDamage(int hurt) {
        int damage = hurt - defense;
        if (damage < 0) {
            damage = 0;
        } else if (damage > 0 & damage < 1) {
            damage = 1;
        }
        HP = HP - damage;
        if (HP < 0) {
            HP = 0;
        }
        return damage;
    }
}

