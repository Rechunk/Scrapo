
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

class Generator {

    var driver: WebDriver? = null

    fun setWebdriver(browser: String){
        when (browser){
            "CHROME" -> {
                System.setProperty("webdriver.chrome.driver", ".\\webdrivers\\chromedriver.exe")
                driver = ChromeDriver()
            }
            else -> {
                throw IllegalArgumentException("The '$browser' Browser count not be found!")
            }
        }
    }

    fun openWebsite(driver: WebDriver, url: String){

        driver.get(url)
    }

    fun clickOnClass(driver: WebDriver, className: String){

        driver.findElements(By.className(className))[0].click()
    }

    fun closeWebbrowser(driver: WebDriver){

        driver.close()
    }

    fun getWebsiteSourceCode(url: String){
    }
}