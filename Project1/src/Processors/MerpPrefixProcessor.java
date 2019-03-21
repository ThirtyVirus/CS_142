package Processors;

import Nodes.BinaryOperatorNode;
import Nodes.MerpNode;
import Nodes.UnaryOperatorNode;

import java.util.Stack;

/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Class to process Merp expressions using prefix notation
 */
public class MerpPrefixProcessor extends MerpProcessor {

    //Constructor
    public MerpPrefixProcessor() {

    }

    //Constructs and assigns a Merp tree from the provided list of MerpNode tokens using prefix notation
    public void constructTree(java.util.ArrayList<java.lang.String> tokens) {
        java.util.ArrayList<java.lang.String> reversedTokens = new java.util.ArrayList<>();
        for (int counter = tokens.size()-1; counter >= 0; counter --){
            reversedTokens.add(tokens.get(counter));
        }

        tree = constructTreeHelper(reversedTokens);
    }

    //Helper to recursively construct the parse tree
    private MerpNode constructTreeHelper(java.util.ArrayList<java.lang.String> tokens) {
        MerpNode root = createMerpNode(tokens.get(tokens.size() - 1));
        tokens.remove(tokens.size()-1);

        if (root.getNodeType() == MerpNode.NodeType.BinaryOperation){

            ((BinaryOperatorNode)root).setLeftChild(constructTreeHelper(tokens));
            ((BinaryOperatorNode)root).setRightChild(constructTreeHelper(tokens));

        }
        else if (root.getNodeType() == MerpNode.NodeType.UnaryOperation){
            ((UnaryOperatorNode)root).setChild(constructTreeHelper(tokens));
        }

        return root;
    }
}
