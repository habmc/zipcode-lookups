/* Name: Phoebe Phan
 * File: LocatedPlace.java
 * Desc:
 * This program represents a place whose latitude and longitude is available,
 * along with its zipcode, town name and state which are inherited
 * from superclass Place.
 */

public class LocatedPlace extends Place {

  // Additional instance variable
  private double latitude; // the latitude
  private double longitude; // the longitude

  /** Creates a LocatedPlace with the given information
   *  @param zip The zipcode
   *  @param town The town name
   *  @param state The state name
   *  @param latitude The latitude
   *  @param longitude The longitude
   */
  public LocatedPlace(String zip, String town, String state,
                      double latitude, double longitude) {
    super(zip, town, state);
    this.latitude = latitude;
    this.longitude = longitude;
  } // LocatedPlace()

  public double getLatitude() {
    return latitude;
  } // getLatitude()

  public double getLongitude() {
    return longitude;
  } // getLongitude()

  // setters
  public void setLatitude(double newest) {
    this.latitude = newest;
  } // setLatitude()

  public void setLongitude(double newest) {
    this.longitude = newest;
  } // setLongitude()

  public String toString() {
    return this.getTown() + ", " + this.getState() + " " + latitude + " " + longitude;
  } // toString()
} // class LocatedPlace