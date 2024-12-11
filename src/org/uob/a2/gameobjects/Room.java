package org.uob.a2.gameobjects;

import java.sql.Array;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a room in the game, which is a type of {@code GameObject}.
 * 
 * <p>
 * Rooms can have items, equipment, features, and exits. They also manage navigation
 * and interactions within the game world.
 * </p>
 */
public class Room extends GameObject {
    ArrayList<Item> items;
    ArrayList<Equipment> equipment;
    ArrayList<Feature> features;
    ArrayList<Exit> exits;


    // constructor
    public Room(String id, String name, String description, boolean hidden) {
        super(id, name, description, hidden);

        items = new ArrayList<Item>();
        equipment = new ArrayList<Equipment>();
        features = new ArrayList<Feature>();
        exits = new ArrayList<Exit>();
    }

    // adds equipment
    public void addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
    }

    // adds exit
    public void addExit(Exit exit) {
        this.exits.add(exit);
    }

    // adds item
    public void addItem(Item item) {
        this.items.add(item);
    }

    // adds feature
    public void addFeature(Feature feature) {
        this.features.add(feature);
    }

    public ArrayList<GameObject> getAll() {
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        gameObjects.addAll(this.items);
        gameObjects.addAll(this.equipment);
        gameObjects.addAll(this.features);
        gameObjects.addAll(this.exits);

        return gameObjects;
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    public Equipment getEquipment(String id) {
        return equipment.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    public Equipment getEquipmentByName(String name) {
        return equipment.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    public ArrayList<Equipment> getEquipments() {
        return equipment;
    }

    public ArrayList<Equipment> getNonHiddenEquipments() {
        return equipment.stream().filter(x -> !x.hidden).collect(Collectors.toCollection(ArrayList::new));
    }

    public Exit getExit(String id) {
        return exits.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    public ArrayList<Exit> getExits() {
        return exits;
    }

    public ArrayList<Exit> getNonHiddenExits() {
        return exits.stream().filter(x -> !x.hidden).collect(Collectors.toCollection(ArrayList::new));
    }

    public Exit getExitByName(String name) {
        return exits.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    public Feature getFeature(String id) {
        return features.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    public Feature getFeatureByName(String name) {
        return features.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public ArrayList<Feature> getNonHiddenFeatures() {
        return features.stream().filter(x -> !x.hidden).collect(Collectors.toCollection(ArrayList::new));
    }

    public Item getItem(String id) {
        return items.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    public Item getItemByName(String name) {
        return items.stream().filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Item> getNonHiddenItems() {
        return items.stream().filter(x -> !x.hidden).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public boolean hasEquipment(String name) {
        return equipment.stream().anyMatch(x -> x.getName().equals(name));
    }

    public boolean hasItem(String name) {
        return items.stream().anyMatch(x -> x.getName().equals(name));
    }

    public boolean hasFeature(String name) {
        return features.stream().anyMatch(x -> x.getName().equals(name));
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void removeEquipment(Equipment equipment) {
        this.equipment.remove(equipment);
    }

    public void removeFeature(Feature feature) {
        this.features.remove(feature);
    }

    public void removeExit(Exit exit) {
        this.exits.remove(exit);
    }


    /**
     String
     * Returns a string representation of the room, including its contents and description.
     *
     * @return a string describing the room
     */
    @Override
    public String toString() {
        String out = "[" + id + "] Room: " + name + "\nDescription: " + description + "\nIn the room there is: ";
        for (Item i : this.items) {
            out += i.toString() + "\n";
        }
        for (Equipment e : this.equipment) {
            out += e.toString() + "\n";
        }
        for (Feature f : this.features) {
            out += f.toString() + "\n";
        }
        for (Exit e : this.exits) {
            out += e.toString() + "\n";
        }
        return out + "\n";
    }
}
