
import java.util.LinkedList;
import java.util.ListIterator;

public class HashTable<Key, Value> {

    private static class Node<Key,Value>{
        Key key;
        Value value;

        Node(Key k,Value v){
            key=k;
            value=v;
        }
    }
    private LinkedList <Node<Key,Value>>[] data;

    public HashTable(int capacity){
        data =  new LinkedList[capacity]; 
        for(int i=0;i<data.length;i++){
            data[i]=new LinkedList<>();
        }
    }

    public  int hash(Key key){
        int h= key.hashCode();
        return (h & 0x7ffffff) % data.length;  //2^3!=8
    }

    public Value put(Key key, Value value){
        if(key==null||value==null){
            throw new IllegalArgumentException("Jey or value is null");
        }
        int hash = hash(key);
        for(Node<Key,Value> node:data[hash]){
            if(node.key.equals(key)){
                Value old =node.value;
                node.key=key;
                node.value=value;
                return old;
            }
        }

        data[hash].add(new Node<>(key,value));
        return null;
    }

    public Value get(Key key){
        if(key==null){
            throw new IllegalArgumentException("Jey or value is null");
        }
        int hash = hash(key);
        for(Node<Key,Value> node:data[hash]){
            if(node.key.equals(key)){
                return node.value;
            }
        }
        return null;

    }


    public Value delete(Key key){
        if(key==null){
            throw new IllegalArgumentException("Jey or value is null");
        }
        int hash = hash(key);
        for(ListIterator<Node<Key,Value>> ite = data[hash].listIterator();ite.hasNext();){
            Node<Key,Value> node = ite.next();
            if(node.key.equals(key)){
                ite.remove();
                return node.value;
            }
        }
        return null;
    }













    public static void main(String[] args) throws Exception {
        HashTable<Point,Integer> table=new HashTable<>(5);
        table.put(new Point(-1, -1), 666);
        table.put(new Point(-1, -6), 555);

        

        System.out.println(table.get((new Point(-1,-1))));
        System.out.println((new Point(-1,-6).hashCode() &0x7ffffff)%5);
    }
}
