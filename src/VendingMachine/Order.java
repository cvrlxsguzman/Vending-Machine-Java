package VendingMachine;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order extends Menu implements MenuPrices, Coins{
    Menu menu;
    ArrayList<String> order = new ArrayList<>();
    ArrayList<String> coins = new ArrayList<>();
    StringBuilder orderItems;
    StringBuilder message;
    String itemName;
    double totalPrice;
    double change;
    boolean checkedOut = false;
    DecimalFormat df = new DecimalFormat("0.00");
    double coinAmount;
    int userInput;
    String userInputString;

    public void  addToOrder() {
        if (!checkedOut) {
            menu = new Menu();
            menu.setMenu();
            while (true) {
                while (true) {
                    try {
                        userInputString = JOptionPane.showInputDialog(null,  menu.getMenu() +
                                "\nWhat item would you like").toLowerCase();

                        if (userInputString.equals("candy") || userInputString.equals("snack") || userInputString.equals("nuts") || userInputString.equals("coke") || userInputString.equals("pepsi") || userInputString.equals("soda")) {
                            break;
                        }  else {
                            JOptionPane.showMessageDialog(null, "Must enter Candy, Snack, Nuts, Coke, Pepsi or Soda");
                        }
                    }
                    catch (NumberFormatException x) {
                        JOptionPane.showMessageDialog(null, "Must enter Candy, Snack, Nuts, Coke, Pepsi or Soda");
                    }
                }

                if (order.contains(userInputString.substring(0, 1).toUpperCase() + userInputString.substring(1))) {
                    JOptionPane.showMessageDialog(null, userInputString.substring(0, 1).toUpperCase() + userInputString.substring(1) + " is already in cart!");
                } else {
                    switch (userInputString) {
                        case "candy" -> {
                            itemName = "Candy";
                            totalPrice += Candy;
                            order.add(itemName);
                        }
                        case "snack" -> {
                            itemName = "Snack";
                            totalPrice += Snack;
                            order.add(itemName);
                        }
                        case "nuts" -> {
                            itemName = "Nuts";
                            totalPrice += Nuts;
                            order.add(itemName);
                        }
                        case "coke" -> {
                            itemName = "Coke";
                            totalPrice += Coke;
                            order.add(itemName);
                        }
                        case "pepsi" -> {
                            itemName = "Pepsi";
                            totalPrice += Pepsi;
                            order.add(itemName);
                        }
                        default -> {
                            itemName = "Soda";
                            totalPrice += Soda;
                            order.add(itemName);
                        }
                    }
                    JOptionPane.showMessageDialog(null, itemName + " added to cart!");
                }

                userInput = JOptionPane.showConfirmDialog(null, "Do you want to add another item right away? (You can add again from menu)");

                if (userInput == 1) {
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Already Checked Out!");
        }
    }

    public void removeFromCart() {
        if (!checkedOut) {
            if (order.size() < 1) {
                JOptionPane.showMessageDialog(null, "Cart is Empty!");
            } else {
                while (true) {
                    try {
                        userInputString = JOptionPane.showInputDialog(null,  getOrderItems() + "\n" +
                                "Total: $" + df.format(getTotalPrice()) +
                                "\nWhat item would you like to remove:").toLowerCase();

                        if (order.contains(userInputString.substring(0, 1).toUpperCase() + userInputString.substring(1))) {
                            if (userInputString.equals("candy")) {
                                totalPrice -= Candy;
                            }
                            if (userInputString.equals("snack")) {
                                totalPrice -= Snack;
                            }
                            if (userInputString.equals("nuts")) {
                                totalPrice -= Nuts;
                            }
                            if (userInputString.equals("coke")) {
                                totalPrice -= Coke;
                            }
                            if (userInputString.equals("pepsi")) {
                                totalPrice -= Pepsi;
                            }
                            if (userInputString.equals("soda")) {
                                totalPrice -= Soda;
                            }
                            order.remove(userInputString.substring(0, 1).toUpperCase() + userInputString.substring(1));
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Oops, Try Again");
                        }
                    }
                    catch (NumberFormatException x) {
                        JOptionPane.showMessageDialog(null, "Oops, Try Again");
                    }
                }

                JOptionPane.showMessageDialog(null, userInputString + " was removed from cart!" + "\n" +
                        "New Total: $ " + df.format(getTotalPrice()));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Already Checked Out!");
        }
    }

    public StringBuilder getOrderItems() {
        if (order.size() < 1) {
            message = new StringBuilder();
            message.append("Cart is empty!");
            return message;
        } else {
            orderItems = new StringBuilder();

            for (String order : order) {
                orderItems.append(order).append("\n");
            }

            return  orderItems;
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void finishOrder() {
        coinAmount = 0;
        if (order.size() < 1) {
            JOptionPane.showMessageDialog(null, "Cart is Empty!");
        } else {
            if (!checkedOut) {
                JOptionPane.showMessageDialog(null, "Cart Total: $" + df.format(getTotalPrice()));

                while (true) {
                    while (true) {
                        try {
                            userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Total: $" + df.format(totalPrice) + "\nInserted: $" + df.format(coinAmount) + "\n\nHow many pennies would you like to insert:"));
                            if (userInput == 0) {
                                coins.add("0 Pennies");
                            } else {
                                coins.add(userInput + " Pennies");
                                coinAmount += (userInput * .01);
                            }
                            break;
                        } catch (NumberFormatException x) {
                            JOptionPane.showMessageDialog(null, "Must enter Number");
                        }
                    }

                    while (true) {
                        try {
                            userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Total: $" + df.format(totalPrice) + "\nInserted: $" + df.format(coinAmount) + "\n\nHow many nickels would you like to insert:"));
                            if (userInput == 0) {
                                coins.add("0 Nickels");
                            } else {
                                coins.add(userInput + " Nickels");
                                coinAmount += (userInput * .05);
                            }
                            break;
                        } catch (NumberFormatException x) {
                            JOptionPane.showMessageDialog(null, "Must enter Number");
                        }
                    }

                    while (true) {
                        try {
                            userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Total: $" + df.format(totalPrice) + "\nInserted: $" + df.format(coinAmount) + "\n\nHow many dimes would you like to insert:"));
                            if (userInput == 0) {
                                coins.add("0 Dimes");
                            } else {
                                coins.add(userInput + " Dimes");
                                coinAmount += (userInput * .1);
                            }
                            break;
                        } catch (NumberFormatException x) {
                            JOptionPane.showMessageDialog(null, "Must enter Number");
                        }
                    }

                    while (true) {
                        try {
                            userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Total: $" + df.format(totalPrice) + "\nInserted: $" + df.format(coinAmount) + "\n\nHow many quarters would you like to insert:"));
                            if (userInput == 0) {
                                coins.add("0 Quarters");
                            } else {
                                coins.add(userInput + " Quarters");
                                coinAmount += (userInput * .25);
                            }
                            break;
                        } catch (NumberFormatException x) {
                            JOptionPane.showMessageDialog(null, "Must enter Number");
                        }
                    }

                    while (true) {
                        try {
                            userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Total: $" + df.format(totalPrice) + "\nInserted: $" + df.format(coinAmount) + "\n\nHow many half-Dollars would you like to insert:"));
                            if (userInput == 0) {
                                coins.add("0 Half-Dollars");
                            } else {
                                coins.add(userInput + " Half-Dollars");
                                coinAmount += (userInput * .5);
                            }
                            break;
                        } catch (NumberFormatException x) {
                            JOptionPane.showMessageDialog(null, "Must enter Number");
                        }
                    }

                    while (true) {
                        try {
                            userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "Total: $" + df.format(totalPrice) + "\nInserted: $" + df.format(coinAmount) + "\n\nHow many Dollars would you like to insert:"));
                            if (userInput == 0) {
                                coins.add("0 Dollars");
                            } else {
                                coins.add(userInput + " Dollars");
                                coinAmount += (userInput);
                            }
                            break;
                        } catch (NumberFormatException x) {
                            JOptionPane.showMessageDialog(null, "Must enter Number");
                        }
                    }


                    if (totalPrice - coinAmount > 0) {
                        JOptionPane.showMessageDialog(null, "Total Amount: $" + df.format(totalPrice) + "\n" +
                                "Amount Inserted: $" + df.format(coinAmount) + "\n\n" +
                                "Amount Needed: $" + df.format(totalPrice - coinAmount));
                    } else {
                        JOptionPane.showMessageDialog(null, "Total Amount: $" + df.format(totalPrice) + "\n" +
                                "Amount Inserted: $" + df.format(coinAmount) + "\n\n" +
                                "Change: $" + df.format(coinAmount - totalPrice));

                        userInput = JOptionPane.showConfirmDialog(null, "Proceed and checkout?");

                        if (userInput == 1) {
                            JOptionPane.showMessageDialog(null, "Order Cancelled!\n" +
                                    "Refund: $" + df.format(coinAmount));
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Vending,,,");

                            JOptionPane.showMessageDialog(null, "Total: $" + df.format(totalPrice) + "\n\n" + getOrderItems());
                            checkedOut = true;
                            break;
                        }
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Already Checked Out!");
            }
        }
    }

}
