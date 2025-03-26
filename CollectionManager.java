/*
 * Veer Desai
 * 3/12/25
 * CSE 123
 * C3: Best of the Best
 * TA: Isayiah Lim
 */
import java.io.*;
import java.util.*;

/*
 * This creates a collection that consists of a variety of a certain item or theme. It has the 
 *      functionality of loading text based data from an external file which would set up the 
 *      collection. The prgram can also save the collection's information to an external file.
 *      The collection can also get created or updated by adding in items one at a time.
 */
public class CollectionManager {
    // TODO: Implement your CollectionManager here! 
    private FootballPlayerNode root;

    /*
     * This creates an empty CollectionManager. 
     */
    public CollectionManager() {
        this.root = null;
    }

    /*
     * This creates a CollectionManager for football players based on text based data with 
     *      many football players from an external file. 
     *      The external file is formatted exactly like how it would be when you save the
     *      CollectionManager into a file. The file has the format of in order 
     *      (all on seperate lines) a player's name, their position, their birthplace, 
     *      their college, their weight, and their height. The next player's information in the
     *      same order is printed below the preceding player. The players are added into the 
     *      spots in the football player collection based on the 
     *      priority of their weight, height, position, birth place, college, and name.
     * Parameters: 
     *      - input: reader through the contents of the external file
     * Exceptions: 
     *      - IllegalArgumentException(): gets thrown if input is null
     */
    public CollectionManager(Scanner input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        root = writeScannerTree(input, root);
    }

    /*
     * This method actually makes a collection of a FootballPlayer tree which consists of many
     *      many football players based on the data from the external file of football players. 
     *      This is created in a binary search tree format which means that the left childs of a 
     *      root football player is every football player that is considered less in priority than
     *      the root. The right childs of the root node football player is every football player 
     *      that is considered greater in priority than the root. 
     * Parameters: 
     *      - input: reader through the contents of the external file
     *      - curr: the tree that is being built
     * Return: 
     *      - FootballPlayerNode: the completed FootballPlayer binary search tree     
     */
    private FootballPlayerNode writeScannerTree(Scanner input, FootballPlayerNode curr) {
        if (!input.hasNextLine()) {
            return curr;
        }
            String playerName = input.nextLine();
            String position = input.nextLine();
            String birthPlace = input.nextLine();
            String college = input.nextLine();
            double weight = Double.parseDouble(input.nextLine());
            double height = Double.parseDouble(input.nextLine());

            FootballPlayer player = new FootballPlayer(playerName, position, birthPlace, college, weight, height);
            curr = add(player, curr);
            return writeScannerTree(input, curr);
        }
    
    

    /*
     * This adds an additional football player to the football player CollectionManager.
     * The new player is added in a spot in the existing CollectionManager based on the 
     * priority of their weight, height, position, birth place, college, and name. 
     * Parameters: 
     *      - player: the football player to be added
     * Exceptions: 
     *      - IllegalArgumentException(): gets thrown if the player to be added is null
     */
    public void add(FootballPlayer player) {
        if (player == null) {
            throw new IllegalArgumentException();
        }
        root = add(player, root);  
    }

    /*
     * This actually contains the logic behind adding a new player to the football player binary 
     *      search tree. The tree is traversed to the left child if the football player to be added
     *       has lesser priority than the root node football player based on both of them being 
     *      compared using the compareTo() method of the FootballPlayer class. 
     *      The right child is traversed to if the football player to be added is of higher 
     *      priority than the root node football player. When a null node is reached, the node gets
     *      newly occupied by the newly added football player. 
     *      
     * Parameter: 
     *      - player: the new football player to be added
     *      - curr: the current node in the binary search tree that is being looked at
     * Return: 
     *      - FootballPlayerNode: the binary search tree with the player added
     */
    private FootballPlayerNode add(FootballPlayer player, FootballPlayerNode curr) {
        if (curr == null) {
            curr = new FootballPlayerNode(player);
        }
        else if (!player.equals(curr.player)) {
            if (player.compareTo(curr.player) < 0) {
                curr.left = add(player, curr.left);
            } else {
                curr.right = add(player, curr.right);
            }
        }
        return curr;
    }

    /*
     * This checks if the football player collection contains a certain football player.
     * Parameters: 
     *      - player: the football player that is being checked
     * Exceptions: 
     *      - IllegalArgumentException: if the football player is null
     * Return: 
     *      - boolean: true if the football player is in the collection and false if not
     */
    public boolean contains(FootballPlayer player) {
        if (player == null) {
            throw new IllegalArgumentException();
        }
        return contains(player, root);
    }

    /*
     * This actually traverses through the meaningful necessary nodes in the football player 
     *      binary search tree and sees if the desired football player is a node in the tree. 
     * Parameters: 
     *      - player: the football player that is being searched for
     *      - curr: the current football player node in the tree that is being looked at
     * Return: 
     *      - boolean: false if the node that is being looked at is null as that means the desired
     *          football player is not in the tree. true if the desired football player has a node
     *          in the tree. 
     */
    private boolean contains(FootballPlayer player, FootballPlayerNode curr) {
        if (curr == null) {
            return false;
        }
        if (player.equals(curr.player)) {
            return true;
        }
        if (player.compareTo(curr.player) < 0) {
            return contains(player, curr.left);
        }
        return contains(player, curr.right);
    }

