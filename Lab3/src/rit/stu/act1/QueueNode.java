package rit.stu.act1;

import rit.cs.Node;
import rit.cs.Queue;

/**
 * A queue implementation that uses a Node to represent the structure.
 * @param <T> The type of data the queue will hold
 * @author Sean Strout @ RIT CS
 * @author Brandon Calabrese
 */
public class QueueNode<T> implements Queue<T> {
    private Node<T> queue;
    private int size;

    /**
     * Create an empty queue.
     */
    public QueueNode() { this.queue = null; }

    //returns the back of the queue
    public T back() {
        assert !empty();
        Node<T> subject = queue;
        for (int counter  = 0; counter < size - 1; counter ++){
            subject = subject.getNext();
        }
        return subject.getData();
    }

    //removes element from front of queue
    public T dequeue() {
        //assert !empty();
        T data = queue.getData();
        queue = queue.getNext();
        size -= 1;
        return data;
    }

    //returns whether or not the queue is empty
    public boolean empty() {
        return (queue == null);
    }

    //Adds to the queue
    public void enqueue(T element) {
        if (size == 0){
            queue = new Node<T>(element, null);
        }
        else{
            Node<T> subject = queue;
            for (int counter = 0; counter < size - 1; counter ++){
                subject = subject.getNext();
            }
            subject.setNext(new Node<T>(element, null));
        }
        size += 1;
    }

    //returns the front element of the queue
    public T front() {
        assert !empty();
        return this.queue.getData();
    }
}
