package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the move command, allowing the player to navigate to a different room in the game world.
 * 
 * <p>
 * The move command checks if the specified direction corresponds to an available exit in the current room.
 * If a matching exit is found, the player's location is updated to the connected room.
 * </p>
 */
public class Move extends Command {
    public Move(String direction) {
        this.value = direction;
        this.commandType = CommandType.MOVE;
    }

    @Override
    public String execute(GameState gameState) {
        if (gameState.getMap().getCurrentRoom().getExitByName(value) != null) {
            if (gameState.getMap().getCurrentRoom().getExitByName(value).getHidden()) {
                return "No exit found in that direction.";
            }
            else {
                gameState.getMap().setCurrentRoom(gameState.getMap().getCurrentRoom().getExitByName(value).getNextRoom());
                return "Moving towards " + value + "\n";
            }
        }
        else {
            gameState.getMap();
            return "No exit found in that direction.";
        }
    }

    @Override
    public String toString() {
        return "MOVE " + this.value;
    }
}
