package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the get command, allowing the player to pick up an item from the current room and add it to their inventory.
 * 
 * <p>
 * This command checks if the specified item is present in the current room. If the item exists and the player
 * does not already have it, the item is added to the player's inventory and removed from the room. Otherwise,
 * an appropriate message is returned.
 * </p>
 */
public class Get extends Command {

    public Get(String item) {
        this.commandType = CommandType.GET;
        this.value = item;
    }

    @Override
    public String execute(GameState gameState) {
        boolean targetExists = gameState.getMap().getCurrentRoom().hasItem(value) ||
                gameState.getMap().getCurrentRoom().hasEquipment(value);
        if (targetExists) {
            boolean playerHasTarget = gameState.getPlayer().hasItem(value) ||
                    gameState.getPlayer().hasEquipment(value);

            if (playerHasTarget) {
                return "You already have " + value;
            }
            else {
                boolean isItem = gameState.getMap().getCurrentRoom().hasItem(value);
                if (isItem) {
                    gameState.getPlayer().addItem(gameState.getMap().getCurrentRoom().getItemByName(value));
                    gameState.getMap().getCurrentRoom().removeItem(gameState.getMap().getCurrentRoom().getItemByName(value));
                }
                else {
                    gameState.getPlayer().addEquipment(gameState.getMap().getCurrentRoom().getEquipmentByName(value));
                    gameState.getMap().getCurrentRoom().removeEquipment(gameState.getMap().getCurrentRoom().getEquipmentByName(value));
                }
                return "You pick up: " + value;
            }
        }
        else {
            return "No " + value + " to get.";
        }
    }

    @Override
    public String toString() {
        return "GET " + this.value;
    }

}
