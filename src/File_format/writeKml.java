package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import GIS.MetaData;
import GIS.gisElement;
import GIS.gisLayer;
import GIS.gisProject;


/**
 * This class is using the readCsv class and write kml file according to the information of each csv file 
 * @author aric and tal
 */
public class writeKml {

	//private static final String ICON_URL = "http://campanalbero.net/icon/";
	private final BufferedWriter writer;

	public writeKml(String output) throws IOException {

		writer = new BufferedWriter(new FileWriter(output));
	}

	/**
	 * This fumction receives some information about the csv file and 
	 * write to kml file  
	 * @param name the wifi name
	 * @param des the type of wifi
	 * @param capab authMode
	 * @param Timestamp time in miliseconds
	 * @param time the date in format yyyy/mm/dd
	 * @param latitude
	 * @param longitude
	 * @param alt
	 * @param color according to the RSSI 
	 * @throws IOException
	 */
	private void writeIcon(String name, String des,String capab,long Timestamp,String time, 
			double latitude,double longitude, double alt, String color) throws IOException {
		String longTime = "" + time;
		longTime = longTime.replace(" ", "T");
		longTime+="Z";
		//time = time.replace(" ", "T"); // added
		//time+="Z";
		writer.write("<Placemark>\n" + 
				"<name><![CDATA["+name+"]]></name>\n" + "<TimeStamp><when>" + longTime + "</when></TimeStamp>" +
				"<description><![CDATA[BSSID: <b>"+des+"</b><br/>Capabilities: <b>"
				+capab+"</b><br/>Timestamp: <b>"+Timestamp+"</b><br/>Date: <b>"
				+time+"</b>]]></description><styleUrl>"+color+"</styleUrl>\n" + 
				"<Point>\n" + 
				"<coordinates>"+latitude+","+longitude+","+alt+"</coordinates></Point>\n" + 
				"</Placemark>\n" + 
				"");
	}

	private void writeStart() throws IOException {

		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		writer.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		writer.write("<Document>\n");
		writer.write("<Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style>\r\n" + 
				"\r\n" + 
				"<Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style>\r\n" + 
				"\r\n" + 
				"<Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>\n" +"\n");
	}

	private void writeEnd() throws IOException {
		writer.write("</Document>\n</kml>\n");
	}

	//create kml file from gis layer
	/**
	 * This function  create a kml file by given a gisLayer
	 * @param la is the gisLayer we want to write into kml file
	 * @throws IOException
	 */
	public void runLayerFile(gisLayer la) throws IOException {
		try { 
			writeStart();

			for(gisElement temp : la.getSet()) {

				MetaData data=null;
				if (temp.getData() instanceof MetaData) {

					data=(MetaData) temp.getData();
				}

				long timeStamp=data.getUTC();
				writeIcon(data.getName(), data.getMac(), data.getAuthMode(),timeStamp,data.getTime(),
						temp.getY(), temp.getX(), temp.getZ(), convertToColor(data.getSignalPow()));
			}

			writeEnd();
			writer.flush();
		}

		catch (Exception e) {
			throw new RuntimeException(e);
		} 
		finally {

			if (writer != null) {

				writer.close();			
			}
		}
	}

	//create one kml file from folder of csv files
	/**
	 * This function create a kml file from a gisProject by exploit all the
	 * data from each gisLayer
	 * @param la is the gisProject we want to create kml file from  
	 * @throws IOException
	 */
	public void runProjectFile(gisProject la) throws IOException {
		try {
			writeStart();

			for(gisLayer layerTemp : la.getAllLayers()) {

				for(gisElement temp : layerTemp.getSet()) {

					MetaData data=null;
					if (temp.getData() instanceof MetaData) {

						data=(MetaData) temp.getData();
					}

					long timeStamp=data.getUTC();
					writeIcon(data.getName(), data.getMac(), data.getAuthMode(),timeStamp,data.getTime(),
							temp.getY(), temp.getX(), temp.getZ(), convertToColor(data.getSignalPow()));
				}
			}
			writeEnd();
			writer.flush();
		}

		catch (Exception e) {
			throw new RuntimeException(e);
		} 
		finally {

			if (writer != null) {

				writer.close();			
			}
		}
	}

//	public static void runProject(gisProject la) throws IOException {
//
//		int i=1;
//		for(gisLayer layerTemp : la.getAllLayers()) {
//
//			gisLayer.createKml(layerTemp,i+"");
//			i++;
//		}
//	}

	/**
	 * This function decide the color of specific gisElement accordnig to the RSSI value 
	 * @param pow is the RSSI value 
	 * @return the color of element, can be: yellow,green or red
	 */
	//by given parsed[5] check which color it should be
	public String convertToColor (String pow) {

		int intColor = Integer.parseInt(pow);
		String color="";

		if (intColor> -80) {

			color="#green";
		}
		else if(intColor<= -80 && intColor>=-100) {

			color ="#yellow";
		}
		else {

			color="#red";
		}

		return color;
	}
}
