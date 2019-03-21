package Nodes;

import Util.SymbolTable;

/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Absolute Value MerpNode
 */
public class AbsValueNode extends UnaryOperatorNode {

    //Constructor that sets the left child and sets the operator to the string | The precedence is set to MULT_DIVIDE
    public AbsValueNode(MerpNode child){
        super(child, Precedence.MULT_DIVIDE, "|");
    }

    //Evaluates the node to determine its integer value
    public int evaluate(SymbolTable symbolTable){
        return Math.abs(child.evaluate(symbolTable));
    }


}
