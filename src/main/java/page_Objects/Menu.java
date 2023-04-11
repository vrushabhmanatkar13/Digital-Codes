package page_Objects;

import java.util.List;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.Digitalcodes.Util.Baseclass;

public class Menu extends Baseclass {

	public String _username;
	public String _type;

	public Menu() {
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//i[normalize-space()='menu']")
	private WebElement menu;

	@FindBy(xpath = "//div[@class='row row--dense']/div[@class='pa-0 text-left col']")
	public WebElement username;

	@FindBy(xpath = "//p[@class='mb-0 caption accent2--text']")
	private WebElement type;

	@FindAll({ @FindBy(xpath = "//div[@class=\"px-2 v-list-item v-list-item--link theme--light\"]") })
	private List<WebElement> items;

	@FindBy(xpath = "//div[@class='v-treeview pl-2 v-treeview--dense theme--light']")
	private WebElement subtitle;

	@FindAll({ @FindBy(xpath = "//div[@class=\"v-treeview-node v-treeview-node--leaf\"]") })
	private List<WebElement> subtitle_1;

	@FindBy(xpath = "//h1[@class='primary--text display-1']")
	private WebElement title_name;

	public void menu() {
		menu.click();
	}

	public void username_type() {
		_username = username.getText();
		_type = type.getText();
	}

	// Click on Menu Items
	public String click_list_item(Object item, Object subitem) {
		for (int i = 0; i < items.size(); i++) {

			if (items.get(i).getText().equals(item)) {
				items.get(i).click();
				System.out.println(items.size());

				if (subtitle.isDisplayed()) {
					for (WebElement title : subtitle_1) {
						if (title.getText().equals(subitem)) {

							title.click();
							// System.out.println(title.getText());

						}

						break;
					}
				}

			}

		}
		return title_name.getText();

	}

}
