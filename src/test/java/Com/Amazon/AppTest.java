package Com.Amazon;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
	public static void main(String[] args)  
	{
		String product      = "APPLE iPhone 13 Mini (Midnight, 256 GB)";
		String flipkartSite = "https://flipkart.com";
		String amzoneSite   = "https://www.amazon.in/";
		String cartUrl      = "https://www.flipkart.com/viewcart?otracker=PP_GoToCart";
		float flipkartPrice;
		float amazonPrice;
		try {
			WebDriverManager.chromedriver().setup();
			WebDriver driver=new ChromeDriver();
			
	        Flipkart fk=new Flipkart(driver,flipkartSite);
	        fk.searchProduct(driver, product);
	        fk.addToCart(driver, cartUrl);
	        System.out.println("");
			Amazon am=new Amazon(driver,amzoneSite);
			am.searchProduct(driver, product);
			am.addToCart(driver, cartUrl);
			flipkartPrice=fk.getPrice();
			amazonPrice=am.getPrice();
			System.out.println("");
			if(flipkartPrice<amazonPrice)
			{
				System.out.println(product+" Price in Flipkart is less than amazon. Flipkart:"+flipkartPrice+",Amazon:"+amazonPrice); 
			}
			else if(amazonPrice<flipkartPrice)
			{
				System.out.println(product+" Price in Amazon is less than Flipkart. Amazon:"+amazonPrice+",Flipkart:"+flipkartPrice); 
			}
			else
			{
				System.out.println(product+" Prices are same on Amazon and Flipkart. Amazon:"+amazonPrice+",Flipkart:"+flipkartPrice); 
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}
}