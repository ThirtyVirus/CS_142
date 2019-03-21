package Nodes;

/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Abstract class to represent a unary operator Unary operators only have a single child
 */
public abstract class UnaryOperatorNode extends java.lang.Object implements MerpNode{

    protected MerpNode child;
    protected java.lang.String operator;
    protected Precedence precedence;

    //Constructor
    public UnaryOperatorNode(MerpNode child, Precedence precedence, java.lang.String operator){
        this.child = child;
        this.precedence = precedence;
        this.operator = operator;
    }

    //Determines the node type
    public MerpNode.NodeType getNodeType(){
        return NodeType.UnaryOperation;
    }

    //Returns the precedence of this node
    public int getPrecedence(){
        return precedence.getPrecedence();
    }

    //determines if the node is an operation node
    public boolean isOperation(){
        return true;
    }

    //Sets the child of this node
    public void setChild(MerpNode child){
        this.child = child;
    }

    //Displays this node as infix notation expression string
    public java.lang.String toInfixString(){
        if (child == null){return operator;}
        return ("(" + operator + " " + child.toInfixString() + ")");
    }

    //Displays this node as postfix notation expression string
    public java.lang.String toPostfixString(){
        if (child == null){return operator;}
        return (child.toPostfixString() + " " + operator);
    }

    //Displays this node as prefix notation expression string
    public java.lang.String toPrefixString(){
        if (child == null){return operator;}
        return (operator + " " + child.toPrefixString());
    }

}
