package org.uob.a2.gameobjects;

public class Equipment extends GameObject implements Usable {
    protected UseInformation useInformation;

    // CONSTRUCTOR
    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation) {
        super(id, name, description, hidden);
        this.useInformation = useInformation;
    }

    // OTHER METHODS

    // uses the equipment object and updates gameState
    public String use(GameObject target, GameState gameState) {
        useInformation.setUsed(true);
        if (target != null) {
            target.setHidden(true);
        }

        boolean newHidden = false;
        if (useInformation.getAction().equals("open")) {
            gameState.setExploredFloors(gameState.getExploredFloors() + 1);
        }
        else if (useInformation.getAction().equals("op")) {
            gameState.setPegsUsed(gameState.getPegsUsed() + 1);
            if (gameState.getPegsUsed() == 3) {
                gameState.setExploredFloors(gameState.getExploredFloors() + 1);
            }
            else {
                newHidden = true;
            }
        }

        GameObject obj = gameState.getMap().getCurrentRoom().getItem(useInformation.getResult());
        if (obj == null) {
            obj = gameState.getMap().getCurrentRoom().getEquipment(useInformation.getResult());
            if (obj == null) {
                obj = gameState.getMap().getCurrentRoom().getFeature(useInformation.getResult());
                if (obj == null) {
                    obj = gameState.getMap().getCurrentRoom().getExit(useInformation.getResult());
                    if (obj != null) {
                        obj.setHidden(newHidden);
                    }
                }
                else {
                    obj.setHidden(newHidden);
                }
            }
            else {
                obj.setHidden(newHidden);
            }
        }
        else {
            obj.setHidden(newHidden);
        }
        String returnMessage = useInformation.getMessage();
        gameState.getPlayer().removeEquipment(this);
        return returnMessage;
    }

    @Override
    public void setUseInformation(UseInformation useInfo) {
        this.useInformation = useInfo;
    }

    @Override
    public UseInformation getUseInformation() {
        return useInformation;
    }

    /**
     * Returns a string representation of this equipment, including the attributes inherited from {@code GameObject}
     * and the associated use information.
     *
     * @return a string describing the equipment
     */
    @Override
    public String toString() {
        return super.toString() + ", useInformation=" + useInformation;
    }

}

