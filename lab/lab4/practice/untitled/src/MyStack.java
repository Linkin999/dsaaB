/*import edu.princeton.cs.algs4.Heap;

import java.util.NoSuchElementException;

public class MyStack<T> {

     static class node<T>{
        T data;
        Node<T> next;
        Node(T d){
            data = d;
            next = null;
        }
    }

    private

    public void push(T element){
        Node<T> node = new Node<>(element); //create a new node
        node.next=head;
        head = node;
    }

    public T peek(){
         if (head==null)
             throw new NoSuchElementException("Stack is empty");
        return head.data;
    }

    public T pop(){
        if (head==null)
            throw new NoSuchElementException("Stack is empty");
        return head.data;
        T result = head.data;
        head=head.next;
        return result;
    }
}*/
