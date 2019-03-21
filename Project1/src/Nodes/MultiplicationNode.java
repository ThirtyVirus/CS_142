package Nodes;

import Util.SymbolTable;
/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Multiplication Node
 */
public class MultiplicationNode extends BinaryOperatorNode{

    //Constructor that sets the left/right children and sets the operator to the string * The precedence is set to MULT_DIVIDE
    public MultiplicationNode(MerpNode left, MerpNode right){
        super(left,right,Precedence.MULT_DIVIDE,"*");
    }

    //Evaluates the node to determine its integer value
    public int evaluate(SymbolTable symbolTable){
        return leftChild.evaluate(symbolTable) * rightChild.evaluate(symbolTable);
    }

}
