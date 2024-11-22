package BrokenlinksEngine;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automationCore.baseClass;

public class Del9 extends baseClass{
	
	WebDriver driver;
	XSSFSheet  worksheet;
	List<String> urlList = new ArrayList<String>();	
	
	@BeforeMethod
	public void initialiseBrowser() throws IOException {
		
		driver = browserInitialiser("Chrome");	
		
		workbook = loadWorkbook();
		worksheet = workbook.getSheet("del9");	
		
		
		
	}
	
	
	
	
	//Collect links on page
	
	@Test
	public void getLinks() {

		System.out.println("Inside getLinks");
		int rows = worksheet.getLastRowNum();
		String url ;
		
		for(int i = 0 ; i<=rows ; i++) {
			
			XSSFRow  currentRow =  worksheet.getRow(i);
			
			XSSFCell cell = currentRow.getCell(0);
			
			url = cell.getStringCellValue();
			
			driver.get(url);
			
			System.out.println("Current URL ---------------> "  + url);			
		
			
        
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("no of Links are : "+ links.size());

		

		for(WebElement e : links) {
			String  link = e.getAttribute("href");
			
			
			System.out.println(" Link   " + link);
			checkBrokenURL(link);
		}

		}

	}
	
	
	
		//Check link if it is broken
		public void checkBrokenURL(String urlString) {

			   System.out.println("Inside checkBrokenURL : " + urlString);
			   
			   

			try {
				URL url =  new URL(urlString);
				HttpURLConnection httpURLConnection =  (HttpURLConnection) url.openConnection();
				httpURLConnection.setConnectTimeout(5000);
				httpURLConnection.connect();

				if(httpURLConnection.getResponseCode()  >= 400) {
					System.out.println(urlString + "  ------> " + httpURLConnection.getResponseMessage() +  "is a broken link");
					urlList.add(urlString);
				}

				else {
					System.out.println(urlString + "--> " + httpURLConnection.getResponseMessage());
				}
				
				httpURLConnection.disconnect();

			} catch (Exception e) {
				// TODO: handle exception
			}
			
		
		}
	
	
	@AfterClass
	public void browserClosure() {
		
		driver.quit();
	}
	
	

}
