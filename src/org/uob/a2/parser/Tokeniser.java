package org.uob.a2.parser;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public String sanitise(String s) {
        StringBuilder sanitised = new StringBuilder(new String());
        char prevC = (char) 0;
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                // if char is not equal to whitespace then it adds the lowercase character
                sanitised.append(Character.toLowerCase(c));
            }
            else if (c != prevC) {
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
        ArrayList<Token> tokens = new ArrayList<>();
        String[] split = this.sanitise(s).split("\\s+");

        // do stuff on the split array to turn it into token array then set this.tokens to local tokens variable
    }
   
}
