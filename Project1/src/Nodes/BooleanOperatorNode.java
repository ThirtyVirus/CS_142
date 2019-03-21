package Nodes;

/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Abstract class to represent a boolean operator Has a left/right child, precedence,
 * and operator. Assumes non-zero values are true, zero is false.
 */
public abstract class BooleanOperatorNode extends BinaryOperatorNode{

    //Constructor for Boolean operation nodes The precedence is set to BOOLEAN
    public BooleanOperatorNode(MerpNode left, MerpNode right, java.lang.String operator){
        super(left,right,Precedence.BOOLEAN, operator);
    }

    //Returns the precedence of this node
    public int getPrecedence(){
        return 4;
    }


}
