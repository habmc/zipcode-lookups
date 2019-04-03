import java.util.*;
import java.io.*;

/* Name: Phoebe Phan
 * File: LookupZip.java
 * Desc:
 *
 * This program includes several methods which can be used to read data
 * from two source files "uszipcodes.csv" and "ziplocs.csv" into an ArrayList
 * of type Place, which can then be used to implement zipcode lookups.
 */
public class LookupZip {
	public static final int ZIP = 0;
	public static final int TOWN = 1;
	public static final int STATE = 2;
	public static final int POPULATION = 3;
	public static final int LATITUDE = 5;
	public static final int LONGITUDE = 6;

	/** Parses one line from uszipcodes.csv file by creating a
	 *  Place/PopulatedPlace that denotes the information in the given line
	 *  @param line One line from the zipcodes file
	 *  @return A Place that contains the relevant information
	 *  zip code, town, state and/or population from that line
	 */
	public static Place parseLine(String line) {
		String[] fields = line.split(",");
		Place place;

		if (fields.length == 3) {
			place = new Place(fields[ZIP], fields[TOWN], fields[STATE]);
		}
		else {
			int population = Integer.parseInt(fields[POPULATION]);
			place = new PopulatedPlace(fields[ZIP], fields[TOWN], fields[STATE]
					, 0, 0, population);
		}
		return place;
	} // parseLine()

	/** Replace a given Place object by an updated PopulatedPlace
	 *  or LocatedPlace object which has the same zip code.
	 *  @param line One line from the zipcodes fil
	 *  @param place the Place object that needs to be replaced
	 *  @return A Place that contains the relevant information
	 *  (zip code, town, state, latitude, longitude) or
	 *  (zip code, town, state, latitude, longitude, population) from that line
	 */

	public static Place replacePlace(String line, Place place) {
		String[] fields = line.split(",");

		// trim all quotation marks from the string containing zipcode
		String zip = fields[ZIP].replaceAll("\"", "");

		if (!fields[LATITUDE].equals("") && !fields[LONGITUDE].equals("")) {
			double latitude = Double.parseDouble(fields[LATITUDE]);
			double longitude = Double.parseDouble(fields[LONGITUDE]);

			if (place instanceof PopulatedPlace) {
				((PopulatedPlace) place).setLatitude(latitude);
				((PopulatedPlace) place).setLongitude(longitude);
				return place;
			}
			else {
				return new LocatedPlace(place.getZip(), place.getTown(),
						place.getState(), latitude, longitude);
			}
		}
		return place;
	} // replacePlace()

	/** Read a line from ziplocs file, checking whether the zipcode it contains
	 *  was already recorded in one of the objects in the ArrayList.
	 *  If yes, replace that object by an updated PopulatedPlace
	 *  or LocatedPlace object created with information in the given
	 *  line, using replacePlace method.
	 *  @param places The name of the Place ArrayList
	 *  @param line The line from ziplocs file
	 */
	public static void checkRepetition(ArrayList<Place> places, String line) {
		String[] tokens = line.split(",");

		// trim all quotation marks from the string containing zipcode
		String zip = tokens[ZIP].replaceAll("\"", "");

		for (int i = 0; i < places.size(); i++) {
			if (places.get(i).getZip().equals(zip)) {
				places.set(i, replacePlace(line, places.get(i)));
				break;
			} // if statement
		} // for loop
	}// checkRepetition()

	/** Reads a zipcodes file and a ziplocs file, creating
	 *  an ArrayList of Places. If a place's population is
	 *  known, it will be represented by a PopulatedPlace
	 *  object; otherwise, if a place's location is known,
	 *  it will be represented by a LocatedPlace object; otherwise
	 *  it will be represented by a Place object.
	 *  @param zipfile The name of the zipcodes file
	 *  @param locfile The name of the ziplocs file
	 *  @return The array of Places representing all the
	 *  data in the files.
	 *  @exception FileNotFoundException is expected
	 */
	public static ArrayList<Place> readZipCodes(String zipfile, String locfile)
			throws FileNotFoundException {

		// read data from the zipcode file into a new ArrayList callled places
		Scanner input1 = new Scanner(new File(zipfile));

		// read the first line of input
		String line1 = input1.nextLine();
		String fields[] = line1.split(",");
		int n = Integer.parseInt(fields[0]); // number of entries in the file

		ArrayList<Place> places = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String line = input1.nextLine();
			places.add(i, parseLine(line));
		} // done reading zipcode file

		// read data from locfile into places
		Scanner input2 = new Scanner(new File(locfile));
		input2.nextLine(); //skip first line

		/* Go through the ziplocs file and check whether it contains
		 * zipcodes which can also be found in places, then update them
		 * accordingly using checkRepetition method.
		 */
		while(input2.hasNext()) {
			String line = input2.nextLine();
			checkRepetition(places, line);
		} // while loop
		return places;
	} // readZipCodes()

	/** Find a Place with a given zip code
	 *  @param zip The zip code (as a String) to look up
	 *  @return A place that matches the given zipcode,
	 *  or null if no such place exists.
	 */
	public static Place lookupZip(ArrayList<Place> places, String zip) {
		for (int i = 0; i < places.size(); i++) {
			if ((places.get(i).getZip()).equals(zip))
				return places.get(i);
		} // end for loop
		return null;
	} // lookupZip()
} // class LookupZip