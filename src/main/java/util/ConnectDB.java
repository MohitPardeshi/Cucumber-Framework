package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class ConnectDB {
    private static Connection connection=null;
    public static void getConnection() throws IOException, SQLException {
        LinkedHashMap<String,String> dbDetails=YAMLHelper.readYML(Constants.DBDETAILS);
        String url="jdbc:mysql://"+dbDetails.get("host")+":"+String.valueOf(dbDetails.get("port"))+"/"+dbDetails.get("databaseName");
        System.out.println(url);
        //jdbc:mysql://localhost:3306/test1
        //jdbc:mysql://localhost:3306/testDB
        String userName=dbDetails.get("userName");
        String password=dbDetails.get("password");
        System.out.println(userName);
        System.out.println(password);
        connection= DriverManager.getConnection(url,userName.trim(),password.trim());
        if(connection!=null){
            System.out.println("Connected to "+dbDetails.get("databaseName"));
        }
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
