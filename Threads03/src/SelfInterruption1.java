/**
 * @author Joshua Bloch
 */
public class SelfInterruption1 {
	  public static void main(String[] args) {
		    Thread.currentThread().interrupt();
		    
		    if (Thread.currentThread().isInterrupted()) {
		      System.out.println("Interrupted: " + Thread.interrupted());
		    } else {
		      System.out.println("Not interrupted: " + Thread.interrupted());
		    }
		  }
}
