package org.uob.a2.commands;

import org.uob.a2.gameobjects.GameState;

public class Combine extends Command {
    private String item1;
    private String item2;

    public Combine(String item1, String item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    @Override
    public String execute(GameState gameState) {
        if (item1.equals("red-amongus") && item2.equals("blue-amongus")) {
            if (gameState.getPlayer().hasItem(item1) && gameState.getPlayer().hasItem(item2)) {
                try {
                    gameState.getPlayer().removeItem(gameState.getPlayer().getItem("red-amongus"));
                    gameState.getPlayer().removeItem(gameState.getPlayer().getItem("blue-amongus"));
                    gameState.getMap().getCurrentRoom().getItemByName("purple-amongus").setHidden(false);
                    gameState.getPlayer().addItem(gameState.getMap().getCurrentRoom().getItemByName("purple-amongus"));
                    return "Combined red and blue amongi into purple amongus";
                }
                catch (Exception e) {
                    return "Combination not available in currentRoom";
                }
            }
            else {
                return "You dont have the required items";
            }
        }
        else {
            return "Invalid combine targets";
        }
    }

    @Override
    public String toString() {
        return "COMBINE: combine " + this.item1 + " " + this.item2;
    }
}
