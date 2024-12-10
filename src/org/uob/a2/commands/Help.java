package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the help command, providing the player with instructions or information
 * about various topics related to the game.
 * 
 * <p>
 * The help command displays information on how to play the game, including details about 
 * available commands, their syntax, and their purpose.
 * </p>
 */
public class Help extends Command {
    public Help(String topic) {
        this.value = topic;
        this.commandType = CommandType.HELP;
    }

    @Override
    public String execute(GameState gameState) {
        final String help = """
            Welcome to the game! Here are the available commands.
            - MOVE: Move to a different location(e.g., 'move north').
            - USE: Use an item in your inventory (e.g., ’use key’ or ’use key on chest').
            - GET: Pick up an item from the current room (e.g., ’get key’).
            - DROP: Drop an item from your inventory (e.g., ’drop key’).
            - LOOK: Look around the current room or inspect an object.
            - STATUS: Check your current status, including inventory.(e.g. ’status player’ or ’status inventory’).
            - HELP: Display this help information or get help on a specific topic
                (e.g., ’help move’).
            - QUIT: Exit the game.
            Explore the different rooms and solve the puzzles to escape muirhead!
            """;

        if (value == null) {
            return help;
        }
        else {
            try {
                return CommandType.valueOf(value.toUpperCase()).helpMessage;
            }
            catch(Exception e) {
                return "No help available for the topic: " + value;
            }
        }
    }

    @Override
    public String toString() {
        if (value == null) {
            return "HELP: help null";
        }
        else {
            return "HELP: help " + this.value;
        }
    }

}
