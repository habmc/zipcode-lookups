import java.io.*;
import java.util.*;

public class Main {
  public static final String STOP = "00000";

    /** Repeatedly input a zip code from the user and then output
     *  the name of the town and the state to which it belongs
     *  until the user exits by typing "00000".
     *  In case the zip code doesn't exist, print out "No such zipcode".
     *  @param args A string array containing the command line arguments.
     *  @exception FileNotFoundException is expected
     *  @return No return value.
     */
     public static void main(String[] args) throws FileNotFoundException {
       String zipfile = "uszipcodes.csv";
       String locfile = "ziplocs.csv";
       String zip; // the zipcode the user inputs
     	 Scanner response = new Scanner(System.in);
       ArrayList<Place> places = LookupZip.readZipCodes(zipfile, locfile);

     	 do {
     	   System.out.print("zipcode: ");

     	   // input a zip code from the user
     	   zip = response.nextLine();

     	    // if the zipcode exists, output the name of the town and state
     	    if (LookupZip.lookupZip(places, zip) != null) {
     		     System.out.println(LookupZip.lookupZip(places, zip) + "\n");
     	    }

     	    // let the user know if it doesn't exist
     	    else if(!(zip.equals(STOP))
     		    && LookupZip.lookupZip(places, zip) == null) {
     		       System.out.println("No such zipcode\n");
     	    }
     	} while (!zip.equals(STOP));

     	 System.out.println("Good Bye!");
     }
}
