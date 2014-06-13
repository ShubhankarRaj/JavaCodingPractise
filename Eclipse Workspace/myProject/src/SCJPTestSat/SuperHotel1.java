package SCJPTestSat;

class Hotel {
    public int bookings;
    public int different =10;
    public void book() {
        bookings++;
    }
}

public class SuperHotel1 extends Hotel {
   
	public void book() {
        bookings--;
    }
    
    public void book(int size) {
        book();
        super.book();
        bookings += size;
    }
    
    public static void main(String args[]) {
        Hotel hotel = new SuperHotel();
        hotel.book();
        System.out.print(hotel.bookings);
        System.out.println(hotel.different);
    }
}
