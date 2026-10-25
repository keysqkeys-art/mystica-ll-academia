import java.util.ArrayList;

public class player extends character {
    private inventory inv;

    public player(String name) {
        super(name, false);
        this.inv = new inventory(new ArrayList<item>());
    }

    public inventory getInv() { return inv; }
}
