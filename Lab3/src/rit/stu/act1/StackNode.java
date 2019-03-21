package rit.stu.act1;

import rit.cs.Stack;
import rit.cs.Node;

/**
 * A stack implementation that uses a Node to represent the structure.
 * @param <T> The type of data the stack will hold
 * @author Sean Strout @ RIT CS
 * @author Brandon Calabrese
 */
public class StackNode<T> implements Stack<T> {
    private Node<T> stack;
    private int size;

    /**
     * Create an empty stack.
     */
    public StackNode() {
        this.stack = null;
    }

    @Override
    public boolean empty() {
        return (stack == null);
    }

    @Override
    public T pop() {
        assert !empty();
        T data = stack.getData();
        stack = stack.getNext();
        size -= 1;
        return data;
    }

    @Override
    public void push(T element) {
        this.stack = new Node(element, stack);
        size += 1;
    }

    @Override
    public T top() {
        assert !empty();
        return this.stack.getData();
    }
}
