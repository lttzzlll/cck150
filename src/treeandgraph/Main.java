package treeandgraph;

/**
 * Created by 10609 on 2017/5/14.
 */

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import sun.awt.image.ImageWatched;

import java.util.*;
import java.util.stream.StreamSupport;

/**
 * Binary Tree Node
 */
class BTNode {
    int data;
    BTNode left;
    BTNode right;

    public BTNode(int data) {
        this.data = data;
    }
}

class BTSNode {
    int data;
    BTSNode left;
    BTSNode right;
    BTSNode parent;

    public BTSNode(int data) {
        this.data = data;
    }
}

enum State {
    Unviseted, Visited, Visiting
}

public class Main {

    /**
     * print binary tree node
     *
     * @param node
     */
    static void visit(BTNode node) {
        System.out.println(node.data);
    }

    /**
     * preorder visit binary tree
     *
     * @param head
     */
    static void preOrder(BTNode head) {
        if (head != null) {
            visit(head);
            preOrder(head.left);
            preOrder(head.right);
        }
    }

    /**
     * binary tree in order visit
     *
     * @param head
     */
    static void inOrder(BTNode head) {
        if (head != null) {
            inOrder(head.left);
            visit(head);
            inOrder(head.right);
        }
    }

    /**
     * binary tree post order visit
     *
     * @param head
     */
    static void postOrder(BTNode head) {
        if (head != null) {
            postOrder(head.left);
            postOrder(head.right);
            visit(head);
        }
    }

    /**
     * 4.1
     *
     * @param root
     * @return
     */
    static int countDepth(BTNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + Math.max(countDepth(root.left), countDepth(root.right));
        }
    }

    /**
     * 4.1
     *
     * @param root
     * @return
     */
    boolean isBalanceBTree(BTNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(countDepth(root.left) - countDepth(root.right)) <= 1;
    }

    /**
     * 4.1 test
     *
     * @param app
     */
    static void testIsBalanceBTree(Main app) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
