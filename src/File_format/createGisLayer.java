package File_format;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import GIS.MetaData;
import GIS.gisElement;

/**
 * The class is require for creating gisLayer from gisElements by reading the csv file and  
 * add gis elements to set of gisElements  
 * @authors aric and Tal
 */

public class createGisLayer {

	//create GisElement collection from csv file
	/**
	 * create GisElement collection from csv file by using the class readCsv
	 * @param path is the location of the csv file
	 * @return set of gisElements (gisLayer)
	 * @throws IOException
	 */
	
	public static Set<gisElement> createGisElementCollection(String path) throws IOException{

		readCsv csv=new readCsv(path); // this path is the input csv file
		ArrayList<String> arr=csv.run(); // create arrayList of all the lines as a string
		Set<gisElement> gisArr=new HashSet<gisElement>(); // new arrayList of the gis element
		 
		for(int i=0;i<arr.size();i++) {

			String [] parsed=arr.get(i).split(",");
			
			double x=Double.parseDouble(parsed[6]);
			double y=Double.parseDouble(parsed[7]);
			double z=Double.parseDouble(parsed[8]);

			MetaData meta=new MetaData(parsed[0],parsed[1],parsed[2],parsed[3],parsed[5]);
			gisElement a=new gisElement(x,y,z,meta);
			gisArr.add(a);
		}
		return gisArr;
	}
}