package Com.Amazon;

import org.openqa.selenium.WebDriver;

abstract class ShoppingSite
{
	public abstract void searchProduct(WebDriver driver,String productName) throws InterruptedException;
	public abstract void addToCart(WebDriver driver,String cartUrl) throws InterruptedException;
}
