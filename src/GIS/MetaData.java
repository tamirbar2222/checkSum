package GIS;

import Geom.Point3D;
/**
 * This class represent the accociated data of gisElement like name color and more...
 * @author aric and tal
 *
 */
public class MetaData implements Meta_data {

	private String mac;
	private String name;
	private String authMode;
	private String time;
	private String signalPow;

	public MetaData(String mac,String name,String authMode,String time,String signalPow) { // constructor

		this.mac=mac;
		this.name=name;
		this.authMode=authMode;
		this.time=time; 
		this.signalPow=signalPow;
	}
	/**
	 * 	 This function returns the Universal Time Clock associated with this data; 
	 *@return the Universal Time Clock associated with this data
	 */
	@Override
	public long getUTC() { // if the date Format is not valid return -1;
		long epoch;
		try {
			epoch = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime() / 1000;
			return epoch+7200; 
		}
		catch(Exception e){
			return -1;
		}			
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * This function create a String that represent the MetaData of the gisElement
	 * @return String represent the metaData
	 */
	public String toString() {

		String s="";

		if (mac.length()>0 && name.length()>0 && authMode.length()>0 && signalPow.length()>0) {

			s="name : "+name+" mac: "+mac+" authMode: "+authMode+" signalPower "+signalPow+" associated with time: "+time;
			return s;
		}else {

			s+=name+time;
			return s;
		}

	}
	//geter
	public String getMac() {
		return mac;
	}

	public String getName() {
		return name;
	}

	public String getAuthMode() {
		return authMode;
	}

	public String getTime() {
		return time;
	}

	public String getSignalPow() {
		return signalPow;
	}

}