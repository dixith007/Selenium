package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BasePage;
import pageObjects.HomePage;
import pageObjects.OrderFormDelivery;
import pageObjects.OrderFormPersInfo;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

public class OrderCompleteTest extends BasePage {

	public OrderCompleteTest() throws IOException {
		super();
		
	}
	
	@BeforeTest
	public void setup() throws IOException, InterruptedException {
		driver = getDriver();
		driver.get(getUrl());
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		driver = null;
	}
	
	@Test
	public void endToEndTest() throws InterruptedException {
		
		 // Initialize home page
        HomePage homepage = new HomePage(driver);
        
        // Handle cookie notification if present
        try {
            homepage.getCookie().click();
        } catch (Exception e) {
            System.out.println("Cookie notification not present.");
        }
        
        // Click on the "TEST STORE" link
        homepage.getTestStoreLink().click();
		
		// TEST STORE
        ShopHomepage shophome =  new ShopHomepage(driver);
		shophome.getProdOne().click();
		
		//PRODUCT PAGE
		ShopProductPage shopProd  =  new ShopProductPage(driver);
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		shopProd.getQuantIncrease().click();
		shopProd.getAddToCartBtn().click();
		
		Thread.sleep(5000);
		
		ShopContentPanel cPanel =  new ShopContentPanel(driver);
		cPanel.getCheckoutBtn().click();
		
		//CART
		ShoppingCart cart = new ShoppingCart(driver);
		cart.getHavePromo().click();
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		
		Thread.sleep(5000);
		cart.getProceedCheckoutBtn().click();
		
		//PERSONAL INFO
		OrderFormPersInfo perslInfo = new OrderFormPersInfo(driver);
		perslInfo.getGenderMr().click();
		perslInfo.getFirstNameField().sendKeys("John");
		perslInfo.getLastnameField().sendKeys("Smith");
		perslInfo.getEmailField().sendKeys("JohnSmith@gmail.com");
		perslInfo.getTermsConditionsCheckbox().click();
		perslInfo.getContinueBtn().click();
		
		//PERSONAL INFO2 
		OrderFormDelivery orderDelivery = new OrderFormDelivery(driver);
		orderDelivery.getAddressField().sendKeys("123 Main Street");
		orderDelivery.getCityField().sendKeys("Houston");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getPostcodeField().sendKeys("77021");
		orderDelivery.getContinueBtn().click();
		
		
		
		
	}

}
