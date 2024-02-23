public class main {
    public static void main(String[] args)
    {
         Hotel hotel = new Hotel();
         HotelChain chain = new HotelChain();

         chain.GenerateRandomChains();
         chain.DisplayHotelChains();
         chain.DisplayHotelsInChain(chain.getChainByName());


    }
}
