package api.utilities;

import java.io.File;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	private File file;
	private int sheetNum = 0;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	private DataFormatter formatter;
	
	public ExcelUtilities(String path) throws Exception {
		file = new File(path);
		if(!file.exists())
			throw new Exception("userData.xlsx file not found.");
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheetAt(sheetNum);
		formatter = new DataFormatter();
	}
	
	public int getRowCount() {
		return sheet.getLastRowNum();
	}
	
	public int getColumnCount() {
		return sheet.getRow(0).getLastCellNum();
	}
	
	public String getCellValue(int rowNum, int columnNum) {
		row = sheet.getRow(rowNum);
		cell = row.getCell(columnNum);
		return formatter.formatCellValue(cell);
	}
}
