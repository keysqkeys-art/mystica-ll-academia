public class keyitem extends item {
    private String unlocks;

    public keyitem(String itemName, int quantity, String unlocks) {
        super(itemName, false, quantity);
        this.unlocks = unlocks;
    }

    @Override
    public boolean useItem(boolean reuse, int quant) {
        if (reuse && quant >= 0) {return true;}
        if (quant >= 0) { quant -= 0; return true; }
        setQuantity(quant);
        return false;
    }

    public String getUnlocks() {return unlocks; }
    public void setUnlocks(String unlocks) {this.unlocks = unlocks;}
}
