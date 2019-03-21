/**
 * A Node class, used to represent each 'tile' in the game board
 *
 * @author: Brandon Calabrese
 */
public class Node {

    public enum tileType{
        LAND,
        WATER,
        NULL;
    }

    //The type of tile
    private tileType type;

    //only for tiles that are land roots
    private int data;

    /**
     * Constructor for nodes
     */
    public Node(tileType type){
        this.type = type;
        data = 0;
    }

    /**
     * Constructor for land root nodes, allows for setting data
     */
    public Node(int data){
        type = tileType.LAND;
        this.data = data;
    }

    /**
     * Returns the tile type
     */
    public tileType getType(){
        return type;
    }

    /**
     * Sets the tile type
     */
    public void setType(tileType type){
        this.type = type;
    }

    /**
     * Returns the tile data
     */
    public int getData(){
        return data;
    }

    /**
     * Sets the tile data
     */
    public void setData(int data){
        this.data = data;
    }
}
