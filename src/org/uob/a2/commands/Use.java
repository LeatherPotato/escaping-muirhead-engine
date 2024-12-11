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
    private String preposition;

    public Use(String equipmentName, String target) {
        this.value = target;
        this.equipmentName = equipmentName;
        this.commandType = CommandType.USE;
    }

    public Use(String equipmentName, String target, String preposition) {
        this.value = target;
        this.equipmentName = equipmentName;
        this.commandType = CommandType.USE;
        this.preposition = preposition;
    }

    @Override
    public String execute(GameState gameState) {
        if (value == null || value.isEmpty()) {
            boolean has = gameState.getPlayer().hasEquipment(equipmentName);
            if (has) {
                boolean used = gameState.getPlayer().getEquipment(equipmentName).getUseInformation().isUsed();
                if (used) {
                    return "You have already used " + equipmentName;
                } else {
                    return gameState.getPlayer().getEquipment(equipmentName).use(null, gameState);
                }
            } else {
                return "You do not have " + equipmentName;
            }
        }
        else {
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
                    } else {
                        boolean isItem = gameState.getMap().getCurrentRoom().hasItem(value) || gameState.getMap().getCurrentRoom().getItem(value) != null;
                        GameObject obj;
                        if (isItem) {
                            obj = gameState.getMap().getCurrentRoom().getItemByName(value);
                            if (obj == null) {
                                obj = gameState.getMap().getCurrentRoom().getItem(value);
                            }
                        } else {
                            obj = gameState.getMap().getCurrentRoom().getFeatureByName(value);
                            if (obj == null) {
                                obj = gameState.getMap().getCurrentRoom().getFeature(value);
                            }
                        }
                        return gameState.getPlayer().getEquipment(equipmentName).use(obj, gameState);
                    }
                } else {
                    return "You do not have " + equipmentName;
                }
            } else {
                return "Invalid use target";
            }
        }
    }

    @Override
    public String toString() {
        return "use " + this.equipmentName + " " + preposition + " " + this.value;
    }

  
}
