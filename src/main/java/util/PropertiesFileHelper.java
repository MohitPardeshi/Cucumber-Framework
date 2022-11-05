package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileHelper {

        public static Properties readProperty(String path) throws IOException {
            Properties properties=new Properties();
            FileInputStream fileInputStream = null;
            try {
                fileInputStream=new FileInputStream(path);
                properties.load(fileInputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            finally {
                fileInputStream.close();
            }
            return properties;
        }

}
