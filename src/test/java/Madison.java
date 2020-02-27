import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;

public class Madison {

    @Test
    public void HomepageTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexandratudosa\\Desktop\\SeleniumProject1\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa3.dev.evozon.com/");
        driver.getTitle();
        System.out.println(driver.getTitle());

        driver.getCurrentUrl();
        System.out.println(driver.getCurrentUrl());

//        WebElement element = driver.findElement(By.cssSelector("#header > div > a"));
        WebElement pageLogo = driver.findElement(By.className("logo"));
        pageLogo.click();

        driver.navigate().to("http://qa3.dev.evozon.com/vip.html");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        driver.quit();
    }

    @Test
    public void AccountTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexandratudosa\\Desktop\\SeleniumProject1\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa3.dev.evozon.com/");

        WebElement account = driver.findElement(By.className("skip-account"));
        account.click();
        driver.quit();
    }

    @Test
    public void LanguagesTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexandratudosa\\Desktop\\SeleniumProject1\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa3.dev.evozon.com/");

        Select oSelect = new Select(driver.findElement(By.cssSelector("#select-language")));

//        List <WebElement> languageSelect = driver.findElements(By.cssSelector("#select-language option"));
        List<WebElement> languageSelect = oSelect.getOptions();
        System.out.println(languageSelect.size());

        oSelect.selectByVisibleText("French");

        driver.quit();
    }

    @Test
    public void Search() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexandratudosa\\Desktop\\SeleniumProject1\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa3.dev.evozon.com/");

        WebElement searchInput = driver.findElement(By.id("search"));
        searchInput.clear();
        searchInput.sendKeys("woman");

        WebElement searchButton = driver.findElement(By.cssSelector("#search_mini_form > div.input-box > button"));
        searchButton.click();


        /* v2
            searchInput.clear();
            searchInput.sendKeys("man");
            searchInput.sendKeys("\n");
         */
        driver.quit();
    }

    @Test
    public void NewProductList() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexandratudosa\\Desktop\\SeleniumProject1\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa2.dev.evozon.com/");

        List<WebElement> newItems = driver.findElements(By.cssSelector(".product-name"));
        System.out.println(newItems.size());

        Iterator <WebElement> itr = newItems.iterator();
        while (itr.hasNext()) {
            String itemText = itr.next().getText();
            System.out.println(itemText);
        }
        driver.quit();

    }

    @Test
    public void Navigation() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexandratudosa\\Desktop\\SeleniumProject1\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa2.dev.evozon.com/");

        List <WebElement> navHeadlines = driver.findElements(By.cssSelector("#nav > ol"));
        Iterator <WebElement> itr = navHeadlines.iterator();

        while(itr.hasNext()){
            String headlinesText = itr.next().getText();
            System.out.println(headlinesText);
        }

        WebElement saleBtn = driver.findElement(By.cssSelector("#nav > ol > li.level0.nav-5.parent"));
        saleBtn.click();

//        List <WebElement> salesElements = driver.findElements(By.cssSelector("#nav > ol > li.level0.nav-5.active.parent > a"));
//        Iterator <WebElement> itr1 = salesElements.iterator();
//
//        while(itr1.hasNext()){
//            String saleTxt = itr1.next().getText();
//            System.out.println(saleTxt);
//            if(saleTxt.equals("Accessories"))
//                itr1.next().click();
//        }

        driver.quit();
    }

}
