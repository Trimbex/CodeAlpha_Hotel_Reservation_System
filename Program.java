import java.util.Scanner;

public class Program
{
    HotelChain chain = new HotelChain();

    void GenerateRandom()
    {

        chain.GenerateRandomChains();

        for(HotelChain chain: chain.chains)
        {
            for(Hotel hotel : chain.hotels)
            {
                hotel.GenerateRandomRoomsList();
            }
        }

    }

    boolean exit(boolean x)
    {
       return x = true;
    }

    void WelcomeMessage()
    {
        String welcomeMessage = """
                Welcome Guest! Select one of three options below:
                1: Manager
                2: Customer
                3: Exit Program
                """;
        System.out.println(welcomeMessage);
    }

    void ManagerWelcomeMessage()
    {
        String welcomeMessage = """
                Welcome Manager! Type the number of operation you want. 
                1: View Data (Chains,Hotels,Room)
                2: Add/Remove a Chain/Hotel/Room
                3: Cancel a reservation  
                4: View Customer's list 
                5: Back to Stakeholder selection
                6: Exit     
                """;
        System.out.println(welcomeMessage);
    }

    void LoginMessage()
    {
        String login =
                """
                Welcome Guest, Please Register or Login 
                1: Register
                2: Login          
                """;
        System.out.println(login);
    }

    void CustomerWelcomeMessage()
    {
        String customerwelcome =
                """
                Welcome Customer! Choose an option:
                1. Book a reservation
                2. Cancel Your reservation
                3. Go back           
                """;
        System.out.println(customerwelcome);
    }

public void Programm()
{
    enum States{WELCOME,MANAGER,CUSTOMER,EXIT}
 //  enum ManagerStates{SELECTION,HOME,VIEWINGCHAINS,VIEWINGHOTELS,VIEWINGROOMS,DATAMANIPULATION,MODIFYCONTENT,EXIT}
    boolean exited = false;
    GenerateRandom();

    System.out.println("Make sure to input correct data, incorrect data might result in program termination");
    System.out.println("Reenter Data if there is no response as it could be runtime issues.");

    while(!exited) {
        States statechosen = States.WELCOME;
        Scanner input = new Scanner(System.in);
        WelcomeMessage();
        int choice = input.nextInt();
        switch (choice)
        {
            case 1 ->statechosen = States.MANAGER;
            case 2 -> statechosen = States.CUSTOMER;
            case 3 ->
            {
                statechosen = States.EXIT;
                exit(exited);
            }
            default -> System.out.println("Invalid choice! Please select again.");
        }

        switch(statechosen) {
            case MANAGER -> {
                boolean managing = true;
                Manager manager = new Manager();
                while (managing) {


                    enum WelcomeManagerStates {

                        VIEW_DATA,
                        ADD_REMOVE,
                        CANCEL_RESERVATION,
                        VIEW_CUSTOMERS,
                        BACK_TO_SELECTION,
                        EXIT
                    }
                    ManagerWelcomeMessage();
                    WelcomeManagerStates managerstate = WelcomeManagerStates.values()[input.nextInt() - 1];


                    switch (managerstate) {

                        case VIEW_DATA:
                            enum ViewData {
                                CHAINS,
                                HOTELS,
                                ROOMS,
                                BACK,
                                EXIT
                            }
                            String View = """
                                    Select what you want to view  
                                    1: View available Hotel Chains
                                    2: View a Hotel Chain's Hotels
                                    3: View A Hotel's Rooms                                    
                                    """;
                            System.out.println(View);
                            ViewData datastate = ViewData.values()[input.nextInt() - 1];


                            switch (datastate) {
                                case CHAINS -> manager.DisplayHotelChains();
                                case HOTELS -> {
                                    System.out.println("Enter Chain Name (Caps Sensitive)");
                                    input.nextLine();
                                    String chainname = input.nextLine();
                                    HotelChain chainobj = chain.getChainByName(chainname);
                                    chain.DisplayHotelsInChain(chainobj);


                                }
                                case ROOMS -> {
                                    manager.DisplayHotelChains();
                                    System.out.println("Enter Chain Name (Caps Sensitive)");
                                    HotelChain chainobj = chain.getChainByName();
                                    chain.DisplayHotelsInChain(chainobj);
                                    System.out.println("Enter Hotel ID");
                                    Hotel hotelobj = chainobj.getHotelByID(input.nextInt());
                                    hotelobj.DisplayRoomsInHotel(hotelobj);
                                    System.out.println("Do u want to Sort/Filter Rooms?");
                                    System.out.println("1: Sort 2: Filter 3: No Thanks");

                                    int sortfilterchoice = input.nextInt();

                                    if (sortfilterchoice == 1) {
                                        hotelobj.Sort();
                                    } else if (sortfilterchoice == 2) {
                                        hotelobj.Filter();
                                    } else continue;

                                }


                            }
                            break;
                        case ADD_REMOVE: {
                            String addremove = """
                                    Selection operation 
                                    1: Add Contents
                                    2: Remove Contents                                                                   
                                    """;
                            System.out.println(addremove);
                            int addremovechoice = input.nextInt();

                            switch (addremovechoice) {
                                case 1: {
                                    String add = """
                                            Selection what to add
                                            1: Add a Chain
                                            2: Add a Hotel
                                            3: Add a Room                                                                  
                                            """;
                                    System.out.println(add);

                                    int addchoice = input.nextInt();

                                    switch (addchoice) {
                                        case 1: {
                                            manager.AddChain();
                                            break;
                                        }
                                        case 2: {
                                            manager.AddHotel();
                                            break;
                                        }
                                        case 3: {
                                            manager.AddRoom();
                                            break;
                                        }
                                    }
                                    break;
                                }
                                case 2: {
                                    String remove = """
                                            Selection what to remove
                                            1: Remove a Chain
                                            2: Remove a Hotel
                                            3: Remove a Room                                                                  
                                            """;
                                    System.out.println(remove);

                                    int removechoice = input.nextInt();

                                    switch (removechoice) {
                                        case 1: {
                                            manager.deleteChain();
                                            break;
                                        }
                                        case 2: {
                                            manager.deleteHotel();
                                            break;
                                        }
                                        case 3: {
                                            manager.deleteRoom();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                        case CANCEL_RESERVATION:
                            manager.CancelReservation();
                            break;
                        case VIEW_CUSTOMERS:
                            manager.DisplayCustomerList();
                            break;
                        case BACK_TO_SELECTION:
                            managing = false;
                            break;
                        case EXIT:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choice!, terminating program.");
                    }


                }

            }
            case CUSTOMER -> {
                Customer customer = new Customer();
                boolean loggedin = false;
                boolean __customer = false;
                LoginMessage();
                int loginchoice = input.nextInt();

                    switch (loginchoice) {
                        case 1:
                            customer.Register();
                            break;
                        case 2:
                            loggedin = customer.Login(loggedin);
                            break;
                        default:
                            break;
                    }


                    while (loggedin) {
                        CustomerWelcomeMessage();
                        int customerchoice = input.nextInt();

                        switch (customerchoice) {
                            case 1: {
                                customer.MakeReservation();
                                break;
                            }
                            case 2: {
                                customer.CancelReservation();
                                break;
                            }
                            case 3: {
                                loggedin = false;
                                break;
                            }

                        }

                    }


                }

                //case EXIT -> System.exit(0);

            }
        }






    }
}


