package org.uob.a2.gameobjects;

public class Equipment extends GameObject implements Usable {
    protected UseInformation useInformation;

    // CONSTRUCTOR
    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation) {
        super(id, name, description, hidden);
        this.id = id;
        this.name = name;
        this.description = description;
        this.hidden = hidden;
        this.useInformation = useInformation;
    }

    // OTHER METHODS

    // uses the equipment object and updates gameState
    public String use(GameObject target, GameState gameState) {
        return "";
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

