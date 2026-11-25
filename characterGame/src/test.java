import java.util.ArrayList;
import java.util.Random;

public class test {
    public static void main(String[] args) {

    }

    public static String getcode() {
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
        System.out.println(str);
        return a.toString();
    }

}
