package GIS;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import File_format.writeKml;

/**
 * This class represenr collection of gisLayers 
 * @author aric and tal
 *
 */
public class gisProject implements GIS_project {

	private Set<gisLayer> allLayers;
	
	public gisProject(Set<gisLayer> a) { // constructor
		
		this.allLayers=a;
	}
	 
	/**
	 * This function create a kml file from gisProject
	 * @param a is the gisProject we want te create  kml from
	 * @param name is the name we want to call the kml file
	 * @throws IOException
	 */
	public static void createKml(gisProject a,String name) throws IOException {
		
		writeKml kml=new writeKml(name + ".kml");	
		 kml.runProjectFile(a);
		}
	
	public Set<gisLayer> getAllLayers() {
		
		return allLayers;
	}

	@Override
	public boolean add(GIS_layer e) {
		
		return allLayers.add((gisLayer) e);
	}
	/**
	 * This function create a MetaData by using the metaData of the first layer and return it 
	 * @return the metaData of the first layer of the project 
	 */
	@Override
	public Meta_data get_Meta_data() {
		
		//return the file name and lastModify of the first file 
		if (allLayers.iterator().hasNext()) {
			
			return allLayers.iterator().next().get_Meta_data();
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		
		Iterator<gisLayer> el=allLayers.iterator();
		String s="";
		int i=1;
		while(el.hasNext()) {
			s+= "Layer number "+i+": ";
			gisLayer pointer=el.next();
			s+=pointer.toString();
			s+=",";
			i++;
		}
		return s;
	}
	

	@Override
	public boolean isEmpty() {
		
		return allLayers.size()==0;

	}
	
	@Override
	public int size() {
		return  allLayers.size();
	}
	
	//unimplement functions
	
	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
}