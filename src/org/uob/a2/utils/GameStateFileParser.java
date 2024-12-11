package org.uob.a2.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.uob.a2.gameobjects.*;

/**
 * Utility class for parsing a game state from a file.
 *
 * <p>
 * This class reads a structured text file to create a {@code GameState} object,
 * including the player, map, rooms, items, equipment, features, and exits.
 * </p>
 */
public class GameStateFileParser {
    public GameStateFileParser() {

    }

    public static GameState parse(String filename) {
        Player player = new Player();
        Map map = new Map();


        BufferedReader reader;
        StringBuilder builder = new StringBuilder();
        try {
            File file = new File(filename);
            reader = new BufferedReader(new FileReader(file));
            String line;
            builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        String[] lines = builder.toString().split("\n");
//        System.out.println(Arrays.toString(lines));

        String[] splitLine;
        String objectType;
        String objectId;
        Room currentRoom = null;
        String firstRoomId = null;

        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            } else {
                splitLine = line.split(",");
                try {
                    objectType = splitLine[0].split(":")[0];
                    objectId = splitLine[0].split(":")[1];
                } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println("unexpected input! a line in the file doesnt start with objectType:objectId!");
                    break;
                    // erroenous input
                }
//          System.out.println("ID_AND_TYPE: " + objectType + " " + objectId);
//          System.out.println(objectType);

                switch (objectType) {
                    case "player":
                        player = new Player(objectId);
                        break;

                    case "map":
                        map = new Map();
                        firstRoomId = objectId;
                        break;

                    case "room":
                        currentRoom = new Room(objectId,
                                splitLine[1],
                                splitLine[2],
                                Boolean.parseBoolean(splitLine[3])
                        );
//                    System.out.println(currentRoom);
                        map.addRoom(currentRoom);
                        break;

                    case "feature":
                    case "container":
                        currentRoom.addFeature(
                                new Feature(objectId,
                                        splitLine[1],
                                        splitLine[2],
                                        Boolean.parseBoolean(splitLine[3])
                                )
                        );
                        break;

                    case "item":
                        currentRoom.addItem(
                                new Item(objectId,
                                        splitLine[1],
                                        splitLine[2],
                                        Boolean.parseBoolean(splitLine[3])
                                )
                        );
                        break;

                    case "equipment":
                        currentRoom.addEquipment(
                                new Equipment(objectId,
                                        splitLine[1],
                                        splitLine[2],
                                        Boolean.parseBoolean(splitLine[3]),
                                        new UseInformation(
                                                false,
                                                splitLine[4],
                                                splitLine[5],
                                                splitLine[6],
                                                splitLine[7]
                                        )
                                )
                        );
                        break;

                    case "exit":
                        currentRoom.addExit(
                                new Exit(objectId,
                                        splitLine[1],
                                        splitLine[2],
                                        splitLine[3],
                                        Boolean.parseBoolean(splitLine[4])
                                )
                        );
                        break;

                    default:
                        System.out.println(objectType);
                        System.out.println("unknown token");

                }

            }
        }

        map.setCurrentRoom(firstRoomId);
        GameState gs = new GameState(map, player);
        return gs;
    }

}
