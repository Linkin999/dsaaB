import java.util.Iterator;

/*implement non-recursive */ 
public class BST<Key extends Comparable<Key>,Value> implements Iterable<KVPair<Key,Value>> {

    private class Node{
        Key key;
        Value val;
        Node left = null;
        Node right = null;
        Node parent = null;
        public Node(Key k,Value v){
            key = k;
            val = v;
        }

    }

    private Node root = null;
    private int size = 0;

    public Value get(Key key){
        Node node = root;
        while(node != null){
            int compare =  key.compareTo(node.key);
            if( compare<0 ){
                node=node.left;
            }
            else if(compare>0){
                node = node.right;
            }
            else{
                return node.val;
            }
        }
        return null;
    }
    public int size(){
        return size;
    }

    public Value put(Key key,Value val){
        if(key == null||val == null)
            throw new IllegalArgumentException("Key or val should noe be null");
        if(root == null){ 
            root = new Node(key,val);
            size=1;
            return null;
        }
            

        Node node = root;
        while(node !=null){
            int compare= key.compareTo(node.key);
            if(compare<0)
                if(node.left==null){
                    node.left = new Node(key,val);
                    node.left.parent=node;
                    size++;
                    return null;
                }
                else{
                node = node.left;
                }
            else if(compare>0){
                if(node.right==null){
                    node.right=new Node(key,val);
                    node.right.parent=node;
                    size++;
                    return null;
                }
                else{
                    node = node.right;
                }
            }
            else{
                Value ret=node.val;
                node.val = val;
                return ret;
            }
        }
        return null;

    }

    private void replaceNodeWithChild(Node node,Node child){
        if(node ==root){
            root = child;
        }
        else if(node==node.parent.left){
            node.parent.left=child;
        }
        else{
            node.parent.right=child;
        }

        if(child!=null){
            child.parent = node.parent;
        }
    }

    private

    private Node deleteMin(Node node){
        node = min(node);
    }

    public Value delete(Key key){
        if(key==null){
            throw new IllegalArgumentException("Key should noe be null");
        }
        if(root==null){
            return null;
        }
        
        Node node = root;
         while(node!=null){
            int compare=key.compareTo(node.key);
            if(compare<0){
                node = node.left;
            }
            else if(compare>0){
                node=node.right;
            }
            else{
                if(node.left==null){
                    replaceNodeWithChild(node,  node.right);
                    return node.val;
                }
                else if(node.right==null){
                    replaceNodeWithChild(node, node.left);
                    return node.val;
                }
                else{
                    Node min=deleteMin(node.right);
                    Value ret =
                }

            }

         }
    }
    
    private class MyIterator implements Iterator<KVPair<Key,Value>>{

        private Node node;
        
        public MyIterator(){
            node =root;
            if(node!=null){
                while(node.left!=null)
                    node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public KVPair<Key, Value> next() {
            // TODO Auto-generated method stub
            KVPair<Key,Value> ret = new KVPair<Key,Value>(null, null);
            return null;
        }
        
    }

    @Override
    public Iterator<KVPair> iterator() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
