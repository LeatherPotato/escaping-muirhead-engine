package org.uob.a2.gameobjects;

/**
 * Represents the current state of the game, including the map and the player.
 * 
 * <p>
 * The game state contains all necessary information about the game's progress, such as
 * the player's status and the state of the game map.
 * </p>
 */
public class GameState {
    private Map map;
    private Player player;
    private int exploredFloors;
    private final int totalFloors = 4;
    private int pegsUsed;

    // default constructor
    public GameState() {

    }

    // constructor with map and player
    public GameState(Map map, Player player) {
        this.map = map;
        this.player = player;
        exploredFloors = 0;
        pegsUsed = 0;
    }

    public int getExploredFloors() {
        return exploredFloors;
    }

    public void setExploredFloors(int exploredFloors) {
        this.exploredFloors = exploredFloors;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public void setPegsUsed(int pegsUsed) {
        this.pegsUsed = pegsUsed;
    }

    public int getPegsUsed() {
        return this.pegsUsed;
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }
    /**
     * Returns a string representation of the game state, including the map and player details.
     *
     * @return a string describing the game state
     */
    @Override
    public String toString() {
        return "GameState {" +
               "map=" + (map != null ? map.toString() : "null") + ", " +
               "player=" + (player != null ? player.toString() : "null") +
               '}';
    }
}
