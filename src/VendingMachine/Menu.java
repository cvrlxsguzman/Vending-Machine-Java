package VendingMachine;

import java.util.ArrayList;

public class Menu {
    private ArrayList<String> items = new ArrayList<>();
    StringBuilder menuItems = new StringBuilder();
    StringBuilder menu = new StringBuilder();

    public void setMenu() {
        setItems();

        menu.append("Menu").append("\n\n")
                .append(menuItems);
    }

    private void setItems() {
        items.add("Candy 10¢");
        items.add("Snack 50¢");
        items.add("Nuts 90¢");
        items.add("Coke 25¢");
        items.add("Pepsi 35¢");
        items.add("Soda 45¢");

        for (String item : items) {
            menuItems.append(item).append("\n");
        }
    }

    public StringBuilder getMenu() {
        return menu;
    }
}
