package fr.emile.mavenone.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class DatabaseConnection {
	private DatabaseConnection() {
	}


	//---------------processing methode ---------------------
public static Connection  getConnection() throws Exception {
	ResourceBundle  myResource = ResourceBundle.getBundle("MavenOne"); // retreive data from Dbase.properties
	
	String dbUser = myResource.getString("user");
	String dbPass= myResource.getString("password");
	String dbUrl = myResource.getString("url");
	
	
	Connection myConection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
	
	return myConection ; 
}

}
