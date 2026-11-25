package ui;

import javabean.Hero;
import javabean.enemy;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FightGame {
    static void main() {
        String s1 = "pangda";
        FightGame fightgame = new FightGame();
        fightgame.startGame(s1);
    }

    public void startGame(String username) {
        System.out.println(username + "欢迎来到这个游戏");
        System.out.println("请选择您的加点");
        incase(username);


    }

    public void incase(String username) {
        int remain = 20;
        Scanner sc = new Scanner(System.in);
        System.out.println("1.生命值(每点属性提升10点生命)");
        System.out.println("2.攻击力(每点属性提升2点攻击力)");
        System.out.println("3.防御力(每点属性提升1点防御力)");
        String[] a = {"生命值", "攻击力", "防御力"};
        int[] value = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.println("分配点数到" + a[i] + "(剩余点数为" + remain + ")");
            System.out.println("请输入你要分配的点数");
            int input = sc.nextInt();
            if (input > remain) {
                System.out.println("属性点不足请重新分配");
            } else if (input < 0) {
                System.out.println("加点不能为负请重新输入");
            } else {
                value[i] = input;
                System.out.println("加点成功");
                remain = remain - input;
            }
        }

        Hero h = new Hero(100 + 10 * value[0], 0 + value[2], username, 10 + value[1]);
        String skill1 = "普通攻击";
        h.getSkillslist().add(skill1);
        h.getSkillslist().add("振奋");
        h.getSkillslist().add("狂怒");
        show(h, username);
        ArrayList<enemy> el = new ArrayList<>();
        enemy e = new enemy(60, 0, "刺客", 20);
        enemy m = new enemy(80, 0, "法师", 15);
        enemy n = new enemy(120, 0, "坦克", 5);
        enemy b = new enemy(100, 0, "战士", 10);
        el.add(e);
        el.add(m);
        el.add(n);
        el.add(b);
        e.getSkillslist().add("刺杀");
        m.getSkillslist().add("火球术");
        n.getSkillslist().add("防御");
        b.getSkillslist().add("猛击");
        Random rand = new Random();
        int count = 1;
        int wins = 0;
        int damage = 0;
        int  round=0;
        while (h.Alive()) {

            int damage1 = 0;

            int r = rand.nextInt(el.size());
            enemy e1 = el.get(r);
            if (wins != 0) {

                e1.setMaxHp(e1.getMaxHp() + wins * 10);
                e1.setHP(e1.getMaxHp());
                e1.setAttract(e1.getAttract() + wins * 10);
            }
            System.out.println("═══════════════════════════════════════");
            System.out.println("⚔\uFE0F 第 " + count + " 场战斗开始！对手: " + e1.getName());
            while (h.Alive()) {

                round++;
                System.out.println("⚔\uFE0F 第 " + round + " 回合战斗开始！ ");
                getHealBar(h.getName(), h.getHP(), h.getMaxHp());
                System.out.print("敌人");
                getHealBar(e1.getName(), e1.getHP(), e1.getMaxHp());
                System.out.println("═══════════════════════════════════════");
                System.out.println("你的回合");
                System.out.println("请选择您的行动");
                System.out.println("1.普通攻击");
                System.out.println("2.振奋");
                System.out.println("3.狂怒");
                String choose = sc.next();
                String act;
                switch (choose) {
                    default:
                        System.out.println("不在选择范围内，自动发动普通攻击");
                    case "1":
                        act = "普通攻击";
                        damage = h.getAttract();
                        break;
                    case "2":
                        act = "振奋";
                        h.setRemainTurn(5);
                        damage=0;
                        break;
                    case "3":
                        act = "狂怒";
                        damage = h.getAttract() ;
                        h.setMaxHp(h.getMaxHp() - 10);
                        h.setHP(h.getHP() + damage * 2);

                        break;
                }
                h.setRemainTurn(h.getRemainTurn() - 1);
                if (h.getRemainTurn() != 0) {
                    damage = 2 * damage;
                }
                System.out.println("你对" + e1.getName() + "发动了\"" + act + "\"造成了" + e1.getdamage(e1.takeDamage(damage)) + "点伤害");
                e1.takeDamage(damage);
                if (e1.Alive()) {
                    System.out.println("═══════════════════════════════════════");
                    System.out.println("对方的回合");
                    String act1,name;
                    act1 = "";
                    name=e1.getName();
                    int k = rand.nextInt(2);
                    if (k == 0) {
                        act1 = "普通攻击";
                        damage1 = e1.getAttract();
                    } else {

                        switch (name) {
                            case "刺客":
                                act1 = e1.getSkillslist().getFirst();
                                damage1 = e1.getAttract() * 3 / 2;
                                break;
                            case "法师":
                                act1 = e1.getSkillslist().getFirst();
                                damage1 = e1.getAttract() * 18 / 10;
                                break;
                            case "坦克":
                                act1 = e1.getSkillslist().getFirst();
                                damage1 = 0;
                                e1.setDefending(true);
                                break;
                            case "战士":
                                act1 = e1.getSkillslist().getFirst();
                                damage1 = e1.getAttract() * 3 / 2;
                                break;
                        }
                    }
                    System.out.println(e1.getName() + "发动了\"" + act1 + "\"造成了" + h.getdamage(damage1) + "点伤害");
                    h.takeDamage(damage1);
                } else {
                    count++;
                    wins++;
                    int remain1 = 20;
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("1.生命值(每点属性提升10点生命)");
                    System.out.println("2.攻击力(每点属性提升2点攻击力)");
                    System.out.println("3.防御力(每点属性提升1点防御力)");
                    String[] g = {"生命值", "攻击力", "防御力"};
                    int[] value1 = new int[3];
                    for (int i = 0; i < 3; i++) {
                        System.out.println("分配点数到" + g[i] + "(剩余点数为" + remain1 + ")");
                        System.out.println("请输入你要分配的点数");
                        int input = sc2.nextInt();
                        if (input > remain1) {
                            System.out.println("属性点不足请重新分配");
                        } else if (input < 0) {
                            System.out.println("加点不能为负请重新输入");
                        } else {
                            value1[i] = input;
                            System.out.println("加点成功");
                            remain1 = remain1 - input;
                        }
                    }
//
                    h.setMaxHp(h.getMaxHp() + 10 * value1[0]);
                    h.setAttract(h.getAttract() + 2 * value1[1]);
                    h.setDefense(h.getDefense() + value1[2]);

                    break;
                }

            }
            if (!h.Alive()) {
                return;
            }


        }


    }

    public void show(Hero h, String username) {

        System.out.println("您的人物" + username + "已经创建完成" + "当前属性为：");
        System.out.println("生命值HP:" + h.getHP() + ",攻击力ATK：" + h.getAttract() + ",防御力DEF:" + h.getDefense());
        System.out.println("您拥有的技能为：");
        h.showSkillslist();
    }

    public void getHealBar(String name, int HP, int maxHP) {
        double filed = Math.ceil((HP * 1.0 / maxHP) * 20);
        System.out.print(name + "的血量：[");
        for (int i = 0; i < 20; i++) {
            if (i < filed) {
                System.out.print("█");
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("]");
        System.out.println("HP:" + HP + "/" + maxHP);
    }
}
