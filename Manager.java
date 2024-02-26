import java.util.Scanner;

public class Manager extends Hotel {
    private String name;

    private String UserName;

    private String password;

    public void CancelReservation() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Chain Name:");
        String chainName = input.nextLine();
        System.out.println("Enter Hotel ID:");
        int hotelID = input.nextInt();
        System.out.println("Enter Customer ID:");
        int customerID = input.nextInt();

        if (!chains.contains(getChainByName(chainName))) {
            System.out.println("Error: Chain not found.");
            return;
        }

        Hotel hotel = getChainByName(chainName).getHotelByID(hotelID);
        if (hotel == null) {
            System.out.println("Error: Hotel with ID " + hotelID + " not found in the chain " + chainName);
            return;
        }

        Customer customer = hotel.getCustomerByID(customerID);
        if (customer == null) {
            System.out.println("Error: Customer with ID " + customerID + " not found in Hotel " + hotel.getHotelName());
            return;
        }

        System.out.println("Success! The following Message is displayed to the target customer:");
        customer.CancelReservation();
    }






    public void DisplayCustomerList() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Chain Name:");
        String chainName = input.nextLine();
        System.out.println("Enter Hotel ID:");
        int hotelID = input.nextInt();

        if (chains.contains(getChainByName(chainName))) {
            Hotel hotel = getChainByName(chainName).getHotelByID(hotelID);
            if (hotel != null) {
                if (hotel.customers == null || hotel.customers.isEmpty()) {
                    System.out.println("No customers in Hotel " + hotel.getHotelName());
                } else {
                    System.out.println("Customer List for Hotel " + hotel.getHotelName() + ":");
                    System.out.println("--------------------------------------------------");
                    System.out.printf("%-10s | %-20s | %-15s | %-7s | %-10s\n", "Customer ID", "Name", "Username", "Age", "Balance");
                    System.out.println("--------------------------------------------------");
                    for (Customer customer : hotel.customers) {
                        System.out.printf("%-10d | %-20s | %-15s | %-7d | $%-10d\n", customer.getID(), customer.getName(), customer.getUserName(), customer.getAge(), customer.getBalance());
                    }
                }
            } else {
                System.out.println("Hotel with ID " + hotelID + " not found in the chain " + chainName);
            }
        } else {
            System.out.println("Chain " + chainName + " not found.");
        }
    }


    public void AddRoom() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Chain Name");
        String chainname = input.nextLine();
        System.out.println("Enter Hotel ID");
        int id = input.nextInt();

        if (chains.contains(getChainByName(chainname))) {
            Hotel hotel = getChainByName(chainname).getHotelByID(id);
            if (hotel != null) {
                System.out.println("Enter Room ID:");
                int roomID = hotel.rooms.size();
                input.nextLine();

                if (!hotel.rooms.contains(hotel.findRoomByID(roomID))) {
                    System.out.println("Is room available? (true/false):");
                    boolean available = input.nextBoolean();
                    input.nextLine();

                    System.out.println("Enter Occupancy:");
                    String occupancy = input.nextLine();

                    System.out.println("Enter Layout:");
                    String layout = input.nextLine();

                    System.out.println("Enter Bed Size:");
                    String bedSize = input.nextLine();

                    System.out.println("Enter Price:");
                    int price = input.nextInt();

                    Room room = new Room(roomID, available, occupancy, layout, bedSize, price);
                    hotel.rooms.add(room);
                    System.out.println("Room added successfully.");
                } else {
                    System.out.println("Room Already Exists");
                }
            } else {
                System.out.println("Hotel with ID " + id + " not found in the chain " + chainname);
            }
        } else {
            System.out.println("Chain " + chainname + " not found.");
        }
    }


    public void AddHotel() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Chain Name:");
        String chainName = input.nextLine();

        if (chains.contains(getChainByName(chainName))) {
         //   System.out.println("Enter Hotel ID:");
            int hotelID = getChainByName(chainName).hotels.size();
        //    input.nextLine();

            if (!getChainByName(chainName).hotels.contains(getChainByName(chainName).getHotelByID(hotelID))) {
                System.out.println("Enter Hotel Name:");
                String hotelName = input.nextLine();
                System.out.println("Enter Location:");
                String location = input.nextLine();
                System.out.println("Enter Price Range:");
                String priceRange = input.nextLine();
                System.out.println("Enter Rating:");
                byte rating = input.nextByte();

                Hotel newHotel = new Hotel(hotelID, hotelName, location, priceRange, rating);
                getChainByName(chainName).hotels.add(newHotel); //getChainByName(chainName).getHotelByID(hotelID)
                System.out.println("Hotel added successfully to the chain " + chainName + ".");
            } else {
                System.out.println("Hotel with ID " + hotelID + " already exists in the chain " + chainName + ".");
            }
        } else {
            System.out.println("Chain " + chainName + " not found.");
        }
    }

    public void AddChain() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Chain Name:");
        String chainName = input.nextLine();

        if (!chains.contains(getChainByName(chainName))) {
            HotelChain newChain = new HotelChain(chainName);
            chains.add(newChain);
            System.out.println("Chain added successfully.");
        } else {
            System.out.println("Chain " + chainName + " already exists.");
        }
    }

    public void deleteRoom() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Chain Name:");
        String chainName = input.nextLine();

        if (chains.contains(getChainByName(chainName))) {
            System.out.println("Enter Hotel ID:");
            int hotelID = input.nextInt();
            input.nextLine();

            Hotel hotel = getChainByName(chainName).getHotelByID(hotelID);
            if (hotel != null) {
                System.out.println("Enter Room ID:");
                int roomID = input.nextInt();
                input.nextLine();

                Room room = hotel.findRoomByID(roomID);
                if (room != null) {
                    hotel.rooms.remove(room);
                    System.out.println("Room deleted successfully.");
                } else {
                    System.out.println("Room with ID " + roomID + " not found in the hotel " + hotel.getHotelName() + ".");
                }
            } else {
                System.out.println("Hotel with ID " + hotelID + " not found in the chain " + chainName + ".");
            }
        } else {
            System.out.println("Chain " + chainName + " not found.");
        }
    }

    public void deleteHotel() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Chain Name:");
        String chainName = input.nextLine();

        if (chains.contains(getChainByName(chainName))) {
            System.out.println("Enter Hotel ID:");
            int hotelID = input.nextInt();
            input.nextLine();

            Hotel hotel = getChainByName(chainName).getHotelByID(hotelID);
            if (hotel != null) {
                getChainByName(chainName).hotels.remove(hotel);
                System.out.println("Hotel deleted successfully from the chain " + chainName + ".");
            } else {
                System.out.println("Hotel with ID " + hotelID + " not found in the chain " + chainName + ".");
            }
        } else {
            System.out.println("Chain " + chainName + " not found.");
        }
    }

    public void deleteChain() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Chain Name:");
        String chainName = input.nextLine();

        HotelChain chain = getChainByName(chainName);
        if (chains.contains(chain)) {
            chains.remove(chain);
            System.out.println("Chain deleted successfully.");
        } else {
            System.out.println("Chain " + chainName + " not found.");
        }
    }


}






