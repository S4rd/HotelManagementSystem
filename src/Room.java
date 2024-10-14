import java.util.Map;
import java.util.Scanner;

public class Room {
    private int roomNumber;
    private String type;
    private String status;
    private Billing billing;

    public Room(int roomNumber, String type, String status) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.status = status;
        this.billing = new Billing(String.valueOf(roomNumber)); // RoomNumber ile yeni bir Billing nesnesi oluştur.
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Billing getBilling() {
        return billing;
    }

    public void orderRoomService(Map<String, Double> itemPrices, Map<String, Integer> inventory) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Order Room Service:");
            System.out.println("1. Add Spa");
            System.out.println("2. Add Extra Meal");
            System.out.println("3. Add Minibar Item");
            System.out.println("0. Back to Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Spa hizmetini fiyat listesinden alarak Billing nesnesine ekliyoruz.
                    this.billing.addItem(new Item("Spa", itemPrices.get("Spa")), 1);
                    break;
                case 2:
                    System.out.print("Enter number of extra meals: ");
                    int meals = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    this.billing.addItem(new Item("Extra Meal", itemPrices.get("Extra Meal")), meals);
                    break;
                case 3:
                    System.out.print("Enter minibar item name: ");
                    String itemName = scanner.nextLine();
                    if (inventory.getOrDefault(itemName, 0) > 0) {
                        this.billing.addItem(new Item(itemName, itemPrices.getOrDefault(itemName, 0.0)), 1);
                        inventory.put(itemName, inventory.get(itemName) - 1); // Decrease the inventory
                    } else {
                        System.out.println(itemName + " is not available.");
                    }
                    break;
                case 0:
                    // Exit the loop if choice is 0
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        // İşlemler bittikten sonra faturayı göster
        this.billing.displayItemizedCosts();
//
//        // Faturadaki tüm öğelerin listesini ve toplam tutarı göster
//        billing.displayItemizedCosts();
//        return billing; // Metod sonunda fatura nesnesini döndür
    }

    private void displayMinibarItems(Map<String, Integer> inventory) {
        System.out.println("1. Cola - Quantity: " + inventory.getOrDefault("Cola", 0));
        System.out.println("2. Alcohol - Quantity: " + inventory.getOrDefault("Alcohol", 0));
        System.out.println("3. Red Bull - Quantity: " + inventory.getOrDefault("Red Bull", 0));
        System.out.println("4. Chocolate Bar - Quantity: " + inventory.getOrDefault("Chocolate Bar", 0));
        System.out.println("5. Chips - Quantity: " + inventory.getOrDefault("Chips", 0));
        System.out.print("Enter the item number to add to your bill (0 to cancel): ");
    }

    private void processMinibarChoice(int choice, Billing billing, Map<String, Integer> inventory) {
        String[] items = {"Cola", "Alcohol", "Red Bull", "Chocolate Bar", "Chips"};
        double[] prices = {5.0, 5.0, 5.0, 5.0, 5.0};  // Assuming all items have the same price for simplicity

        if (choice > 0 && choice <= items.length) {
            String selectedItem = items[choice - 1];
            double price = prices[choice - 1];

            if (inventory.getOrDefault(selectedItem, 0) > 0) {
                billing.addItem(new Item(selectedItem, price), 1);
                inventory.put(selectedItem, inventory.get(selectedItem) - 1);
            } else {
                System.out.println(selectedItem + " is out of stock!");
            }
        }
    }
}
