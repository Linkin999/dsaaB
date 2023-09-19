import java.util.Iterator;

public class QueueOfInitial <Item> implements Iterable<Item>{

    private Node first; //
    private Node last;
    private int N;


    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){return first==null;}
    public int size(){return N;}

    public Item dequeue(){ //向表头删除元素
        Item item= first.item;
        first= first.next;
        if (isEmpty()){
            last=null;
        }
        N--;
        return item;

    }
    public void enqueue(Item item){      //从表尾添加元素
        Node oldlast=last;
        last=new Node();
        last.item=item;
        last.next=null;
        if (isEmpty()){
            first=last;
        }
        else {
            oldlast.next=last;
        }
        N++;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current=first;
        public boolean hasNext(){return current!=null;}
        public Item next(){
            Item item=current.item;
            current=current.next;
            return item;
        }
    }
}
