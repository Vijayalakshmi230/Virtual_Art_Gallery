package util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    public static String getPropertyString(String fileName) {
        try {
            Properties props = new Properties();
            InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
            
            if (input == null) {
                throw new RuntimeException("Unable to find " + fileName);
            }

            props.load(input);

            String host = props.getProperty("host");
            String port = props.getProperty("port");
            String dbname = props.getProperty("dbname");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            if (host == null || port == null || dbname == null || username == null || password == null) {
                throw new RuntimeException("Some DB properties are missing!");
            }

            return "jdbc:mysql://" + host + ":" + port + "/" + dbname +
                    "?user=" + username + "&password=" + password;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
