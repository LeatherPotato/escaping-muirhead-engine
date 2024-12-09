package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

import java.util.stream.Collectors;

/**
 * Represents the look command, allowing the player to examine various elements of the game world.
 * 
 * <p>
 * The look command can provide details about the current room, its exits, features, or specific items and equipment.
 * Hidden objects are not included in the output unless explicitly revealed.
 * </p>
 */
public class Look extends Command {
    public Look(String target) {
        this.value = target;
        this.commandType = CommandType.LOOK;
    }

    @Override
    public String execute(GameState gameState) {
        if (value == null) {
            return "";
        }

        else if (value.equals("room")) {
            final String roomName = gameState.getMap().getCurrentRoom().getName();
            final String roomDescription = gameState.getMap().getCurrentRoom().getDescription();
            final String roomFeatures = gameState.getMap().getCurrentRoom().getFeatures().stream().map(n -> "- " + n.getName() + ": " + n.getDescription()).collect(Collectors.joining("\n"));
            final String roomItems = gameState.getMap().getCurrentRoom().getItems().stream().map(n -> "- " + n.getName() + ": " + n.getDescription()).collect(Collectors.joining("\n"));
            final String roomEquipment = gameState.getMap().getCurrentRoom().getEquipments().stream().map(n -> "- " + n.getName() + ": " + n.getDescription()).collect(Collectors.joining("\n"));

           return "Room name: " + roomName + "\nRoom description: " + roomDescription + "\nFeatures:\n" + roomFeatures + "\nItems:\n" + roomItems + "\nEquipment:\n" + roomEquipment;
        }

        else if(value.equals("exits")) {
            final String exits = gameState.getMap().getCurrentRoom().getExits().stream().map(n -> "- " + n.getName() + ": " + n.getDescription()).collect(Collectors.joining("\n"));

            return "The available exits are:\n" + exits;
        }

        else if(value.equals("features")) {
            final String roomFeatures = gameState.getMap().getCurrentRoom().getFeatures().stream().map(n -> "- " + n.getName() + ": " + n.getDescription()).collect(Collectors.joining("\n"));

            return "You also see: " + roomFeatures;
        }

        else if(gameState.getMap().getCurrentRoom().hasItem(value)) {
            return gameState.getMap().getCurrentRoom().getItemByName(value).getDescription();
        }

        else if(gameState.getMap().getCurrentRoom().hasEquipment(value)) {
            return gameState.getMap().getCurrentRoom().getEquipmentByName(value).getDescription();
        }

        else if(gameState.getMap().getCurrentRoom().hasFeature(value)) {
            return gameState.getMap().getCurrentRoom().getFeatureByName(value).getDescription();
        }

        else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "LOOK " + this.value;
    }


}
