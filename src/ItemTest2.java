import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest2 {

    @Test
    void testGetName_ValidItem_ReturnsCorrectName() {
        Item spaItem = new Item("Spa", 100.0);
        assertEquals("Spa", spaItem.getName());
    }

    @Test
    void testGetPrice_ValidItem_ReturnsCorrectPrice() {
        Item spaItem = new Item("Spa", 100.0);
        assertEquals(100.0, spaItem.getPrice());
    }
}