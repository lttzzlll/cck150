package queue;

import sun.rmi.server.InactiveGroupException;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * Created by 10609 on 2017/5/8.
 */
interface MyStack {
    void push(int item);

    int pop();

    boolean isEmpty();

    int peek();

    int min();
}

/**
 * 3.1
 * Three Stack using one array
 */
class StackFactory {
    private int size;
    private int[] store;
    private int p1 = 0, p2 = 0, p3 = 0;
    private int step = 3;

    public StackFactory(int size) {
        this.size = size;
        store = new int[size];
    }

    public boolean isEmpty1() {
        return p1 < 1;
    }

    public boolean isEmpty2() {
        return p2 < 2;
    }

    public boolean isEmpty3() {
        return p3 < 3;
    }

    public int peek1() {
        if (!isEmpty1()) {
            return store[p1];
        }
        return -1;
    }

    public int peek2() {
        if (!isEmpty2()) {
            return store[p2];
        }
        return -1;
    }

    public int peek3() {
        if (!isEmpty3()) {
            return store[p3];
        }
        return -1;
    }

    public int pop1() {
        if (!isEmpty1()) {
            p1 -= 3;
            return store[p1 + 3];
        }
        return -1;
    }

    public int pop2() {
        if (!isEmpty2()) {
            p2 -= 3;
            return store[p2 + 3];
        }
        return -1;
    }

    public int pop3() {
        if (!isEmpty3()) {
            p3 -= 3;
            return store[p3 + 3];
        }
        return -1;
    }

    public void push1(int item) {
        if (p1 == 0) {
            p1 = 1;
        } else {
            p1 += 3;
        }
        store[p1] = item;
    }

    public void push2(int item) {
        if (p2 == 0) {
            p2 = 2;
        } else {
            p2 += 3;
        }
        store[p2] = item;
    }

    public void push3(int item) {
        p3 += 3;
        store[p3] = item;
    }
}

/**
 * helper Node
 */
class Node {
    int data;
    Node next = null;

    public Node(int data) {
        this.data = data;
    }
}

/**
 * 3.2
 */
class MyMinStack implements MyStack {

    Node head = null;
    Node minptr = null;

    @Override
    public void push(int item) {
        Node node = new Node(item);
        node.next = head;
        head = node;
        if (minptr == null) {
            minptr = head;
        } else {
            if (item < minptr.data) {
                minptr = node;
            }
        }
    }

    @Override
    public int pop() {
        if (!isEmpty()) {
            Node cur = head;
            head = head.next;
            return cur.data;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int peek() {
        if (!isEmpty()) {
            return head.data;
        }
        return -1;
    }

    @Override
    public int min() {
        if (!isEmpty()) {
            return minptr.data;
        }
        return -1;
    }
}

/**
 * 3.3
 */
class SetOfStack {
    LinkedList<Stack<Integer>> list = new LinkedList<Stack<Integer>>();
    int size = 10;

    public SetOfStack(int size) {
        this.size = size;
    }

    boolean isEmpty() {
        return list.isEmpty();
    }

    void push(int item) {
        if (isEmpty()) {
            Stack<Integer> stack = new Stack<Integer>();
            stack.push(item);
            list.add(stack);
        } else {
            if (list.getLast().size() == size) {
                Stack<Integer> stack = new Stack<Integer>();
                stack.push(item);
                list.add(stack);
            } else {
                list.getLast().add(item);
            }
        }
    }

    int pop() {
        if (isEmpty()) {
            return -1;
        }
        if (list.getLast().isEmpty()) {
            list.removeLast();
            if (list.isEmpty()) {
                return -1;
            } else {
                return list.getLast().pop();
            }
        } else {
            return list.getLast().pop();
        }
    }
}

/**
 * 3.5 helper
 */
interface MyQueue {
    boolean isEmpty();

    void enqueue(int item);

    int dequeue();
}

/**
 * 3.5
 */
class StackToQueue implements MyQueue {
    Stack<Integer> s1;
    Stack<Integer> s2;

    public StackToQueue() {
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
    }

    @Override
    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    @Override
    public void enqueue(int item) {
        s1.push(item);
    }

    @Override
    public int dequeue() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        int val = s2.pop();
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return val;
    }
}

/**
 * 3.6 helper
 */
interface MiStack {
    boolean isEmpty();

