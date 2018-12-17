package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class reads from csv file and save each line in arrayList
 * @author aric and Tal 
 */

public class readCsv {

	private final BufferedReader reader;

	public readCsv(String input) throws IOException {
		reader = new BufferedReader(new FileReader(input));
	}

	/**
	 * This function require to create kml file from csv 
	 * @return arrayList of each line of the csv file
	 * @throws IOException
	 */
	public ArrayList<String> run() throws IOException {

		ArrayList<String> strings=new ArrayList<String>();
		try { 

			reader.readLine(); // skip 1st line
			reader.readLine(); // skip 2nd line
			String str = reader.readLine();

			while (str != null) {

				strings.add(str);
				str = reader.readLine();
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		} 
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch(Exception e) {

					throw new RuntimeException(e);
				}
			} 
		}
		
		return strings; // return the arrayList of all the lines in the csv file as strings
	}
}