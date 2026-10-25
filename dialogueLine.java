import java.util.ArrayList;

public class dialogueLine {
    protected String name;
    protected String dialogue;
    protected String id;
    protected boolean hidden;
    protected String trigger;
    protected ArrayList<String> triggerList;

    public dialogueLine(String name, String dialogue, String id, boolean hidden) {
        this.name = name;
        this.dialogue = dialogue;
        this.id = id;
        this.hidden = hidden;
        this.trigger = "";
        this.triggerList = new ArrayList<>();
    }

    public String getName() {return name;}
    public String getDialogue() {return dialogue;}
    public String getId() {return id;}
    public boolean getHidden() {return hidden;}
    public String getTrigger() {return trigger;}
    public ArrayList<String> getTriggerList() {return triggerList;}

    public void setTrigger(String trigger) {this.trigger = trigger;}
    public void setTriggerList(ArrayList<String> triggerList) {this.triggerList = triggerList;}
}


// character1|"__________"|0001|false|askChoice