package Nodes;

/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Abstract class to represent a binary operator Has a left/right child, precedence, and operator
 */
public abstract class BinaryOperatorNode extends java.lang.Object implements MerpNode{

    protected MerpNode leftChild;
    protected java.lang.String operator;
    protected Precedence precedence;
    protected MerpNode rightChild;

    //Binary Node Constructor
    public BinaryOperatorNode(MerpNode leftChild, MerpNode rightChild, Precedence precedence, java.lang.String operator){
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.precedence = precedence;
        this.operator = operator;
    }

    //Determines the node type
    public MerpNode.NodeType getNodeType(){
        return NodeType.BinaryOperation;
    }

    //Returns the precedence of this node
    public int getPrecedence(){
        return precedence.getPrecedence();
    }

    //determines if the node is an operation node
    public boolean isOperation(){
        return true;
    }

    //Setter for left child
    public void setLeftChild(MerpNode leftChild){
        this.leftChild = leftChild;
    }

    //Setter for right child
    public void setRightChild(MerpNode rightChild){
        this.rightChild = rightChild;
    }

    //Displays this node as infix notation expression string
    public java.lang.String toInfixString(){
        if (leftChild == null && rightChild == null){
            return operator;
        }
        return "(" + leftChild.toInfixString() + " " + operator + " " + rightChild.toInfixString()+ ")";
    }

    //Displays this node as postfix notation expression string
    public java.lang.String toPostfixString(){
        if (leftChild == null && rightChild == null){
            return operator;
        }
        return leftChild.toPostfixString() + " " + rightChild.toPostfixString() + " " + operator;
    }

    //Displays this node as prefix notation expression string
    public java.lang.String toPrefixString(){
        if (leftChild == null && rightChild == null){
            return operator;
        }
        return operator + " " + leftChild.toPrefixString() + " " + rightChild.toPrefixString();
    }
}
