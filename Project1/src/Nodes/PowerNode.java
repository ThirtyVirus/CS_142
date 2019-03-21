package Nodes;

import Util.SymbolTable;
/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Power Node
 */
public class PowerNode extends BinaryOperatorNode{

    //Constructor that sets the left/right children and sets the operator to the string ^ The precedence is set to POWER
    public PowerNode(MerpNode left, MerpNode right){
        super(left,right,Precedence.POWER,"^");
    }

    //Evaluates the node to determine its integer value
    public int evaluate(SymbolTable symbolTable){
        return (int)Math.pow(leftChild.evaluate(symbolTable), rightChild.evaluate(symbolTable));
    }

}
