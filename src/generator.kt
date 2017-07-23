
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*
import kotlin.concurrent.*
import sun.security.jgss.GSSUtil.login
import com.google.gson.annotations.Until
import org.openqa.selenium.ElementNotVisibleException


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

    // TODO: Modularize clickOn && typeInto into one method
    fun clickOn(selector: By, driver: WebDriver, index: Int) {

        try {
            try {
                driver.findElements(selector)[index].click()
            }
            catch(ex: Exception){
                Thread.sleep(1000)
                clickOn(selector, driver, index)
            }
        }
        catch (ex: IndexOutOfBoundsException){
            closeWebbrowser(driver)
            throw ElementNotFoundException("The element called $selector could not be found")
        }
    }

    fun typeInto(selector: By, driver: WebDriver, index: Int, toType: String){

        try {
            try {
                driver.findElements(selector)[index].sendKeys(toType)
            }
            catch(ex: Exception){
                Thread.sleep(1000)
                typeInto(selector, driver, index, toType)
            }
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