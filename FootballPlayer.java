/*
 * Veer Desai
 * 3/12/25
 * CSE 123
 * C3: Best of the Best
 * TA: Isayiah Lim
 */
import java.util.*;
/* 
 * This program creates a virtual football player. It can store player information like
 *      player name, position, birth place, college, height, and weight. This implements 
 *      the Comparable interface. 
 */
public class FootballPlayer implements Comparable<FootballPlayer> {
    private static final String[] BALLSIDE= {"offense", "defense", "kicker", "punter"};

    private final String playerName;
    private final String position;
    private final String birthPlace;
    private final String college;
    private final double weight;
    private final double height;

    /*
     * This creates a profile for a football player with their essential information in it. 
     *      Their profile includes their name, position, birth place, what college they went to, 
     *      their weight in pounds, and their height in inches. 
     * Exceptions: 
     *      - IllegalArgumentException() case #1: gets thrown if either the weight or height 
     *          is a negative number
     *      - IllegalArgumentException() case #2: gets thrown if the position for the player
     *          is not valid, so if not one of offense or defense or kicker or punter
     */
    public FootballPlayer(String playerName, String position, String birthPlace, String college, double weight, 
            double height) {
        if (weight < 0 || height < 0) {
            throw new IllegalArgumentException("Weight or height cannot be negative!");
        }
        if (!FootballPlayer.checkWeight(position.toLowerCase())) {
            throw new IllegalArgumentException("Invalid position!");
        }
        this.playerName = playerName;
        this.position = position.toLowerCase();
        this.birthPlace = birthPlace;
        this.college = college;
        this.weight = weight;
        this.height = height;
    }

    /*
     * This creates a duplicate copy of a football player with every single information also in it.
     *      So the name, position, birth place, what college they went to, weight, and height are 
     *      all duplicated for another version of the player.
     * Parameters: 
     *      - player: the football player's profile which would be duplicated
     * Exceptions: 
     *      - IllegalArgumentException() case #1: gets thrown if either the weight or height 
     *          is a negative number
     *      - IllegalArgumentException() case #2: gets thrown if the position for the player
     *          is not valid, so if not one of offense or defense or kicker or punter
     */
    public FootballPlayer(FootballPlayer player) {
        this(player.playerName, player.position, player.birthPlace, player.college, player.weight, player.height);
    }

    /*
     * This method retrieves the String representation name of the football player.
     */
    public String getName() {
        return this.playerName;
    }

    /*
     * This method retrieves the String representation position of the football player.
     */
    public String getPosition() {
        return this.position;
    }

    /*
     * This method retrives the String representation birth place of the football player.
     */
    public String getBirthPlace() {
        return this.birthPlace;
    }

    /*
     * This method retrives the String representation college of the football player.
     */
    public String getCollege() {
        return this.college;
    }

    /*
     * This method retrives the double representation weight of the football player.
     */
    public double getWeight() {
        return this.weight;
    }

    /*
     * This method retrives the double representation height of the football player.
     */
    public double getHeight() {
        return this.height;
    }

    /*
     * This allows the user to make their own football player profile. 
     * It asks what player name they want, what position they play, where the player was born, 
     *      what college the player went to, what the weight of the player is in pounds, 
     *      and what the height of the player is in inches. 
     * Parameters: 
     *      - input: processes the user's answers to the questions asked about the player creation
     * Return: 
     *      - FootballPlayer: the newly created profile for the football player
     */
    public static FootballPlayer parse(Scanner input) {
        System.out.print("What is the name of the player? ");
        String playerName = input.nextLine();
        System.out.print("What is the ball side position of the player? " +
                "Valid options are offense, defense, kicker, and punter. ");
        String position = input.nextLine();

        System.out.print("Where was this player born? "); 
        String birthPlace = input.nextLine();

        System.out.print("What college did this player go to? ");
        String college = input.nextLine();

        System.out.print("What is the weight of this player in pounds? ");
        double weight = Double.parseDouble(input.nextLine());

        System.out.print("What is the height of this player in inches? ");
        double height = Double.parseDouble(input.nextLine());

        return new FootballPlayer(playerName, position, birthPlace, college, weight, height);
    }

