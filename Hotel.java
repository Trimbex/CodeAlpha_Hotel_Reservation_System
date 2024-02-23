import java.util.ArrayList;
import java.util.Random;

public class Hotel extends HotelChain {

    private int HotelID;
    private String HotelName;

    private int NumberOfRooms;

    private ArrayList<Room> rooms;

    private String Location;
    private String PriceRange;
    private byte rating;


    public Hotel() {
        this.HotelID = 0;
        this.HotelName = null;
    }

    public Hotel(int hotelID, String hotelName) {
        this.HotelID = hotelID;
        this.HotelName = hotelName;
    }

    public Hotel(int HotelID, String HotelName, String Location, String PriceRange, byte rating) {
        this.HotelID = HotelID;
        this.HotelName = HotelName;
        this.Location = Location;
        this.PriceRange = PriceRange;
        this.rating = rating;
    }

    public int getHotelID() {
        return HotelID;
    }

    public void setHotelID(int hotelID) {
        HotelID = hotelID;
    }


    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public int getNumberOfRooms() {
        return NumberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        NumberOfRooms = numberOfRooms;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPriceRange() {
        return PriceRange;
    }

    public void setPriceRange(String priceRange) {
        PriceRange = priceRange;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    private static final String[] roomCategories = {
            "Standard",
            "Deluxe",
            "Suite",
            "Executive",
            "Family",
            "Accessible",
            "Pet-Friendly",
            "Adjoining",
            "Premium",
            "Themed"
    };

    private static final String[] occupancyOptions = {
            "Single",
            "Double",
            "Triple",
            "Quad",
            "Queen",
            "King"

    };

    private static final String[] bedSizeOptions = {
            "Twin",
            "Full",
            "Queen",
            "King",
            "California King"

    };


    public void GenerateRandomRoomsList() {

        Random rand = new Random();
        NumberOfRooms = rand.nextInt(91) + 30;

        rooms = new ArrayList<>();

        for (int counter = 0; counter < NumberOfRooms; counter++) {
            rooms.add(new Room(
                    counter,
                    rand.nextInt(2) == 1,
                    occupancyOptions[rand.nextInt(occupancyOptions.length)],
                    roomCategories[rand.nextInt(roomCategories.length)],
                    bedSizeOptions[rand.nextInt(bedSizeOptions.length)],
                    rand.nextInt(2001) + 300
            ));
        }

    }

    public void DisplayRoomsInHotel(Hotel hotel)
    {
        String format = "%-12s %-10s %-20s %-20s %-12s %-12s\n";
        System.out.format(format, "Room Number", "Occupancy", "Layout", "Bed Size", "Price/Night", "Availability");
        System.out.println("---------------------------------------------------------------------------------------");

        for(Room rooms : hotel.rooms)
        {
            System.out.format(format, rooms.getID(), rooms.getOccupancy(), rooms.getLayout(), rooms.getBedSize(), rooms.getPrice() + "$", rooms.isAvailable());
        }
    }
}
