package citi.lat_requirement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SwagLabs {

	@Test
	public void costlyItem() {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

		driver.get("https://www.saucedemo.com/");

		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

		List<WebElement> allProducts = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		int sizeProd = allProducts.size();
		List<Float> priceFloatList = new ArrayList<Float>();
		for (int i = 0; i < sizeProd - 1; i++) {
			// String[] splitPrice = allProducts.get(i).getText().split("$");
			// allPriceValue.add(splitPrice[0].replace("$", " "));
			String textPrice = allProducts.get(i).getText();
			String trimedStr = textPrice.replace("$", " ").trim();
			priceFloatList.add(Float.parseFloat(trimedStr));
		}
//		allProducts.stream().forEach(x -> {
//			String textPrice = x.getText();
//			String trimedStr = textPrice.replace("$", " ").trim();
//			priceFloatList.add(Float.parseFloat(trimedStr));
//		});
		Collections.sort(priceFloatList);
		Float higPrice = priceFloatList.get(priceFloatList.size()-1);
		String higPriceStr = higPrice.toString();
		System.out.println(higPriceStr);
		
		for(int j=0; j<=sizeProd-1; j++) {
			
		if(allProducts.get(j).getText().contains(higPriceStr)) {
			driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
		}
		}
		//driver.close();
		

	}

}
