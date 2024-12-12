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
    final static String MUIRHEAD_TEXT = """
 * ▓█████  ██████ ▄████▄  ▄▄▄      ██▓███  ██▓███▄    █  ▄████
 * ▓█   ▀▒██    ▒▒██▀ ▀█ ▒████▄   ▓██░  ██▓██▒██ ▀█   █ ██▒ ▀█▒
 * ▒███  ░ ▓██▄  ▒▓█    ▄▒██  ▀█▄ ▓██░ ██▓▒██▓██  ▀█ ██▒██░▄▄▄░
 * ▒▓█  ▄  ▒   ██▒▓▓▄ ▄██░██▄▄▄▄██▒██▄█▓▒ ░██▓██▒  ▐▌██░▓█  ██▓
 * ░▒████▒██████▒▒ ▓███▀ ░▓█   ▓██▒██▒ ░  ░██▒██░   ▓██░▒▓███▀▒
 * ░░ ▒░ ▒ ▒▓▒ ▒ ░ ░▒ ▒  ░▒▒   ▓▒█▒▓▒░ ░  ░▓ ░ ▒░   ▒ ▒ ░▒   ▒
 *  ░ ░  ░ ░▒  ░ ░ ░  ▒    ▒   ▒▒ ░▒ ░     ▒ ░ ░░   ░ ▒░ ░   ░
 *    ░  ░  ░  ░ ░         ░   ▒  ░░       ▒ ░  ░   ░ ░░ ░   ░
 *    ░  ░     ░ ░ ░           ░  ░        ░          ░      ░
 *               ░
 *  ███▄ ▄███▓█    ██ ██▓██▀███  ██░ ██▓█████▄▄▄     ▓█████▄
 * ▓██▒▀█▀ ██▒██  ▓██▓██▓██ ▒ ██▓██░ ██▓█   ▒████▄   ▒██▀ ██▌
 * ▓██    ▓██▓██  ▒██▒██▓██ ░▄█ ▒██▀▀██▒███ ▒██  ▀█▄ ░██   █▌
 * ▒██    ▒██▓▓█  ░██░██▒██▀▀█▄ ░▓█ ░██▒▓█  ░██▄▄▄▄██░▓█▄   ▌
 * ▒██▒   ░██▒▒█████▓░██░██▓ ▒██░▓█▒░██░▒████▓█   ▓██░▒████▓
 * ░ ▒░   ░  ░▒▓▒ ▒ ▒░▓ ░ ▒▓ ░▒▓░▒ ░░▒░░░ ▒░ ▒▒   ▓▒█░▒▒▓  ▒
 * ░  ░      ░░▒░ ░ ░ ▒ ░ ░▒ ░ ▒░▒ ░▒░ ░░ ░  ░▒   ▒▒ ░░ ▒  ▒
 * ░      ░   ░░░ ░ ░ ▒ ░ ░░   ░ ░  ░░ ░  ░   ░   ▒   ░ ░  ░
 *        ░     ░     ░    ░     ░  ░  ░  ░  ░    ░  ░  ░
 *                                                    ░
""";

    public static void main(String[] args) {
        System.out.println("Welcome to...");
        System.out.println(MUIRHEAD_TEXT);
        String filename = "./data/game.txt";
        GameState gameState = GameStateFileParser.parse(filename);
        gameState.setExploredFloors(0);
        gameState.getPlayer().addEquipment(new Equipment("kpcd","code-muirhead","the code to the keypad", true, new UseInformation(false, "open", "f2f1", "f2e0","you have enterred the correct code!")));
        gameState.getPlayer().setScore(100);


//        System.out.println(gameState.getMap().getRooms());

        boolean quit = false;

        Scanner scanner = new Scanner(System.in);
        Tokeniser tokeniser = new Tokeniser();
        Parser parser = new Parser();
        Command lastCommand = null;

        while (!quit) {
            System.out.print(">");
            String input =  scanner.nextLine();
//            System.out.println(tokeniser.getTokens().stream().map(n -> n.getTokenType().name()).collect(Collectors.joining(" ")));
            if (input.equals("play keypadGame") && gameState.getExploredFloors() == 4) {
                boolean win = false;
                Scanner mgScanner = new Scanner(System.in);
                String lastInput;
                String code = "MUIRHEAD";


                System.out.println("This is a memory game. you ned to remember the code thatll be flashed on screen 5 seconds.");


                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }


                System.out.println(new String(new char[100]).replace("\0", "\n"));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                    System.out.println( code);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    System.out.println(new String(new char[100]).replace("\0", "\n"));
                    // clears the screen:

                    lastInput = mgScanner.nextLine();
                    if (code.equals(lastInput)) {
                        System.out.println("it appears youve exited the building!'\nYou have officially won the game...");
                        System.out.println(MUIRHEAD_TEXT);
                        quit = true;
                    }
                    else {
                        System.out.println("WRONG!");
                    }
            }

            tokeniser.tokenise(input);
            try {
                lastCommand = parser.parse(tokeniser.getTokens());
                System.out.println(UI.addColour(lastCommand.execute(gameState)));

                if (lastCommand.commandType == CommandType.QUIT) {
                    quit = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

}
