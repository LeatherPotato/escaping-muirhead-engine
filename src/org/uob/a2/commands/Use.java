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
    private String equipmentName;

    public Use(String equipmentName, String target) {
        this.value = target;
        this.equipmentName = equipmentName;
        this.commandType = CommandType.USE;
    }

    @Override
    public String execute(GameState gameState) {
        // incorrect check for target existence
        boolean targetExists = gameState.getMap().getCurrentRoom().hasItem(value) ||
                gameState.getMap().getCurrentRoom().getItem(value) != null ||
                gameState.getMap().getCurrentRoom().hasFeature(value) ||
                gameState.getMap().getCurrentRoom().getFeature(value) != null;
        if (targetExists) {
            boolean has = gameState.getPlayer().hasEquipment(equipmentName);
            if (has) {
                boolean used = gameState.getPlayer().getEquipment(equipmentName).getUseInformation().isUsed();
                if (used) {
                    return "You have already used " + equipmentName;
                }
                else {
                    gameState.getPlayer().getEquipment(equipmentName).getUseInformation().setUsed(true);
                    return gameState.getPlayer().getEquipment(equipmentName).getUseInformation().getMessage();
                }
            }
            else {
                return "You do not have " + equipmentName;
            }
        }
        else {
            return "Invalid use target";
        }
    }

    @Override
    public String toString() {
        return "USE itemname: " + this.equipmentName + " target: " + this.value;
    }

  
}
