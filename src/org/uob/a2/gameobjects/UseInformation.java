package org.uob.a2.gameobjects;

/**
 * Represents information about how an object can be used in the game.
 * 
 * <p>
 * This class stores details about the usage of an object, such as whether it has
 * already been used, the type of action it performs, the target of the action,
 * the result of the action, and any associated message.
 * </p>
 */
public class UseInformation {
    boolean isUsed;
    String action;
    String target;
    String result;
    String message;

    public UseInformation(boolean isUsed, String action, String target, String result, String message) {
        this.isUsed = isUsed;
        this.action = action;
        this.target = target;
        this.result = result;
        this.message = message;
    }

    String getAction() {
        return action;
    }

    String getTarget() {
        return target;

    }

    String getResult() {
        return result;
    }

    String getMessage() {
        return message;
    }

    boolean isUsed() {
        return isUsed;
    }

    void setAction(String action) {
        this.action = action;
    }

    void setTarget(String target) {
        this.target = target;
    }

    void setResult(String result) {
        this.result = result;
    }

    void setMessage(String message) {
        this.message = message;
    }

    void setUsed(boolean used) {
        isUsed = used;
    }

    /**
     * Returns a string representation of the usage information, including all attributes.
     *
     * @return a string describing the usage information
     */
    @Override
    public String toString() {
        return "UseInformation{" +
                "isUsed=" + isUsed +
                ", action='" + action + '\'' +
                ", target='" + target + '\'' +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
