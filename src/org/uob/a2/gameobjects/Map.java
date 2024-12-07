package org.uob.a2.gameobjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;

import org.uob.a2.utils.*;

/**
 * Represents the game map, which consists of a collection of rooms and the current room the player is in.
 * 
 * <p>
 * The map allows for navigation between rooms, adding new rooms, and managing the current room context.
 * </p>
 */
public class Map {
    private ArrayList<Room> rooms;
    private Room currentRoom;

    public Map() {}

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String roomId) {
        currentRoom = rooms.stream().filter(x -> x.getId().equals(roomId)).findFirst().orElse(null);
    }

    /**
     * Returns a string representation of the map, including all rooms.
     *
     * @return a string describing the map and its rooms
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Map:\n");
        for (Room r : this.rooms) {
            out.append(r.toString()).append("\n");
        }
        return out.toString();
    }
}

