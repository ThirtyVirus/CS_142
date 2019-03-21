package Nodes;

import Util.Errors;
import Util.SymbolTable;
/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Division Node
 */
public class DivisionNode extends BinaryOperatorNode{

    //Constructor that sets the left/right children and sets the operator to the string // The precedence is set to MULT_DIVIDE
    public DivisionNode(MerpNode left, MerpNode right){
        super(left,right,Precedence.MULT_DIVIDE,"//");
    }

    //Evaluates the node to determine its integer value Errors if the right child evaluates to zero
    public int evaluate(SymbolTable symbolTable){
        if(rightChild.evaluate(symbolTable) == 0){
            Errors.error("Cannot Divide By Zero", rightChild);
        }
        return leftChild.evaluate(symbolTable) / rightChild.evaluate(symbolTable);
    }

}