    void push(int item);

    int pop();

    int peek();
}

/**
 * 3.6
 */
class OrderedStack implements MiStack {
    private Stack<Integer> stack;

    public OrderedStack() {
        stack = new Stack<Integer>();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void push(int item) {
        if (stack.isEmpty()) {
            stack.push(item);
        } else {
            if (stack.peek() > item) {
                Stack<Integer> temp = new Stack<Integer>();
                while (!stack.isEmpty() && stack.peek() > item) {
                    temp.push(stack.pop());
                }
                stack.push(item);
                while (!temp.isEmpty()) {
                    stack.push(temp.pop());
                }
            }
        }
    }

    @Override
    public int pop() {
        return stack.pop();
    }

    @Override
    public int peek() {
        return stack.peek();
    }
}

class HanoiState {
    int n;
    String from;
    String via;
    String to;

    public HanoiState(int n, String from, String via, String to) {
        this.n = n;
        this.from = from;
        this.via = via;
        this.to = to;
    }
}

/**
 * 3.7
 */
abstract class CatAndDog {
    int count;
    String type;

    public CatAndDog(int count, String type) {
        this.count = count;
        this.type = type;
    }

}

class Cat extends CatAndDog {
    public Cat(int count, String type) {
        super(count, type);
    }

    public Cat() {
        super(0, "cat");
    }
}

class Dog extends CatAndDog {
    public Dog(int count, String type) {
        super(count, type);
    }

    public Dog() {
        super(0, "dog");
    }
}

class CatAndDogQueue {
    Queue<CatAndDog> cats;
    Queue<CatAndDog> dogs;

    private int count = 0;

    private int getCount() {
        return ++count;
    }

    public CatAndDogQueue() {
        cats = new LinkedList<>();
        dogs = new LinkedList<>();
    }

    public void enqueue(CatAndDog item) {
        // set the count
        item.count = getCount();
        if (item.type.equals("dog")) {
            dogs.add(item);
        } else if (item.type.equals("cat")) {
            cats.add(item);
        } else {
//            throw new Exception(" type error ");
        }
    }

