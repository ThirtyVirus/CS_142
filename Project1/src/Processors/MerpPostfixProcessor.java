package Processors;

import Nodes.BinaryOperatorNode;
import Nodes.MerpNode;
import Nodes.UnaryOperatorNode;

/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Class to process Merp expressions using postfix notation
 */
public class MerpPostfixProcessor extends MerpProcessor{

    //Constructor
    public MerpPostfixProcessor(){

    }

    //Constructs and assigns a Merp tree from the provided list of MerpNode tokens using postfix notation
    public void constructTree(java.util.ArrayList<java.lang.String> tokens){
        tree = constructTreeHelper(tokens);
    }

    //Helper to recursively contstruct the parse tree
    private MerpNode constructTreeHelper(java.util.ArrayList<java.lang.String> tokens){
        MerpNode root = createMerpNode(tokens.get(tokens.size() - 1));
        tokens.remove(tokens.size()-1);

        if (root.getNodeType() == MerpNode.NodeType.BinaryOperation){

            ((BinaryOperatorNode)root).setRightChild(constructTreeHelper(tokens));
            ((BinaryOperatorNode)root).setLeftChild(constructTreeHelper(tokens));

        }
        else if (root.getNodeType() == MerpNode.NodeType.UnaryOperation){
            ((UnaryOperatorNode)root).setChild(constructTreeHelper(tokens));
        }

        return root;
    }

}
