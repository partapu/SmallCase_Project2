package Com.Amazon;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class Amazon extends ShoppingSite
{
	Float price;
	Amazon(WebDriver driver,String amzoneSite) throws InterruptedException
	{
		driver.get(amzoneSite);
		System.out.println("Opened Amazon website");
		Thread.sleep(2000);	
	}
	//Return price
	public Float getPrice()
	{
		return price;
	}
    //Search Product
	public void searchProduct(WebDriver driver,String productName) throws InterruptedException
	{
		WebElement searchBox= driver.findElement(By.id("twotabsearchtextbox"));
		if(searchBox.isDisplayed())
		{
			if(searchBox.isEnabled())
			{
				searchBox.sendKeys(productName);
				System.out.println("Entered "+productName+" in the searched bar" );
				driver.findElement(By.id("nav-search-submit-button")).click();
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
		WebElement firstItem=driver.findElements(By.xpath("//h2[@class=\"a-size-mini a-spacing-none a-color-base s-line-clamp-2\"]/a")).get(0);
		String UrlToClick=firstItem.getAttribute("href");
		firstItem.click();
		String currentHandle= driver.getWindowHandle();
		Thread.sleep(4000);
	    Set<String> handles=driver.getWindowHandles();
	    for (String actual: handles) {
	         if (!actual.equalsIgnoreCase(currentHandle)) {
	             //Switch to the opened tab
	              driver.switchTo().window(actual);
	             //opening the URL saved.
	              driver.get(UrlToClick);
	         }
	     }
	    Thread.sleep(4000);	    
	    System.out.println("Price of product:"+driver.findElement(By.xpath("//span[@class= \"a-price a-text-price a-size-medium apexPriceToPay\"]")).getText());
	   	driver.findElement(By.id("add-to-cart-button")).click();
	   	System.out.println("Added Product to the cart" );
	   	Thread.sleep(4000);
	   	String cost=driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
	   	System.out.println("Price from cart:"+cost);
	   	if(cost.contains("₹"))
	   		cost=cost.substring(1);
	   	this.price=Float.parseFloat(cost.replaceAll(",", "")); 
	}	
}
