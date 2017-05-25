package sortandsearch;

import com.sun.xml.internal.ws.policy.sourcemodel.PolicyModelTranslator;

import java.security.PublicKey;
import java.util.Random;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


/**
 * Created by 10609 on 2017/5/22.
 */
public class Main {
    /**
     * swap to ints
     *
     * @param arr
     * @param left
     * @param right
     */
    void swap(int[] arr, int left, int right) {
        int t = arr[left];
        arr[left] = arr[right];
        arr[right] = t;
    }

    /**
     * partation
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    int partation(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        while (left <= right) {
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    /**
     * quick sort
     *
     * @param arr
     * @param left
     * @param right
     */
    void qsort(int[] arr, int left, int right) {
        int pivot = partation(arr, left, right);
//        System.out.println(String.format("left: %d, pivot: %d, right: %d", left, pivot, right));
        if (left < pivot - 1) {
            qsort(arr, left, pivot - 1);
        }
        if (right > pivot) {
            qsort(arr, pivot, right);
        }
    }

    /**
     * qucik sort driver
     *
     * @param arr
     */
    void quickSort(int[] arr) {
        if (arr.length > 1) {
            qsort(arr, 0, arr.length - 1);
        }
    }

    /**
     * quick sort test
     *
     * @param app
     */
    static void testQuickSort(Main app) {
        int M = 10;
        int[] arr = new int[M];
        Random random = new Random();
        for (int i = 0; i < M; i++) {
            arr[i] = (Math.abs(random.nextInt()) % M);
        }
        for (int i : arr) {
            System.out.print(String.format("%4d", i));
        }
        System.out.println("\n------------------------");

        app.quickSort(arr);

        for (int i : arr) {
            System.out.print(String.format("%4d", i));
        }
        System.out.println();
    }


    /**
     * merge
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param helper
     */
    void merge(int[] arr, int left, int mid, int right, int[] helper) {
        for (int i = left; i <= right; i++) {
            helper[i] = arr[i];
        }
        int l = left, r = mid + 1, idx = left;
        while (l <= mid && r <= right) {
            if (helper[l] <= helper[r]) {
                arr[idx++] = helper[l++];
            } else {
                arr[idx++] = helper[r++];
            }
        }
        for (int i = l; i <= mid; i++) {
            arr[idx++] = helper[i];
        }
    }

