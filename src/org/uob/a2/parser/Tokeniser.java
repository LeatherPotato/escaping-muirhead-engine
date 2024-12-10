package org.uob.a2.parser;

import org.uob.a2.commands.CommandType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * The {@code Tokeniser} class is responsible for converting a string input into a list of tokens
 * that can be parsed into commands by the game.
 * 
 * <p>
 * The tokeniser identifies keywords, variables, and special symbols, assigning each a {@code TokenType}.
 * </p>
 */
public class Tokeniser {
    ArrayList<Token> tokens;

    public Tokeniser() {
        tokens = new ArrayList<>();
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public String sanitise(String s) {
        StringBuilder sanitised = new StringBuilder();
        char prevC = (char) 0;
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                // if char is not equal to whitespace then it adds the lowercase character
                sanitised.append(Character.toLowerCase(c));
            }
            else if (c != prevC && i!=0) {
                // if char is whitespace and is not equal to the previous, character, then it is added
                // therefore only 1 whitespace character is added
                sanitised.append(' ');
            }
            prevC = c;
        }

        // if whitespace character is at end of input string then it is removed
        if (sanitised.charAt(sanitised.length()-1) == ' ') {
            sanitised.setLength(sanitised.length()-1);
        }
        return sanitised.toString();
    }

    public void tokenise(String s) {
        tokens.clear();
        String[] split = this.sanitise(s).split("\\s+");
//        System.out.println(Arrays.toString(split));

        for (int i=0; i<split.length; i++) {
            String tokenString = split[i];
            try {
                if (tokenString.equals("var") || tokenString.equals("error") || tokenString.equals("preposition") || tokenString.equals("eol")) {
                    tokens.add(new Token(TokenType.ERROR, null));
                }
                else {
                    Token tok = new Token(TokenType.valueOf(tokenString.toUpperCase()), null);
                    if (i == 0) {
                        tokens.add(tok);
                    }
                }
            } catch (Exception e) {
                final String[] prepositions = {"on", "with", "to", "using"};


                if (Arrays.stream(prepositions).anyMatch(tokenString::contains)) {
                    tokens.add( new Token(TokenType.PREPOSITION, tokenString) );
                }

                else if (false) {
                    continue;
                    // not sure what to do with the TokenType.ERROR type so ill just leave this part empty and if
                    // figure out how that works then you can add it in later
                }

                else {
                    tokens.add(new Token(TokenType.VAR, tokenString));
                }
            }
        }

        tokens.add(new Token(TokenType.EOL, null));
//        System.out.println(tokens.stream().map(n -> n.getTokenType().toString()).collect(Collectors.joining(" ")));

        // do stuff on the split array to turn it into token array then set this.tokens to local tokens variable
    }
   
}
