package SCJPMon;

public class LongExp{
	  LongExp() throws Exception{
	    LongExp.start();
	  }
	  public static void start()throws RuntimeException{
	    throw new IllegalMonitorStateException();
	  }
	  public static void main(String args[]) throws Throwable{
	    try{
	      try{
	        try{
	          new LongExp();
	        } catch(Throwable t){
	          throw t;
	        }
	      }catch(Throwable t){
	        if (t instanceof IllegalMonitorStateException){
	          throw (RuntimeException)t;
	        }else{
	          throw (IllegalMonitorStateException)t;
	        }
	      }
	    }catch(IllegalMonitorStateException e){
	      System.out.println("Inside-IllegalMonitorStateException");
	    }catch(RuntimeException e){
	      System.out.println("Inside-RuntimeException");
	    }catch(Exception e){
	      System.out.println("Inside-Exception");
	    }catch(Throwable e){
	      System.out.println("Inside-Throwable");
	    }
	  }
	}
