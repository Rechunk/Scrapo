import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.chrome.ChromeDriver

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

    fun executeScript(query: String){
        val executor: JavascriptExecutor = driver!! as JavascriptExecutor
        executor.executeScript(query)
    }

    fun interactWithElement(performInteraction: () -> Unit, selector: By, index: Int){

        try {
            try {
                performInteraction()
            }
            catch(ex: Exception){
                Thread.sleep(1000)
                interactWithElement(performInteraction, selector, index)
            }
        }
        catch (ex: Exception){
            closeWebbrowser()
            throw ElementNotFoundException("The element called $selector could not be found")
        }
    }

    fun performPrint(toPrint: String){
        println(toPrint)
    }

    fun alert(toAlert: String){

    }

    fun waitTime(milliseconds: Long){
        Thread.sleep(milliseconds)
    }

    fun closeWebbrowser(){
        driver!!.close()
    }

}