    /**
     * merge sort
     *
     * @param arr
     * @param left
     * @param right
     * @param helper
     */
    void mergeSort(int[] arr, int left, int right, int[] helper) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, helper);
            mergeSort(arr, mid + 1, right, helper);
            merge(arr, left, mid, right, helper);
        }
    }

    /**
     * merge sort driver
     *
     * @param arr
     */
    void mergeSort(int[] arr) {
        if (arr.length > 0) {
            int[] helper = new int[arr.length];
            mergeSort(arr, 0, arr.length - 1, helper);
        }
    }

    /**
     * test merge sort
     *
     * @param app
     */
    static void testMergeSort(Main app) {
        int M = 20;
        int[] arr = new int[M];
        Random random = new Random();
        for (int i = 0; i < M; i++) {
            arr[i] = Math.abs(random.nextInt() % M);
        }
        for (int i : arr) {
            System.out.print(String.format("%4d", i));
        }
        System.out.println("\n-------------------");
        app.mergeSort(arr);
        for (int i : arr) {
            System.out.print(String.format("%4d", i));
        }
        System.out.println("\n-------------------");
    }

    /**
     * 11.1
     *
     * @param A
     * @param B
     * @param count
     */
    void mergeAB(int[] A, int[] B, int count) {
        int mid = count - 1;
        for (int i = 0; i < B.length; i++) {
            A[count++] = B[i];
        }
        int[] helper = new int[count];
        for (int i = 0; i < count; i++) {
            helper[i] = A[i];
        }
        int left = 0, right = mid + 1;
        int idx = left;
        while (left <= mid && right < count) {
            if (helper[left] < helper[right]) {
                A[idx++] = helper[left++];
            } else {
                A[idx++] = helper[right++];
            }
        }
        for (int i = left; i <= mid; i++) {
            A[idx++] = helper[i];
        }
    }

    /**
     * 11.1 test
     *
     * @param app
     */
    static void testMergeAB(Main app) {
        int M = 20;
        int N = 5;
        int[] A = new int[M * M];
        Random random = new Random();
        for (int i = 0; i < M; i++) {
            A[i] = Math.abs(random.nextInt() % M);
        }
        app.qsort(A, 0, M - 1);
        for (int i = 0; i < M; i++) {
            System.out.print(String.format("%4d", A[i]));
        }
        System.out.println("\n-----------------");

        int[] B = new int[N];
        for (int i = 0; i < N; i++) {
            B[i] = Math.abs(random.nextInt() % M);
        }
        app.quickSort(B);
        for (int i : B) {
            System.out.print(String.format("%4d", i));
        }
        System.out.println("\n--------------------");


        app.mergeAB(A, B, M);

        for (int i = 0; i < M + N; i++) {
            System.out.print(String.format("%4d", A[i]));
        }
        System.out.println("\n-----------------");
    }

    /**
     * 11.5
     *
     * @param arr
     * @param left
     * @param right
     * @param value
     * @return
     */
    int search(String[] arr, int left, int right, String value) {
        if (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == "") {
                int l = mid, r = mid;
                while (arr[l] == "") {
                    l--;
                }
                while (arr[r] == "") {
                    r++;
                }
                if (l >= left) {
                    int res1 = search(arr, left, l, value);
                    if (res1 != -1) {
                        return res1;
                    }
                }
                if (r <= right) {
                    int res2 = search(arr, r, right, value);
                    if (res2 != -1) {
                        return res2;
                    }
                }
            } else {
                if (arr[mid].compareTo(value) < 0) {
                    return search(arr, mid + 1, right, value);
                } else if (arr[mid].compareTo(value) > 0) {
                    return search(arr, left, mid - 1, value);
                } else {
                    return mid;
                }
            }
        }
        return -1;
    }

    /**
     * O(n)
     *
     * @param arr
     * @param value
     * @return
     */
    int find(String[] arr, String value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 11.5 test
     *
     * @param app
     */
    static void testSearch(Main app) {
        String[] arr = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", "", "qut"};
//        System.out.println(app.search(arr, 0, arr.length - 1, "at"));
//        System.out.println(app.search(arr, 0, arr.length - 1, "ball"));
//        System.out.println(app.search(arr, 0, arr.length - 1, "car"));
//        System.out.println(app.search(arr, 0, arr.length - 1, "dad"));
        System.out.println(app.search(arr, 0, arr.length - 1, "qut"));
//        System.out.println(app.find(arr, "but"));
    }

    static void testCompare(Main app) {
        String a = "abc";
        String b = "b";
        System.out.println(a.compareTo(b));
        System.out.println(b.compareTo(a));
        System.out.println(a.compareTo(a));
    }

    /**
     * O(M*log(N))
     * @param mat
     * @param value
     * @return
     */
    Point search(int[][] mat, int value) {
        int row = 0, col = mat[0].length-1;
        while (row <= mat.length-1 && col >= 0) {
            if (mat[row][col] < value) {
                row++;
            } else if (mat[row][col] > value) {
                col--;
            } else {
                return new Point(row, col);
            }
        }
        return null;
    }

    Point search(int[][] mat, int rowLow, int rowHigh, int colLow, int colHeight, int value) {
        if (rowLow <= rowHigh && colLow <= colHeight) {
            int rowMid = (rowLow + rowHigh) / 2;
            int colMid = (colLow + colHeight) / 2;
            if (mat[rowMid][colMid] < value) {
                if (rowHigh - rowMid <= 1 && colHeight - colMid <= 1) {
                    if (mat[rowMid][colHeight] == value) {
                        return new Point(rowMid, colHeight);
                    } else if (mat[rowHigh][colMid] == value) {
                        return new Point(rowHigh, colMid);
                    } else if (mat[rowHigh][colHeight] == value) {
                        return new Point(rowHigh, colHeight);
                    }
                }
                return search(mat, rowMid, rowHigh, colMid, colHeight, value);
            } else if (mat[rowMid][colMid] > value) {
                Point lowPart = search(mat, rowLow, rowMid-1, colLow, colHeight, value);
                if (lowPart != null) {
                    return lowPart;
                } else {
                    return search(mat, rowMid, rowHigh, colLow, colMid-1, value);
                }
            } else {
                return new Point(rowMid, colMid);
            }
        }
        return null;
    }

    Point find(int[][] mat, int M, int N, int value) {
        return search(mat, 0, M - 1, 0, N - 1, value);
    }

    static void testFind(Main app) {
        int M = 10;
        int N = 10;
        int[][] mat = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                mat[i][j] = i * 10 + j;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(String.format("%4d", mat[i][j]));
            }
            System.out.println();
        }
//
        Point p = app.find(mat, M, N, 0);
        System.out.println(String.format("x: %d, y: %d", p.x, p.y));
        Point pp = app.find(mat, M, N, 9);
        System.out.println(String.format("x: %d, y: %d", pp.x, pp.y));
        Point ppp = app.find(mat, M, N, 90);
        System.out.println(String.format("x: %d, y: %d", ppp.x, ppp.y));
        Point pppp = app.find(mat, M, N, 99);
        System.out.println(String.format("x: %d, y: %d", pppp.x, pppp.y));
//        for (int i = 0; i < M; i++) {
//            for (int j = 0; j < N; j++) {
//                Point p = app.search(mat, i * 10 + j);
//                System.out.println(String.format("%d, %d", p.x,p.y));
//            }
//
//        }

    }

    public static void main(String[] args) {
        Main app = new Main();
//        testQuickSort(app);
//        testMergeSort(app);
//        testMergeAB(app);
//        testSearch(app);
//        testCompare(app);
        testFind(app);
    }
}
