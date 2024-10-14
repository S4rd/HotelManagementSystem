import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Item {
    static Scanner scanner = new Scanner(System.in);
    private String name;
    private double price;
    static Map<String, Integer> inventory = new HashMap<>();
    // Add other parameters and methods as needed


    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    static void addItemToInventory() {
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter quantity to add: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (inventory.containsKey(itemName)) {
            inventory.put(itemName, inventory.get(itemName) + quantity);
        } else {
            inventory.put(itemName, quantity);
        }
        System.out.println(quantity + " " + itemName + "(s) added to inventory.");
    }

    static void removeItemFromInventory() {
        System.out.print("Enter item name to remove: ");
        String itemName = scanner.nextLine();

        if (inventory.containsKey(itemName)) {
            System.out.print("Enter quantity to remove (0 to remove all): ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            int currentQuantity = inventory.get(itemName);
            if (quantity >= 0 && quantity <= currentQuantity) {
                if (quantity == 0 || quantity == currentQuantity) {
                    inventory.remove(itemName);
                    System.out.println(itemName + " removed from inventory.");
                } else {
                    inventory.remove(itemName);
                    inventory.put(itemName, currentQuantity - quantity);
                    System.out.println(quantity + " " + itemName + "(s) removed from inventory.");
                }
            } else {
                System.out.println("Invalid quantity.");
            }
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    static void checkInventoryQuantities() {
        System.out.println("Inventory Quantities:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}