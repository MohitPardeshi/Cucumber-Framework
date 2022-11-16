package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class ConnectDB {
    public static void getConnection() throws IOException, SQLException {
        LinkedHashMap<String,String> dbDetails=YAMLHelper.readYML(Constants.DBDETAILS);
        String url="jdbc:mysql://"+dbDetails.get("host")+":"+dbDetails.get("port")+"/"+dbDetails.get("databaseName");
        String userName=dbDetails.get("username");
        String password=dbDetails.get("password");
        Connection connection= DriverManager.getConnection(url,userName,password);
        if(connection!=null){
            System.out.println("Connected to "+dbDetails.get("databaseName"));
        }
    }
}
