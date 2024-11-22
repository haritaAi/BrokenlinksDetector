package automationCore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class baseClass {
	public WebDriver driver;
	public XSSFWorkbook workbook;
	
	public WebDriver browserInitialiser(String browser) throws IOException {	
		
		
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\sesame\\eclipse-workspace\\BrokenlinksDetector\\src\\main\\resources\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		return driver;
	}
	
	public XSSFWorkbook  loadWorkbook() throws IOException {
		
		FileInputStream xlFile = new FileInputStream("C:\\Users\\sesame\\eclipse-workspace\\BrokenlinksDetector\\src\\main\\resources\\TestData\\website-links.xlsx");
		workbook =  new XSSFWorkbook(xlFile);
		return workbook;
	}

}