//        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);
//        root.right.left = new BTNode(6);
//        root.right.right = new BTNode(7);
        System.out.println(app.countDepth(root));
        System.out.println(app.isBalanceBTree(root));
    }

    /**
     * 4.3
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    BTNode createBinarySearchTree(int[] nums, int left, int right) {
        if (left <= right) {
            if (left == right) {
                return new BTNode(nums[left]);
            } else {
                int l = left, r = right;
                while (l < r) {
                    int mid = (l + r) / 2;
                    BTNode node = new BTNode(nums[mid]);
                    node.left = createBinarySearchTree(nums, l, mid - 1);
                    node.right = createBinarySearchTree(nums, mid + 1, r);
                    return node;
                }
            }
        }
        return null;
    }

    /**
     * 4.3 test
     *
     * @param app
     */
    static void testCreateBinarySearchTree(Main app) {
        int M = 10;

        int[] nums = new int[M];
        for (int i = 0; i < M; i++) {
            nums[i] = i + 1;
        }
        BTNode head = app.createBinarySearchTree(nums, 0, nums.length - 1);
        preOrder(head);
        System.out.println("--------------------------------");
        inOrder(head);
        System.out.println("--------------------------------");
        postOrder(head);
        System.out.println("--------------------------------");
    }

    /**
     * 4.5
     *
     * @param head
     * @return
     */
    boolean isBinarySearchTree(BTNode head) {
        if (head != null) {
            if (head.left != null && head.right != null) {
                return head.left.data <= head.data
                        && head.data <= head.right.data
                        && isBinarySearchTree(head.left)
                        && isBinarySearchTree(head.right);
            } else if (head.left == null && head.right != null) {
                return head.data <= head.right.data
                        && isBinarySearchTree(head.right);
            } else if (head.right == null && head.left != null) {
                return head.left.data <= head.data
                        && isBinarySearchTree(head.left);
            }
        }
        return true;
    }

    /**
     * 4.5 test
     *
     * @param app
     */
    static void testIsBinarySearchTree(Main app) {
        int M = 10;

        int[] nums = new int[M];
        for (int i = 0; i < M; i++) {
            nums[i] = i + 1;
        }
        BTNode head = app.createBinarySearchTree(nums, 0, nums.length - 1);
        System.out.println(app.isBinarySearchTree(head));
    }

    /**
     * 4.9
     *
     * @param nodes
     */
    void printNode(ArrayList<BTNode> nodes) {
        for (BTNode node : nodes) {
            System.out.print(String.format("%d=>", node.data));
        }
        System.out.println("||\n--------------------------------");
    }

    void printNode(BTNode[] nodes, int length) {
        if (length <= nodes.length && length > 0) {
            for (int i = 0; i < length; i++) {
                System.out.print(String.format("%d=>", nodes[i].data));
            }
            System.out.println("||\n--------------------------------");
        }
    }

    boolean isLeaf(BTNode node) {
        return node != null && node.left == null && node.right == null;
    }

    /**
     * 4.9
     *
     * @param head
     * @param queue
     * @param value
     */
    void trace(BTNode head, ArrayList<BTNode> queue, int value) {
        if (head != null) {
            if (head.data == value) {
                queue.add(head);
                printNode(queue);
                queue.clear();
            } else if (head.data < value) {
                queue.add(head);
                trace(head.left, queue, value - head.data);

            }
        }
    }

    /**
     * 4.9
     *
     * @param head
     * @param nodes
     * @param pos
     * @param val
     */
    void tracePath(BTNode head, BTNode[] nodes, int pos, int val) {
        if (head != null) {
            if (head.data == val) {
                nodes[pos++] = head;
                printNode(nodes, pos);
            } else if (head.data < val) {
                nodes[pos++] = head;
                tracePath(head.left, nodes, pos, val - head.data);
                tracePath(head.right, nodes, pos, val - head.data);
            } else {
                tracePath(head.left, nodes, pos, val - head.data);
                tracePath(head.right, nodes, pos, val - head.data);
            }
        }
    }

    /**
     * 4.9 driver
     *
     * @param head
     * @param value
     */
    void traceDriver(BTNode head, int value) {
        if (head != null) {
            BTNode[] nodes = new BTNode[100];
            tracePath(head, nodes, 0, value);
            traceDriver(head.left, value);
            traceDriver(head.right, value);
        }
    }

    /**
     * 4.9 test
     *
     * @param app
     */


    /**
     * 4.9
     *
     * @param app
     */
    static void testTrace(Main app) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);
        root.right.left = new BTNode(6);
        root.right.right = new BTNode(7);
