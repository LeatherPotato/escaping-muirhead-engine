package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the drop command, allowing the player to drop an item from their inventory into the current room.
 * 
 * <p>
 * This command checks if the player possesses the specified item and, if so, removes it from their inventory
 * and adds it to the current room. If the player does not have the item, an error message is returned.
 * </p>
 */
public class Drop extends Command {
    public Drop(String item) {
        this.commandType = CommandType.DROP;
        this.value = item;
    }

    @Override
    public String execute(GameState gameState) {
        if(gameState.getPlayer().hasItem(value)) {
            Item item = gameState.getPlayer().getItem(value);
            gameState.getPlayer().removeItem(item);
            gameState.getMap().getCurrentRoom().addItem(item);
            return "You drop: " + item.getName();
        }

        else if(gameState.getPlayer().hasEquipment(value)) {
            Equipment equipment = gameState.getPlayer().getEquipment(value);
            gameState.getPlayer().removeEquipment(equipment);
            gameState.getMap().getCurrentRoom().addEquipment(equipment);
            return "You drop: " + equipment.getName();
        }

        else {
            return "You cannot drop " + value;
        }
    }

    @Override
    public String toString() {
        return "DROP " + this.value;
    }
}
