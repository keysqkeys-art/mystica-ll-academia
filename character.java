public class character {
    protected String name;
    protected boolean nameHidden;

    public character (String name, boolean nameHidden) {
        this.name = name;
        this.nameHidden = nameHidden;
    }

    public String getName() { return name; }
    public boolean getNameHidden() { return nameHidden; }

    public void setName(String name) {this.name = name;}
    public void setNameHidden(boolean nameHidden) {this.nameHidden = nameHidden;}

    public String getDisplayName() {
        if (nameHidden) {return "???";}
        return name;
    }

    public void introduce() {
        System.out.println(getDisplayName() + " : \"I am " + getDisplayName() + ".\"");
    }
}


