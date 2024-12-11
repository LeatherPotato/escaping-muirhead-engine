package org.uob.a2;

import java.util.Scanner;
import java.util.stream.Collectors;

import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.*;

/**
 * Main class for the game application. Handles game setup, input parsing, and game execution.
 * 
 * <p>
 * This class initializes the game state, reads user input, processes commands, and maintains the game loop.
 * </p>
 */
public class Game {

    public static void main(String[] args) {
        System.out.println("Welcome to UOB");
        String filename = "./data/game.txt";
        GameState gameState = GameStateFileParser.parse(filename);
        gameState.setExploredFloors(0);

//        System.out.println(gameState.getMap().getRooms());

        boolean quit = false;

        Scanner scanner = new Scanner(System.in);
        Tokeniser tokeniser = new Tokeniser();
        Parser parser = new Parser();
        Command lastCommand = null;

        while (!quit) {
            System.out.print(">");
            String input =  scanner.nextLine();
            tokeniser.tokenise(input);
//            System.out.println(tokeniser.getTokens().stream().map(n -> n.getTokenType().name()).collect(Collectors.joining(" ")));

            try {
                lastCommand = parser.parse(tokeniser.getTokens());
                System.out.println(lastCommand.execute(gameState));

                if (lastCommand.commandType == CommandType.QUIT) {
                    quit = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

}
