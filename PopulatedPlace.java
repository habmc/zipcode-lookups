/* Name: Phoebe Phan
 * File: PopulatedPlace.java
 * Desc:
 * This program represents a place whose population informatio
 * is available, along with its zipcode, town name, state name, latitude and
 * longitude which are available in its superclass LocatedPlace.
 */

public class PopulatedPlace extends LocatedPlace {

  // Additional instance variable
  private int population; // population of that place

  /** Creates a PopulatedPlace with the given information
   *  @param zip The zipcode
   *  @param town The town name
   *  @param state The state name
   *  @param latitude The latitude
   *  @param longitude The longitude
   *  @param population The population
   */
  public PopulatedPlace(String zip, String town, String state, double
          latitude, double longitude, int population) {
    super(zip, town, state, latitude, longitude);
    this.population = population;
  } // PopulatedPlace()

  public double getPopulation() {
    return population;
  } // getPopulation()

  // Print method
  public String toString() {
    return this.getTown() + ", " + this.getState() + " " + this.getLatitude() +
            " " + this.getLongitude() + " " + population;
  } // toString()
} // class PopulatedPlace