import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public static ElementsCollection OPEN_CATEGORIES = $$(".col-md-12 .menu-item.dropdown.wide_menu");
    public static ElementsCollection OPEN_SUBCATEGORIES = $$(".row.margin0.flexbox [href^=\"/catalog/\"]");
    public static SelenideElement HIDDEN_MENU_OF_CATEGORIES =
            $x("//*[@id=\"header\"]/div[2]/div[2]/div/div/div/div/nav/div/table/tbody/tr/td[7]");
    public static ElementsCollection PRODUCT_LIST = $$(".item_block.col-4.col-md-3.col-sm-6.col-xs-6");
    public static SelenideElement CATALOG_ON_THE_TOP = $("#bx_breadcrumb_2 > a > span");
    public static ElementsCollection CATALOG_ON_THE_LEFT = $$(".icons_fa.parent");
    public static SelenideElement SUBCATALOG_NAME = $("#pagetitle");
    public static SelenideElement SUBCATALOG_ON_THE_TOP = $("#navigation > span > span >span");
    public static SelenideElement SEARCH = $("#title-search-input_fixed");
    public static SelenideElement SEARCH_BUTTON = $("#title-search_fixed > form > div.search-button-div > button");
    public static SelenideElement PAGINATION = $(".nums");
    public static SelenideElement NOT_FOUND = $x("//*[@id=\"content\"]/div[2]/div/div/div/div[1]/div");
    public static SelenideElement POSTPONE_FIRST_PRODUCT =
            $x("/html/body/div[3]/div[4]/div[2]/div[1]/div/div/div/div/div/div[3]/div[1]/div/div[1]/div/div[2]/div/div[1]/div[2]/div");
    public static ElementsCollection POSTPONE_FIRST_PRODUCT_LIST =
            $$x("/html/body/div[3]/div[4]/div[2]/div[1]/div/div/div/div/div/div[3]/div[1]/div/div[1]/div/div[2]/div/div[1]/div[2]/div/span");
    public static SelenideElement NUMBER_OF_DEFERRED = $x("//*[@id=\"header\"]/div[2]/div[1]/div/div/div/div[4]/div[1]/a/span/span[2]");
    public static SelenideElement BASKET_OF_DEFERRED = $(".basket-link.delay.with_price.big");
    public static SelenideElement BASKET = $x("//*[@id=\"header\"]/div[2]/div[1]/div/div/div/div[4]/div[2]/a");
    public static SelenideElement NAME_OF_PRODUCT = $x("/html/body/div[3]/div[4]/div[2]/div[1]/div/div" +
            "/div/div/div/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[1]/a/span");
    public static ElementsCollection NAME_OF_PRODUCT_IN_BASKET_OF_DEFERRED = $$x("/html/body/div[3]/div[4]" +
            "/div[2]/div/div/div/div/div[3]/div/div/div[2]/div[2]/table/tbody[2]/tr/td[1]/div/div[2]/h2/a/span");
    public static SelenideElement CLEAN_BASKET_OF_DEFERRED = $x("//*[@id=\"basket-items-list-wrapper\"]/div[1]/div[2]/div/span");

    public static SelenideElement getElementFromOpenCategories (int randomNumber) {
        return $x("//*[@id=\"header\"]/div[2]/div[2]/div/div/div/div/nav/div/table/tbody/tr/td[" + randomNumber + "]/div");
    }

    public static ElementsCollection getElementFromOpenSubcategories (int randomNumber) {
        return $$x("//*[@id=\"header\"]/div[2]/div[2]/div/div/div/div/nav/div/table/tbody/tr/td[" + randomNumber + "]/div/ul/li");
    }

    public static SelenideElement getElementFromHiddenCategories (int randomNumber) {
        return $x("//*[@id=\"header\"]/div[2]/div[2]/div/div/div/div/nav/div/table/tbody/tr/td[7]/div/ul/li[" + randomNumber + "]");
    }

    public static ElementsCollection getElementFromHiddenSubcategories (int randomNumber) {
        return $$x("//*[@id=\"header\"]/div[2]/div[2]/div/div/div/div/nav/div/table/tbody/tr/td[7]/div/ul/li[" + randomNumber + "]/ul/li");
    }
}
