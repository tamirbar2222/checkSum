package GIS;

import Geom.Geom_element;
import Geom.Point3D;
import Coords.MyCoords;

/**
 * This class represent an element which have lat,lon,alt coordinates
 * and other data accociated with him like color,name... 
 * @author aric and tal
 *
 */

public class gisElement implements GIS_element {

	//Geom
	private double x; // Lat
	private double y; // Lon
	private double z; // Alt

	private MetaData meta; // the meta data of this element like color,time...

	public gisElement(double x,double y,double z,MetaData m) {

		this.x=x;
		this.y=y;
		this.z=z;
		this.meta=m;
	}

	/**
	 * This function exploit only the geomtric data of element and return it
	 * @return  Geom_element only the geometric data (lat,lon,alt)
	 */
	@Override
	public Geom_element getGeom() {

		Point3D p = new Point3D(x,y,z);
		return p;
	}

	/**
	 * This function exploit only the accociated data of element and return it
	 * @return Meta_data only the accociated data with the element except from geometric data
	 */
	@Override
	public Meta_data getData() {

		return meta;
	}
/**
 * This function changes the value of the point by adding a 3D vector (in meters)
 */
	@Override
	public void translate(Point3D vec) {
		MyCoords m = new MyCoords();
		m.add((Point3D) getGeom(), vec); //safe casting becuse we returned point3D in getGeom funct
	}
	
	/**
	 * This function return String of all the data of this gisElement
	 */
	public String toString () {

		String s="";
		s+="(lat,lon,alt): "+"["+x+","+y+","+z+"]"+" "+meta;
		return s;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
}