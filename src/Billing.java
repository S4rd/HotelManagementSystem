import java.util.ArrayList;
import java.util.List;

public class Billing {
    private String roomId;
    private static List<Item> items;
    private static double totalCost;

    static {
        items = new ArrayList<>();
    }

    public Billing(String roomId) {
        this.roomId = roomId;

    }

    public void addItem(Item item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            items.add(item);
            totalCost += item.getPrice(); // Update total cost
        }
    }

    public void displayItemizedCosts() {
        System.out.println("HotelManagment.Billing Details for HotelManagment.Room " + roomId + ":");
        for (Item item : items) {
            System.out.println(item.getName() + ": $" + item.getPrice());
        }
        System.out.println("Total: $" + totalCost);
    }

    public double getTotalCost() {
        return totalCost;
    }



}


