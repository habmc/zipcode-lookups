import java.util.*;
import java.io.*;

/* Name: Phoebe Phan
 * File: Place.java
 * Desc:
 * This represents a place whose zipcode, town name, and state is available.
 */
public class Place {
    private String zip; // zipcode
    private String town; // name of town
    private String state; // name of state

    /** Creates a Place with the given zip, town name, and state
     *  @param zip The 5-digit zip code
     *  @param town The town name
     *  @param state The state abbreviation
     */
    public Place(String zip, String town, String state) {
        this.zip = zip;
        this.town = town;
        this.state = state;
    } // Place()

    // Accessors
    public String getZip() {
        return zip;
    } // getZip()
    public String getTown() {
        return town;
    } // getTown()
    public String getState() {
        return state;
    } // getState()

    // Print method
    public String toString() {
        return town + ", " + state;
    } // toString()
} // class Place