package util;

/**
 * @author Y.bear
 * @version 创建时间：2018年10月9日 下午2:36:45 类说明
 */
public class StringUtils {
    public static String removeFirstAndLast(String s) {
        s = s.substring(1, s.length());
        s = s.substring(0, s.length() - 1);
        return s;
    }

    public static int checkNumOfSymbol(String text, char symbol) {
        char[] chars = text.toCharArray();
        int num = 1;
        for (char t :
                chars) {
            if (t == symbol) {
                num++;
            }
        }
        return num;
    }
}