    /*
     * This saves the football player collection to an external file. The format is exactly like
     *      the how the file from making the football player collection was formatted. So it is 
     *      entirely possible that the saved file from here can be used to make a football player
     *      collection again. The file has the format of in order 
     *      (all on seperate lines) a player's name, their position, their birthplace, 
     *      their college, their weight, and their height. The next player's information in the
     *      same order is printed below the preceding player. The collection is traversed through 
     *      the pre-order traversal tactic.
     * Parameters: 
     *      - output: writes the information about the players into the external file
     * Exceptions: 
     *      - IllegalArgumentException(): gets thrown if the output is null
     */
    public void save(PrintStream output) {
        if (output == null) {
            throw new IllegalArgumentException();
        }
        save(output, root);
    }

    /*
     * This actually contains the logic behind saving the football player binary search tree
     *      into an external file. Every single node in the tree is accounted for and is traversed
     *      through the pre-order traversal. 
     * Parameters: 
     *      - output: writes the information about the players into the external file
     *      - curr: the current football player node that is being looked at and having its
     *          player information being processed and written in the external file
     */
    private void save(PrintStream output, FootballPlayerNode curr) {
        if (curr != null) {
            output.println(curr.player.getName());
            output.println(curr.player.getPosition());
            output.println(curr.player.getBirthPlace());
            output.println(curr.player.getCollege());
            output.println((int) (curr.player.getWeight()));
            output.println((int) (curr.player.getHeight()));
            save(output, curr.left);
            save(output, curr.right);
        }
    }

    /*
     * This retrieves all football players in the collection that are assosciated with 
     *      a certain position. (Note to Grader: this is my extension)
     * Parameters: 
     *      - position: the desired position whose football players are to be retrieved for
     * Return: 
     *      - List<FootballPlayer>: the list of the football players that are assosciated with
     *          the desired position
     */
    public List<FootballPlayer> filter(String position) {
        List<FootballPlayer> results = new ArrayList<>();
        filter(position, root, results);
        return results;
    }

    /*
     * This actually traverses through the football player binary search tree and looks at every
     *      single player node in it and sees if the player's position matches 
     *      the desired position. 
     * Parameters: 
     *      - position: the desired position whose football players are to be retrieved for
     *      - curr: the current player node being looked at
     *      - storage: the list of the football players that play the desired position
     */
    public void filter(String position, FootballPlayerNode curr, 
            List<FootballPlayer> storage) {
        if (curr != null) {
            if (curr.player.getPosition().equalsIgnoreCase(position)) {
                storage.add(curr.player);
            }
            filter(position, curr.left, storage);
            filter(position, curr.right, storage);
        }
    }

    /*
     * This provides a readable representation of the football player collection. Only the names
     *      of the football players are shown for the collection.
     * Return: 
     *      - String: the readable football player collection with the player names. If the
     *          whole collection is null, then only "null" will be shown. If a certain part of
     *          the collection is null, then whicever part is null will be labelled as "null" and
     *          the rest of the non-null parts will be shown as normal
     */
    public String toString() {
        return toString(root);
    }

    /*
     * This actually traverses through the whole football player binary search tree and retrives
     *      the names of the football players in the nodes. The leaf nodes 
     *      are emphasized by being in square brackets by themselves. The left subtree is to the 
     *      right of its root and the right subtree is to the right of the left subtree 
     *      in the string.  The subtrees are seperated by having their own asquare brackets group.
     * Parameters: 
     *      - curr: the current node in the tree being looked at
     * Return: 
     *      - String: the readable football player collection with the player names. If the
     *          whole collection is null, then only "null" will be shown. If a certain part of
     *          the collection is null, then whicever part is null will be labelled as "null" and
     *          the rest of the non-null parts will be shown as normal
     */
    private String toString(FootballPlayerNode curr) {
        if (curr == null) {
            return "null";
        }
        else if (curr.left == null && curr.right == null) {
            return "[" + curr.player.getName() + "]";
        }
        else {
            return "[" + curr.player.getName() + " " + toString(curr.left) + " " + 
                toString(curr.right) + "]";
        }
    }


    /*
     * This helps to create a football player binary tree. It can also modify an existing tree.
     */
    private static class FootballPlayerNode {
        public final FootballPlayer player;
        public FootballPlayerNode left;
        public FootballPlayerNode right;

        /*
         * This creates a football player node with no childs
         */
        public FootballPlayerNode(FootballPlayer player) {
            this(player, null, null);
        }

        /*
         * This creates a football player node with left and right childs
         */
        public FootballPlayerNode(FootballPlayer player, FootballPlayerNode left, FootballPlayerNode right) {
            this.player = player;
            this.left = left;
            this.right = right;
        }
    }
}