    /*
     * This retrieves the index of a certain position for the created player from the BALLSIDE 
     *       array, which is a constant of the class. 
     * Parameters: 
     *      - position: the position for the created player
     * Return: 
     *      - int: the index of the position in the BALLSIDE array
     */
    private static int getPositionIndex(String position) {
        position = position.toLowerCase();
        for (int i = 0; i < BALLSIDE.length; i++) {
            if (BALLSIDE[i].equals(position)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * This checks if the created player's position is valid in the BALLSIDE array or not. 
     * Parameters: 
     *      - position: the position of the crated player
     * Return: 
     *      - boolean: true if the player's position is in the array and false if not
     */
    private static boolean checkWeight(String position) {
        for (String validPosition: BALLSIDE) {
            if (validPosition.equals(position)) {
                return true;
            }
        }
        return false;
    }

    /*
     * This provides a readable description of the important information about the crated player. 
     *      This includes information on the player's name, their positon, their birthplace, 
     *      what college they went to, how much they weigh, and how tall are they.
     * Return: 
     *      - String: the created player's description
     */
    public String toString() {
        return "Player's name: " + this.playerName + ". Position: " + this.position + 
            ". Birthplace: " + this.birthPlace + ". College: " + this.college +
            ". Weight: " + this.weight + " pounds. Height: " + this.height + " inches.";
    }

    /*
     * This compares two players and determines who is "better". A positive number being 
     *      returned means this current player has greater priority over the other player and if 
     *      negative is returned, then the other player has higher priority than
     *      this current player.
     * Parameters: 
     *      - other: the other football player that will be compared to this current player
     * Return: 
     *      - int: If the current football player weighs more than the other player, then a
     *          positive number is returned and negative the other way around. If they both weigh
     *          the same, then their heights are checked. If the current player is taller than the
     *          other player, then a positve nuber is returned and negative otherwise. If they both
     *          are the same height, then their positions are compared. The position priorities
     *          from highest to lowest are offense, defense, kicker, and punter. If the current 
     *          player's position priority is higher, then a positive number is returned and
     *          negative otherwise. If they both have the same position priorities, then 
     *          their birthplaces are compared. If the current player's birthplace town name
     *          appears before alphabetically then the other player's, then a positive number
     *          is returned and negative otherwise. If they both have the same birthplace, then
     *          their colleges are compared. If the current player's college appears before
     *          alphabetically then the other player's, then a positve number is returned and
     *          negative otherwise. If the colleges are the same, then both of the player's names
     *          are compared. If the current player's name appears before alphabetically then the
     *          other player's, then a positive number is returned and negative otherwise. If
     *          both the player's names are the same, then 0 is returned in the end. 
     */
    public int compareTo(FootballPlayer other) {
        if (this.weight != other.weight) {
            return (int) (this.weight - other.weight);
        }
        else if (this.height != other.height) {
            return (int) (this.height - other.height);
        }
        else if (!this.position.equals(other.position)) {
            return FootballPlayer.getPositionIndex(other.position) - 
                FootballPlayer.getPositionIndex(this.position);
        }
        else if (!this.birthPlace.equals(other.birthPlace)) {
            return this.birthPlace.compareTo(other.birthPlace);
        }
        else if (!this.college.equals(other.college)) {
            return this.college.compareTo(other.college);
        }
        else {
            return this.playerName.compareTo(other.playerName);
        }
    }

    /*
     * This checks if two players are the same players or not. Two players are the same if
     *      they have the exact same name, position birth place, college, weight, and height. 
     *      Even if one of these things are different, then these two players are considered
     *      different. 
     * Paremeters: 
     *      - o: the other player being checked with this current player
     * Return: 
     *      - boolean: true if two players are considered the same and false if not
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        else if (o instanceof FootballPlayer) {
            FootballPlayer otherPlayer = (FootballPlayer) o;
            return (otherPlayer.playerName.equals(this.playerName)
                    && otherPlayer.position.equals(this.position) 
                    && otherPlayer.birthPlace.equals(this.birthPlace)
                    && otherPlayer.college.equals(this.college)
                    && otherPlayer.weight == this.weight
                    && otherPlayer.height == this.height);
        }
        else {
            return false;
        }
    }

    /*
     * Returns a hash code value for this football player. It is basically used
     *      as another identifier for the player. 
     * Return: 
     *      - int: the hash code value
     */
    public int hashCode() {
        int hash = 0;
        hash = 31 * hash + playerName.hashCode();
        hash = 31 * hash + position.hashCode();
        hash = 31 * hash + birthPlace.hashCode();
        hash = 31 * hash + college.hashCode();
        hash = 31 * hash + (int) height;
        hash = 31 * hash + (int) weight;
        return hash;
    }
}
