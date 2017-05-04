package arrayandstr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by 10609 on 2017/5/2.
 */
public class Main {
    /**
     * 1.1 using hash set
     *
     * @param str
     * @return
     */
    boolean isUniqueStr(String str) {
        int len = str.length();
        HashSet<Character> set = new HashSet<Character>();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            set.add(c);
        }
        return set.size() == len;
    }

    /**
     * 1.1 bit version
     *
     * @param str
     * @return
     */
    boolean isUniqueChars(String str) {
        if (str.length() > 256)
            return false;
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

    /**
     * 1.1 using String method
     *
     * @param str
     * @return
     */
    boolean isUniqueStr2(String str) {
        int halfLen = str.length() / 2;
        String str1 = str.substring(0, halfLen + 1);
        String str2 = str.substring(halfLen + 1, str.length());
        for (int i = 0; i < str1.length(); i++) {
            char c = str1.charAt(i);
            if (str2.indexOf(c) != -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1.1 char set version
     *
     * @param str
     * @return
     */
    boolean isUniqueChars2(String str) {
        if (str.length() > 256)
            return false;
        boolean[] char_set = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    /**
     * 1.2 void swap(char *a, char *b) { char temp = *a; *a = *b; *b = temp; }
     *
     * void reverse(char *str) { int len = strlen(str); int halfLen = len / 2;
     * for (int i = 0; i < halfLen; i++) { swap(str + i, str + len - 1 - i); } }
     */

    /**
     * 1.2 pointer version void reverse(char *str) { char *end = str; char tmp;
     * if (str) { while (*end) { end++; } } end--;
     *
     * while (str < end) { tmp = *str; *str++ = *end; *end-- = tmp; } }
     *
     */

    /**
     * 1.3
     *
     * @param str1
     * @param str2
     * @return
     */
    boolean checkIsSame(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        if (len1 != len2) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>(str1.length());
        for (int i = 0; i < len1; i++) {
            char c = str1.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.replace(c, map.get(c) + 1);
            }
        }
        for (int i = 0; i < len2; i++) {
            char c = str2.charAt(i);
            if (!map.containsKey(c)) {
                return false;
            }
            int count = map.get(c);
            if (count == 0) {
                return false;
            } else {
                map.replace(c, count - 1);
            }
        }
        return true;
    }

    /**
     * String sort
     *
     * @param s
     * @return
     */
    String sort(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return String.valueOf(content);
    }

    /**
     * 1.3 sort version
     *
     * @param s
     * @param t
     * @return
     */
    boolean permutation(String s, String t) {
        if (s.length() != t.length())
            return false;
        return sort(s).equals(sort(t));
    }

    /**
     * 1.3 char map version
     *
     * @param s
     * @param t
     * @return
     */
    boolean permutation2(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] letters = new int[256];
        char[] s_array = s.toCharArray();
        for (char c : s_array) {
            letters[c]++;
        }
        for (int i = 0; i < t.length(); i++) {
            int c = (int) t.charAt(i);
            if (--letters[c] < 0) {
                return false;
            }
        }
        return true;
    }

    /***
     * 1.4
     *
     * @param str
     * @return
     */
    String replaceChar(String str) {
        str = str.trim();
        int len = str.length();
        char[] res = new char[len * 2];
        int idx = 0;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (Character.isSpaceChar(c)) {
                res[idx++] = '%';
                res[idx++] = '2';
                res[idx++] = '0';
            } else {
                res[idx++] = c;
            }
        }
        return String.valueOf(res);
    }

    /**
     * 1.4 char array version
     *
     * @param str
     * @param length
     */
    void replaceSpaces(char[] str, int length) {
        int spaceCount = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        int newLength = length + spaceCount * 2;
        str[newLength] = '\0';
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[newLength - 1] = '0';
                str[newLength - 2] = '2';
                str[newLength - 3] = '%';
                newLength -= 3;
            } else {
                str[newLength - 1] = str[i];
                newLength -= 1;
            }
        }
    }

    /**
     * 1.5
     *
     * @param str
     * @return
     */
    String zipStr(String str) {
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        boolean flag = false;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            int count = 1;
            while (i + 1 < len && c == str.charAt(i + 1)) {
                count++;
                i++;
            }
            if (count > 1) {
                flag = true;
            }
            sb.append(c);
            sb.append(count);
        }
        return flag ? sb.toString() : str;
    }

    /**
     * 1.5 String Compression
     *
     * @param str
     * @return
     */
    String compressBetter(String str) {
        int size = countCompression(str);
        if (size >= str.length()) {
            return str;
        }
        StringBuffer mystr = new StringBuffer();
        char last = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                count++;
            } else {
                mystr.append(last);
                mystr.append(count);
                last = str.charAt(i);
                count = 1;
            }
        }
        mystr.append(last);
        mystr.append(count);
        return mystr.toString();
    }

    /**
     * 1.5 helper method count string length after compression
     *
     * @param str
     * @return
     */
    int countCompression(String str) {
        if (str == null || str.isEmpty())
            return 0;
        char last = str.charAt(0);
        int size = 0;
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                count++;
            } else {
                last = str.charAt(i);
                size += i + String.valueOf(count).length();
                count = 1;
            }
        }
        size += 1 + String.valueOf(count).length();
        return size;
    }

    /**
     * 1.5 char array version
     *
     * @param str
     * @return
     */
    String compressAlternative(String str) {
        int size = countCompression(str);
        if (size >= str.length()) {
            return str;
        }
        char[] array = new char[size];
        int index = 0;
        int count = 1;
        char last = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                count++;
            } else {
                index = setChar(array, last, index, count);
                last = str.charAt(i);
                count = 1;
            }
        }
        index = setChar(array, last, index, count);
        return String.valueOf(array);
    }

    /**
     * 1.5 helper method
     *
     * @param array
     * @param c
     * @param index
     * @param count
     * @return
     */
    int setChar(char[] array, char c, int index, int count) {
        array[index] = c;
        index++;

        char[] cnt = String.valueOf(count).toCharArray();
        for (char x : cnt) {
            array[index] = x;
            index++;
        }
        return index;
    }

    /**
     * 1.6
     *
     * @param img
     * @return
     */
    void imgTranspose(int[][] img, int N) {
        for (int i = 0; i < N / 2; i++) {
            for (int j = i; j < N - 1 - i; j++) {
                int temp = img[i][j];
                img[i][j] = img[N - 1 - j][i];
                img[N - 1 - j][i] = img[N - 1 - i][N - 1 - j];
                img[N - 1 - i][N - 1 - j] = img[j][N - 1 - i];
                img[j][N - 1 - i] = temp;
            }
        }
    }

    /***
     * 1.7
     *  O(n*3)
     * @param mat
     * @param M
     * @param N
     */
    void setMatrix(int[][] mat, int M, int N) {
        boolean[][] mark = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!mark[i][j]) {
                    for (int k = 0; k < M; k++) {
                        mat[k][j] = 0;
                    }
                    for (int k = 0; k < N; k++) {
                        mat[i][k] = 0;
                    }
                } else {
                    mark[i][j] = true;
                }
            }
        }
    }

    /**
     * 1.7
     * O(n*2)
     *
     * @param matrix
     */
    void setZeros(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];

        // mark
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }
        // clear
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 1.8 helper method
     *
     * @param s1
     * @param s2
     * @return
     */
    boolean isSubstring(String s1, String s2) {
        return s1.length() >= s2.length() && s1.indexOf(s2) != -1;
    }

    /***
     * 1.8
     * @param s1
     * @param s2
     * @return
     */
    boolean isRotation(String s1, String s2) {
        int len = s1.length();
        if (len == s2.length() && len > 0) {
            String s1s1 = s1 + s1;
            return isSubstring(s1s1, s2);
        }
        return false;
    }

    static void testImgTranspose(Main app) {
        int N = 7;
        int[][] img = new int[N][N];
        int count = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                img[i][j] = count++;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(String.format("%7d", img[i][j]));
            }
            System.out.println();
        }
        System.out.println("--------------------------------------");
        app.imgTranspose(img, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(String.format("%7d", img[i][j]));
            }
            System.out.println();
        }
    }

    /***
     * setMatrix Test Case
     *
     * @param app
     */
    static void testSetMatrix(Main app) {
        int M = 5;
        int N = 4;
        int[][] mat = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                mat[i][j] = (int) Math.round(Math.random());
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(String.format("%8d", mat[i][j]));
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
        app.setMatrix(mat, M, N);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(String.format("%8d", mat[i][j]));
            }
            System.out.println();
        }
    }

    static void testBit() {
        for (int i = 0; i < 32; i++) {
            System.out.println(1 << i);
        }
    }

    static void testIsUniqueChars(Main app) {
        String str = "abcdefg";
        System.out.println(app.isUniqueChars(str));
    }

    static void testCompressBetter(Main app) {
        String str = "aaaaaaaaaaa";
        System.out.println(app.compressBetter(str));
    }

    static void testCompressAlternative(Main app) {
        String str = "aaaaaaaaaaa";
        System.out.println(app.compressAlternative(str));
    }

    static void testIsSubstring(Main app) {
        String s1 = "hello";
        String s2 = "elloe";
        System.out.println(app.isSubstring(s1, s2));
    }

    static void testIsRotation(Main app) {
        String s1 = "helloworld";
        String s2 = "worldhello";
        System.out.println(app.isRotation(s1, s2));
    }

    public static void main(String[] args) {
        Main app = new Main();
        // testSetMatrix(app);
        // testBit();
        // testIsUniqueChars(app);
        // testCompressBetter(app);
//		testCompressAlternative(app);
//        testImgTranspose(app);
//        testIsSubstring(app);
        testIsRotation(app);
    }
}
