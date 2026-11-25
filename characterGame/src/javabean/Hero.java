package javabean;

import java.util.ArrayList;

public class Hero extends character {
  private int remainTurn=0;
   ArrayList<String> skillslist ;
    public Hero() {
        super();
       skillslist = new ArrayList<>();
    }
    public Hero(int HP, int defense, String name, int attract) {
        super(HP, defense, name, attract);
         skillslist = new ArrayList<>();
    }
    public void showSkillslist() {
        for (int i = 0; i < skillslist.size(); i++) {
            System.out.println(skillslist.get(i));
        }
    }
    public ArrayList<String> getSkillslist() {
        return skillslist;
    }

    public int getRemainTurn() {
        return remainTurn;
    }

    public void setRemainTurn(int remainTurn) {

        if (remainTurn < 0) {
            remainTurn = 0;
        }
        this.remainTurn = remainTurn;
    }
}


