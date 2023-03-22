package dumpsExerc;

public final class CreditCard {
//	  private final int number;
	private int number1, number2;
		 
	  public CreditCard(int number1, int number2) {
	    this.number1 = number1;
	    this.number2 = number2;
	  }
	 
	  public boolean equals(Object o) {
	    if (o == this) {
	      return true;
	    }
	    if (!(o instanceof CreditCard)) {
	      return false;
	    }
	    CreditCard cc = (CreditCard)o;
	    return cc.number1 == number1;
	
	  }
	 
	  public static void main(String[] args) {
	    Map<CreditCard, String> m = new HashMap<CreditCard, String>();
	    m.put(new CreditCard(100, 102), "4111111111111111");
	    System.out.println(m.get(new CreditCard(100, 102))); 
	  }
	}