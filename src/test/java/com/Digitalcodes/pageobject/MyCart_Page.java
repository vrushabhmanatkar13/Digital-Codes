package com.Digitalcodes.pageobject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.Digitalcodes.utilities.Baseclass;

public class MyCart_Page extends Baseclass{
	
	WebDriver driver;
	public MyCart_Page() {
		this.driver=Baseclass.driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h4[@class='subtitle-1 font-weight-bold']")
	private WebElement titleName;
	
	@FindBy(xpath = "//input[@type='number']")
	private WebElement quantity;
	
	@FindBy(xpath = "//span[contains(@class,'v-size--default white--text')]/span")
	private WebElement subscription;
	
	@FindBy(xpath = "//span[@class='subheading font-weight-bold']" )
	private List<WebElement> price;
	
	@FindBy(xpath = "//span[@class='font-weight-bold']")
	private List<WebElement> subtotal;
	
	@FindBy(xpath = "//p[@class='title pt-4']")
	private WebElement subtotaltext;
	
	@FindBy(xpath = "(//span[@class='font-weight-bold'])[1]" )
	private WebElement subtotleprice;
	
	@FindBy(xpath = "(//div[@class='primary--text col'])[2]")
	private WebElement subtotaltext1;
	
	@FindBy(xpath = "//span[@class='title font-weight-thin']")
	private WebElement items;
	
	@FindBy(xpath = "(//span[contains(text(),'Proceed to Checkout')])[1]")
	private WebElement proceedtocheckout;
	
	@FindBy(xpath = "//i[text()='add']")
	private WebElement plusbutton;
	
	@FindBy(xpath = "//i[text()='remove']" )
	private WebElement minusbutton;
	
	//Shopping Cart
	
	@FindBy(xpath = "(//div[@class='product-item-details']/strong)[2]")
	private WebElement productname;
	
	@FindBy(xpath = "//td[@class=\"col price\"]//span[@class='price']" )
	private WebElement shoppingcart_price;
	
	@FindBy(xpath = "//td[@class=\"col subtotal\"]//span[@class='price']")
	private WebElement shoppingcart_subtotal1;
	
	@FindBy(xpath = "//span[@data-th='Subtotal']" )
	private WebElement shoppingcart_subtotal;
	
	@FindBy(xpath = "//td[@data-th='Order Total']/strong")
	private WebElement shoppingcart_ordertotal;
	
	@FindBy(xpath = "//td[@data-th='Discount']/span")
	private WebElement shoppingcart_discounttotal;
	
	@FindBy(id = "block-discount-heading" )
	private WebElement shoppingcart_discount;
	
	@FindBy(id="coupon_code")
	private WebElement shoppingcart_discountinput;
	
	@FindBy(xpath = "//span[@class='amcoupons-message']")
	private WebElement shoppingcart_discountapplyed;
	
	@FindBy(xpath = "//li/button[@title='Proceed to Checkout']" )
	private WebElement shoppingcart_proceedtocheckout;
	
	@FindBy(xpath = "//button[text()='Accept']")
	private WebElement accept;
	
	@FindBy(xpath = "//a[@class='right font-weight-bold remove_link error--text']")
	private WebElement remove;
	
	@FindBy(xpath = "//button[@class='white--text v-btn v-btn--contained theme--light v-size--default red darken-1']")
     private WebElement removebutton;
	
	@FindBy(xpath = "(//div[@class='v-card__text text-center']//p)[1]")
	private WebElement emptycarttext;
	
	//Fill Address form and click Place Order now
	
	@FindBy(xpath = "(//input[@name='street[0]'])[2]")
	private WebElement streetaddress;
	
	@FindBy(xpath = "(//select[@name='country_id'])[2]")
	private WebElement selectcountry;
	
	@FindBy(xpath = "(//select[@name='region_id'])[2]")
	private WebElement selectregion;
	
	@FindBy(xpath = "(//input[@name='city'])[2]")
	private WebElement cityinput;
	
	@FindBy(xpath = "(//input[@name='postcode'])[2]")
	private WebElement postcodeinput;
	
	@FindBy(xpath = "(//input[@name='telephone'])[2]")
	private WebElement phonenumberinput;
	
	@FindBy(xpath = "//button[@class='action action-update']")
	private WebElement update;
	
	@FindBy(id = "terms_and_conditions_checkbox")
	private WebElement termscheckbox;
	
	@FindBy(id="opcheckout-button-place-order")
	private WebElement placeorder;
	
	@FindBy(xpath = "//h1[@class='page-title']/span")
	private WebElement thankyoutext;
	
	public String getTitleName(String titlename) {
		List<String> finaltitlename=new ArrayList<String>();
		for (String webElement : getText(titleName).split(" ")) {
		       for (String webElement2 : titlename.split(" ")) {
				if (webElement.equalsIgnoreCase(webElement2)) {
					finaltitlename.add(webElement);
				}
			}
			
		}
		
		return String.join(" ", finaltitlename);
	}
	
	public String getQutentiy() {
		return getText(quantity);
	}
	
	public boolean subscriptionIsSelected(String text) {
		if (getText(subscription).equalsIgnoreCase(text)) {
			return subscription.isSelected();
		}
		else {
			return false;
		}
	}
	
	
	public String getSubscribtionPrice() {
		return getText(price.get(0));
	}
	public String getTotalprice() {
		return getText(price.get(1));
	}
	
	public Double getTotalPrice() {
		return Double.parseDouble(getText(price.get(1)).replace("$", ""));
	}
	
	public Double getTotalPriceAsQty(int qty) {
		Double singleprice=Double.parseDouble(getText(price.get(0)).replace("$", ""));
		return singleprice * qty;
	}
	
	public boolean verifyPrice(String subtotleprice) {
		Set<String> Allprice = new HashSet<String>();
		Allprice.add(price.get(0).getText());
		Allprice.add(price.get(1).getText());
		Allprice.add(subtotal.get(0).getText());
		Allprice.add(subtotal.get(1).getText());
		
		if (Allprice.contains(subtotleprice)) {
			return true;
			
		}
		else {
			return false;
		}
	}
	
	
	public boolean veriySubtotalItemText(String price) {
		
		String text=getText(subtotaltext).replace(price, "");
		String item=text.replace(":", "").trim().replace("s", "");
		
		if (item.equalsIgnoreCase(subtotaltext1.getText().replace("s", ""))) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getItems() {
		return getText(items);
	}
	
	public void ClickProccedToCheckout() {
		click(proceedtocheckout);
	}
	
	
	public void clickPlusButton(int quantity) {
		for (int i=1;i<=quantity;i++) {
			click(plusbutton);
		}
	}
	
	public void clickMinusButton(int quantity) throws InterruptedException {
		for (int i=1;i<=quantity;i++) {
			Thread.sleep(500);
			click(minusbutton);
		}
	}
	
	
	public String getProductName(String titlename) {
		List<String> finaltitlename=new ArrayList<String>();
		for (String webElement : getText(productname).split(" ")) {
		       for (String webElement2 : titlename.split(" ")) {
				if (webElement.equalsIgnoreCase(webElement2)) {
					finaltitlename.add(webElement);
				}
			}
			
		}
		
		return String.join(" ", finaltitlename);
	}
	
	public void clickRemove() {
		click(remove);
		click(removebutton);
	}
	
	public String getEmptyCartText() {
		return getText(emptycarttext);
	}
	
	public boolean subTotalPrice(String qty) {
		if (qty.equals("1")) {
			return getText(shoppingcart_price).equals(getText(shoppingcart_subtotal1));
		}
		else {
			Float price1= Float.parseFloat(getText(shoppingcart_price).replace("$", "").trim());
			int qty1= Integer.parseInt(qty);
			float result= price1 * qty1;
			System.out.println(result);
			return getText(shoppingcart_subtotal1).replace("$", "").trim().equals(String.valueOf(result));
		}
		
	}
	
	
	public String getDiscountPercentage() {
		Float price1=  Float.parseFloat(getText(shoppingcart_price).replace("$", "").trim());
		Float price2=  Float.parseFloat(getText(shoppingcart_ordertotal).replace("$", "").trim());
		
		return String.valueOf((price2/price1)*100)+"%";
	}
	
	public boolean subTotal(String price) {
		return getText(shoppingcart_subtotal).equals(price);
	        
	}
	
	public boolean orderTotal(String price) {
		return getText(shoppingcart_ordertotal).equals(price);
	}
	
	public String applyDiscount(String discount) {
		click(shoppingcart_discount);
		sendKeys(shoppingcart_discountinput, discount);
		shoppingcart_discountinput.sendKeys(Keys.ENTER);
		return getText(shoppingcart_discountapplyed);
	}
	
	public String discount() {
		return getText(shoppingcart_discounttotal);
		
	}
	
	public void clickAccpet() {
		click(accept);
	}
	
	public void clickProceedToCheckout() {
		click(shoppingcart_proceedtocheckout);
	}
	
	
	public void updateAddress(String street,String country, String region, String city, String postcode,String phonenumber) throws InterruptedException {
		sendKeys(streetaddress,street);
		Baseclass.Select(selectcountry).selectByVisibleText(country);
		Baseclass.Select(selectregion).selectByVisibleText(region);
		sendKeys(cityinput, city);
		sendKeys(postcodeinput, postcode);
		sendKeys(phonenumberinput, phonenumber);
		click(update);
		Thread.sleep(5000);
		Baseclass.action.click(termscheckbox).build().perform();
		Thread.sleep(3000);
		click(placeorder);
		
	   
		
	}
	
	
	
}
