package GIS;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import File_format.MultiCSV;
import File_format.createGisLayer;
import Geom.Point3D;
import junit.framework.Assert;

class JunitProject {

	private File path=new File("D:\\מדעי המחשב\\שנה ב\\סמסטר א\\מונחה עצמים\\מטלה 3\\Ex2\\Ex2\\data"); 
	String pathName="D:\\מדעי המחשב\\שנה ב\\סמסטר א\\מונחה עצמים\\מטלה 3\\Ex2\\Ex2\\data\\WigleWifi_20171201110209.csv";//the location of the file

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*@Test
	void test() {
		fail("Not yet implemented");
	}*/

	@Test
	public void projectSetTest() throws IOException {

		//check 1
		Set<gisLayer> toProject = new HashSet<gisLayer>();
		gisProject project =new gisProject(toProject);
		
		MultiCSV.FolderToGisproject(path,project);//insert into project all the csv files in the folder

		int expected=2;
		int current=project.size();
		Assert.assertEquals(expected, current);
		
		//check 2
		//count the lines(gisElements)
		gisProject.createKml(project,"ourProject");
		expected=344;//159+185=344
		Set<gisLayer> ourSet=project.getAllLayers();// get the collection of the project
	     
		current=0;
		for(gisLayer layerTemp : ourSet) {

			for(gisElement temp : layerTemp.getSet()) {

				current++;
			
			}
		} 
		Assert.assertEquals(expected, current);
	
	}
	
	@Test
	public void layerTest() throws IOException {
		
		int expected=159;//the number of the gisElements in the file located pathName
		Set<gisElement> toProject = createGisLayer.createGisElementCollection(pathName);
		int current=toProject.size();
		Assert.assertEquals(expected, current);
		
	}
	
	@Test 
	public void getGeomTest() {
		
		MetaData meta=new MetaData("mac","name","auth","time","pow");
		gisElement element=new gisElement(32.3,35.7,600,meta);
		Point3D current=(Point3D) element.getGeom();//beacuse we know it return a point3D
		Point3D expected =new Point3D(32.3,35.7,600);
		
		Assert.assertEquals(current.x(), expected.x());
		Assert.assertEquals(current.y(), expected.y());
		Assert.assertEquals(current.z(), expected.z());
	}
	
	@Test
	public void getUtcTest() {
		
		MetaData meta=new MetaData("mac","name","auth","2018-11-30 15:00:35","pow");
		long current=meta.getUTC();
		long expected=1543590035;
		Assert.assertEquals(expected, current);
	}
	
	
}