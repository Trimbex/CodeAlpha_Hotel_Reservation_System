import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class HotelChain
{
    private static final String[] CHAIN_NAMES = {
            "Hilton",
            "Marriott",
            "Hyatt",
            "Radisson",
            "IHG"};
    private static final String[] HOTEL_NAMES = {
            "Grand",
            "Plaza",
            "Park",
            "Royal",
            "Majestic",
            "Palace",
            "Resort",
            "Inn",
            "Suite"};

    public static final String[] HOTEL_LOCATIONS = {
            "New York",
            "Los Angeles",
            "Chicago",
            "Miami",
            "San Francisco"};
    public ArrayList<Hotel> hotels; // Chain contains Hotels
    public static ArrayList<HotelChain> chains;
    private static int NumberOfChains;

    private String ChainName;

    private int NumberOfHotels;


    public HotelChain() {
        ChainName = null;
    }

    public HotelChain(String chainName) {
        ChainName = chainName;
    }


    public String getChainName() {
        return ChainName;
    }

    public void setChainName(String chainName) {
        ChainName = chainName;
    }

    public static int getNumberOfChains() {
        return NumberOfChains;
    }

    public static void setNumberOfChains(int numberOfChains) {
        NumberOfChains = numberOfChains;
    }

    public int getNumberOfHotels() {
        return NumberOfHotels;
    }

    public void setNumberOfHotels(int numberOfHotels) {
        NumberOfHotels = numberOfHotels;
    }


    String GeneratePriceRange()
    {
        Random rand = new Random();
        int low = rand.nextInt(40, 5000);
        int high = rand.nextInt(low + 500,5500);
        return low + "$" + " - " + high + "$";

    }


    public void GenerateRandomChains()
    {
        // ANTIDUPLICATE STILL NEEDED
        Random rand = new Random();
        NumberOfChains = rand.nextInt(1,4);
        chains = new ArrayList<>(NumberOfChains); //chains = new HotelChain[NumberOfChains];
        for(int j = 0; j < NumberOfChains; j++) {
            HotelChain chain = new HotelChain(CHAIN_NAMES[rand.nextInt(0, CHAIN_NAMES.length - 1)]);

            NumberOfHotels = rand.nextInt(1, 4);

            chain.hotels = new ArrayList<>(NumberOfHotels);//chain.hotels = new Hotel[NumberOfHotels];





            for (int i = 0; i < NumberOfHotels; i++) {
                chain.hotels.add( new Hotel(
                        i,
                        HOTEL_NAMES[rand.nextInt(HOTEL_NAMES.length - 1)],
                        HOTEL_LOCATIONS[rand.nextInt(HOTEL_LOCATIONS.length - 1)],
                        GeneratePriceRange(),
                        (byte) rand.nextInt(1,5)));

                chain.hotels.get(i).GenerateRandomRoomsList();
            }
            chain.setNumberOfHotels(NumberOfHotels);
            chains.add(chain);
        }



    }



    public void DisplayHotelChains()
    {
        String format = "%-12s %-12s\n";
        System.out.format(format, "Chain Name", "Number of Hotels");
        System.out.println("--------------------------------------------------");
      for(HotelChain chain: chains)
      {
          System.out.format(format, chain.getChainName() , chain.getNumberOfHotels());
      }
    }

    public void DisplayHotelsInChain(HotelChain chain)
    {
        String format = "%-12s %-17s %-15s %-10s %-12s %-12s\n";
        System.out.format(format, "Hotel ID", "Hotel Name", "# of Rooms", "Location" , "Price Range", "rating (out of 5)");
        System.out.println("----------------------------------------------------------------------------------------");
        for(Hotel hotel: chain.hotels)
        {
            System.out.format(format,
                    hotel.getHotelID(),
                    hotel.getHotelName(),
                    hotel.getNumberOfRooms(),
                    hotel.getLocation(),
                    hotel.getPriceRange(),
                    hotel.getRating());
        }


    }


    HotelChain getChainByName()
    {
        Scanner scanner = new Scanner(System.in);

        for(HotelChain chain: chains)
        {
            if(chain.getChainName().equalsIgnoreCase(scanner.nextLine()))
            {
               return chain;
            }
        }
       // System.out.println("Chain not found");
        return null;

    }


    public HotelChain getChainByName(String chainName) {
        for (HotelChain chain : chains) {
            if (chain.getChainName().equalsIgnoreCase(chainName)) {
                return chain;
            }
        }
      //  System.out.println("Chain not found");
        return null;
    }

    public Hotel getHotelByID(int hotelID) {
        for (Hotel hotel : hotels) {
            if (hotel.getHotelID() == hotelID) {
                return hotel;
            }
        }
      //  System.out.println("Hotel Not Found");
        return null;
    }

}
