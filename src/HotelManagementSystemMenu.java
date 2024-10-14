import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class HotelManagementSystemMenu {
    static Scanner scanner = new Scanner(System.in);
    static List<Feedback> feedbacks = new ArrayList<>();
    static List<Room> rooms = new ArrayList<>();
    static List<Housekeeper> housekeepers = new ArrayList<>();
    static List<RoomCleaning> cleanings = new ArrayList<>();
    public static Map<String, Integer> inventory = new HashMap<>();
    static Map<String, String> reservations = new HashMap<>();
    static Map<String, Billing> roomBills = new HashMap<>();
    static List<Event> events = new ArrayList<>();
    public static boolean loggedIn = false; // Track login status
    public static String loggedInRole = "";
    private static Map<Integer, BigDecimal> additionalServicesMap = new HashMap<>(); // Map to store additional services




        public static void main(String[] args) {
        inventory.put("Cola", 50);
        inventory.put("Red Bull", 50);
        inventory.put("Alcohol", 50);
        inventory.put("Chocolate Bar", 50);
        inventory.put("Chips", 50);
        int choice  = 9;

        // Start threads to simulate concurrent users
            int numberOfUsers = 50;
            Thread[] userThreads = new Thread[numberOfUsers];
            for (int i = 0; i < numberOfUsers; i++) {
                userThreads[i] = new Thread(new UserThread("User" + (i + 1)));
                userThreads[i].start();
            }

            // Wait for all user threads to finish
            for (Thread thread : userThreads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        do {
            // Get current time in UTC+3 timezone
            Clock clock = Clock.systemUTC();
            ZoneId zoneId = ZoneId.of("UTC+3");
            ZonedDateTime utcPlus3Time = ZonedDateTime.now(clock.withZone(zoneId));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = utcPlus3Time.format(formatter);
            System.out.println("UTC+3 time : " + formattedTime);

            if (!loggedIn) {
                login();
                if (!loggedIn) continue;
            }

            System.out.println("Hotel Management System Menu");

            System.out.println("1. Manage Reservations");
            System.out.println("2. Order Room Service");
            System.out.println("3. Track Housekeeping Services");
            System.out.println("4. Monitor Billing Processes");
            System.out.println("5. Enter Customer Feedback");
            System.out.println("6. Edit Daily Schedule");
            System.out.println("7. Manage Hotel Inventory");
            System.out.println("8. Enter Customer Data");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    if (checkPermission("RECEPTIONIST", "MANAGER", "administrator")) manageReservations();
                    break;
                case 2:
                    if (checkPermission("RECEPTIONIST", "MANAGER", "administrator")) orderRoomService();
                    break;
                case 3:
                    if (checkPermission("MANAGER", "administrator")) trackHousekeepingServices();
                    break;
                case 4:
                    if (checkPermission("MANAGER", "administrator")) monitorBillingProcesses();
                    break;
                case 5:
                    if (checkPermission("RECEPTIONIST", "MANAGER", "administrator")) enterCustomerFeedback();
                    break;
                case 6:
                    if (checkPermission("MANAGER", "administrator")) editDailySchedule();
                    break;
                case 7:
                    if (checkPermission("MANAGER", "administrator")) manageHotelInventory();
                    break;
                case 8:
                    if (checkPermission("RECEPTIONIST", "administrator")) enterCustomerData();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static final List<Map<String, String>> users = Arrays.asList(
            createUser("reception", "password", "RECEPTIONIST"),
            createUser("manager", "password", "MANAGER"),
            createUser("admin", "password", "ADMINISTRATOR")
    );

    private static Map<String, String> createUser(String username, String password, String role) {
        Map<String, String> user = new HashMap<>();
        user.put("username", username);
        user.put("password", password);
        user.put("role", role);
        return user;
    }

    public static void login() {
        System.out.print("Enter username: ");
        String enteredUsername = scanner.next();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.next();

        if (authenticate(enteredUsername, enteredPassword)) {
            System.out.println("Login successful.");
            loggedIn = true;
            loggedInRole = getUserRole(enteredUsername);
            System.out.println("Logged in role: " + loggedInRole); // Debug output
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }


    public static boolean checkPermission(String... allowedRoles) {
        for (String role : allowedRoles) {
            if (loggedInRole.equals(role)) {
                return true;
            }
        }
        System.out.println("Access denied. You do not have permission to perform this action.");
        return false;
    }

    public static boolean authenticate(String username, String password) {
        String sql = "SELECT password FROM Users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return password.equals(storedPassword);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static String getUserRole(String username) {
        String sql = "SELECT role FROM Users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }


    private static void manageReservations() {
        System.out.println("Manage Reservations:");
        System.out.println("1. View All Reservations");
        System.out.println("2. Cancel Reservation");
        System.out.println("0. Back to Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                viewAllReservations();
                break;
            case 2:
                cancelReservation();
                break;
            case 0:
                // Return to main menu
                break;
            default:
                System.out.println("Invalid choice.");
        }
        backToMenu();
    }

     public static void viewAllReservations() {
        String sql = "SELECT room_number, start_date FROM Reservations";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No reservations found.");
            } else {
                System.out.println("All Reservations:");
                while (rs.next()) {
                    String roomNumber = rs.getString("room_number");
                    String reservationDate = rs.getString("start_date");
                    System.out.println("Room " + roomNumber + " - Reserved on " + reservationDate);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void cancelReservation() {
        System.out.print("Enter room number to cancel reservation: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();
        // Veritabanından rezervasyonu kaldırmak için SQL DELETE sorgusu oluşturun
        String deleteSql = "DELETE FROM Reservations WHERE room_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {

            pstmt.setInt(1, roomNumber);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Rezervasyon veritabanından başarıyla kaldırıldı, ilgili odanın durumunu "available" olarak güncelleyin.
                updateRoomStatus(roomNumber, "available");
                System.out.println("Reservation for room " + roomNumber + " canceled successfully.");
            } else {
                System.out.println("Reservation not found for room " + roomNumber + ".");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateRoomStatus(int roomNumber, String newStatus) {
        String updateRoomStatusSQL = "UPDATE Rooms SET status = ? WHERE room_number = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateRoomStatusSQL)) {

            pstmt.setString(1, newStatus);
            pstmt.setInt(2, roomNumber);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error while updating room status: " + e.getMessage());
            e.printStackTrace();
        }
    }



    private static void loadRoomsFromDatabase() {
        rooms.clear(); // Öncelikle listeyi temizleyin.
        String sql = "SELECT room_number, type, status FROM Rooms";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int roomNumber = rs.getInt("room_number");
                String type = rs.getString("type");
                String status = rs.getString("status");
                rooms.add(new Room(roomNumber, type, status));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    static Map<String, Double> itemPrices = initializeItemPrices();

    private static Map<String, Double> initializeItemPrices() {
        Map<String, Double> prices = new HashMap<>();
        prices.put("Spa", 100.0);
        prices.put("Extra Meal", 30.0);
        prices.put("Cola", 3.0);
        // Diğer öğeler ve fiyatları...
        return prices;
    }
    private static void orderRoomService() {
        System.out.println("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline.

        Room room = findRoomByNumber(roomNumber);
        if (room != null && "occupied".equals(room.getStatus())) {
            // Fiyat listesi ve envanteri sağlayarak orderRoomService metodunu çağır
            room.orderRoomService(itemPrices, inventory);
        } else {
            System.out.println("Invalid room number or room not reserved.");
        }
        backToMenu();
    }



    public static Room findRoomByNumber(int roomNumber) {
        String sql = "SELECT * FROM Rooms WHERE room_number = ? AND status = 'occupied'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String type = rs.getString("type");
                String status = rs.getString("status");
                // Yeni bir Room nesnesi oluşturarak döndürün.
                return new Room(roomNumber, type, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }


    public static boolean isRoomReserved(int roomNumber) {
        String sql = "SELECT room_number FROM Reservations WHERE room_number = ? AND status = 'active'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, roomNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Eğer oda aktif olarak rezerve edilmişse true döner.
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private static void trackHousekeepingServices() {
        List<String> timeSpans = Arrays.asList(
                "10:00 AM - 11:00 AM",
                "11:00 AM - 12:00 PM",
                "12:00 PM - 1:00 PM",
                "1:00 PM - 2:00 PM",
                "2:00 PM - 3:00 PM",
                "3:00 PM - 4:00 PM",
                "4:00 PM - 5:00 PM",
                "5:00 PM - 6:00 PM"
                // ... add other time spans as needed ...
        );
        if (housekeepers.isEmpty()) {
            Housekeeper asd = new Housekeeper("kadir", true);
            housekeepers.add(asd);
        }

        System.out.println("Track Housekeeping Services:");
        System.out.println("Housekeepers:");

        for (Housekeeper housekeeper : housekeepers) {
            System.out.println(housekeeper.getName() + " - Available:");
            // Print availability for each time slot
            for (String timeSpan : timeSpans) {
                boolean isAvailable = housekeeper.isAvailableForTimeSpan(timeSpan);
                System.out.println(timeSpan + " - " + (isAvailable ? "Yes" : "No"));
            }
        }

        System.out.print("Enter room number to assign housekeeper (0 to cancel): ");
        String roomId = scanner.next();

        if (!roomId.equals("0")) {
            if (isRoomReserved(Integer.parseInt(roomId))) {
                System.out.println("Select Housekeeper:");
                for (int i = 0; i < housekeepers.size(); i++) {
                    System.out.println((i + 1) + ". " + housekeepers.get(i).getName());
                }

                int housekeeperChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (housekeeperChoice >= 1 && housekeeperChoice <= housekeepers.size()) {
                    Housekeeper selectedHousekeeper = housekeepers.get(housekeeperChoice - 1);

                    System.out.println("Select Time Span:");
                    for (int i = 0; i < timeSpans.size(); i++) {
                        System.out.println((i + 1) + ". " + timeSpans.get(i));
                    }

                    int timeSpanChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (timeSpanChoice >= 1 && timeSpanChoice <= timeSpans.size()) {
                        String selectedTimeSpan = timeSpans.get(timeSpanChoice - 1);

                        if (selectedHousekeeper.isAvailableForTimeSpan(selectedTimeSpan)) {
                            selectedHousekeeper.setAvailableForTimeSpan(selectedTimeSpan, false);
                            cleanings.add(new RoomCleaning(selectedHousekeeper, roomId, selectedTimeSpan));
                            System.out.println("Room " + roomId + " assigned to housekeeper " + selectedHousekeeper.getName() + " for cleaning during " + selectedTimeSpan);
                            for (int i = 0; i < cleanings.size(); i++) {
                                System.out.println(cleanings.get(i));
                            }
                        } else {
                            System.out.println(selectedHousekeeper.getName() + " is not available for " + selectedTimeSpan + ". Please choose another time slot.");
                        }
                    } else {
                        System.out.println("Invalid time span choice.");
                    }
                } else {
                    System.out.println("Invalid housekeeper choice.");
                }
            } else {
                System.out.println("Invalid room number or room not reserved.");
            }
        }

        backToMenu();
    }


//    private static void updateBilling(int roomNumber, Billing billing) {
//        // Örnek olarak toplam tutar ve durumu güncelleyeceğiz.
//        String updateSql = "UPDATE Billing SET total_amount = ?, status = ? WHERE reservation_id = (SELECT id FROM Reservations WHERE room_number = ? AND status = 'active')";
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
//
//            // Billing nesnesinden toplam tutarı ve durumu alın.
//            pstmt.setBigDecimal(1, billing.getTotalAmount());
//            pstmt.setString(2, billing.getStatus());
//            pstmt.setInt(3, roomNumber);
//
//            // Sorguyu çalıştır
//            int affectedRows = pstmt.executeUpdate();
//            if (affectedRows > 0) {
//                System.out.println("Billing updated successfully for room " + roomNumber);
//            } else {
//                System.out.println("No active billing found for room " + roomNumber);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Database error: " + e.getMessage());
//        }
//    }


    private static void monitorBillingProcesses() {
        System.out.println("Monitor Billing Processes:");
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        Integer reservationId = findReservationIdByRoomNumber(roomNumber);
        if (reservationId != null) {
            String reservationSql = "SELECT r.price, re.start_date, re.end_date FROM Rooms r JOIN Reservations re ON r.room_number = re.room_number WHERE re.room_number = ? AND re.status = 'active'";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(reservationSql)) {

                pstmt.setInt(1, roomNumber);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    BigDecimal roomPrice = rs.getBigDecimal("price");
                    Date startDate = rs.getDate("start_date");
                    Date endDate = rs.getDate("end_date");

                    if (endDate == null) {
                        endDate = new Date(System.currentTimeMillis());
                    }

                    long durationInMillis = endDate.getTime() - startDate.getTime();
                    long durationInDays = (durationInMillis / (1000 * 60 * 60 * 24)) + 1;
                    if (durationInDays < 1) {
                        durationInDays = 1; // Minimum 1 day
                    }

                    BigDecimal totalRoomCost = roomPrice.multiply(BigDecimal.valueOf(durationInDays));

                    BigDecimal additionalServicesAmount = additionalServicesMap.getOrDefault(roomNumber, BigDecimal.ZERO);
                    BigDecimal totalAmount = totalRoomCost.add(additionalServicesAmount);

                    System.out.println("Total Amount: $" + totalAmount);
                } else {
                    System.out.println("No active reservation found for room number " + roomNumber);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Database error: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid room number or no active reservation.");
        }
        backToMenu();
    }


    private static Map<String, Double> calculateAverageRatings() {
        Map<String, Double> sumRatings = new HashMap<>();
        Map<String, Double> averageRatings = new HashMap<>();

        feedbacks.forEach(feedback -> {
            sumRatings.merge("Hygiene", (double) feedback.getHygiene(), Double::sum);
            sumRatings.merge("Animation", (double) feedback.getAnimation(), Double::sum);
            sumRatings.merge("Room Comfort", (double) feedback.getRoomComfort(), Double::sum);
            sumRatings.merge("Staff Friendliness", (double) feedback.getStaffFriendliness(), Double::sum);
            sumRatings.merge("Food Quality", (double) feedback.getFoodQuality(), Double::sum);
            sumRatings.merge("Amenities", (double) feedback.getAmenities(), Double::sum);
            sumRatings.merge("Cleanliness", (double) feedback.getCleanliness(), Double::sum);
            sumRatings.merge("WiFi Service", (double) feedback.getWifiService(), Double::sum);
            sumRatings.merge("Location", (double) feedback.getLocation(), Double::sum);
            sumRatings.merge("Value for Money", (double) feedback.getValueForMoney(), Double::sum);
            sumRatings.merge("Security", (double) feedback.getSecurity(), Double::sum);
            sumRatings.merge("Environmental Practices", (double) feedback.getEnvironmentalPractices(), Double::sum);
        });

        sumRatings.forEach((category, total) -> averageRatings.put(category, total / feedbacks.size()));
        return averageRatings;
    }
    protected static void displayRatings() {
        Map<String, Double> averages = calculateAverageRatings();

        System.out.println("\nHotel Ratings:");
        averages.forEach((category, average) -> System.out.println(category + ": " + String.format("%.2f", average) + "/5"));
    }


    private static void enterCustomerFeedback() {
        System.out.print("Enter room number for feedback: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Integer reservationId = findReservationIdByRoomNumber(roomNumber);

        if (reservationId == null) {
            System.out.println("No active reservation found for this room.");
            return;
        }

        // Get feedback values from the user
        int hygiene = getValidatedInput("Hygiene");
        int animation = getValidatedInput("Animation");
        int roomComfort = getValidatedInput("Room Comfort");
        int staffFriendliness = getValidatedInput("Staff Friendliness");
        int foodQuality = getValidatedInput("Food Quality");
        int amenities = getValidatedInput("Amenities");
        int cleanliness = getValidatedInput("Cleanliness");
        int wifiService = getValidatedInput("Wi-Fi Service");
        int location = getValidatedInput("Location");
        int valueForMoney = getValidatedInput("Value for Money");
        int security = getValidatedInput("Security");
        int environmentalPractices = getValidatedInput("Environmental Practices");

        // Print feedback to the console
        System.out.println("Feedback for reservation ID " + reservationId + ":");
        System.out.println("Hygiene: " + hygiene);
        System.out.println("Animation: " + animation);
        System.out.println("Room Comfort: " + roomComfort);
        System.out.println("Staff Friendliness: " + staffFriendliness);
        System.out.println("Food Quality: " + foodQuality);
        System.out.println("Amenities: " + amenities);
        System.out.println("Cleanliness: " + cleanliness);
        System.out.println("Wi-Fi Service: " + wifiService);
        System.out.println("Location: " + location);
        System.out.println("Value for Money: " + valueForMoney);
        System.out.println("Security: " + security);
        System.out.println("Environmental Practices: " + environmentalPractices);

        backToMenu();
    }


    private static int findReservationIdByRoomNumber(int roomNumber) {
        String sql = "SELECT reservation_id FROM Reservations WHERE room_number = ? AND status = 'active'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("reservation_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database error: " + e.getMessage());
        }
        return -1;
    }


    private static int getValidatedInput(String inputType) {
        int input;
        do {
            System.out.print(inputType + " (1-5): ");
            input = scanner.nextInt();
            scanner.nextLine();
            if (input < 1 || input > 5) {
                System.out.println(inputType + " must be between 1 and 5. Please enter again.");
            }
        } while (input < 1 || input > 5);
        return input;
    }


    private static void editDailySchedule() {
        System.out.println("Edit Daily Schedule:");
        viewAllEvents();

        String choice;
        do {
            manageEvents();
            System.out.println("Do you want to do another action? (Yes/No)");
            choice = scanner.nextLine();
        } while (choice.equalsIgnoreCase("Yes"));

        if (choice.equalsIgnoreCase("No")) {
            backToMenu();
        } else {
            System.out.println("Invalid choice. Returning to the main menu.");
            backToMenu();
        }
    }

    private static void manageEvents() {
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
    private static void addEvent() {
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

    private static void deleteEvent() {
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
    private static void updateEvent() {
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


    private static void viewAllEvents() {
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

    private static void manageHotelInventory() {
        System.out.println("Manage Hotel Inventory:");
        int choice;

        do {
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Check Quantities");
            System.out.println("0. Back to Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addItemToInventory();
                    break;
                case 2:
                    removeItemFromInventory();
                    break;
                case 3:
                    checkInventoryQuantities();
                    break;
                case 0:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
        backToMenu();
    }

    public static void addItemToInventory() {
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

    private static void removeItemFromInventory() {
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

    private static void checkInventoryQuantities() {
        System.out.println("Inventory Quantities:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void enterCustomerData() {
        System.out.println("Entering Customer Data....");
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter customer contact number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();  // Oda numarası genellikle sayısal bir değerdir.
        scanner.nextLine();  // Sayısal girişten sonra scanner'ı temizle.
        System.out.print("Enter reservation date (YYYY-MM-DD): ");
        String reservationDate = scanner.nextLine();

        // SQL INSERT ifadesi ile veritabanına kaydetme işlemi.
        String sql = "INSERT INTO Reservations (room_number, customer_name, contact_number, start_date, status) VALUES (?, ?, ?, ?, 'active')";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, roomNumber);
            pstmt.setString(2, customerName);
            pstmt.setString(3, contactNumber);
            pstmt.setString(4, reservationDate);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                // Rezervasyon başarıyla eklendi, ilgili odanın durumunu "occupied" olarak güncelleyin.
                updateRoomStatus(roomNumber, "occupied");
                System.out.println("Customer data for " + customerName + " with contact number " + contactNumber + " entered, and room " + roomNumber + " reserved successfully.");
            } else {
                System.out.println("An error occurred and the customer data was not entered.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database error: " + e.getMessage());
        }
    }






    protected static void backToMenu() {
        System.out.println("Press Enter to go back to the main menu...");
        try {
            System.in.read(new byte[2]); // Attempt to consume the newline character left in the buffer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class UserThread implements Runnable {
        private static final int MAX_ITERATIONS = 1; // Limit the number of iterations
        private String name;

        public UserThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_ITERATIONS; i++) {
                try {
                    // Simulate user activities
                    makeReservation();
                    Thread.sleep(1000); // Simulate some processing time
                    checkIn();
                    Thread.sleep(1000); // Simulate some processing time
                    checkOut();
                    Thread.sleep(1000); // Simulate some processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void makeReservation() {
            // Simulate making a reservation
            System.out.println(name + " is making a reservation...");
        }

        private void checkIn() {
            // Simulate the check-in process
            System.out.println(name + " is checking in...");
        }

        private void checkOut() {
            // Simulate the check-out process
            System.out.println(name + " is checking out...");
        }
    }

}

