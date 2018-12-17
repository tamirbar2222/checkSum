package File_format;
 
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Set;
import GIS.gisElement;
import GIS.gisLayer;
import GIS.gisProject;

/**
 * This class exploits csv files from folder and create gisProject from all gisLayers(csv files)   
 * @author aric and tal
 */

public class MultiCSV {

	/**
	 * recursive funciton that scan in the dir for subDirs and send sole folder to the createProjectFromFolder
	 * @param dir is the location of directory 
	 * @param project is a gisProject  
	 * @throws IOException
	 */
	
public static void FolderToGisproject(File dir,gisProject project) throws IOException {
		File[] subDirs = dir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		}
				);
		System.out.println("Directory of : " + dir.getAbsolutePath());
		createProjectFromFolder(dir,project);
		for(File folder: subDirs) {
			FolderToGisproject(folder,project);
		} 
	}

	/**
	 * This function recive folder and project and add the gisLayers associated with the csv 
	 *  files in the folder to the project
	 * @param dir is the current folder that we are looking for csv files  
	 * @param project is the project that we want to add to
	 * @throws IOException
	 */
	protected static void createProjectFromFolder(File dir,gisProject project) throws IOException {

		File [] files = dir.listFiles();
		for(File file: files) {
			if(file.getAbsolutePath().endsWith(".csv")) {
				System.out.println("file name : "+file.getName());
				Set<gisElement> currentGisLayer = createGisLayer.createGisElementCollection(file.getAbsolutePath());	//insert to Set all the gisElements

				gisLayer layer=new gisLayer(currentGisLayer); // create a gisLayer object from all the gis elements 
				layer.setDate(file.lastModified()/1000);
				layer.setName(file.getName());
				pushToProject(project,layer);		
			}
		}
	}

	/**
	 * This funciton get layer and project and add the layer to the project
	 * @param project
	 * @param layer is the layer we want to add to this project
	 */
	private static void pushToProject(gisProject project,gisLayer layer) {
		project.add(layer);
	}

	
//	private Set<gisLayer> toProject = new HashSet<gisLayer>();
//	private gisProject project =new gisProject(toProject);

	
/*	void listFolder(File dir) throws IOException {
		File[] subDirs = dir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		}
				);
		System.out.println("Directory of : " + dir.getAbsolutePath());
		listFile(dir);
		for(File folder: subDirs) {
			listFolder(folder);
		}
	}
	
	private void listFile(File dir) throws IOException {
		
		File [] files = dir.listFiles();
				for(File file: files) {
			if(file.getAbsolutePath().endsWith(".csv")) {
				System.out.println(file.getName()); // Or get the full path : file.getAbsolutePath()
				String name=file.getName().substring(0, file.getName().length()-4); // for cancel .csv.kml
				
				//Creating kml file with data
//				CsvToKml c2=new CsvToKml(file.getAbsolutePath(), file.getName()+".kml"); 
//				c2.run();
				
				//create createGisElem object with the file path in order to start the function
				//createGisElem csvToGis=new createGisElem(file.getAbsolutePath()); 
			//	ArrayList<gisElement> gisArray =csvToGis.createGisElementCollection();	
				
				Set<gisElement> currentGisLayer = createGisElem.createGisElementCollection(file.getAbsolutePath());	//insert to Set all the gisElements

				//need to change "a" to csv file
				gisLayer a=new gisLayer(currentGisLayer);//create a gisLayer object from all the gis elements 
				
         //		a.createKml(a,name);
				
				//gisLayer.createKml(a, name);
          
				System.out.println(project.add(a));
				
			}
			
		}
		System.out.println(project.getAllLayers().size());
		System.out.println("print project   "+project.getAllLayers().toString());
		gisProject.createKml(project);
		
		
	}*/

}
