package Com.Amazon;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class Flipkart extends ShoppingSite
{
	Float price;
	Flipkart(WebDriver driver,String flipkartSite) throws InterruptedException
	{
		driver.get(flipkartSite);
		driver.manage().window().maximize();
		System.out.println("Opened Filpkart website");
		Thread.sleep(2000);
		WebElement logInWindow= driver.findElement(By.className("_2doB4z"));
		if(logInWindow.isDisplayed())
		{
			if(logInWindow.isEnabled())
			{
				logInWindow.click();
			}	
		}
	}
	//return product price
	public Float getPrice()
	{
		return price;
	}
	//Search product
	public void searchProduct(WebDriver driver,String productName) throws InterruptedException
	{
		WebElement searchBox=driver.findElement(By.className("_3704LK"));
		if(searchBox.isDisplayed())
		{
			if(searchBox.isEnabled())
			{
				searchBox.sendKeys(productName);
				System.out.println("Entered "+productName+" in the searched bar" );
				driver.findElement(By.className("L0Z3Pu")).click();
				Thread.sleep(4000);
			}
			else
			{
				System.err.println("Search text box is not enabled");
			}
		} 
		else
		{
			   System.err.println("Search text box is not present in the webpage");  

		}
		System.out.println("Searched "+productName);
	}
	//Add product to the cart
	public void addToCart(WebDriver driver,String cartUrl) throws InterruptedException
	{
	    String UrlToClick=driver.findElement(By.xpath("//a[@class=\"_1fQZEK\"][1]")).getAttribute("href");
	    driver.findElement(By.xpath("(//div[@class=\"_13oc-S\"])[1]")).click();
	    String currentHandle= driver.getWindowHandle();
	    Thread.sleep(5000);
	    Set<String> handles=driver.getWindowHandles();
	    for (String actual: handles) {
	         if (!actual.equalsIgnoreCase(currentHandle)) {
	              driver.switchTo().window(actual);
	              driver.get(UrlToClick);
	         }
	     }
	     System.out.println("Price of product: "+driver.findElement(By.xpath("//div[@class=\"_30jeq3 _16Jk6d\"]")).getText());
	     driver.findElement(By.xpath("//button[@class=\"_2KpZ6l _2U9uOA _3v1-ww\"]")).click();
	     System.out.println("Added Product to the cart" );
	     Thread.sleep(2000);
	     driver.get(cartUrl);
	     Thread.sleep(5000);
	     String cost=driver.findElement(By.xpath("//span[@class=\"_2-ut7f _1WpvJ7\"]")).getText();
	     System.out.println("Price from cart:"+cost);
		 if(cost.contains("₹"))
		    cost=cost.substring(1);
	     cost=cost.replaceAll(",", "");
		 this.price=Float.parseFloat(cost);  
	     
	}

}
