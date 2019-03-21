package Processors;

import Util.SymbolTable;

import java.util.ArrayList;

/**
 * Created by Brandon Calabrese on 3/08/2017.
 *
 * Class to process Merp Programming Language Statements
 */
public class EquationProcessor extends java.lang.Object{

    java.util.ArrayList<java.lang.String> equations;
    MerpProcessor processor;
    SymbolTable symbolTable;

    //Constructor to create an Equation Processor.
    public EquationProcessor(java.util.ArrayList<java.lang.String> equations, MerpProcessor processor){
        this.equations = equations;
        this.processor = processor;
        symbolTable = new SymbolTable();
    }

    //Processes single equation
    private void processEquation(java.lang.String eq){
        eq = eq.trim();
        String[] word = eq.split(" ");




        //Non - Fancy Command Line Implementation, need to read per-word and evaluate each time
        switch(word[0]){
            case "exp:":
                ArrayList<String> e = new ArrayList<String>();
                for (int counter = 1; word[counter] != ")"; counter++){
                    e.add(word[counter]);
                }
                processor.constructTree(e);
                processor.evaluateTree(symbolTable);
                break;
            case "print(":
                ArrayList<String> expression = new ArrayList<String>();
                for (int counter = 1; word[counter] != ")" && counter < word.length - 1; counter++){
                    expression.add(word[counter]);
                }
                processor.constructTree(expression);
                System.out.println(processor.evaluateTree(symbolTable));
                break;
            case "printVars()":
                symbolTable.dump();
                break;
            case "if(":
                break;
            case "while( ":
                break;
            default:
                if (word[0].matches( "^[a-zA-Z].*" )){

                    ArrayList<String> toProcess = new ArrayList<String>();
                    for (int counter = 2; counter < word.length; counter++){
                        toProcess.add(word[counter]);
                    }
                    processor.constructTree(toProcess);
                    int value = processor.evaluateTree(symbolTable);

                    symbolTable.put(word[0], value);
            }
        }

    }

    //Processes the provided list of statements using the provided Merp Processor and Sysmbol Table.
    public void processEquations(){
        for (String e : equations){
            processEquation(e);
        }
    }


}
