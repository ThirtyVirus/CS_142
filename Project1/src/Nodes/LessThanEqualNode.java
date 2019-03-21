package Nodes;

import Util.SymbolTable;
/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Node representing the less than euqal to operator
 */
public class LessThanEqualNode extends BooleanOperatorNode{

    //Constructor that sets the left/right children and sets the operator to the string <=
    public LessThanEqualNode(MerpNode left, MerpNode right){
        super(left,right,"<=");
    }

    //Evaluates the node to determine its integer value 1 for true, zero for false
    public int evaluate(SymbolTable symbolTable){
        if (leftChild.evaluate(symbolTable) <= rightChild.evaluate(symbolTable)){
            return 1;
        }
        else{
            return 0;
        }

    }


}
