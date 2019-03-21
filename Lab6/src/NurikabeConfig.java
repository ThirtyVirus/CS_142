import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * A class to represent a single configuration in the Nurikabe puzzle.
 *
 * @author Sean Strout @ RITCS
 * @author Brandon Calabrese
 */
public class NurikabeConfig implements Configuration {

    public Node[][] board;
    public int width = 0;
    public int height = 0;

    /**
     * Construct the initial configuration from an input file whose contents
     * are, for example:<br>
     * <tt><br>
     * 3 3          # rows columns<br>
     * 1 . #        # row 1, .=empty, 1-9=numbered island, #=island, &#64;=sea<br>
     * &#64; . 3    # row 2<br>
     * 1 . .        # row 3<br>
     * </tt><br>
     * @param filename the name of the file to read from
     * @throws FileNotFoundException if the file is not found
     */
    public NurikabeConfig(String filename) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File(filename))) {

            String[] line1 = in.nextLine().split(" ");
            width = Integer.parseInt(line1[0]);
            height = Integer.parseInt(line1[1]);

            board = new Node[width][height];

            for (int counter = 0; counter < width; counter++){
                String[] currentRow = in.nextLine().split(" ");

                for (int counter2 = 0; counter2 < height; counter2++){

                    if (currentRow[counter2].equals(".")){
                        board[counter][counter2] = new Node(Node.tileType.NULL);
                    }
                    else if (currentRow[counter2].equals("#")){
                        board[counter][counter2] = new Node(Node.tileType.LAND);
                    }
                    else if (currentRow[counter2].equals("@")){
                        board[counter][counter2] = new Node(Node.tileType.WATER);
                    }
                    else{
                        board[counter][counter2] = new Node(Integer.parseInt(currentRow[counter2]));
                    }
                }
            }

        }

    }

    /**
     * The copy constructor takes a config, other, and makes a full "deep" copy
     * of its instance data.
     *
     * @param other the config to copy
     */
    protected NurikabeConfig(NurikabeConfig other) {
        width = other.width;
        height = other.height;

        board = new Node[width][height];

        for (int counter = 0; counter < width; counter++){
            for (int counter2 = 0; counter2 < height; counter2++){
                Node copyNode = other.board[counter][counter2];
                if (copyNode.getType() == Node.tileType.LAND && copyNode.getData() != 0){
                    board[counter][counter2] = new Node(copyNode.getData());
                }
                else{
                    board[counter][counter2] = new Node(copyNode.getType());
                }

            }
        }
    }

    @Override
    /**
     * Method that guesses the next moves for the puzzle
     *
     * @return successors for current configuration
     */
    public Collection<Configuration> getSuccessors() {
        NurikabeConfig attempt1 = new NurikabeConfig(this);
        NurikabeConfig attempt2 = new NurikabeConfig(this);

        out:
        for (int counter = 0; counter < width; counter++) {
            for (int counter2 = 0; counter2 < height; counter2++) {
                if (board[counter][counter2].getType() == Node.tileType.NULL){
                    attempt1.board[counter][counter2].setType(Node.tileType.WATER);
                    attempt2.board[counter][counter2].setType(Node.tileType.LAND);
                    break out;
                }
            }
        }

        LinkedList<Configuration> successors = new LinkedList<>();
        successors.add(attempt1);
        successors.add(attempt2);
        //System.out.println(successors.size());

        return successors;
    }

    /**
     * Helper method that counts the number of like tiles connected to the startNode
     * @param start - where the search begins
     *
     * @return whether or not the board is a valid solution
     */
    public ArrayList<Point> countConnected(Point start){
        ArrayList<Point> visitedNodes = new ArrayList<>();
        ArrayList<Point> currentNodes = new ArrayList<>();
        //check tiles above, below, left, right. If similar add to currentTiles

        currentNodes.add(start);
        while (currentNodes.size() > 0){
            Point current = currentNodes.get(0);
            //test if coordinate is valid then test for like tiles

            boolean match = false;
            for (Point P : visitedNodes){
                if (P.x == current.x && P.y == current.y) match = true;
            }

            if (match == false){
                if (current.x > 0){
                    if (board[current.x - 1][current.y].getType() == board[current.x][current.y].getType()){
                        currentNodes.add(new Point(current.x - 1,current.y));
                    }
                }
                if (current.x < width - 1){
                    if (board[current.x + 1][current.y].getType() == board[current.x][current.y].getType()){
                        currentNodes.add(new Point(current.x + 1,current.y));
                    }
                }
                if (current.y > 0){
                    if (board[current.x][current.y - 1].getType() == board[current.x][current.y].getType()){
                        currentNodes.add(new Point(current.x,current.y - 1));
                    }
                }
                if (current.y < height - 1){
                    if (board[current.x][current.y + 1].getType() == board[current.x][current.y].getType()){
                        currentNodes.add(new Point(current.x,current.y + 1));
                    }
                }
            }

            if (match == false) visitedNodes.add(current);
            currentNodes.remove(current);
        }

        return visitedNodes;
    }
    /**
     * Checks if the configuration of the board is valid according to the game rules
     *
     * @return whether or not the board is a valid solution
     */
    @Override
    public boolean isValid() {

        //Determines if the board generation is complete yet
        boolean foundNull = false;
        out:
        for (int counter = width - 1; counter > 0; counter--){
            for (int counter2 = height - 1; counter2 > 0; counter2--){
                if (board[counter][counter2].getType() == Node.tileType.NULL) {foundNull = true; break out;}
            }
        }

        //Test if all water is connected, while also collecting all water tile locations
        int connectedWater = 0;
        ArrayList<Point> allWater = new ArrayList<>();

        if (foundNull == false) {
            for (int counter = 0; counter < width; counter++) {
                for (int counter2 = 0; counter2 < height; counter2++) {
                    if (board[counter][counter2].getType() == Node.tileType.WATER) {
                        allWater.add(new Point(counter, counter2));
                    }
                }
            }
        }

        if (allWater.size() > 0) connectedWater = countConnected(allWater.get(0)).size();
        if (allWater.size() != connectedWater){return false;}

        //Test for 2x2 water pools
            for (Point current : allWater) {
                if (current.x < width - 1 && current.y < height - 1) {
                    if (board[current.x + 1][current.y].getType() == Node.tileType.WATER) {
                        if (board[current.x][current.y + 1].getType() == Node.tileType.WATER) {
                            if (board[current.x + 1][current.y + 1].getType() == Node.tileType.WATER) {
                                return false;
                            }
                        }
                    }
                }
            }

        //Collects all locations for island roots
        ArrayList<Point> landRoots = new ArrayList<>();
        for (int counter = 0; counter < width; counter++) {
            for (int counter2 = 0; counter2 < height; counter2++) {
                if (board[counter][counter2].getData() != 0){
                    landRoots.add(new Point(counter, counter2));
                }
            }
        }
        //check if island root is surrounded in water
        for (Point root : landRoots){
            int availableSpaces = 4;
            int waterSpaces = 0;

            if (root.x > 0){
                if (board[root.x - 1][root.y].getType() == Node.tileType.WATER) {waterSpaces += 1;}
            }
            else {
                availableSpaces -= 1;
            }
            if (root.x < width - 1){if (board[root.x + 1][root.y].getType() == Node.tileType.WATER) {waterSpaces += 1;}}
            else {availableSpaces -= 1;}
            if (root.y > 0){if (board[root.x][root.y - 1].getType() == Node.tileType.WATER) {waterSpaces += 1;}}
            else {availableSpaces -= 1;}
            if (root.y < height - 1){if (board[root.x][root.y + 1].getType() == Node.tileType.WATER) {waterSpaces += 1;}}
            else {availableSpaces -= 1;}

            if (waterSpaces == availableSpaces && waterSpaces != 0 && board[root.x][root.y].getData() > 1){
                //System.out.println("Found stranded Island: " + board[root.x][root.y].getData());
                return false;
            }

        }

        //Check if actual island total size is equal to expected
        if (foundNull == false){

            //count expected number of land tiles
            int expectedLand = 0;
            for (Point root : landRoots){
                expectedLand += board[root.x][root.y].getData();
            }
            //count actual number of land tiles
            int actualLand = 0;

            for (int counter = 0; counter < width; counter++) {
                for (int counter2 = 0; counter2 < height; counter2++) {
                    if (board[counter][counter2].getType() == Node.tileType.LAND){
                        actualLand += 1;
                    }
                }
            }
            if (!(expectedLand == actualLand)) return false;
        }

        //Tests for proper island sizes using countConnected helper and makes sure islands do not connect
        for (Point root : landRoots){

            ArrayList<Point> connectedLand = countConnected(root);
            //If the island size is incorrect
            if (board[root.x][root.y].getData() < connectedLand.size()){
                if (foundNull){
                    return false;
                }
            }
            else if (board[root.x][root.y].getData() > connectedLand.size()){
                if (foundNull == false){
                    return false;
                }
            }
            //If two islands connect BROKEN
            for (Point other : connectedLand){
                if (!(root.x == other.x && root.y == other.y)){
                    if (board[other.x][other.y].getData() != 0){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Checks if the board was no null nodes
     *
     * @return whether or not the board does not contain null nodes
     */
    @Override
    public boolean isGoal() {
        for (int counter = 0; counter < width; counter++){
            for (int counter2 = 0; counter2 < height; counter2++) {
                if (board[counter][counter2].getType() == Node.tileType.NULL){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns a string representation of the puzzle, e.g.: <br>
     * <tt><br>
     * 1 . #<br>
     * &#64; . 3<br>
     * 1 . .<br>
     * </tt><br>
     */
    @Override
    public String toString() {
        String boardString = "";

        for (int counter = 0; counter < width; counter++){
            for (int counter2 = 0; counter2 < height; counter2++){
                if (board[counter][counter2].getType() == Node.tileType.NULL){
                    boardString = boardString + ". ";
                }
                else if (board[counter][counter2].getType() == Node.tileType.WATER){
                    boardString = boardString + "@ ";
                }
                else if (board[counter][counter2].getType() == Node.tileType.LAND){
                    if (board[counter][counter2].getData() == 0){
                        boardString = boardString + "# ";
                    }
                    else{
                        boardString = boardString + Integer.toString(board[counter][counter2].getData()) + " ";
                    }
                }
            }
            boardString = boardString + "\n";
        }
        return boardString;
    }
}
