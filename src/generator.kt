
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

    fun interactWithElement(function: () -> Unit, selector: By, index: Int){

        try {
            try {
                function()
            }
            catch(ex: Exception){
                Thread.sleep(1000)
                interactWithElement(function, selector, index)
            }
        }
        catch (ex: Exception){
            closeWebbrowser()
            throw ElementNotFoundException("The element called $selector could not be found")
        }
    }

    fun waitTime(milliseconds: Long){
        Thread.sleep(milliseconds)
    }

    fun closeWebbrowser(){
        driver!!.close()
    }

}