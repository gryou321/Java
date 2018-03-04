import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	private static String path;
	
	public Excel(String p) {
		path = p;
	}
	
	public void read() {
		try {
			FileInputStream fis = new FileInputStream(path);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis); 
			XSSFSheet s = wb.getSheet("Sheet1");
			
			for(int i = 0; i < s.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = s.getRow(i);
				for(int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
					System.out.printf("%-20s", row.getCell(j));
				}
				
				System.out.println();
			}
			
			wb.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void write() {
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet s = wb.getSheetAt(0); 
			XSSFRow row = s.createRow(s.getPhysicalNumberOfRows());
			
			CellStyle cs = wb.createCellStyle();
			cs.setAlignment(HorizontalAlignment.RIGHT);
			cs.setFillForegroundColor(IndexedColors.GOLD.index);
			cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			for(int i = 0; i < s.getRow(1).getPhysicalNumberOfCells(); i++) {
				XSSFCell cell = row.createCell(i);
				
				cell.setCellValue("new cell");
				cell.setCellStyle(cs);
			}
			
			wb.write(new FileOutputStream("C:/Users/userpc/Desktop/filePractice/zzz.xlsx"));
			
			wb.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
