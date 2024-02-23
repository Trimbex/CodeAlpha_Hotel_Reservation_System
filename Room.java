public class Room extends Hotel{
    private int ID;
    private boolean available;
    private String occupancy;
    private String layout;
    private String bedSize;

    private int Price;




   // private String CheckIn;

   // private String CheckOut;

    public Room() {
        this.ID = 0;
        this.available = false;
        this.occupancy = null;
        this.layout = null;
        this.bedSize = null;
        this.Price = 0;
    }


    public Room(int ID, boolean available, String occupancy, String layout, String bedSize, int Price) {
        this.ID = ID;
        this.available = available;
        this.occupancy = occupancy;
        this.layout = layout;
        this.bedSize = bedSize;
        this.Price = Price;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(String occupancy) {
        this.occupancy = occupancy;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getBedSize() {
        return bedSize;
    }

    public void setBedSize(String bedSize) {
        this.bedSize = bedSize;
    }


}

