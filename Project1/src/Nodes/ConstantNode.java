package Nodes;

import Util.SymbolTable;
/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Constant MerpNode
 */
public class ConstantNode extends java.lang.Object implements MerpNode{

    private int	value;

    //Constructor that sets the value of this node
    public ConstantNode(int value){
        this.value = value;
    }

    //Returns the value of this node
    public int evaluate(SymbolTable symbolTable){
        return value;
    }

    //Determines the node type
    public MerpNode.NodeType getNodeType(){
        return NodeType.Constant;
    }

    //Returns the precedence of this node
    public int getPrecedence(){
        return 3;
    }

    //determines if the node is an operation node
    public boolean isOperation(){
        return false;
    }

    //Returns the value of this node in infix notation
    public java.lang.String toInfixString(){
        return Integer.toString(value);
    }

    //Returns the value of this node in postfix notation
    public java.lang.String toPostfixString(){
        return Integer.toString(value);
    }

    //Returns the value of this node in prefix notation
    public java.lang.String toPrefixString(){
        return Integer.toString(value);
    }
}
