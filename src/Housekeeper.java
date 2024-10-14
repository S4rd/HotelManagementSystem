import java.util.HashMap;
import java.util.Map;

public class Housekeeper {
    private String name;
    private Map<String, Boolean> availability; // Map to store availability for each time span

    public Housekeeper(String name, boolean available) {
        this.name = name;
        this.availability = new HashMap<>(); // Initialize the availability map
    }

    public String getName() {
        return name;
    }

    // Check availability for a specific time span
    public boolean isAvailableForTimeSpan(String timeSpan) {
        return availability.getOrDefault(timeSpan, true); // Default to true if not set
    }

    // Set availability for a specific time span
    public void setAvailableForTimeSpan(String timeSpan, boolean available) {
        availability.put(timeSpan, available);
    }
}

