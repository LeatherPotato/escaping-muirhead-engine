package org.uob.a2.gameobjects;

/**
 * Represents a generic game object that can be part of the game world.
 * 
 * <p>
 * Game objects have a name, description, unique identifier, and visibility state.
 * This abstract class serves as a base for more specific types of game objects.
 * </p>
 */
public abstract class GameObject {
    protected String description;
    protected boolean hidden;
    protected String id;
    protected String name;

    // CONSTRUCTOR
    public GameObject(String id, String name, String description, boolean hidden) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hidden = hidden;
    }

    // returns the description attribute
    public String getDescription() {
        return this.description;
    }

    // returns the hidden attibute
    public boolean getHidden() {
        return this.hidden;
    }

    // returns the id attribute
    public String getId() {
        return this.id;
    }

    // returns the name attribute
    public String getName() {
        return this.name;
    }

    // sets the description attribute
    public void setDescription(String description) {
        this.description = description;
    }

    // sets the hidden attribute
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    // sets the name attribute
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Returns a string representation of the game object, including its ID, name,
     * description, and visibility state.
     *
     * @return a string describing the game object
     */
    @Override
    public String toString() {
        return "GameObject {" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", description='" + this.description + '\'' +
                ", hidden=" + this.hidden +
                '}';
    }

}
