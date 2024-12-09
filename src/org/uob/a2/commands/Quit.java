package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

import java.util.stream.Collectors;

/**
 * Represents the quit command, allowing the player to exit the game.
 * 
 * <p>
 * The quit command signals the end of the game session and provides a summary of the player's
 * current status before termination.
 * </p>
 */
public class Quit extends Command {
    public Quit() {
        this.commandType = CommandType.QUIT;
    }

    @Override
    public String execute(GameState gameState) {
        Status status = new Status("player");
        return "Game over: Your current status is:\n" + status.execute(gameState).toLowerCase();
    }

    @Override
    public String toString() {
        return "QUIT";
    }
}
