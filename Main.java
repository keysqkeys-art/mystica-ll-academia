import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
// import java.awt.event.KeyEvent;

public class Main implements Runnable {
    private static final String textfile = "plotline.txt";
    // Main obj = new Main();
    // Thread check = new Thread(obj); 

    public static void main(String[] args) {
        
        
        Scanner userInput = new Scanner(System.in);
        List<dialogueLine> lines = loadFile(textfile);
        GameState state = new GameState();
        
        player p1 = new player("");
        p1.setNameHidden(false);

        playConversation(loadConversation(lines, "0001"), userInput, lines, "someone", p1, state);
        
    }

    // ------------------------------


    private static void waitSeconds(float seconds) {
        try {
            Thread.sleep(Math.round(seconds*1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("somerhing bad happened " + e);
        }
    }

    private static void playtestDialogue(float seconds, String dialogue){
        String[] splitDialogue = dialogue.split("");
        try {
            // check.start();
            for (String s : splitDialogue) {

                Thread.sleep(Math.round(seconds*1000));
                System.out.print(s);
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("somerhing bad happened " + e);
        }
    }

    public void run() {
        // if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        //     System.out.println("Enter key pressed!");
        // }   
        // get this to work
    }

    private static String askChoice(Scanner input, List<String> choices, String prompt, boolean showPrompt) {
        while(true) {
            // if (showPrompt) System.out.println(prompt);

            for (int i = 0; i < choices.size(); i++) {
                System.out.println((i + 1) + ". " + choices.get(i).split("\\-")[0]);
            }
            System.out.print("> ");

            try {
                int ans = Integer.parseInt(input.nextLine().trim());


                if ((ans >= 0) && (ans <= choices.size())) { 
                    
                    // System.out.println("askchoice returns : " + choices.get(ans-1).split("\\-")[1]);
                    return choices.get(ans-1).split("\\-")[1].trim(); }
            } catch (Exception e) {
                System.out.println("enter in a number");
            }
            
            System.out.println("invalid choice");
        }
    }

    private static String askInput(Scanner input, String prompt, boolean showPrompt) {
        while (true) {
            // if (showPrompt) System.out.println(prompt);
            System.out.print("> ");
            String ans = input.nextLine().trim();

            if ((ans == null) || (ans.equals(""))) {
                System.out.println("invalid input");
            } else {
                return ans;
            }
        }
    }

    // -----------------------------------------------

    private static List<dialogueLine> loadConversation(List<dialogueLine> data, String id) {
        List<dialogueLine> conversation = new ArrayList<>();
        // System.out.println("size of file : " + data.size());

        for (dialogueLine d : data) {
            // System.out.println(d.getDialogue() + " " + d);
            // System.out.println(d.getId() + ", " + id);

            if (d.getId().equals(id)) { 
                conversation.add(d);
            }
        }
        
        return conversation;
    }

    private static void playConversation(List<dialogueLine> data, Scanner sc, List<dialogueLine> file, String name, player p1, GameState state) {
        // System.out.println("trying to play conversation " + data);
        for (dialogueLine d : data) {

            if (d.getHidden() == true) {
                
                System.out.print("\u001B[47m" + "\u001B[30m" + "???" + "\u001B[0m" + " : " );
                playtestDialogue((float) 0.03,  (d.getDialogue().replace("#", p1.getDisplayName())) + "\n");

            } else {

                if (d.getName().equals("narrator")) {
                    playtestDialogue((float) 0.03, "\u001B[36m" + (d.getDialogue().replace("#", p1.getDisplayName())) + "\u001B[0m" + "\n");
                } else if (!d.getName().equals("player")) {
                    System.out.print("\u001B[47m" + "\u001B[30m" + d.getName() + "\u001B[0m" + " : " );
                    playtestDialogue((float) 0.03,  (d.getDialogue().replace("#", p1.getDisplayName())) + "\n");
                } else {
                    System.out.print("\u001B[47m" + "\u001B[30m" + p1.getDisplayName() + "\u001B[0m" + " : " );
                    playtestDialogue((float) 0.03, (d.getDialogue().replace("#", p1.getDisplayName()))+ "\n");
                }

            }
            

            handleTrigger(d, sc, file, p1, state); 
            
            waitSeconds(1);
        }
    }

    private static String handleTrigger(
        dialogueLine d,
        Scanner sc,
        List<dialogueLine> file,
        player p1,
        GameState state) {


            String trigger = d.getTrigger();

            // ------
            ArrayList<ArrayList<String>> trlist = new ArrayList<ArrayList<String>>();

            for (String t : d.getTriggerList()) {
                String[] currentList = t.split("-");
                ArrayList<String> currentArrayList = new ArrayList<String>();
            
                for (String s : currentList) { currentArrayList.add(s); }

                trlist.add(currentArrayList); 
                // System.out.println("trlist " + trlist);
            }
            // ------
            // stores trigger data
            String first = "", second = "", third = "";

            // .. : |".."|..|..|trigger|Opt1-0002,Opt2-0003,Opt3-0004
            // first-second####-third -> arraylist

            // System.out.println(trlist.size()-1);
            for (int i = 0; i < trlist.size(); i++) { // previously was trlist.size()-1
                // System.out.println(i);
                if (trlist.get(i).size() >= 1) { first = trlist.get(i).get(0); }
                if (trlist.get(i).size() >= 2) { second = trlist.get(i).get(1); }
                if (trlist.get(i).size() >= 3) { third = trlist.get(i).get(2); }
            } 

            // System.out.println("v '" + first + "', '" + second + "', '" + third + "'");

            
            // ------

            if (trigger.equals("askChoice")) {
                playConversation(loadConversation(file, askChoice(sc, d.getTriggerList(), d.getDialogue(), true)), sc, file, p1.getDisplayName(),p1, state);
            } else if (trigger.equals("askInput")) {
                p1.setName(askInput(sc, d.getDialogue(), true));
                playConversation(loadConversation(file, second), sc, file, p1.getDisplayName(), p1, state);
            } 
            // item usage ---------------------------
            else if (trigger.equals("addItem")) {
                boolean reuse = first.equals("true");
                p1.getInv().addItem(new item(third, reuse, Integer.parseInt(second.trim())));
            } else if (trigger.equals("useItem")) {
                    if (p1.getInv().hasItem(first)) {
                        p1.getInv().useItem(first); 
                        playConversation(loadConversation(file, second), sc, file, p1.getDisplayName(), p1, state); 
                    } else {
                        playConversation(loadConversation(file, third), sc, file, p1.getDisplayName(), p1, state);
                    }
            } else if (trigger.equals("viewInventory")) {
                System.out.println("current inventory : " + p1.getInv().getItems()); // format this
            }
            // flags --------------------------------
            else if (trigger.equals("setFlag")) {
                state.setFlag(first, true);
                playConversation(loadConversation(file, third), sc, file, p1.getDisplayName(), p1, state);
            } else if (trigger.equals("checkFlag")) {
                if (state.getFlag(first) == true) {
                    playConversation(loadConversation(file, second), sc, file, p1.getDisplayName(), p1, state);
                } else {
                    playConversation(loadConversation(file, third), sc, file, p1.getDisplayName(), p1, state);
                }
            }
            // etc ----------------------------------
            else if (trigger.equals("swapConvo")) {
                playConversation(loadConversation(file, first), sc, file, p1.getDisplayName(), p1, state);
            } else if (trigger.equals("ending")) {
                System.out.println("You got Ending : '" + first + "'.");
            }

            // speaker|dialogue|conversationID|hidden|trigger|triggerData
            // askChoice: label-nextID,label-nextID
            // askInput: placeholder-nextID
            // addItem: type-name-quantity-extra
            // useItem: itemName-successID-failID
            // setFlag: flagName-value-nextID
            // checkFlag: flagName-trueID-falseID
            // swapConvo: nextID
            // ending: endingName

            return null;
        }

    public static List<dialogueLine> loadFile(String textfile) {
        ArrayList<dialogueLine> lines = new ArrayList<>();

        

        try (BufferedReader br = new BufferedReader(new FileReader(textfile))) {
            String line;
           
            while (((line = br.readLine()) != null) && !line.isEmpty()) {
                if (line.indexOf("-") == 0) {continue;}
 
                String[] parts = line.split("\\|");
                ArrayList<String> tlist = new ArrayList<>();
                boolean hid;

                if (parts.length >= 5) {
                    String[] templist = parts[5].split("\\,");
                    for (String t : templist) {
                        tlist.add(t);
                    } 
                }
                
               
                if (parts[3].equals("true")) { hid = true; } else { hid = false ; }

                dialogueLine newLine = new dialogueLine(parts[0], parts[1], parts[2], hid);
               
                if (parts.length >= 5) { 
                    newLine.setTrigger(parts[4]);
                    newLine.setTriggerList(tlist);
                }

                lines.add(newLine);
            }
        } catch (IOException e) {
            System.out.println("error in loadFile : " + e.getMessage());
        }
        // System.out.println("lines (loadfile) : " + lines);
        return lines;
    }
}
