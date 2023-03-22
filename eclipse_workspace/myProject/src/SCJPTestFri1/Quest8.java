package SCJPTestFri1;
	class SuperHotel {
	    public int bookings;
	    public void book() {
	        bookings++;
	    }
	}

	public class Quest8 extends SuperHotel {
	    public void book() {
	        bookings--;
	    }
	    
	    public void book(int size) {
	        book();
	        super.book();
	        bookings += size;
	    }
	    
	    public static void main(String args[]) {
	    	Quest8 hotel = new Quest8();
	        hotel.book(2);
	        System.out.print(hotel.bookings);
	    }
	}

