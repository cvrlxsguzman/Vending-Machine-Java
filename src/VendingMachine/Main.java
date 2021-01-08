package VendingMachine;

import javax.swing.*;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setMenu();
        DecimalFormat df = new DecimalFormat("0.00");
        boolean quit = false;
        int userInput;
        String userInputString;

        JOptionPane.showMessageDialog(null, "Welcome to the vending machine");

        do {
            boolean checkout = false;
            Order order  = new Order();
            while (true) {
                try {
                    userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Vending Machine\n\n" +
                            "What do you and to do:\n" +
                            "1. Get Items\n" +
                            "2. Quit"));

                    if (userInput == 1 || userInput == 2) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Sorry! Please enter 1-2");
                    }
                }
                catch (NumberFormatException x) {
                    JOptionPane.showMessageDialog(null, "Sorry! Please enter 1-2");
                }
            }

            switch (userInput) {
                case 1-> {
                    while (!checkout) {
                        while (true) {
                            try {
                                userInput = Integer.parseInt(JOptionPane.showInputDialog(null,  "What item would you like to do:\n" +
                                        "1. Add items to Cart\n" +
                                        "2. Remove items from Cart\n" +
                                        "3. Check out Cart\n" +
                                        "4. Checkout\n" +
                                        "5. Quit"));

                                if (userInput == 1 || userInput == 2  || userInput == 3 || userInput == 4 || userInput == 5) {
                                    break;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Must enter 1-5");
                                }
                            }
                            catch (NumberFormatException x) {
                                JOptionPane.showMessageDialog(null, "Must enter 1-5");
                            }
                        }

                        switch (userInput) {
                            case 1-> order.addToOrder();
                            case 2->order.removeFromCart();
                            case 3-> JOptionPane.showMessageDialog(null, order.getOrderItems() + "\n" + "$" + df.format(order.getTotalPrice()));
                            case 4-> order.finishOrder();
                            case 5-> checkout = true;
                        }
                    }

                }
                case 2-> quit = true;
            }
        } while (!quit);

        JOptionPane.showMessageDialog(null, "See you later!");
    }
}
