/**
 * Created by User on 16.07.2017.
 */
import org.openqa.selenium.WebDriver
import java.awt.Desktop
import java.net.URI
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver


class Generator {
    fun openWebsite(url: String){

        /*if (Desktop.isDesktopSupported()){
            Desktop.getDesktop().browse(URI(url))
        }*/

        val driver: WebDriver = ChromeDriver()

        driver.get("https://google.com")

    }

    fun getWebsiteSourceCode(url: String){
        val driver: WebDriver = PhantomJSDriver()
    }
}