package Processors;

import Nodes.*;

import java.util.*;

/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Class to process Merp expressions using infix notation
 */
public class MerpInfixProcessor extends MerpProcessor{

    //Constructor
    public MerpInfixProcessor(){

    }

    //Constructs and assigns a Merp tree from the provided list of MerpNode tokens using infix notation
    public void constructTree(java.util.ArrayList<java.lang.String> tokens){

        Queue<MerpNode> output = new LinkedList();
        Stack<MerpNode> OperatorStack = new Stack<>();

        for (String token : tokens) {
            MerpNode newNode = createMerpNode(token);

            // operator
            if (newNode.isOperation()) {

                if (!OperatorStack.isEmpty()) {
                    while (newNode.getPrecedence() > OperatorStack.peek().getPrecedence()) {
                        output.add(OperatorStack.pop());
                        if (OperatorStack.isEmpty()) {
                            break;
                        }
                    }
                }

                OperatorStack.push(newNode);
            }
            // constant
            else {
                output.add(newNode);
            }
        }

        while ( ! OperatorStack.isEmpty())
            output.add(OperatorStack.pop());

        //Format data correctly for processing
        while (!output.isEmpty()){
            OperatorStack.add(output.poll());
        }

        tree = processStack(OperatorStack);
    }

    //Processes an stack of MerpNodes to create a Merp Parse Tree
    private MerpNode processStack(java.util.Stack<MerpNode> stack){
        MerpNode root = stack.pop();

        if (root.getNodeType() == MerpNode.NodeType.BinaryOperation){

            ((BinaryOperatorNode)root).setRightChild(processStack(stack));
            ((BinaryOperatorNode)root).setLeftChild(processStack(stack));

        }
        else if (root.getNodeType() == MerpNode.NodeType.UnaryOperation){
            ((UnaryOperatorNode)root).setChild(processStack(stack));
        }

        return root;
    }

}
