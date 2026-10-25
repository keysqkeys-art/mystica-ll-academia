public class toolitem extends item {
    private String action;

    public toolitem(String itemName, boolean reusable, int quantity, String action) {
        super(itemName, reusable, quantity);
        this.action = action;
    }

    @Override
    public boolean useItem(boolean reuse, int quant) {
        if (reuse && quant >= 0) {return true;}
        if (quant >= 0) { quant -= 0; return true; }
        setQuantity(quant);
        return false;
    }










    
    public String getAction() {return action;}
    public void setAction(String action) {this.action = action;}
}
