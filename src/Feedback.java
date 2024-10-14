import java.util.*;

public class Feedback {
    static Scanner scanner = new Scanner(System.in);
    static List<Feedback> feedbacks = new ArrayList<>();
    private int hygiene;
    private int animation;
    private int roomComfort;
    private int staffFriendliness;
    private int foodQuality;
    private int amenities;
    private int cleanliness;
    private int wifiService;
    private int location;
    private int valueForMoney;
    private int security;
    private int environmentalPractices;

    public Feedback(int hygiene, int animation, int roomComfort, int staffFriendliness, int foodQuality,
                    int amenities, int cleanliness, int wifiService, int location, int valueForMoney,
                    int security, int environmentalPractices) {
        this.hygiene = hygiene;
        this.animation = animation;
        this.roomComfort = roomComfort;
        this.staffFriendliness = staffFriendliness;
        this.foodQuality = foodQuality;
        this.amenities = amenities;
        this.cleanliness = cleanliness;
        this.wifiService = wifiService;
        this.location = location;
        this.valueForMoney = valueForMoney;
        this.security = security;
        this.environmentalPractices = environmentalPractices;
    }

    public int getHygiene() {
        return hygiene;
    }

    public int getAnimation() {
        return animation;
    }

    public int getRoomComfort() {
        return roomComfort;
    }

    public int getStaffFriendliness() {
        return staffFriendliness;
    }

    public int getFoodQuality() {
        return foodQuality;
    }

    public int getAmenities() {
        return amenities;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public int getWifiService() {
        return wifiService;
    }

    public int getLocation() {
        return location;
    }

    public int getValueForMoney() {
        return valueForMoney;
    }

    public int getSecurity() {
        return security;
    }

    public int getEnvironmentalPractices() {
        return environmentalPractices;
    }


    public void setHygiene(int hygiene) {
        this.hygiene = hygiene;
    }

    public void setAnimation(int animation) {
        this.animation = animation;
    }

    public void setRoomComfort(int roomComfort) {
        this.roomComfort = roomComfort;
    }

    public void setStaffFriendliness(int staffFriendliness) {
        this.staffFriendliness = staffFriendliness;
    }

    public void setFoodQuality(int foodQuality) {
        this.foodQuality = foodQuality;
    }

    public void setAmenities(int amenities) {
        this.amenities = amenities;
    }

    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public void setWifiService(int wifiService) {
        this.wifiService = wifiService;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setValueForMoney(int valueForMoney) {
        this.valueForMoney = valueForMoney;
    }

    public void setSecurity(int security) {
        this.security = security;
    }

    public void setEnvironmentalPractices(int environmentalPractices) {
        this.environmentalPractices = environmentalPractices;
    }
    static Map<String, Double> calculateAverageRatings() {
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
    private static void displayRatings() {
        Map<String, Double> averages = calculateAverageRatings();

        System.out.println("\nHotel Ratings:");
        averages.forEach((category, average) -> System.out.println(category + ": " + String.format("%.2f", average) + "/5"));
    }

    static void enterCustomerFeedback() {
        System.out.println("Entering Customer Feedback:");
        System.out.print("Hygiene (1-5): ");
        int hygiene = scanner.nextInt();
        System.out.print("Animation (1-5): ");
        int animation = scanner.nextInt();
        System.out.print("Room Comfort (1-5): ");
        int roomComfort = scanner.nextInt();
        System.out.print("Staff Friendliness and Efficiency (1-5): ");
        int staffFriendliness = scanner.nextInt();
        System.out.print("Food Quality (1-5): ");
        int foodQuality = scanner.nextInt();
        System.out.print("Amenities (1-5): ");
        int amenities = scanner.nextInt();
        System.out.print("Cleanliness (1-5): ");
        int cleanliness = scanner.nextInt();
        System.out.print("Internet/Wi-Fi Service (1-5): ");
        int wifiService = scanner.nextInt();
        System.out.print("Location (1-5): ");
        int location = scanner.nextInt();
        System.out.print("Value for Money (1-5): ");
        int valueForMoney = scanner.nextInt();
        System.out.print("Security (1-5): ");
        int security = scanner.nextInt();
        System.out.print("Environmental Practices (1-5): ");
        int environmentalPractices = scanner.nextInt();

        Feedback feedback = new Feedback(hygiene, animation, roomComfort, staffFriendliness, foodQuality, amenities, cleanliness, wifiService, location, valueForMoney, security, environmentalPractices);
        feedbacks.add(feedback);

        System.out.println("Feedback entered successfully.");
        displayRatings();
        HotelManagementSystemMenu.backToMenu();
    }
}

