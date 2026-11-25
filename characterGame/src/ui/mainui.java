package ui;

import account.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class mainui {
    static ArrayList<User> u = new ArrayList<>();

    public void mainui() {
        while (true) {
            System.out.println("---------------------------");
            System.out.println("     欢迎来到文字格斗游戏");
            System.out.println("---------------------------");
            System.out.println("选择您的操作：1.注册 2.登录 3.退出");
            Scanner sc = new Scanner(System.in);
            String a = sc.next();
            switch (a) {
                case "1":
                    System.out.println("注册");
                    register();
                    break;
                case "2":
                    System.out.println("登录");
                    login();
                    break;
                case "3":
                    System.out.println("谢谢使用");
                    System.exit(0);
                    break;
            }
        }
    }

    public void register() {
        User user = new User();
        System.out.println("请输入用户名");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String name = sc.next();
            //验证用户名是否合法，合法则录入否则提示用户名不合法
            //长度在3-8位，必须由字母数字组成，用户名唯一
            if (!checkname(name) || !checklength(3, 8, name)) {
                System.out.println("用户名不合法请重新输入（长度在3-8位，必须由字母数字组成）");
                continue;
            }
            if (contains(u, name)) {
                System.out.println("用户名已存在");
                continue;
            }
            user.setUsername(name);
            break;
        }
        while (true) {
            System.out.println("请输入密码");
            String password = sc.next();
            //验证密码合法性
            if (!checklength(3, 8, password) || !checkname(password)) {
                System.out.println("密码不合法请重新输入（长度在3-8位，必须由字母数字组成）");
                continue;
            }
            user.setPassword(password);
            break;
        }
        u.add(user);
        System.out.println("注册成功");
    }

    public boolean checkname(String name) {
        int charcount = 0;
        int numcount = 0;
        int othercount = 0;
        boolean result = false;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c >= 'a' && c <= 'z') {
                charcount++;
            } else if (c >= 48 && c <= 57) {
                numcount++;
            } else if (c >= 'A' && c <= 'Z') {
                charcount++;
            } else {
                othercount++;
            }
        }
        if (othercount == 0) {
            result = true;
        }
        return result;
    }

    public boolean contains(ArrayList<User> list, String username) {
        boolean result = false;
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUsername().equals(username)) {
                result =true;
            }
        }
        return result;
    }

    public boolean checklength(int minlen, int maxlen, String str) {
        return str.length() >= minlen && str.length() <= maxlen;
    }


    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String a = sc.next();
        //如果有用户信息则登录，没有就输出“请注册账户”
        mainui i = new mainui();
        if (!i.contains(u, a)) {
            System.out.println("没有该用户，请先注册");
            return;
        }
        int index = getIndex(u, a);
        User u1 = u.get(index);
        boolean s = u1.getStatus();
        int times=0;
        while (true) {
            if (times<4) {
                times++;
                if (s == true) {
                    System.out.println("请输入密码");
                    String c = getCode();
                    String b = sc.next();
                    System.out.println("请输入验证码："+c);

                    String d = sc.next();
                    if (b.equals(u1.getPassword())&&d.equalsIgnoreCase(c)) {
                        System.out.println("登录成功");
                        break;
                    }else if(b.equals(u1.getPassword())&&!d.equalsIgnoreCase(c)) {
                        System.out.println("验证码错误，请重新输入验证码");
                        continue;
                    } else if (!b.equals(u1.getPassword())&&d.equalsIgnoreCase(c)) {
                        System.out.println("密码错误请重新输入密码");
                        continue;
                    }else {
                        System.out.println("请重新输入密码");
                        continue;
                    }

                }else if (s == false) {
                    System.out.println("该用户被禁用，请联系客服~");
                    return;
                }
            }else{
                System.out.println("用户已被锁定，请联系客服");
                u1.setStatus(false);
                return;
            }

        }
        FightGame f = new FightGame();
        f.startGame(u1.getUsername());
    }

    public static String getCode() {
        //创建一个集合存储所有字符
        ArrayList<Character> a = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            a.add((char) ('a' + i));
            a.add((char) ('A' + i));
        }
        //随机挑选四位数字，获取集合中的字符
        Random r = new Random();
        int index = 0;
        int num = r.nextInt(10);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            index = r.nextInt(a.size());
            sb.append(a.get(index));
        }
        sb.append(num);
        //将字符串转换为数组然后交换最大索引数据的位置
        char[] chars = sb.toString().toCharArray();
        int s = r.nextInt(chars.length);
        int d = chars.length - 1;
        char temp = chars[s];
        chars[s] = chars[d];
        chars[d] = temp;
        //将字符类型转化为字符串
        String str = new String(chars);
        return str;
    }

    public int getIndex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }
}
