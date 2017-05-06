package linkedlist;

import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

/**
 * Created by 10609 on 2017/5/4.
 */

class Node {
    Node next = null;
    int data;

    public Node(int data) {
        this.data = data;
    }

    void appendToTail(int data) {
        Node end = new Node(data);
        Node n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    Node deleteNode(Node head, int data) {
        Node n = head;
        if (n.data == data) {
            return head.next;
        }
        while (n.next != null) {
            if (n.data == data) {
                n.next = n.next.next;
                return head;
            }
            n = n.next;
        }
        return head;
    }
}

public class Main {
    /**
     * 2.1 O(n*2)
     *
     * @param head
     * @return
     */
    Node removeRepeatNodes(Node head) {
        if (head != null) {
            Node cur = head;
            while (cur != null) {
                Node prev = cur, next = cur.next;
                while (next != null) {
                    while (next != null && next.data == cur.data) {
                        next = next.next;
                    }
                    prev.next = next;
                    if (next != null) {
                        prev = next;
                        next = next.next;
                    } else {
                        break;
                    }
                }
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * display linked list
     *
     * @param head
     */
    static void disp(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(String.format("%d => ", cur.data));
            cur = cur.next;
        }
        System.out.println("||");
    }

    /**
     * 2.1 test
     *
     * @param app
     */
    static void testRemoveRepeatNodes(Main app) {
        Node head = null;
        int N = 10;
        int M = 20;
        Random random = new Random();
        for (int i = 0; i < M; i++) {
            int val = Math.abs(random.nextInt() % N);
            Node node = new Node(val);
            node.next = head;
            head = node;
        }
        disp(head);
        head = app.removeRepeatNodes(head);
        disp(head);
    }

    /**
     * generate linked list
     *
     * @param M
     * @param N
     * @return
     */
    static Node generateLinkedList(int M, int N) {
        Node head = null;
        Random random = new Random();
        for (int i = 0; i < M; i++) {
            int val = Math.abs(random.nextInt() % N);
            Node node = new Node(val);
            node.next = head;
            head = node;
        }
        return head;
    }

    /**
     * 2.2
     *
     * @param head
     * @param k
     * @return
     */
    Node findLastKNode(Node head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (--k == 0) {
                return cur;
            }
        }
        return null;
    }

    /**
     * 2.2 test
     *
     * @param app
     */
    static void testFindLastKNode(Main app) {
        int M = 20;
        Node lst = generateLinkedList(20, 10);
        disp(lst);
        for (int i = 1; i <= M; i++) {
            Node node = app.findLastKNode(lst, i);
            System.out.print(String.format("%-5d", node.data));
        }
    }

    /**
     * 2.3
     *
     * @param node
     */
    void deleteNode(Node node) {
        Node prev = node, cur = node.next;
        while (cur != null && cur.next != null) {
            prev.data = cur.data;
            prev = cur;
            cur = cur.next;
        }
        prev.next = null;
    }

    /**
     * 2.3 test
     *
     * @param app
     */
    static void testDeleteNode(Main app) {
        int M = 20;
        Node lst = generateLinkedList(20, 10);
        disp(lst);

        System.out.println(String.format("delete node: %d ", lst.data));
        app.deleteNode(lst);
        disp(lst);

        System.out.println(String.format("delete node: %d ", lst.next.next.data));
        app.deleteNode(lst.next.next);
        disp(lst);

        Node cur = lst;
        while (cur.next.next != null) {
            cur = cur.next;
        }
        System.out.println(String.format("delete node: %d ", cur.data));
        app.deleteNode(cur);
        disp(lst);
    }

    /**
     * 2.4 quick sort split
     *
     * @param arr
     * @param val
     */
    void split(int[] arr, int val) {
        int left = 0, right = arr.length - 1;
        int l = left, r = right;
        while (l < r) {
            while (l < right && arr[l] < val) {
                l++;
            }
            while (r > left && arr[r] >= val) {
                r--;
            }
            if (l < r) {
                int tmp = arr[l];
                arr[l] = arr[r];
                arr[r] = tmp;
                l++;
                r--;
            }
        }
    }

    /**
     * 2.4 split linked list by val
     *
     * @param head
     * @param val
     * @return
     */
    Node splitBy(Node head, int val) {
        Node cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        int[] arr = new int[count];
        cur = head;
        count = 0;
        while (cur != null) {
            arr[count++] = cur.data;
            cur = cur.next;
        }
        split(arr, val);
        cur = head;
        count = 0;
        while (cur != null) {
            cur.data = arr[count++];
            cur = cur.next;
        }
        return head;
    }

    /**
     * @param app
     */
    static void testSplit(Main app) {
        int M = 20, N = 10;

        for (int i = 0; i < N; i++) {
            int[] nums = generateArr(M, N);
            disp(nums);
            app.split(nums, i);
            System.out.println(i);
            disp(nums);
            System.out.println("-----------------------------------------");
        }
    }

    /**
     * generate array
     *
     * @param M
     * @param N
     * @return
     */
    static int[] generateArr(int M, int N) {
        int[] nums = new int[M];

        Random random = new Random();
        for (int i = 0; i < M; i++) {
            int val = Math.abs(random.nextInt() % N);
            nums[i] = val;
        }
        return nums;
    }

    /**
     * display array
     *
     * @param nums
     */
    static void disp(int[] nums) {
        for (int i : nums) {
            System.out.print(String.format("%4d", i));
        }
        System.out.println();
    }

    /**
     * 2.4 test
     *
     * @param app
     */
    static void testSplitBy(Main app) {
        int M = 20, N = 10;
        for (int i = 0; i < N; i++) {
            Node lst = generateLinkedList(M, N);
            disp(lst);
            System.out.println(i);
            lst = app.splitBy(lst, i);
            disp(lst);
            System.out.println("-----------------------------------------------");
        }
    }

    /**
     * 2.5 get number from linked list
     *
     * @param head
     * @return
     */
    int getNum(Node head) {
        int res = 0;
        int cnt = 0;
        Node cur = head;
        while (cur != null) {
            res += cur.data * (int) Math.pow(10, cnt++);
            cur = cur.next;
        }
        return res;
    }

    /**
     * 2.5 get linked list from number
     *
     * @param num
     * @return
     */
    Node getNode(int num) {
        Node head = null;
        Node prev = null;
        while (num > 0) {
            Node node = new Node(num % 10);
            if (head == null) {
                head = node;
            }
            if (prev == null) {
                prev = node;
            } else {
                prev.next = node;
                prev = prev.next;
            }
            num /= 10;
        }
        return head;
    }

    /**
     * 2.5 calculate sum from linked list
     *
     * @param lst1
     * @param lst2
     * @return
     */
    Node sum(Node lst1, Node lst2) {
        int res1 = getNum(lst1);
        int res2 = getNum(lst2);
        int res = res1 + res2;
        Node head = getNode(res);
        return head;
    }

    /**
     * 2.5 getNum test
     *
     * @param app
     */
    static void testGetNum(Main app) {
        Node head = null;
        Node node1 = new Node(7);
        Node node2 = new Node(1);
        Node node3 = new Node(6);
        head = node1;
        node1.next = node2;
        node2.next = node3;
        System.out.println(app.getNum(head));
    }

    /**
     * 2.5 getNode test
     *
     * @param app
     */
    static void testGetNode(Main app) {
        int num = 617;
        Node head = app.getNode(num);
        Node cur = head;
        while (cur != null) {
            System.out.print(String.format("%4d", cur.data));
            cur = cur.next;
        }
        System.out.println();
    }


    /**
     * 2.5 sum test
     *
     * @param app
     */
    static void testSum(Main app) {
        Node node1 = new Node(7);
        Node node2 = new Node(1);
        Node node3 = new Node(6);
        node2.next = node3;
        node1.next = node2;
        Node lst1 = node1;

        Node node4 = new Node(5);
        Node node5 = new Node(9);
        Node node6 = new Node(2);
        node5.next = node6;
        node4.next = node5;
        Node lst2 = node4;

        Node lst = app.sum(lst1, lst2);
        Node cur = lst;
        while (cur != null) {
            System.out.print(String.format("%4d", cur.data));
            cur = cur.next;
        }
        System.out.println("\n--------------------------------------------------");
    }

    /**
     * 2.5.2
     *
     * @param head
     * @return
     */
    int getNum2(Node head) {
        int res = 0;
        Node cur = head;
        while (cur != null) {
            res = res * 10 + cur.data;
            cur = cur.next;
        }
        return res;
    }

    /**
     * 2.5.2 getNode
     *
     * @param num
     * @return
     */
    Node getNode2(int num) {
        Node head = null;
        while (num > 0) {
            Node node = new Node(num % 10);
            node.next = head;
            head = node;
            num /= 10;
        }
        return head;
    }

    /**
     * 2.5.2 calculate sum of two linked list
     *
     * @param lst1
     * @param lst2
     * @return
     */
    Node sum2(Node lst1, Node lst2) {
        int num1 = getNum2(lst1);
        int num2 = getNum2(lst2);
        int num = num1 + num2;
        Node head = getNode2(num);
        return head;
    }

    /**
     * 2.5.2 getNum test
     *
     * @param app
     */
    static void testGetNum2(Main app) {
        Node node1 = new Node(6);
        Node node2 = new Node(1);
        Node node3 = new Node(7);
        node2.next = node3;
        node1.next = node2;
        Node lst = node1;
        System.out.println(app.getNum2(lst));
    }

    /**
     * 2.5.2 getNode test
     *
     * @param app
     */
    static void testGetNode2(Main app) {
        int num = 617;
        Node head = app.getNode2(num);
        disp(head);
    }

    /**
     * 2.6
     *
     * @param head
     * @return
     */
    Node getCycleNode(Node head) {
        HashSet<Node> set = new HashSet<Node>();
        Node cur = head;
        while (cur != null) {
            if (!set.contains(cur)) {
                set.add(cur);
            } else {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    /**
     * 2.6 getCycleNode test
     *
     * @param app
     */
    static void testGetCycleNode(Main app) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node2;
        Node head = node1;
        Node cur = app.getCycleNode(head);
        if (cur != null) {
            System.out.println(cur.data);
        }
    }

    /**
     * 2.7
     *
     * @param head
     * @return
     */
    boolean isPalindromic(Node head) {
        Node cur = head;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        Node[] nodes = new Node[count];
        cur = head;
        count = 0;
        while (cur != null) {
            nodes[count++] = cur;
            cur = cur.next;
        }
        for (int i = 0; i < count / 2; i++) {
            if (nodes[i].data != nodes[count - 1 - i].data) {
                return false;
            }
        }
        return true;
    }

    /**
     * 2.7 test
     *
     * @param app
     */
    static void testIsPalindromic(Main app) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(2);
//        Node node5 = new Node(1);
//        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        Node head = node1;
        System.out.println(app.isPalindromic(head));
    }

    /**
     * 2.2
     *
     * @param head
     * @param k
     * @return
     */
    Node findLastKNode2(Node head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        Node slow = head;
        Node fast = slow;
        while (k > 0 && fast != null) {
            fast = fast.next;
            k--;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 2.2 test
     *
     * @param app
     */
    static void testFindLastKNode2(Main app) {
        Node lst = generateLinkedList(10, 10);
        disp(lst);
        for (int i = 0; i < 10; i++) {
            Node node = app.findLastKNode2(lst, i + 1);
            System.out.println(String.format("last %d element is %d", i + 1, node.data));
            System.out.println("---------------------------------");
        }
    }

    /**
     * 2.3
     *
     * @param node
     * @return
     */
    boolean deleteNode2(Node node) {
        if (node == null || node.next == null) {
            return false;
        }
        node.data = node.next.data;
        node.next = node.next.next;
        return true;
    }

    /**
     * 2.3 test
     *
     * @param app
     */
    static void testDeleteNode2(Main app) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node2.next = node3;
        node1.next = node2;
        Node head = node1;
        disp(head);
        System.out.println(app.deleteNode2(head.next));
        disp(head);
    }

    /**
     * 2.4
     *
     * @param head
     * @param pivot
     * @return
     */
    Node splitList(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        Node cur = head, small = null, large = null, prev = null, last = null;
        while (cur != null) {
            if (cur.data < pivot) {
                if (small == null) {
                    small = cur;
                    prev = small;
                } else {
                    prev.next = cur;
                    prev = cur;
                }
            } else {
                if (large == null) {
                    large = cur;
                    last = large;
                } else {
                    last.next = cur;
                    last = cur;
                }
            }
            cur = cur.next;
        }
        if (last != null) {
            last.next = null;
        }
        if (prev != null) {
            prev.next = null;
        }
//        System.out.println("--------------------small part-------------------");
//        disp(small);
//        System.out.println("-------------------- end -------------------------");
//        System.out.println("--------------------large part--------------------");
//        disp(large);
//        System.out.println("-------------------- end -------------------------");
        if (small != null && prev != null) {
            prev.next = large;
        }
        head = small;
        return head;
    }

    /**
     * 2.4 test
     *
     * @param app
     */
    static void testSplitList(Main app) {
        for (int i = 0; i < 10; i++) {
            Node lst = generateLinkedList(20, 10);
            disp(lst);
            System.out.println(String.format("pivot: %d", i));
            lst = app.splitList(lst, i);
            disp(lst);
            System.out.println("====================================================");
        }
    }

    /**
     * 2.6
     *
     * @param head
     * @return
     */
    Node findBeginnig(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    /**
     * 2.6
     *
     * @param app
     */
    static void testFindBeginnig(Main app) {
        Node head = null;
        for (int i = 0; i < 10; i++) {
            Node node = new Node(i);
            node.next = head;
            head = node;
        }
        disp(head);
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        // 0 -------------------> 6
        cur.next = head.next.next.next;
        Node node = app.findBeginnig(head);
        if (node != null) {
            System.out.println(node.data);
        }
    }

    /**
     * 2.7
     *
     * @param head
     * @return
     */
    boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Node> stack = new Stack<Node>();
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            stack.push(slow);
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null && fast.next == null) { // odd
            slow = slow.next;
        }
        while (slow != null) {
            Node cur = stack.pop();
            if (cur.data != slow.data) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    /**
     * 2.7 test
     *
     * @param app
     */
    static void testIsPalindrome(Main app) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(3);
        Node node5 = new Node(2);
        Node node6 = new Node(1);
        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        Node head = node1;
        System.out.println(app.isPalindrome(head));
    }

    public static void main(String[] args) {
        Main app = new Main();
//        testRemoveRepeatNodes(app);
//        testFindLastKNode(app);
//        testDeleteNode(app);
//        testSplit(app);
//        testSplitBy(app);
//        testGetNum(app);
//        testGetNode(app);
//        testSum(app);
//        testGetNum2(app);
//        testGetNode2(app);
//        testGetCycleNode(app);
//        testIsPalindromic(app);
//        testFindLastKNode2(app);
//        testDeleteNode2(app);
//        testSplitList(app);
//        testFindBeginnig(app);
        testIsPalindrome(app);
    }
}
