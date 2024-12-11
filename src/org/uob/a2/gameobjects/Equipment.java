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
        GameObject obj = gameState.getMap().getCurrentRoom().getItem(useInformation.getResult());
        if (obj == null) {
            obj = gameState.getMap().getCurrentRoom().getEquipment(useInformation.getResult());
            if (obj == null) {
                obj = gameState.getMap().getCurrentRoom().getFeature(useInformation.getResult());
                if (obj == null) {
                    obj = gameState.getMap().getCurrentRoom().getExit(useInformation.getResult());
                    if (obj != null) {
                        obj.setHidden(false);
                    }
                }
                else {
                    obj.setHidden(false);
                }
            }
            else {
                obj.setHidden(false);
            }
        }
        else {
            obj.setHidden(false);
        }
        if (useInformation.getAction().equals("open")) {
            gameState.setExploredFloors(gameState.getExploredFloors() + 1);
        }
        return useInformation.getMessage();
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

