package api.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

	private Properties properties;
	private String path = "./src/test/resources/Routes.properties";
	
	public PropertyReader() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(path));
		} catch (Exception e) {
			throw new RuntimeException("Routes.properties file not found.");
		}
	}
	
	public String readProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}
}
