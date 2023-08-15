package com.Digitalcodes.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Digitalcodes.pageobject.Collections_Page;
import com.Digitalcodes.pageobject.Header;
import com.Digitalcodes.pageobject.MyCart_Page;
import com.Digitalcodes.pageobject.Registration_Page;
import com.Digitalcodes.utilities.Baseclass;
import com.Digitalcodes.utilities.Sparkreport;

import io.qameta.allure.Step;


public class Title_Purchase_Test extends Prerequisites_Teardown{

	
	Header header;
	Collections_Page collectionpage;
	Registration_Page registerpage;
	MyCart_Page mycart;
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		header=new Header();
		registerpage=new Registration_Page();
		collectionpage=new Collections_Page();
		mycart=new MyCart_Page();
		
	}
	
	@Test(priority = 2,description = "Verify user able to Purchase Premium Complete", groups = {"Smoke","Regression"})
	public void TC_PurchaseTitle() throws Exception {
	
		menu.navigetToStaticFeaturs("Home");
		Sparkreport.Step("Click Home");
		String subscribedbuttontext=registerpage.clickSubscribeToPremium();
		Sparkreport.Step("Click "+subscribedbuttontext);
		Thread.sleep(2000);
		String title=collectionpage.getHeading();
		Sparkreport.Step("Title Name:- "+ title);
		String trialtext=registerpage.get14DayTrialText();
		Sparkreport.Step(trialtext);
		String price=registerpage.selectBillingCycle(jsonValue("annually"));
		Sparkreport.Step("Select :- "+jsonValue("annually")+" Price:- "+price);
		registerpage.clickAddToCart();
	    Sparkreport.Step("Click Add to Cart");
		String qtyshoppingcart=header.click_getQtyShoppingCart();
		Sparkreport.Step("Cart Qty:- "+qtyshoppingcart);
		Sparkreport.Step("Click Cart");
		
		String title1=mycart.getTitleName("Premium Complete");
		Sparkreport.Step("Title Name My Cart:- "+title1);
		boolean actualprice=mycart.verifyPrice(price);
		
		Double totalprice=mycart.getTotalPrice();
		Double totalprice1=mycart.getTotalPriceAsQty(Integer.valueOf(qtyshoppingcart));
		Sparkreport.Step("Total Price For Qty "+ qtyshoppingcart +" is "+ String.valueOf(totalprice1));
		
		String itemstext=mycart.getItems().replace("Items", "").trim();
		Sparkreport.Step(itemstext);
		boolean item=mycart.veriySubtotalItemText(price);
		
		mycart.ClickProccedToCheckout();
		Sparkreport.Step("Click Procced To Checkout");
		Thread.sleep(4000);
		Baseclass.switchToWindow();
	
		org.testng.Assert.assertEquals(totalprice, totalprice1);
		assertEquals(trialtext, jsonArrayValue("Basic", "start trial"));
		assertEquals(qtyshoppingcart, itemstext);
		assertTrue(actualprice);
		assertTrue(item);
		
		mycart.clickAccpet();
		String productname=mycart.getProductName(title1);
		Sparkreport.Step("Title Name in Shopping Cart :- "+productname);
		boolean subtotal=mycart.subTotalPrice(qtyshoppingcart);
		boolean subtotal1=mycart.subTotal(price);
		boolean ordertotal=mycart.orderTotal(price);
		String msg=mycart.applyDiscount("Go-Live");
		Sparkreport.Step("Apply Discount:- "+"Go-Live");
		String discount=mycart.discount();
		Sparkreport.Step("Discount applyed price :- "+discount);
		String percntage=mycart.getDiscountPercentage();
		Sparkreport.Step("Discount Percentage:- "+percntage);
		mycart.ClickProccedToCheckout();
		Sparkreport.Step("Click Procced To checkout");
		
		assertEquals(productname, title1);
		assertTrue(subtotal);
		assertTrue(subtotal1);
		assertTrue(ordertotal);
		assertEquals(msg, "Coupon was successfully applied.");
		assertEquals(discount, "-"+price);
		assertEquals(percntage,"0.0%");
		
		mycart.updateAddress("800 N FISKE BLVD", "United States", "Florida", "COCOA", "32922-7363", "(321) 208-7934");
		Sparkreport.Step("Fill Address and Click Place Order now");
		Thread.sleep(6000);
		String successtitle=getTitle();
		Baseclass.closeWindow();
		Baseclass.retrunToMainWindow();
		assertEquals(successtitle, jsonArrayValue("Page-titles", "success-page"));
		
		do {
			 Baseclass.refreshBrowser();
           
		} while (header.getSubscrptionType().equalsIgnoreCase("Basic") || header.getSubscrptionType().equalsIgnoreCase("Signin"));
		
	}
	
	@Test(priority = 1,description = "Verify user able to Update Qty and Remove product in My cart", groups = {"Regression"})
	public void TC_verifyUpdateQutentiy_RemoveIncart() throws InterruptedException{
		
		menu.navigetToStaticFeaturs("Home");
		Sparkreport.Step("Click Home");
		String subscribedbuttontext=registerpage.clickSubscribeToPremium();
		Sparkreport.Step("Click "+subscribedbuttontext);
		
		String title=collectionpage.getHeading();
		Sparkreport.Step("Title Name:- "+ title);
		
		String price=registerpage.selectBillingCycle(jsonValue("annually"));
		Sparkreport.Step("Select :- "+jsonValue("annually")+" Price:- "+price);
		registerpage.clickAddToCart();
	    Sparkreport.Step("Click Add to Cart");
	    
		String qtyshoppingcart=header.click_getQtyShoppingCart();
		Sparkreport.Step("Cart Qty:- "+qtyshoppingcart);
		Sparkreport.Step("Click Cart");
		
		
		Sparkreport.Step("Increase Qtentity");
		mycart.clickPlusButton(4);
		
		String itemstext=mycart.getItems();
		String updatedqty=header.getQtyShoppingCart();
		Sparkreport.Step(itemstext);
		Sparkreport.Step("Updated Qutenty "+updatedqty);
		
		boolean item=mycart.veriySubtotalItemText(mycart.getTotalprice());
		
		Double totalprice1=mycart.getTotalPriceAsQty(Integer.parseInt(updatedqty));
		Sparkreport.Step("Total Price For Qty "+ updatedqty +" is "+ String.valueOf(totalprice1));
		
		System.out.println(mycart.getTotalPrice());
		
		org.testng.Assert.assertEquals(mycart.getTotalPrice(), totalprice1);
		assertEquals(updatedqty, itemstext.replace("Items", "").trim());
		assertTrue(item);
		
		Sparkreport.Step("Reduce Qtentity");
		mycart.clickMinusButton(4);
		String updatedqty1=header.getQtyShoppingCart();
		Sparkreport.Step("Updated Qutenty "+updatedqty1);
		
		Double updatedprice=mycart.getTotalPriceAsQty(Integer.parseInt(updatedqty1));
		Sparkreport.Step("Total Price For Qty "+ updatedqty1 +" is "+ String.valueOf(updatedprice));
		
		boolean item1=mycart.veriySubtotalItemText(mycart.getTotalprice());
		
		org.testng.Assert.assertEquals(mycart.getTotalPrice(), updatedprice);
		assertEquals(updatedqty1, mycart.getItems().replace("Items", "").trim());
		assertTrue(item1);
		Thread.sleep(2000);
		mycart.clickRemove();
		 Sparkreport.Step("Click Remove");
		 Sparkreport.Step(mycart.getEmptyCartText());
		assertEquals(mycart.getEmptyCartText(), jsonValue("empty cart"));
		
		/*
		 * do{ refreshBrowser(); Thread.sleep(3000); mycart.clickRemove();
		 * Sparkreport.Step("Click Remove");
		 * 
		 * } while(mycart.titleNameIsDIisplay());
		 * 
		 * Sparkreport.Step(mycart.getEmptyCartText());
		 * assertEquals(mycart.getEmptyCartText(), jsonValue("empty cart"));
		 */
		
		
	}

	
	
	
	
}
