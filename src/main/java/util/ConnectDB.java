package util;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.TreeMap;

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

    public static TreeMap<String,Object> readPageObjectFromDB() throws SQLException {
        TreeMap<String,Object> pageObject= new TreeMap<>();
        String query="select * from pageObject;";
        Statement stmt=connection.createStatement();
        ResultSet resultSet=stmt.executeQuery(query);
        while (resultSet.next()){
            String pageName=resultSet.getString(1);
            String elementName=resultSet.getString(2);
            String accessName=resultSet.getString(3);
            String accessType=resultSet.getString(4);
            LinkedHashMap<String,String> tempMap=new LinkedHashMap<>();
            tempMap.put("accessName",accessName);
            tempMap.put("accessType",accessType);
            LinkedHashMap<String,Object> elementTemp=new LinkedHashMap<>();
            elementTemp.put(elementName,tempMap);
            if(pageObject.containsKey(pageName)) {

            }else
                pageObject.put(pageName, elementTemp);
        }
        return pageObject;
    }
}
