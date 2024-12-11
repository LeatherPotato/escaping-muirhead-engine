package org.uob.a2.gameobjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;
import java.util.stream.Collectors;

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

    final String FLOOR_NOT_ON = "▒";
    final String FLOOR_ON_IN_ROOM = "▓";
    final String FLOOR_ON = "█";
    final String STAIRS = "░";
    final String EMPTY = " ";


    public Map() {
        rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(String roomId) {
        currentRoom = rooms.stream().filter(x -> x.getId().equals(roomId)).findFirst().orElse(null);
    }

    private Room getRoom(String roomId) {

        return rooms.stream().filter(x -> x.getId().equals(roomId)).findFirst().orElse(null);
    }

   public String display(int exploredFloors, int totalFloors) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Room> floors = rooms.stream().filter(n -> n.getId().length() == 2).collect(Collectors.toCollection(ArrayList::new));

        UI ui = new UI();

       final String horizontalBorder = "+" + new String(new char[16]).replace("\0", "16") + "+";
       sb.append(horizontalBorder);

        for (int i = 0; i < exploredFloors; i++) {
            ArrayList<Room> roomsOnFloor = floors.get(i).getExits().stream().map(n -> getRoom(n.getNextRoom())).collect(Collectors.toCollection(ArrayList::new));
            String[] roomsOnFloorText = new String[roomsOnFloor.size()];
            String floorText;
            if (i%2 == 0) {
                for (int n = 0; n<roomsOnFloor.size(); n++) {
                    Room room = roomsOnFloor.get(n);
                    if (room.equals(currentRoom)) {
                        roomsOnFloorText[n] = ui.addMapPlayerPosColour(room.getId().replaceAll("F\\d", ""));
                    }
                    else {
                        roomsOnFloorText[n] = room.getId();
                    }
                }
                if (floors.get(i).equals(currentRoom)) {
                    floorText = ui.addMapPlayerPosColour(new String(new char[7]).replace("\0", FLOOR_ON));
                }
                else if (i == Character.getNumericValue(currentRoom.getId().charAt(1))) {
                    floorText = new String(new char[5]).replace("\0", FLOOR_ON_IN_ROOM);
                }
                else {
                    floorText = new String(new char[5]).replace("\0", FLOOR_NOT_ON);
                }
                sb.append("|" + " ").append(roomsOnFloorText[1]).append(" ").append(roomsOnFloorText[0]).append("         ").append("|\n");
                sb.append("|").append(roomsOnFloorText[2]).append(floorText).append("|\n");
                sb.append("|" + " ").append(roomsOnFloorText[3]).append(" ").append(roomsOnFloorText[4]);

                if (i<exploredFloors-1) {
                    sb.append(STAIRS).append("        |\n");
                }
                else {
                    sb.append("         |\n");
                }
            }
            else {
                for (int n = 0; n<roomsOnFloor.size(); n++) {
                    Room room = roomsOnFloor.get(n);
                    if (room.equals(currentRoom)) {
                        roomsOnFloorText[n] = ui.addMapPlayerPosColour(room.getId().replaceAll("F\\d", ""));
                    }
                    else {
                        roomsOnFloorText[n] = room.getId();
                    }
                }
                if (floors.get(i).equals(currentRoom)) {
                    floorText = ui.addMapPlayerPosColour(new String(new char[7]).replace("\0", FLOOR_ON));
                }
                else if (i == Character.getNumericValue(currentRoom.getId().charAt(1))) {
                    floorText = new String(new char[5]).replace("\0", FLOOR_ON_IN_ROOM);
                }
                else {
                    floorText = new String(new char[5]).replace("\0", FLOOR_NOT_ON);
                }
//                sb.append("|" + " " + roomsOnFloorText[1] + " " + roomsOnFloorText[0] + "         " + "|\n");
//                sb.append("|" + roomsOnFloorText[2] + floorText + "|\n");
//                sb.append("|" + " " + roomsOnFloorText[3]+ " " + roomsOnFloorText[4]);
                sb.append("|        ").append(roomsOnFloorText[0]).append(" ").append(roomsOnFloorText[1]).append("  |\n");
                sb.append("|         ").append(floorText).append(roomsOnFloorText[2]).append("|\n");

                if (i<exploredFloors-1) {
                    sb.append("|        " + STAIRS).append(roomsOnFloorText[3]).append(" ").append(roomsOnFloorText[4]).append("  |\n");
                }
                else {
                    sb.append("|         ").append(roomsOnFloorText[3]).append(" ").append(roomsOnFloorText[4]).append("|\n");
                }
            }
        }

        sb.append(horizontalBorder);

        return sb.toString();
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

