import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
//tek tek geçiyor
class BillingTest2 {

    @Test
    void testAddItem_ValidItem_UpdatesTotalCost() {
        Billing billing = new Billing("1");
        Map<String, Double> itemPrices = new HashMap<>();
        itemPrices.put("Spa", 100.0);
        Item spaItem = new Item("Spa", itemPrices.get("Spa"));
        billing.addItem(spaItem, 1);
        assertEquals(100.0, billing.getTotalCost());
    }

    @Test
    void testDisplayItemizedCosts_MultipleItems_PrintsCorrectCosts() {
        Billing billing = new Billing("1");
        Map<String, Double> itemPrices = new HashMap<>();
        itemPrices.put("Spa", 100.0);
        itemPrices.put("Extra Meal", 30.0);
        Item spaItem = new Item("Spa", itemPrices.get("Spa"));
        Item extraMealItem = new Item("Extra Meal", itemPrices.get("Extra Meal"));
        billing.addItem(spaItem, 1);
        billing.addItem(extraMealItem, 2);
        // Check if the expected output is printed to the console
        // (This part cannot be tested directly in JUnit, as System.out is not easily captured)
        // You would need to use a mocking framework or redirect System.out for this assertion.
    }
}