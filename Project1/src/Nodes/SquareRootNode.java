package Nodes;

import Util.SymbolTable;
/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Square Root MerpNode
 */
public class SquareRootNode extends UnaryOperatorNode{

    //Constructor that sets the left child and sets the operator to the string @ The precedence is set to POWER
    public SquareRootNode(MerpNode child){
        super(child,Precedence.POWER,"@");
    }

    //Evaluates the node to determine its integer value Errors if the child evaluates to a negative number
    public int evaluate(SymbolTable symbolTable){
        return (int)Math.sqrt(child.evaluate(symbolTable));
    }
}
