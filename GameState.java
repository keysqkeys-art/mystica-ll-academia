
public class GameState {
    private boolean snitched, skippedClass, detention, helpedMaya, foundDocuments;

    public GameState() {
        this.snitched = snitched;
        this.skippedClass = skippedClass;
        this.detention = detention;
        this.helpedMaya = helpedMaya;
        this.foundDocuments = foundDocuments;
    }

    public void setFlag(String flagName, boolean value) {
        switch (flagName) {
            case "snitched" :
                snitched = value;
                break;
            case "skippedClass" :
                skippedClass = value;
                break;
            case "detention" :
                detention = value;
                break;
            case "helpedMaya" :
                helpedMaya = value;
                break;
            case "foundDocuments" :
                foundDocuments = value;
                break;
        }
    }

    public boolean getFlag(String flagName) {
        switch (flagName) {
            case "snitched" :
                return snitched;
            case "skippedClass" :
                return skippedClass;
            case "detention" :
                return detention;
            case "helpedMaya" :
                return helpedMaya;
            case "foundDocuments" :
                return foundDocuments;
        }
        return false;
    }
}
