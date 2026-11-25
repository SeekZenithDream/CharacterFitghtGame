package javabean;

import java.util.ArrayList;

public class enemy extends character {
    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    boolean defending=false;
    ArrayList<String> skillslist;
    public enemy() {
        super();

        ArrayList<String> skillslist=new ArrayList<>();
    }
    public enemy(int HP, int defense, String name, int attract) {
        super(HP, defense, name, attract);
        skillslist=new ArrayList<>();
    }

    public ArrayList<String> getSkillslist() {
        return skillslist;
    }

    public void setSkillslist(ArrayList<String> skillslist) {
        this.skillslist = skillslist;
    }

    @Override
    public int takeDamage(int damage) {
       int hurt=damage;
        if(defending){
            hurt=damage/2>1?damage/2:1;
            defending=false;
        }
        super.takeDamage(hurt);
        return hurt;
    }
    @Override
    public int getdamage(int damage) {

        if (defending) {
            damage = damage/2;
            defending=false;
        }
        return damage;
    }

}
