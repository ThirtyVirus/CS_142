import turtle.Turtle;

/**
 * Created by ThirtyVirus on 1/31/2017.
 */
public class HTree {
    private static int MAX_SEGMENT_LENGTH = 1024;

    public static Turtle init(int length, int depth){
        /**
        Initialize the graphics.

                length (int): The length of the main snowflake branch.
                depth (int): The depth of recursion.
        """
        */
        Turtle t = new Turtle(0,0,0);
        t.setWorldCoordinates(-length*2, -length*2, length*2, length*2);
        t.setCanvasTitle("H-Tree, depth: " + depth);
        return t;
    }

    public static void drawHTree(Turtle t, int length, int depth){
        /**
        Recursively draw the H-Tree.

                length (int): the length of the current h-tree segments
        depth (int): the current depth of recursion

        pre-conditions:
        depth is greater than or equal to 0
        turtle is at center of current h-tree, down, facing east

        post-conditions:
        turtle is at center of current h-tree, down, facing east
        */

        if (depth > 0){

            //start in center of H, move to upper right
            t.goForward(length / 2);
            t.turnLeft(90);
            t.goForward(length / 2);
            t.turnRight(90);

            //recurse
            drawHTree(t, length / 2, depth - 1);

            //move to lower right of H
            t.turnRight(90);
            t.goForward(length);
            t.turnLeft(90);

            //recurse
            drawHTree(t, length / 2, depth - 1);

            //move to upper left of H
            t.turnLeft(90);
            t.goForward(length / 2);
            t.turnLeft(90);
            t.goForward(length);
            t.turnRight(90);
            t.goForward(length / 2);
            t.turnRight(90);

            //recurse
            drawHTree(t, length / 2, depth - 1);

            //move to lower left of H
            t.turnRight(90);
            t.goForward(length);
            t.turnLeft(90);

            //recurse
            drawHTree(t, length / 2, depth - 1);

            //return to center of H
            t.turnLeft(90);
            t.goForward(length / 2);
            t.turnRight(90);
            t.goForward(length / 2);

        }

    }

    public static void main(String[] args){
        /**
        The main method reads the command line argument for the depth
        and draws the h-tree.
        */

    // check the depth is >= 0
        int depth = Integer.parseInt(args[0]);
        if (depth < 0){
            System.out.println("The depth must be greater than or equal to 0");
        }
        else{
            // initialize turtle
            Turtle t = init(MAX_SEGMENT_LENGTH, depth);

            // draw the h-tree
            drawHTree(t, MAX_SEGMENT_LENGTH, depth);

            System.out.println("Close The Window to Exit");
        }

    }
}
