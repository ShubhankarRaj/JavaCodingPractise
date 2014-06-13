package SCJPTestFri1;



	public class Quest7 {
	    private int roomNr;
	    
	    public Quest7(int roomNr) {
	        this.roomNr = roomNr;
	    }
	    
	    public int getRoomNr() {
	        return this.roomNr;
	    }
	    
	    static Quest7 doStuff(Quest7 hotel) {
	        hotel = new Quest7(1);
	        return hotel;
	    }
	    
	    public void book(){synchronized(Quest7.class){} }
	    
	    public static void main(String args[]) {
	    	Quest7 h1 = new Quest7(100);
	        System.out.print(h1.getRoomNr() + " ");
	        Quest7 h2 = doStuff(h1);
	        System.out.print(h1.getRoomNr() + " ");
	        System.out.print(h2.getRoomNr() + " ");
	        h1 = doStuff(h2);
	        System.out.print(h1.getRoomNr() + " ");
	        System.out.print(h2.getRoomNr() + " ");
	    }
	}

