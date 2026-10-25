public class npc extends character {
    private String role;

    public npc (String name, boolean nameHidden, String role) {
        super(name,nameHidden);
        this.role = role;
    }

    @Override
    public void introduce() {
        // System.out.println(d.getDialogue().replace("#", playerName));
        // "i'm #" -> "i'm Name"
        System.out.println(getDisplayName() + " :  \"I'm " + getDisplayName() + ", and I am a " + role + ".\"");
    }

    // @Override
    public void customIntroduce(String dialogue) {
        // System.out.println(d.getDialogue().replace("#", playerName));
        // "i'm #" -> "i'm Name"
        System.out.println(name + " : " + dialogue.replace("#", name));
    }
    
}