package org.uob.a2.gameobjects;
import java.util.ArrayList;


/**
 * Represents the player in the game, including their name, inventory, and equipment.
 * 
 * <p>
 * The player can carry inventory and equipment, interact with the game world, and perform
 * actions using their inventory or equipment.
 * </p>
 */
public class Player {
    private ArrayList<Equipment> equipment;
    private ArrayList<Item> inventory;
    private String name;

    // defqault constructor
    public Player() {}

    // constructor with player name
    public Player(String name) {
        this.name = name;
    }

    public void addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public Equipment getEquipment(String equipmentName) {
        return equipment.stream().filter(x -> x.getName().equals(equipmentName)).findFirst().orElse(null);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Item getItem(String itemName) {
        return inventory.stream().filter(x -> x.getName().equals(itemName)).findFirst().orElse(null);
    }

    public String getName() {
        return name;
    }

    public boolean hasEquipment(String equipmentName) {
        return equipment.stream().filter(x -> x.getName().equals(equipmentName)).findFirst().isPresent();
    }

    public boolean hasItem(String itemName) {
        return inventory.stream().filter(x -> x.getName().equals(itemName)).findFirst().isPresent();
    }


    /**
     * Returns a string representation of the player's current state, including their name,
     * inventory, and equipment descriptions.
     *
     * @return a string describing the player, their inventory, and equipment
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Player Name: " + this.name + "\nInventory:\n");
        for (Item i : this.inventory) {
            out.append("- ").append(i.getDescription()).append("\n");
        }
        out.append("Equipment:\n");
        for (Equipment e : this.equipment) {
            out.append("- ").append(e.getDescription()).append("\n");
        }
        return out.toString();
    }
}
