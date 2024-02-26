import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Customer extends Hotel
{
    private int ID;
    private String UserName;
    private String Password;
    private String Name;
    private int age;
    private int balance = 5000;
    private Room room = null;
    private String CheckIn;
    private String CheckOut;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getCheckIn() {
        return CheckIn;
    }

    public void setCheckIn(String checkIn) {
        CheckIn = checkIn;
    }

    public String getCheckOut() {
        return CheckOut;
    }

    public void setCheckOut(String checkOut) {
        CheckOut = checkOut;
    }

    public Customer() {

    }

    public Customer(int ID, String userName, String password, String name, int age, int balance, Room room, String checkIn, String checkOut) {
        this.ID = ID;
        UserName = userName;
        Password = password;
        Name = name;
        this.age = age;
        this.balance = balance;
        this.room = room;
        CheckIn = checkIn;
        CheckOut = checkOut;

    }

   String getCheckInDate()
   {
       return LocalDate.now().toString();
   }
   String getCheckOutDate(int Nights)
   {
       return LocalDate.now().plusDays(Nights).toString();
   }


    public void MakeReservation() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Chain Name:");
        DisplayHotelChains();
        String chainName = input.nextLine();

        HotelChain chain = getChainByName(chainName);
        if (chain == null) {
            System.out.println("Chain not found.");
            return;
        }
        chain.DisplayHotelsInChain(chain);
        System.out.println("Enter Hotel ID:");
        int hotelID = input.nextInt();

        Hotel hotel = chain.getHotelByID(hotelID);

        hotel.DisplayRoomsInHotel(hotel);

        System.out.println("Enter Room Number that you want to book:");
        int roomID = input.nextInt();

        Room selectedRoom = hotel.findRoomByID(roomID);
        if (selectedRoom == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.println("Enter the number of nights you want to stay:");
        int numberOfNights = input.nextInt();

        if (numberOfNights <= 0) {
            System.out.println("Number of nights must be greater than 0.");
            return;
        }

        int roomPrice = selectedRoom.getPrice() * numberOfNights;

        if (!selectedRoom.isAvailable()) {
            System.out.println("Room " + roomID + " is not available. Please choose another room.");
            return;
        }

        if (balance < roomPrice) {
            System.out.println("Insufficient balance to book the room.");
            return;
        }


        setRoom(selectedRoom);
        selectedRoom.setAvailable(false);
        setCheckIn(getCheckInDate());
        setCheckOut(getCheckOutDate(numberOfNights));

        System.out.println("You have successfully booked Room " + selectedRoom.getID() + " for " + numberOfNights + " Nights.");
        System.out.println("Total Price: $" + roomPrice);
        System.out.println("Remaining Balance: $" + (balance - roomPrice));
       // printInvoice();
    }



    void CancelReservation()
    {
         if(room != null)
         {
             setRoom(null);
             setCheckIn(null);
             setCheckOut(null);
             System.out.println("Your Reservation has been cancelled");
         }
         else
             System.out.println("You have not made a reservation yet");
    }

    String printInvoice()
    {

        if (room == null || CheckIn == null || CheckOut == null) {
            return "No reservation details available for invoice generation.";
        }
        return "Invoice for Reservation:\n" +
                "Customer Name: " + Name + "\n" +
                "Room ID: " + room.getID() + "\n" +
                "Check-in Date: " + CheckIn + "\n" +
                "Check-out Date: " + CheckOut + "\n";
    }

    public void Register() {
        Scanner input = new Scanner(System.in);

        System.out.println("In Order to register, you will need to specify which hotel you are registering in");
        DisplayHotelChains();
        System.out.println("Enter Chain name");
        HotelChain chain = getChainByName(input.nextLine());
        DisplayHotelsInChain(chain);
        System.out.println("Enter Hotel ID");
        int hotelID = input.nextInt();
        input.nextLine(); // Consume newline character

        Hotel hotel = chain.getHotelByID(hotelID);

        System.out.println("Enter your username:");

        String username = input.nextLine();

        for (Customer customer : hotel.customers) {
            if (Objects.equals(customer.UserName, username)) {
                System.out.println("Username already exists.");
                return;
            }
        }

        setUserName(username);

        System.out.println("Enter your Password:");
        String password = input.nextLine();
        setPassword(password);

        System.out.println("Re-enter your password:");
        String password1 = input.nextLine();

        if (!password1.equals(password)) {
            System.out.println("Passwords do not match. Registration failed.");
            return;
        }

        System.out.println("Enter your Name:");
        String name = input.nextLine();
        setName(name);

        System.out.println("Enter your Age:");
        int age = input.nextInt();
        setAge(age);

        hotel.customers.add(this);

        System.out.println("Registration successful.");
    }

    public boolean Login(boolean loggedin) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Chain name");
        HotelChain chain = getChainByName(input.nextLine());
        DisplayHotelsInChain(chain);
        System.out.println("Enter Hotel ID");
        int hotelID = input.nextInt();
        input.nextLine();
        Hotel hotel = chain.getHotelByID(hotelID);
        System.out.println("Enter your username:");
        String username = input.nextLine().toLowerCase();
        for (Customer customer : hotel.customers) {
            if (customer.getUserName().toLowerCase().equals(username)) {
                System.out.println("Enter your Password");
                if (customer.getPassword().equals(input.nextLine())) {
                    System.out.println("Login Successful");
                    return true;

                }
            }
        }

        System.out.println("Username not found, try registering.");
        return false;
    }



}