//        Queue<BTNode> queue = new LinkedList<BTNode>();
        app.traceDriver(root, 7);
    }

    /**
     * 4.7
     *
     * @param head
     * @param node
     * @return
     */
    boolean contains(BTNode head, BTNode node) {
        if (head != null) {
            if (head == node) {
                return true;
            } else {
                return contains(head.left, node) || contains(head.right, node);
            }
        }
        return false;
    }

    /**
     * 4.7
     *
     * @param head
     * @param node1
     * @param node2
     * @return
     */
    BTNode ancestor(BTNode head, BTNode node1, BTNode node2) {
        if (contains(head, node1) && contains(head, node2)) {
            if (contains(head.left, node1) && contains(head.left, node2)) {
                return ancestor(head.left, node1, node2);
            } else if (contains(head.right, node1) && contains(head.right, node2)) {
                return ancestor(head.right, node1, node2);
            } else {
                return head;
            }
        }
        return null;
    }

    /**
     * 4.7 test
     *
     * @param app
     */
    static void testAncestor(Main app) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);
        root.right.left = new BTNode(6);
        root.right.right = new BTNode(7);
        BTNode node = app.ancestor(root, root.right.left, root.left);
        if (node != null) {
            System.out.println(node.data);
        }
    }

    /**
     * TryCatchFinally
     *
     * @return
     */
    static int testTryCatchFinally() {
        try {
            return 0;
        } catch (Exception e) {

        } finally {
            return 1;
        }

    }

    /**
     * 4.8
     *
     * @param root1
     * @param root2
     * @return
     */
    boolean isSubTree(BTNode root1, BTNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null && root2 != null || root1 != null && root2 == null) {
            return false;
        } else {
            if (root1.data == root2.data) {
                return isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
            } else {
                return isSubTree(root1.left, root2) || isSubTree(root1.right, root2);
            }
        }
    }

    /**
     * 4.8 test
     *
     * @param app
     */
    static void testIsSubTree(Main app) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);
        root.right.left = new BTNode(6);
        root.right.right = new BTNode(7);

        BTNode root2 = new BTNode(3);
        root2.left = new BTNode(6);
        root2.right = new BTNode(7);

        System.out.println(app.isSubTree(root, root2));
    }

    static void testInOrder(Main app) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);
        root.right.left = new BTNode(6);
        root.right.right = new BTNode(7);
        inOrder(root);
    }

    boolean isLeaf(BTSNode node) {
        return node != null && node.left == null && node.right == null;
    }

    /**
     * 4.6
     *
     * @param node
     * @return
     */
    BTSNode next(BTSNode node) {
        if (node != null) {
            if (isLeaf(node)) {
                BTSNode tmp = node;
                if (node.parent.right == node) {
                    while (node.parent != null) {
                        node = node.parent;
                    }
                    return node.data > tmp.data ? node : null;
                } else if (node.parent.left == node) {
                    return node.parent;
                }
            } else {
                return node.right;
            }
        }
        return null;
    }

    /**
     * 4.6 test
     *
     * @param app
     */
    static void testNext(Main app) {
        BTSNode root = new BTSNode(1);
        root.parent = null;
        root.left = new BTSNode(2);
        root.left.parent = root;
        root.right = new BTSNode(3);
        root.right.parent = root;
        root.left.left = new BTSNode(4);
        root.left.left.parent = root.left;
        root.left.right = new BTSNode(5);
        root.left.right.parent = root.left;
        root.right.left = new BTSNode(6);
        root.right.left.parent = root.right;
        root.right.right = new BTSNode(7);
        root.right.right.parent = root.right;

        BTSNode node = app.next(root);
        if (node != null) {
            System.out.println(String.format("%d-->%d", root.data, node.data));
        }
        node = app.next(root.left);
        if (node != null) {
            System.out.println(String.format("%d-->%d", root.left.data, node.data));
        }
        node = app.next(root.left.left);
        if (node != null) {
            System.out.println(String.format("%d-->%d", root.left.left.data, node.data));
        }
        node = app.next(root.left.right);
        if (node != null) {
            System.out.println(String.format("%d-->%d", root.left.right.data, node.data));
        }
        node = app.next(root.right);
        if (node != null) {
            System.out.println(String.format("%d-->%d", root.right.data, node.data));
        }
        node = app.next(root.right.left);
        if (node != null) {
            System.out.println(String.format("%d-->%d", root.right.left.data, node.data));
        }
        node = app.next(root.right.right);
        if (node != null) {
            System.out.println(String.format("%d-->%d", root.right.right.data, node.data));
        }
    }

    /**
     * 4.4
     *
     * @param root
     * @return
     */
    LinkedList<LinkedList<BTNode>> genList(BTNode root) {
        LinkedList<LinkedList<BTNode>> list = new LinkedList<>();
        Queue<BTNode> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<BTNode> temp = new LinkedList<BTNode>();
            Queue<BTNode> nodes = new LinkedList<BTNode>();
            while (!queue.isEmpty()) {
                BTNode node = queue.poll();

                temp.add(node);

                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            queue.addAll(nodes);

            list.add(temp);
        }
        return list;
    }

    /**
     * 4.4 test
     *
     * @param app
     */
    static void testGenList(Main app) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);
        root.right.left = new BTNode(6);
        root.right.right = new BTNode(7);
        LinkedList<LinkedList<BTNode>> list = app.genList(root);
        for (LinkedList<BTNode> nodes : list) {
            for (BTNode node : nodes) {
                System.out.print(String.format("%8d", node.data));
            }
            System.out.println("\n----------------");
        }
    }


    /**
     * 4.1 get tree depth
     *
     * @param root
     * @return
     */
    int getDepth(BTNode root) {
        if (root != null) {
            return 1 + Math.max(getDepth(root.left), getDepth(root.right));
        } else {
            return 0;
        }
    }

    /**
     * 4.1 check if is a binary blance tree
     *
     * @param root
     * @return
     */
    boolean isBlance(BTNode root) {
        if (root == null) {
            return true;
        } else {
            if (Math.abs(getDepth(root.left) - getDepth(root.right)) > 1) {
                return false;
            } else {
                return isBlance(root.left) && isBlance(root.right);
            }
        }
    }

    /**
     * 4.1
     *
     * @param root
     * @return
     */
    int isBlanceBetter(BTNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = isBlanceBetter(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = isBlanceBetter(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 4.1
     *
     * @param root
     * @return
     */
    boolean isBlanceDriver(BTNode root) {
        if (isBlanceBetter(root) == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 4.1 test
     *
     * @param app
     */
    static void testIsBlanceBetter(Main app) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);
        root.right.left = new BTNode(6);
        root.right.right = new BTNode(7);
        System.out.println(app.isBlanceDriver(root));
    }

    /**
     * 4.1 test
     *
     * @param app
     */
    static void testGetDepth(Main app) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);
        root.right.left = new BTNode(6);
        root.right.right = new BTNode(7);
        System.out.println(app.getDepth(root));
    }

    /**
     * 4.1 test
     *
     * @param app
     */
    static void testIsBlance(Main app) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
//        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);
//        root.right.left = new BTNode(6);
//        root.right.right = new BTNode(7);
        System.out.println(app.isBlance(root));
    }

    /**
     * 4.5
     *
     * @param root
     * @param lastVal
     * @return
     */
    boolean checkBST(BTNode root, int lastVal) {
        if (root == null) {
            return true;
        }
        if (!checkBST(root.left, lastVal)) {
            return false;
        }
        if (root.data < lastVal) {
            return false;
        }
        return checkBST(root.right, root.data);
    }

    /**
     * 4.5
     *
     * @param root
     * @return
     */
    boolean checkBinarySearchTree(BTNode root) {
        return checkBST(root, Integer.MIN_VALUE);
    }

    /**
     * 4.5
     *
     * @param root
     * @param min
     * @param max
     * @return
     */
    boolean checkBSTree(BTNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.data < min || root.data > max) {
            return false;
        }
        return checkBSTree(root.left, min, root.data) && checkBSTree(root.right, root.data, max);
    }

    /**
     * 4.5
     *
     * @param root
     * @return
     */
    boolean checkBSTreeDriver(BTNode root) {
        return checkBSTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * 4.6
     *
     * @param node
     * @return
     */
    BTSNode mostLeftOfRight(BTSNode node) {
        if (node != null) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        return null;
    }

    /**
     * 4.6
     *
     * @param node
     * @return
     */
    BTSNode succeed(BTSNode node) {
        if (node != null) {
            if (node.right != null) {
                return mostLeftOfRight(node.right);
            }
            if (node.parent != null) {
                if (node.parent.left == node) {
                    return node.parent;
                } else {
                    BTSNode cur = node.parent;
                    while (cur != null) {
                        if (cur.left == node) {
                            break;
                        }
                        node = cur;
                        cur = cur.parent;
                    }
                    return cur;
                }
            }
            return null;
        }
        return null;
    }

    /**
     * 4.6 test
     *
     * @param app
     */
    static void testSucceed(Main app) {
        BTSNode root = new BTSNode(1);
        root.left = new BTSNode(2);
        root.right = new BTSNode(3);
        root.left.left = new BTSNode(4);
        root.left.right = new BTSNode(5);
        root.right.left = new BTSNode(6);
        root.right.right = new BTSNode(7);
        root.left.left.left = new BTSNode(8);
        root.left.left.right = new BTSNode(9);
        root.left.right.left = new BTSNode(10);
        root.left.right.right = new BTSNode(11);
        root.right.left.left = new BTSNode(12);
        root.right.left.right = new BTSNode(13);
        root.right.right.left = new BTSNode(14);
        root.right.right.right = new BTSNode(15);

        root.parent = null;

        root.left.parent = root;
        root.right.parent = root;

        root.left.left.parent = root.left;
        root.left.right.parent = root.left;
        root.right.left.parent = root.right;
        root.right.right.parent = root.right;

        root.left.left.left.parent = root.left.left;
        root.left.left.right.parent = root.left.left;
        root.left.right.left.parent = root.left.right;
        root.left.right.right.parent = root.left.right;
        root.right.left.left.parent = root.right.left;
        root.right.left.right.parent = root.right.left;
        root.right.right.left.parent = root.right.right;
        root.right.right.right.parent = root.right.right;

        System.out.println(app.succeed(root.left.left.left).data);
        System.out.println(app.succeed(root.left.left.right).data);
        System.out.println(app.succeed(root.left.right.left).data);
        System.out.println(app.succeed(root.left.right.right).data);
        System.out.println(app.succeed(root.right.left.left).data);
        System.out.println(app.succeed(root.right.left.right).data);
        System.out.println(app.succeed(root.right.right.left).data);
        System.out.println(app.succeed(root).data);
        System.out.println(app.succeed(root.right).data);
        try {
            System.out.println(app.succeed(root.right.right.right).data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * if contains node of tree root
     *
     * @param root
     * @param node
     * @return
     */
    boolean has(BTNode root, BTNode node) {
        if (root != null) {
            if (root == node) {
                return true;
            }
            return has(root.left, node) || has(root.right, node);
        }
        return false;
    }

    /**
     * if contains node of tree root
     *
     * @param root
     * @param node
     * @return
     */
    boolean has(BTSNode root, BTSNode node) {
        if (root != null) {
            if (root == node) {
                return true;
            }
            return has(root.left, node) || has(root.right, node);
        }
        return false;
    }

    /**
     * test
     *
     * @param app
     */
    static void testHas(Main app) {
        BTSNode root = new BTSNode(1);
        root.left = new BTSNode(2);
        root.right = new BTSNode(3);
        root.left.left = new BTSNode(4);
        root.left.right = new BTSNode(5);
        root.right.left = new BTSNode(6);
        root.right.right = new BTSNode(7);
        root.left.left.left = new BTSNode(8);
        root.left.left.right = new BTSNode(9);
        root.left.right.left = new BTSNode(10);
        root.left.right.right = new BTSNode(11);
        root.right.left.left = new BTSNode(12);
        root.right.left.right = new BTSNode(13);
        root.right.right.left = new BTSNode(14);
        root.right.right.right = new BTSNode(15);

        root.parent = null;

        root.left.parent = root;
        root.right.parent = root;

        root.left.left.parent = root.left;
        root.left.right.parent = root.left;
        root.right.left.parent = root.right;
        root.right.right.parent = root.right;

        root.left.left.left.parent = root.left.left;
        root.left.left.right.parent = root.left.left;
        root.left.right.left.parent = root.left.right;
        root.left.right.right.parent = root.left.right;
        root.right.left.left.parent = root.right.left;
        root.right.left.right.parent = root.right.left;
        root.right.right.left.parent = root.right.right;
        root.right.right.right.parent = root.right.right;

        System.out.println(app.has(root, root.left.left.left));
    }

    BTNode selfParent(BTNode root, BTNode node) {
        if (root != null) {
            if (root == node || root.left == node || root.right == node) {
                return root;
            }
            BTNode p = selfParent(root.left, node);
            return p != null ? p : selfParent(root.right, node);
        }
        return null;
    }

    BTNode parent(BTNode root, BTNode a, BTNode b) {
        if (root != null) {
            BTNode pa = selfParent(root, a);
            BTNode pb = selfParent(root, b);
            if (pa == pb) {
                return pa;
            }
            return parent(root, pa, pb);
        }
        return null;
    }

    BTNode parentDriver(BTNode root, BTNode a, BTNode b) {
        if (root != null && a != null && b != null && has(root, a) && has(root, b)) {
            return parent(root, a, b);
        }
        return null;
    }

    static void testParent(Main app) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);
        root.right.left = new BTNode(6);
        root.right.right = new BTNode(7);
        root.left.left.left = new BTNode(8);
        root.left.left.right = new BTNode(9);
        root.left.right.left = new BTNode(10);
        root.left.right.right = new BTNode(11);
        root.right.left.left = new BTNode(12);
        root.right.left.right = new BTNode(13);
        root.right.right.left = new BTNode(14);
        root.right.right.right = new BTNode(15);
        System.out.println(app.parentDriver(root, root.left.left.left, root.right.right.right).data);
        System.out.println(app.parentDriver(root, root.left.left.left, root.left.right.right).data);
    }

    BTNode findParent(BTNode root, BTNode a, BTNode b) {
        if (root == null) {
            return null;
        }
        if (root == a && root == b) {
            return root;
        }
        BTNode pa = findParent(root.left, a, b);
        if (pa != null && pa != a && pa != b) {
            return pa;
        }
        BTNode pb = findParent(root.right, a, b);
        if (pb != null && pb != a && pb != b) {
            return pb;
        }
        if (pa != null && pb != null) {
            return root;
        } else if (root == pa || root == pb) {
            return root;
        } else {
            return pa == null ? pb : pa;
        }
    }


    /**
     * 4.7
     *
     * @param root
     * @param a
     * @param b
     * @return
     */
    BTNode findCommonAncestor(BTNode root, BTNode a, BTNode b) {
        if (root != null) {
            if (has(root.left, a)) {
                if (has(root.left, b)) {
                    return findCommonAncestor(root.left, a, b);
                } else {
                    return root;
                }
            } else {
                if (has(root.right, b)) {
                    return findCommonAncestor(root.right, a, b);
                } else {
                    return root;
                }
            }
        }
        return null;
    }

    /**
     * 4.7
     *
     * @param root
     * @param a
     * @param b
     * @return
     */
    BTNode findCommonAncestorDriver(BTNode root, BTNode a, BTNode b) {
        if (root != null && a != null && b != null && has(root, a) && has(root, b)) {
            return findCommonAncestor(root, a, b);
        }
        return null;
    }

    /**
     * 4.7 test
     *
     * @param app
     */
    static void testFindParent(Main app) {
        BTNode root = new BTNode(1);
        root.left = new BTNode(2);
        root.right = new BTNode(3);
        root.left.left = new BTNode(4);
        root.left.right = new BTNode(5);
        root.right.left = new BTNode(6);
        root.right.right = new BTNode(7);
        root.left.left.left = new BTNode(8);
        root.left.left.right = new BTNode(9);
        root.left.right.left = new BTNode(10);
        root.left.right.right = new BTNode(11);
        root.right.left.left = new BTNode(12);
        root.right.left.right = new BTNode(13);
        root.right.right.left = new BTNode(14);
        root.right.right.right = new BTNode(15);

        BTNode parentNode = app.findCommonAncestorDriver(root, root.left.left.left, root.left.right);
        if (parentNode != null) {
            System.out.println(parentNode.data);
        } else {
            System.out.println(parentNode);
        }
    }

    /**
     * 4.8
     * check is same tree
     *
     * @param a
     * @param b
     * @return
     */
    boolean isSameTree(BTNode a, BTNode b) {
        if (a != null && b != null) {
            if (a.data == b.data) {
                return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
            } else {
                return false;
            }
        } else if (a == null && b == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 4.8
     * @param a
     * @param b
     * @return
     */
    boolean isSubTree2(BTNode a, BTNode b) {
        if (a != null && b != null) {
            if (a.data == b.data && isSameTree(a, b)) {
                return true;
            } else {
                return isSubTree2(a.left, b) || isSubTree2(a.right, b);
            }
        } else if (a != null && b == null) {
            return true;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
//        testIsBalanceBTree(app);
//        testCreateBinarySearchTree(app);
//        testIsBinarySearchTree(app);
//        testTrace(app);
//        testAncestor(app);
//        System.out.println(testTryCatchFinally());
//        testIsSubTree(app);
//        testInOrder(app);
//        testNext(app);
//        testGenList(app);
//        testGetDepth(app);
//        testIsBlance(app);
//        testIsBlanceBetter(app);
//        testSucceed(app);
//        testHas(app);
//        testParent(app);
        testFindParent(app);
    }
}
