import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.ClientInfoStatus;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MadisonAfter {

    WebDriver driver;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexandratudosa\\Desktop\\SeleniumProject1\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://qa2.dev.evozon.com/");
    }

//    @After
//    public void after() {
//        driver.quit();
//    }

    @Test
    public void HomepageTest() {
        String realTitle = driver.getTitle();
        System.out.println(realTitle);
        String expectedTitle = "Madison Island";
        assertEquals(realTitle, expectedTitle);

        String realURL = driver.getCurrentUrl();
        String expectedURL = "http://qa2.dev.evozon.com/";
        assertEquals(realURL, expectedURL);

        boolean pageLogo = driver.findElement(By.className("logo")).isDisplayed();
        assertTrue(pageLogo);

        driver.navigate().to("http://qa2.dev.evozon.com/sale/accessories.html");

        WebElement logoClick = driver.findElement(By.className("logo"));
        logoClick.click();

        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
    }

    @Test
    public void AccountTest() {
        WebElement account = driver.findElement(By.className("skip-account"));
        account.click();

        boolean dropdown = driver.findElement(By.id("header-account")).isDisplayed();
        assertTrue(dropdown);

        String accountText = driver.findElement(By.className("skip-account")).getText();
        assertEquals("ACCOUNT", accountText);
    }

    @Test
    public void LanguagesTest() {
        Select oSelect = new Select(driver.findElement(By.id("select-language")));
        List<WebElement> languageSelect = oSelect.getOptions();
        int actualNoOfLanguage = languageSelect.size();

        int expectedNoOfLanguages = 3;
        assertEquals(expectedNoOfLanguages, actualNoOfLanguage);

        oSelect.selectByVisibleText("French");

        String newDefaultLanguage = driver.findElement(By.cssSelector("#select-language")).getText();
        assertTrue(newDefaultLanguage.contains("French"));

    }

    @Test
    public void Search() {
        WebElement searchInput = driver.findElement(By.id("search"));
        searchInput.clear();

        String expectedSearchInput = "";
        assertTrue(expectedSearchInput, true);

//        first search
//        searchInput.sendKeys("glass");
//        searchInput.sendKeys("\n");
////        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        boolean displaySort = driver.findElement(By.className("toolbar")).isDisplayed();
//        assertTrue(displaySort);


        searchInput.sendKeys("woman");
        searchInput.sendKeys("\n");
        String expectedWordSearched = "woman";
        String actualWordSearched = driver.findElement(By.className("page-title")).getText().toLowerCase();
        assertTrue(actualWordSearched.contains(expectedWordSearched));

    }

    @Test
    public void Navigation() {
        List<WebElement> navHeadlines = driver.findElements(By.className("nav-primary"));
        Iterator<WebElement> itr = navHeadlines.iterator();

        while (itr.hasNext()) {
            String headlinesText = itr.next().getText();
            System.out.println(headlinesText);
        }

        WebElement saleBtn = driver.findElement(By.cssSelector("li.level0.nav-5.parent"));
        saleBtn.click();
        assertEquals("http://qa2.dev.evozon.com/sale.html", driver.getCurrentUrl());
    }


    public WebElement getInputByTitle(String title){
        return driver.findElement(By.cssSelector("li.active input[title='" + title + "']"));
    }


    public void randomElement(String css) {
        Random rand = new Random();
        List<WebElement> randomElement = driver.findElements(By.cssSelector(css));
        WebElement random = randomElement.get(rand.nextInt(randomElement.size()));
        random.click();
    }

    public void randomProperties(String css) {
        Random rand = new Random();
        List<WebElement> randomProperty = driver.findElements(By.cssSelector(css));
        WebElement random = randomProperty.get(rand.nextInt(randomProperty.size()));
        WebElement addToCartBtn = driver.findElement(By.cssSelector(".add-to-cart-buttons"));
        if(addToCartBtn.isEnabled())
            random.click();
    }


    @Test
    public void Checkout() throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(By.className("nav-primary"));
        action.moveToElement(we).moveToElement(driver.findElement(By.cssSelector("li.level1.nav-4-2 > a"))).click().build().perform();

        randomElement("div.category-products li.item > a");
        driver.findElement(By.cssSelector(".add-to-cart-buttons")).click();

        WebElement searchInput = driver.findElement(By.id("search"));
        searchInput.sendKeys("pants");
        searchInput.sendKeys("\n");

        boolean displaySort = driver.findElement(By.className("toolbar")).isDisplayed();
        assertTrue(displaySort);

       // add a custom product
        randomElement("div.category-products li.item > a");
        //random color
        randomProperties("ul#configurable_swatch_color li");
        //random size
        randomElement("ul#configurable_swatch_size li");
        driver.findElement(By.cssSelector(".add-to-cart-buttons")).click();

        boolean checkIfCartHasProducts = driver.findElement(By.cssSelector("#shopping-cart-table > tfoot > tr")).isDisplayed();
        assertTrue(checkIfCartHasProducts);

        WebElement checkoutButton = driver.findElement(By.className("btn-checkout"));
        checkoutButton.click();
        WebElement continueButton = driver.findElement(By.id("onepage-guest-register-button"));
        continueButton.click();

        getInputByTitle("First Name").sendKeys("Ana");
        getInputByTitle("Middle Name/Initial").sendKeys("Maria");
        getInputByTitle("Last Name").sendKeys("Pop");
        getInputByTitle("Email Address").sendKeys("ana@yahoo.com");
        getInputByTitle("Street Address").sendKeys("Muncii");
        getInputByTitle("City").sendKeys("Cluj");
        WebElement state = driver.findElement(By.cssSelector("#billing\\:region_id"));
        state.click();
        getInputByTitle("Zip/Postal Code").sendKeys("50400");
        getInputByTitle("Telephone").sendKeys("0752826110");
        Select stateCheckout = new Select(driver.findElement(By.cssSelector("#billing\\:country_id")));
        stateCheckout.selectByValue("AL");
        driver.findElement(By.cssSelector("#billing\\:use_for_shipping_no")).click();

        WebElement continueBtn = driver.findElement(By.cssSelector("#billing-buttons-container > button"));
        continueBtn.click();
        /*
            tried for shipping to the same address => failed!!
            WebDriverWait wait = (new WebDriverWait(driver, 30));
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#billing-buttons-container > button")));
            driver.findElement(By.cssSelector("#opc-shipping > div.step-title")).click();
         */

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#shipping\\:firstname"))).click();

        getInputByTitle("First Name").sendKeys("S");
        getInputByTitle("Last Name").sendKeys("Maya");
        getInputByTitle("Street Address").sendKeys("Muncii");
        getInputByTitle("City").sendKeys("Cluj");

        Select countryShipping = new Select(driver.findElement(By.name("shipping[country_id]")));
        countryShipping.selectByVisibleText("Romania");

        Select stateShipping = new Select(driver.findElement(By.name("shipping[region_id]")));
        stateShipping.selectByVisibleText("Cluj");

        getInputByTitle("Telephone").sendKeys("0752826110");
        getInputByTitle("Zip/Postal Code").sendKeys("50400");

        WebElement continueShipping = driver.findElement(By.cssSelector("#shipping-buttons-container > button"));
        continueShipping.click();

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout-shipping-method-load .sp-methods")));
//        List<WebElement> shippingOptions = driver.findElements(By.cssSelector("#checkout-shipping-method-load dt"));
        WebElement shippingOptions = driver.findElement(By.cssSelector("#checkout-shipping-method-load .sp-methods"));
        List<WebElement> listOpt = shippingOptions.findElements(By.tagName("dt"));
        System.out.println("sizeeeee"+listOpt.size());



        if(listOpt.size() > 1) {
           new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.id("#s_method_freeshipping_freeshipping"))).click();
           new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#shipping-method-buttons-container .button"))).click();
        }
        else {
            new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#shipping-method-buttons-container .button"))).click();
        }

        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#payment-buttons-container > button"))).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#review-buttons-container  .button"))).click();

        String orderPLacedMessage = "THANK YOU FOR YOUR PURCHASE!";
        new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".sub-title"))).click();
        assertEquals(driver.findElement(By.cssSelector(".sub-title")).getText().toUpperCase(), orderPLacedMessage.toUpperCase());
    }

    @Test
    public void CheckoutRandom() {

        List<WebElement> productsList = driver.findElements(By.cssSelector("div.category-products li.item > a"));
        Random rand = new Random();
        WebElement randomProduct = productsList.get(rand.nextInt(productsList.size()));
        randomProduct.click();
    }

}
