package org.uob.a2.parser;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.uob.a2.commands.*;

/**
 * The {@code Parser} class processes a list of tokens and converts them into {@code Command} objects
 * that can be executed by the game.
 * 
 * <p>
 * The parser identifies the type of command from the tokens and creates the appropriate command object.
 * If the command is invalid or incomplete, a {@code CommandErrorException} is thrown.
 * </p>
 */
public class Parser {
    public Parser() {}

    public Command parse(ArrayList<Token> tokens) throws CommandErrorException {
        Command command = null;
        CommandType commandType;
        if (!tokens.get(tokens.size() -1).getTokenType().equals(TokenType.EOL)) {
            tokens.add(new Token(TokenType.EOL));
        }
        try {
//            System.out.println(tokens.get(0).getTokenType().name());
            commandType = CommandType.valueOf(tokens.get(0).getTokenType().name().toUpperCase());
        }
        catch (Exception e) {
            throw new CommandErrorException("Not a valid command type");
        }
        switch (commandType) {
            case CommandType.MOVE:
                if (tokens.size() == 3) {
                    command = new Move(tokens.get(1).getValue());
                }
                else {
                    throw new CommandErrorException("'move' requires one argument (direction)");
                }
                break;

            case CommandType.USE:
                if (tokens.size() == 3) {
                    if (tokens.get(1).getTokenType().equals(TokenType.VAR)) {
                        command = new Use(tokens.get(1).getValue(), null);
                    }
                    else {
                        throw new CommandErrorException("Illegal argument.");
                    }
                }
                else {
                    if (tokens.size() == 5) {
                        if (tokens.get(1).getTokenType().equals(TokenType.VAR) && tokens.get(2).getTokenType().equals(TokenType.PREPOSITION) & tokens.get(3).getTokenType().equals(TokenType.VAR)) {
                            command = new Use(tokens.get(1).getValue(), tokens.get(3).getValue(), tokens.get(2).getValue());
                        }
                    }
                    else {
                        throw new CommandErrorException("'use' requires at least one argumet");
                    }
                }
                break;

            case CommandType.GET:
                if (tokens.size() == 3) {
                    if (tokens.get(1).getTokenType().equals(TokenType.VAR)) {
                        command = new Get(tokens.get(1).getValue());
                    }
                    else {
                        throw new CommandErrorException("Illegal argument.");
                    }
                }
                else {
                    throw new CommandErrorException("'get' requires one argument (the item/equipment to be picked up)");
                }
                break;


            case CommandType.DROP:
                if (tokens.size() == 3) {
                    if (tokens.get(1).getTokenType().equals(TokenType.VAR)) {
                        command = new Drop(tokens.get(1).getValue());
                    }
                    else {
                        throw new CommandErrorException("Illegal argument.");
                    }
                }
                else {
                    throw new CommandErrorException("'drop' requires one argument (the item/equipment to be dropped)");
                }
                break;


            case CommandType.LOOK:
                if (tokens.size() == 3) {
                    if (tokens.get(1).getTokenType().equals(TokenType.VAR)) {
                        command = new Look(tokens.get(1).getValue());
                    }
                    else {
                        throw new CommandErrorException("Illegal argument.");
                    }
                }
                else {
                    throw new CommandErrorException("'look' requires one argument (the item/room/feature/exit to be looked at)");
                }
                break;

            case CommandType.STATUS:
                if (tokens.size() == 3) {
                    if (tokens.get(1).getTokenType().equals(TokenType.VAR)) {
                        command = new Status(tokens.get(1).getValue());
                    }
                    else {
                        throw new CommandErrorException("Illegal argument.");
                    }
                }
                else {
                    throw new CommandErrorException("'status' requires one argument (the object whos status is wanted)");
                }
                break;

            case CommandType.COMBINE:
                if (tokens.size() == 4) {
                    if (tokens.get(1).getTokenType().equals(TokenType.VAR) && tokens.get(2).getTokenType().equals(TokenType.VAR)) {
                        command = new Combine(tokens.get(1).getValue(), tokens.get(2).getValue());
                    }
                    else {
                        throw new CommandErrorException("Illegal arguments.");
                    }
                }
                else{
                    throw new CommandErrorException("'combine' requires two arguments.");
                }
                break;

            case CommandType.HELP:
//                System.out.println(tokens.stream().map(n -> n.getTokenType().name()).collect(Collectors.joining(" ")));
                if (tokens.size() == 3) {
                    if (tokens.get(1).getTokenType().equals(TokenType.VAR)) {
                        command = new Help(tokens.get(1).getValue());
                    }
                    else {
                        throw new CommandErrorException("Illegal argument.");
                    }
                }
                else {
                    if (tokens.size() == 2) {
                        command = new Help(null);
                    }
                    else {
                        throw new CommandErrorException("'help' requires one argument (the command you want more information about)");
                    }
                }
                break;

            case CommandType.QUIT:
                if (tokens.size() == 2) {
                    command = new Quit();
                }
                else {
                    throw new CommandErrorException("'quit' accepts no arguments");
                }
                break;

            default:
                throw new CommandErrorException("Not a valid command type - def");

        }

        return command;
    }
 
}