    public CatAndDog dequeueAny() {
        if (cats.isEmpty() && !dogs.isEmpty()) {
            return dogs.poll();
        } else if (dogs.isEmpty() && !cats.isEmpty()) {
            return cats.poll();
        } else if (!dogs.isEmpty() && !cats.isEmpty()) {
            if (dogs.peek().count < cats.peek().count) {
                return dogs.poll();
            } else if (cats.peek().count < dogs.peek().count) {
                return cats.poll();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public CatAndDog dequeueDog() {
        return dogs.poll();
    }

    public CatAndDog dequeueCat() {
        return cats.poll();
    }

    public boolean isEmpty() {
        return cats.isEmpty() && dogs.isEmpty();
    }

}

public class Main {
    /**
     * 3.4
     *
     * @param n
     * @param from
     * @param via
     * @param to
     * @return
     */
    int hanoi(int n, String from, String via, String to) {
        if (n == 1) {
            System.out.println(String.format("Move %d from %s to %s", 1, from, to));
            return 1;
        } else {
            int res1 = hanoi(n - 1, from, to, via);
            System.out.println(String.format("Move %d from %s to %s", n, from, to));
            int res3 = hanoi(n - 1, via, from, to);
            return res1 + 1 + res3;
        }
    }

    int hanoiStack(int n, String from, String via, String to) {
        int res = 0;
        Stack<HanoiState> stack = new Stack<HanoiState>();
        HanoiState init = new HanoiState(n, from, via, to);
        stack.push(init);
        while (!stack.isEmpty()) {
            HanoiState state = stack.pop();
            if (state.n == 1) {
                System.out.println(String.format("Move %d from %s to %s", 1, state.from, state.to));
                res += 1;
            } else {

            }
        }
        return res;
    }

    /**
     * 3.1 test
     *
     * @param app
     */
    static void testStackFactory(Main app) {
        StackFactory stackFactory = new StackFactory(1000);
        stackFactory.push1(1);
        stackFactory.push1(2);
        stackFactory.push1(3);
        while (!stackFactory.isEmpty1()) {
            System.out.println(String.format("%d, %d", stackFactory.peek1(), stackFactory.pop1()));
        }

        stackFactory.push2(1);
        stackFactory.push2(2);
        stackFactory.push2(3);
        while (!stackFactory.isEmpty2()) {
            System.out.println(String.format("%d, %d", stackFactory.peek2(), stackFactory.pop2()));
        }
        stackFactory.push3(1);
        stackFactory.push3(2);
        stackFactory.push3(3);
        while (!stackFactory.isEmpty3()) {
            System.out.println(String.format("%d, %d", stackFactory.peek3(), stackFactory.pop3()));
        }
    }

    /**
     * 3.2 test
     *
     * @param app
     */
    static void testMyMinStack(Main app) {
        MyMinStack myMinStack = new MyMinStack();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int val = Math.abs(random.nextInt() % 10);
            System.out.println(val);
            myMinStack.push(val);
            System.out.println(String.format("min %d, peek: %d", myMinStack.min(), myMinStack.peek()));
        }
        while (!myMinStack.isEmpty()) {
            System.out.println(String.format("min %d, peek: %d, pop: %d", myMinStack.min(), myMinStack.peek(), myMinStack.pop()));
        }
    }

    /**
     * 3.3 test
     *
     * @param app
     */
    static void testSetOfStack(Main app) {
        SetOfStack stack = new SetOfStack(10);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int val = Math.abs(random.nextInt() % 10);
            stack.push(val);
        }
        System.out.println("----------------------------------------------");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 3.5
     *
     * @param app
     */
    static void testStackToQueue(Main app) {
        StackToQueue stackToQueue = new StackToQueue();
        for (int i = 0; i < 10; i++) {
            stackToQueue.enqueue(i);
        }
        while (!stackToQueue.isEmpty()) {
            System.out.println(stackToQueue.dequeue());
        }
    }

    /**
     * 3.6 test
     *
     * @param app
     */
    static void testOrderedStack(Main app) {
        Random random = new Random();
        OrderedStack orderedStack = new OrderedStack();
        for (int i = 0; i < 10; i++) {
            int val = Math.abs(random.nextInt() % 10);
            System.out.println(val);
            orderedStack.push(val);
        }
        System.out.println("-----------------------------------");
        while (!orderedStack.isEmpty()) {
            System.out.println(orderedStack.pop());
        }
    }

    /**
     * 3.4 test
     *
     * @param app
     */
    static void testHanoi(Main app) {
        for (int i = 1; i <= 4; i++) {
            System.out.println(app.hanoi(i, "A", "B", "C"));
        }
    }

    /**
     * 3.4 test stack version
     *
     * @param app
     */
    static void testHanoiStack(Main app) {
        for (int i = 1; i <= 4; i++) {
            System.out.println(app.hanoiStack(i, "A", "B", "C"));
        }
    }

    static void testCatAndDog(Main app) {
        Cat cat1 = new Cat();
        Dog dog1 = new Dog();
        Cat cat2 = new Cat();
        Dog dog2 = new Dog();
        Cat cat3 = new Cat();
        Dog dog3 = new Dog();


        CatAndDogQueue catAndDogQueue = new CatAndDogQueue();
        catAndDogQueue.enqueue(cat1);
        catAndDogQueue.enqueue(cat2);
        catAndDogQueue.enqueue(cat3);
        catAndDogQueue.enqueue(dog1);
        catAndDogQueue.enqueue(dog2);
        catAndDogQueue.enqueue(dog3);

        while (!catAndDogQueue.isEmpty()) {
            CatAndDog item = catAndDogQueue.dequeueAny();
            System.out.println(String.format("type: %s, count: %d", item.type, item.count));
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
//        testStackFactory(app);
//        testMyMinStack(app);
//        testSetOfStack(app);
//        testStackToQueue(app);
//        testOrderedStack(app);
//        testHanoi(app);
//        testHanoiStack(app);
        testCatAndDog(app);
    }
}
