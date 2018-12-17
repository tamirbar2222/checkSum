package File_format;

import java.io.IOException;
import java.util.Set;
import GIS.gisElement;
import GIS.gisLayer;
 
/**
 * This class tests creating kml file from specific csv file 
 * @author aric and tal
 *
 */

public class Csv2Kml {

	public static void main(String[] args) throws IOException {

		Set<gisElement> gisArray =createGisLayer.createGisElementCollection("C:\\Users\\aric\\eclipse-workspace\\Ex2-4\\csv files\\WigleWifi_20171201110209.csv");	
		gisLayer gis=new gisLayer(gisArray); // create gisLayer from the set of gisElements that we created from the csv file
		gisLayer.createKml(gis, "aric and tal");
		
	}
}
