package SCJPTestSat;

import java.io.*;

class SuperHotel {
	
}

public class Hotel extends SuperHotel implements Serializable {
//    private Room room = new Room();
    
    public static void main(String[] args) {
        Hotel h = new Hotel();
        try {
            FileOutputStream fos = new FileOutputStream("Hotel.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(h);
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

class Room extends SuperHotel{
}     
