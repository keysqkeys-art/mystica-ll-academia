import java.util.*;

public class inventory {
    private ArrayList<item> items;

    public inventory(ArrayList<item> items) {
        this.items = new ArrayList<>(); // previously items
    }

    public ArrayList<item> getItems() {return items;}
    public void setItems(ArrayList<item> items) {this.items = items;}

    public void addItem(item addeditem) { 
        item existing = findItem(addeditem.getItemName());

        if(existing == null) {
            getItems().add(addeditem);
        } else {
            existing.setQuantity(existing.getQuantity()+1);
        }
         
    }

    public boolean hasItem(String findItemName) {
        for (item i : getItems()) {
            if (i.getItemName().equals(findItemName)) {return true;}
        }
        return false;
    }

    public item findItem(String findItemName) {
        for (item i : getItems()) {
            if (i.getItemName().equals(findItemName)) {return i;}
        }
        return null;
    }

    public boolean useItem(String findItemName) {
        for (item i : getItems()) {
            if (i.getItemName().equals(findItemName)) {
                i.useItem(i.getReusable(), i.getQuantity()); return true;
            }
        }
        return false;
    }

    public void showInventory() {
        for (item i : getItems()) {
            System.out.println(i.getInfo());
        }
    }

    public boolean removeItem(String itemName) {
        item removing = findItem(itemName);

        if (removing.getQuantity() > 1) { removing.setQuantity(removing.getQuantity()-1); return true;}
        else if (removing.getQuantity() == 1) {items.remove(items.indexOf(removing)); return true;}
        return false;
    }
}
