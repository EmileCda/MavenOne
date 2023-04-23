package fr.emile.mavenone.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {
	
	private Utils() {};
	
// display debug information with filename and line number
	
	public static void trace(String msg) {
		Throwable sys = new Throwable();
		
		System.out.printf("=>[%s]:%d : %s\n",sys.getStackTrace()[1].getFileName(),
				sys.getStackTrace()[1].getLineNumber(),msg);
	

	}
	
	
// convert SQL date to java Date 	
	public  static Date toJavaDate(java.sql.Date sqlDate) {
		if (sqlDate != null)
				return new Date(sqlDate.getTime());
		return null ;
	}; 
	
// convert java Date to SQL date  	

	public static java.sql.Date toSqlDate(Date myDate) {
		if (myDate != null)
			return new java.sql.Date(myDate.getTime());
		return null ;
	}; 

// create date from a string format 	
	public static Date string2Date(String dateString,String dateFormat) throws Exception{
		return new SimpleDateFormat(dateFormat).parse(dateString);

	}	

// create string  from date with format 	
	
	public static String date2String(Date date,String dateFormat) throws Exception{
		return new SimpleDateFormat(dateFormat).format(date);

	}	

//-----------------------------------------------------------------------
	public static String firstToUpper(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();

	}	

	//-----------------------------------------------------------------------
	// fonction random qui renvoie une fonction entre minValue et maxValue
	// attention dans le cas de l'utilisation pour indexer un tableau (maxIndex = length)
    public static int randInt(int minValue, int maxValue) {
        // minvalue er maxValue inclus
        return   (int)(Math.random() * (maxValue + 1-minValue) + minValue );
    }
    
    
    public static List<String> JsonFile2List(String filename,String key){
    	
    	return null ; 
    }
    	

}
