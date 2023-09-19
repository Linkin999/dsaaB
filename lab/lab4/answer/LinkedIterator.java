import java.util.*;

class Node<Item> {
    Item item;
    Node<Item> next;
    Node<Item> pre;

    public Node(Item item) {
        this.item = item;
    }
}

class LinkedIterator<Item> implements Iterator<Item> {
    private Node<Item> current;
    private Node<Item> lastNode = null;


    public LinkedIterator(Node<Item> first) {
        current = first;
    }

    public boolean hasNext() {
        return current != null;
    }

    public void remove() {
        if (lastNode == null) {
            System.out.println("必须先调用next()方法才可以删除元素，程序结束");
            throw new NoSuchElementException();
        }
        if (lastNode.pre != null && lastNode.next != null) {
            lastNode.pre.next = lastNode.next;
            lastNode.next.pre = lastNode.pre;
            lastNode.pre = null;
            lastNode.next = null;
        } else if (lastNode.pre == null && lastNode.next == null) {
            return;
        } else if (lastNode.pre == null) {
            lastNode.next.pre = null;
            lastNode.next = null;
        } else if (lastNode.next == null) {
            lastNode.pre.next = null;
            lastNode.pre = null;
        }
    }

    public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
        Item item = current.item;
        lastNode = current;
        current = current.next;
        return item;
    }

//    简单的测试
//    public static void main(String[] args) {
//        Node<Integer> a = new Node<>(1);
//        Node<Integer> b = new Node<>(2);
//        Node<Integer> c = new Node<>(3);
//        Node<Integer> d = new Node<>(4);
//        a.next = b;
//        b.next = c;
//        c.next = d;
//        d.pre = c;
//        c.pre = b;
//        b.pre = a;
//        LinkedIterator<Integer> iterator = new LinkedIterator<>(a);
//        while (iterator.hasNext()) {
//            int item = iterator.next();
//            System.out.println(item);
//            if (item == 2) iterator.remove();
//        }
//        LinkedIterator<Integer> after = new LinkedIterator<>(a);
//        while (after.hasNext()) {
//            int item = after.next();
//            System.out.println(item);
//        }
//    }
}