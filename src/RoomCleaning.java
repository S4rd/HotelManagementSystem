public class RoomCleaning {
    private Housekeeper housekeeper;
    private String roomId;
    private String timeSpan;
    // Add other parameters and methods as needed

    public RoomCleaning(Housekeeper housekeeper, String roomId, String timeSpan) {
        this.housekeeper = housekeeper;
        this.roomId = roomId;
        this.timeSpan = timeSpan;
    }

    public Housekeeper getHousekeeper() {
        return housekeeper;
    }

    public void setTimeSpan(String timeSpan) {
        this.timeSpan = timeSpan;
    }

    @Override
    public String toString() {
        return "RoomCleaning{" +
                "housekeeper=" + housekeeper.getName() +
                ", roomId='" + roomId + '\'' +
                ", timeSpan='" + timeSpan + '\'' +
                '}';
    }
}

