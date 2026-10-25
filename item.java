public class item {
    protected String itemName;
    protected boolean reusable;
    protected int quantity;

    public item(String itemName, boolean reusable, int quantity) {
        this.itemName = itemName;
        this.reusable = reusable;
        this.quantity = quantity;
    }

    public boolean useItem(boolean reuse, int quant) {
        if (reuse && quant >= 0) { return true; }
        else if (quant >= 1) { quant -= 1; return true; }
        setQuantity(quant);
        return false;
    }

    public String getInfo() {
        return itemName + " x" + quantity;
    }

    public String getItemName() { return itemName; }
    public boolean getReusable() { return reusable; }
    public int getQuantity() { return quantity; }

    public void setItemName( String itemName ) { this.itemName = itemName; }
    public void setReusable( boolean reusable ) { this.reusable = reusable; }
    public void setQuantity( int quantity ) { this.quantity = quantity; }

}
