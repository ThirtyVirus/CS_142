package Nodes;

import Util.SymbolTable;
/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Negation MerpNode
 */
public class NegationNode extends UnaryOperatorNode{

    //Constructor that sets the left child and sets the operator to the string _ The precedence is set to MULT_DIVIDE
    public NegationNode(MerpNode child){
        super(child,Precedence.MULT_DIVIDE,"_");
    }

    //Evaluates the node to determine its integer value
    public int evaluate(SymbolTable symbolTable){
        return -1 * child.evaluate(symbolTable);
    }


}
