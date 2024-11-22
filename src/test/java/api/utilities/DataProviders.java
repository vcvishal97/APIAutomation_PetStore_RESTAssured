package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	private String path = ".\\testData\\userData.xlsx";
	private int rowCount, columnCount;
	private ExcelUtilities excel;
	private String[][] userData;
	private String[] usernames;
	
	public DataProviders() throws Exception {
		excel = new ExcelUtilities(path);
		rowCount = excel.getRowCount();
		columnCount = excel.getColumnCount();
	}
	
	@DataProvider(name = "userData")
	public String[][] getAllData() {
		userData = new String[rowCount][columnCount];
		for(int i = 1; i <= rowCount; i++)
			for(int j = 0; j < columnCount; j++)
				userData[i - 1][j] = excel.getCellValue(i, j);
		return userData;
	}
	
	@DataProvider(name = "usernames")
	public String[] getUsernames() {
		usernames = new String[rowCount];
		for(int i = 1; i <= rowCount; i++)
			usernames[i - 1] = excel.getCellValue(i, 1);
		return usernames;
	}
}
