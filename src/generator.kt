
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*
import kotlin.concurrent.*

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

    fun clickOn(selector: By, driver: WebDriver, index: Int) {

        try {
            driver.findElements(selector)[index].click()
        }
        catch (ex: IndexOutOfBoundsException){
            throw ElementNotFoundException("The element called $selector could not be found")
        }
    }

    fun typeInto(selector: By, driver: WebDriver, index: Int){

        try {
            driver.findElements(selector)[index].sendKeys("This is really it...")
        }
        catch (ex: IndexOutOfBoundsException){
            throw ElementNotFoundException("The element called $selector could not be found")
        }
    }

    fun waitTime(milliseconds: Long){

        Thread.sleep(milliseconds)
    }

    fun closeWebbrowser(driver: WebDriver){

        driver.close()
    }

    fun getWebsiteSourceCode(url: String){
    }
}