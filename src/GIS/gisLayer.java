package GIS;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import File_format.writeKml;

/**
 * This class represent a gisLayer that is a collection of gisElements
 * @author aric and tal
 *
 */
public class gisLayer implements GIS_layer {

	private Set<gisElement> layer;
	private long date;//represent the date of the csv file
	private String name;//represent the name of the csv file

	public void setDate(long date) {
		this.date = date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public gisLayer(Set<gisElement> arr) {

		this.layer=arr;
	}

	public Set<gisElement> getSet() {
		return layer;
	}

	@Override
	public boolean add(GIS_element e) {

		return layer.add((gisElement) e);
	}
	

	@Override
	//the metaData of the layer is the name and the lastModifyData
	/**
	 *This function exploit the metaData of this gisLayer
	 * the metaData of gisLayer is the name and the last modify data
	 *@return the MetaData of this gisLayer name and date
	 */
	public Meta_data get_Meta_data() {

		String date = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date (this.date *1000));
		//System.out.println(date);
		MetaData meta=new MetaData("", "", "", "the time of last modify associated with "+name+" is "+date, "");

		return meta;
	}
/**
 * 
 * @param a is the gisLayer we want to create a kml from
 * @param name is the name of the kml file we want to create
 * @throws IOException
 */
	public static void createKml(gisLayer a,String name) throws IOException {//create kml from speceific file(layer)

		writeKml kml=new writeKml(name + ".kml");	
		kml.runLayerFile(a); 
	}
	/**
	 * This function print all the gisElements of this layer
	 * @return string that represent the gis layer(all the gisElements data)
	 */
	@Override
	public String toString() {

		Iterator<gisElement> el=layer.iterator();
		String s="";
		while(el.hasNext()) {

			gisElement pointer=el.next();
			s+=pointer.toString();
			s+=",";
		}
		return s;
	}
	
	@Override
	public int size() {
		
		return layer.size();
	}
	

	@Override
	public boolean isEmpty() {

		return layer.size()==0;
		
	}


	//unimplement functions
	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
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
	public Iterator<GIS_element> iterator() {
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
