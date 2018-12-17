package File_format;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import GIS.gisLayer;
import GIS.gisProject;

/**
 * This class tests creating one kml file from multi csv files that stored in some directory  
 * by scan recursive all the csv files that stored in the directory and sub directories 
 * @author aric and tal
 */

public class TestKML {

	public static void main(String[] args) throws IOException {
		//path format is with two slash ("\\")
		File path=new File("D:\\מדעי המחשב\\שנה ב\\סמסטר א\\מונחה עצמים\\מטלה 3\\Ex2\\Ex2\\data"); 
		
		gisProject project =new gisProject(new HashSet<gisLayer>()); // empty project
		
		MultiCSV.FolderToGisproject(path,project); // insert into project all the layers (csv Files)
		gisProject.createKml(project,"ourProject"); // creating the kml File with the name "OurProject"
		
		
	}
}

