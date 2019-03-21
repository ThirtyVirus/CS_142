package Nodes;

import Util.SymbolTable;
/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Variable MerpNode
 */
public class VariableNode extends java.lang.Object implements MerpNode{

    //Node name
    private java.lang.String name;

    //Constructor
    public VariableNode(java.lang.String name){
        this.name = name;
    }

    //Evaluates the node to determine its integer value
    public int evaluate(SymbolTable symbolTable){
        return symbolTable.get(name);
    }

    //Determines the node type
    public MerpNode.NodeType getNodeType(){
        return NodeType.Variable;
    }

    //Returns the precedence of this node
    public int getPrecedence(){
        return 3;
    }

    //determines if the node is an operation node
    public boolean isOperation(){
        return false;
    }

    //Displays this node as infix notation expression string
    public java.lang.String toInfixString(){
        return name;
    }

    //Displays this node as postfix notation expression string
    public java.lang.String toPostfixString(){
        return name;
    }

    //Displays this node as prefix notation expression string
    public java.lang.String toPrefixString(){
        return name;
    }
}
