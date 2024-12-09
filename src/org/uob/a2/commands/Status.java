package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

import java.util.stream.Collectors;

/**
 * Represents the status command, allowing the player to retrieve information
 * about their inventory, specific items, or their overall status.
 * 
 * <p>
 * The status command can display a list of items in the player's inventory, 
 * provide details about a specific item, or show the player's general status.
 * </p>
 */
public class Status extends Command {
    public Status(String topic) {
        this.value = topic;
        this.commandType = CommandType.STATUS;
    }

    @Override
    public String execute(GameState gameState) {
        if (value == null) {
            return "";
        }
        else if (value.equals("inventory")) {
            final String items = gameState.getPlayer().getInventory().stream().map(n -> n.getName()).collect(Collectors.joining(", "));
            final String equipment = gameState.getPlayer().getEquipment().stream().map(n -> n.getName()).collect(Collectors.joining(", "));
            return items + ", " + equipment;
        }

        else if (value.equals("player")) {
            final String items = gameState.getPlayer().getInventory().stream().map(n -> "- " + n.getDescription()).collect(Collectors.joining("\n"));
            final String equipment = gameState.getPlayer().getEquipment().stream().map(n -> "- " + n.getDescription()).collect(Collectors.joining("\n"));
            final String name = gameState.getPlayer().getName();

            return "Player Name: " + name + "\nInventory:\n" + items + "\nEquipment:\n" + equipment;
        }

        else if(gameState.getMap().getCurrentRoom() == null) {
            if(gameState.getPlayer().hasItem(value)) {
                return gameState.getPlayer().getItem(value).getDescription();
            }

            else if(gameState.getPlayer().hasEquipment(value)) {
                return gameState.getPlayer().getEquipment(value).getDescription();
            }
        }

        else if (gameState.getMap().getCurrentRoom() != null) {
            if(gameState.getMap().getCurrentRoom().hasItem(value)) {
                return gameState.getMap().getCurrentRoom().getItem(value).getDescription();
            }

            else if(gameState.getMap().getCurrentRoom().hasEquipment(value)) {
                return gameState.getMap().getCurrentRoom().getEquipment(value).getDescription();
            }
        }

        else {
            return "";
        }
        return "";
    }

    @Override
    public String toString() {
        return "STATUS " + this.value;
    }
}
