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
        switch (this.value) {
            case "move":
                return "MOVE";

            case "use":
                return "USE";

            case "get":
                return "GET";

            case "drop":
                return "DROP";

            case "look":
                return "LOOK";

            case "status":
                return "STATUS";

            case "help":
                return "HELP";

            case "quit":
                return "QUIT";

            default:
                // TODO: add checks for objects here from gameState
                return "invalid command";
        }
    }

    @Override
    public String toString() {
        return "HELP " + this.value;
    }

}
