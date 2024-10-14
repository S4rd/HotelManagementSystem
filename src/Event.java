import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Event {
    static Scanner scanner = new Scanner(System.in);
    static List<Event> events = new ArrayList<>();
    private String eventName;
    private String date;
    private String time;
    private String location;
    private String notes;

    public Event(String eventName, String date, String time, String location, String notes) {
        if (!isValidDate(date)) {
            throw new IllegalArgumentException("Invalid date format");
        }
        if (!isValidTime(time)) {
            throw new IllegalArgumentException("Invalid time format");
        }
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.location = location;
        this.notes = notes;
    }


    private boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }


    private boolean isValidTime(String time) {
        return time.matches("\\d{2}:\\d{2}");
    }
    public String getEventName() {
        return eventName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getNotes() {
        return notes;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            this.date = date;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + date, e);
        }
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    static void manageEvents() {
        System.out.println("Manage Events:");
        System.out.println("1. Add Event");
        System.out.println("2. View All Events");
        System.out.println("3. Delete Event");
        System.out.println("4. Update Event");
        System.out.println("0. Back to Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addEvent();
                break;
            case 2:
                viewAllEvents();
                break;
            case 3:
                deleteEvent();
                break;
            case 4:
                updateEvent();
            case 0:
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    static void addEvent() {
        System.out.println("Add Event:");
        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter time: ");
        String time = scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        System.out.print("Enter notes: ");
        String description = scanner.nextLine();

        Event event = new Event(eventName, date, time, location, description);
        events.add(event);
        System.out.println("Event added successfully.");
    }

    static void deleteEvent() {
        System.out.println("Delete Event:");
        System.out.print("Enter the event name to delete: ");
        String eventName = scanner.nextLine();

        boolean found = false;
        for (Event event : events) {
            if (event.getEventName().equalsIgnoreCase(eventName)) {
                events.remove(event);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Event deleted successfully.");
        } else {
            System.out.println("Event not found.");
        }
    }
    static void updateEvent() {
        System.out.println("Update Event:");
        if (events.isEmpty()) {
            System.out.println("No events found.");
            return; // Exit the method if there are no events to update
        }

        // Display all events for the user to select
        System.out.println("Available Events:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i).getEventName());
        }

        System.out.print("Enter the number of the event to update: ");
        int eventIndex = scanner.nextInt();
        scanner.nextLine();

        if (eventIndex < 1 || eventIndex > events.size()) {
            System.out.println("Invalid event number.");
            return;
        }

        Event selectedEvent = events.get(eventIndex - 1);

        System.out.println("Enter new details for the event:");
        System.out.print("Event name: ");
        String newName = scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        String newDate = scanner.nextLine();
        System.out.print("Time: ");
        String newTime = scanner.nextLine();
        System.out.print("Location: ");
        String newLocation = scanner.nextLine();
        System.out.print("Notes: ");
        String newDescription = scanner.nextLine();

        selectedEvent.setEventName(newName);
        selectedEvent.setDate(newDate);
        selectedEvent.setTime(newTime);
        selectedEvent.setLocation(newLocation);
        selectedEvent.setNotes(newDescription);

        System.out.println("Event updated successfully.");
    }


    static void viewAllEvents() {
        if (events.isEmpty()) {
            System.out.println("No events found.");
        } else {
            System.out.println("All Events:");
            for (Event event : events) {
                System.out.println("Event Name: " + event.getEventName());
                System.out.println("Date: " + event.getDate());
                System.out.println("Time: " + event.getTime());
                System.out.println("Location: " + event.getLocation());
                System.out.println("Notes: " + event.getNotes());
                System.out.println();
            }
        }
    }
}


