package org.uob.a2.commands;

import java.util.Arrays;

/**
 * Represents the various types of commands that can be executed in the game.
 * 
 * <p>
 * Each command type corresponds to a specific player action or game functionality.
 * </p>
 */
public enum CommandType {
    /**
     * Represents a command to move the player to a different location.
     */
    MOVE("MOVE Command: Use the 'move' command to move to a different location as defined by the exit's name/direction (e.g. 'move north') by typing 'move <exit name>'"),

    /**
     * Represents a command to use an item or interact with a game object.
     */
    USE("USE Command: Use the 'use' command to use an item in your inventory, on its own or on a feature/item, by typing 'use <equipment name> on|with <feature|item>'"),

    /**
     * Represents a command to pick up an item.
     */
    GET("GET Command: Use the 'get' command to pickup an item or equipment into your inventory by typing 'get <item name|equipment name>"),

    /**
     * Represents a command to drop an item from the player's inventory.
     */
    DROP("DROP Command: Use the 'drop' command to drop an item/equipment from your inventory by typing 'drop <item name|equipment name>'"),

    /**
     * Represents a command to look around the current location or inspect an object.
     */
    LOOK("LOOK Command: use the 'look' command to look around the room, at an exit, at a feature, or at a specific item, equipment, or feature, by typing 'look <room|exit|feature>|<item name>|<feature name>'"),

    /**
     * Represents a command to check the player's current status.
     */
    STATUS("STATUS Command: Use the 'status' command to check your current status, inventory, or to get more information about a specific item or equipment in your inventory (e.g., 'status player' and 'status inventory'), by typing 'status <inventory|player|item name|equipment name|map|score>'"),

    COMBINE("COMBINE Command: Use the 'combine' command to combine two items by using 'combine <item1> <item2>'"),

    /**
     * Represents a command to display help information.
     */
    HELP("HELP Command: Use the 'help' command to display help information on a specific topic (e.g. 'help move' or 'help') by typing 'help <topic>'"),

    /**
     * Represents a command to quit the game.
     */
    QUIT("QUIT Command: Use the 'quit' command to end the game and output your current status by typing 'quit'");

    public final String helpMessage;

    CommandType(String helpMessage) {
        this.helpMessage = helpMessage;
    }



}
