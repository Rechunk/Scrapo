/**
 * Created by User on 16.07.2017.
 */
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import kotlin.system.exitProcess


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

    fun closeWebbrowser(){

    }

    fun getWebsiteSourceCode(url: String){
    }
}