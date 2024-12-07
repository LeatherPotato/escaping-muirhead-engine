package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the use command, allowing the player to use equipment on a specific target in the game.
 * 
 * <p>
 * The use command checks if the player has the specified equipment and whether it can interact with
 * the target. The target can be a feature, item, or the current room, depending on the game context.
 * </p>
 */
public class Use extends Command {
    private String equimpemtName;

    public Use(String equipmentName, String target) {
        this.value = target;
        this.equimpemtName = equipmentName;
        this.commandType = CommandType.USE;
    }

    @Override
    public String execute(GameState gameState) {
        return "";
    }

    @Override
    public String toString() {
        return "";
    }

  
}
