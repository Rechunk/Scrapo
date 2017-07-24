import com.sun.corba.se.impl.io.TypeMismatchException
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.NotFoundException

class Parser {

    val testingData: MutableList<String> = mutableListOf()

    fun parse(tokens: MutableList<Token>){

        val generator: Generator = Generator()

        fun addTestingValue(value: String){
            testingData.add(value)
        }

        fun userSpecifiedIndex(i: Int) : Boolean {
            return (tokens[i+1].value + tokens[i+3].value == "[]")
        }

        var i = 0
        while (i < tokens.size-1){

            fun handleFunctionCallWithIndex(keyword: String){
                var index: Int = 0
                if (userSpecifiedIndex(i)){
                    if (tokens[i+2].type == TokenType.INT){
                        index = tokens[i+2].value.toInt()
                        i += 4
                    } else {
                        generator.closeWebbrowser()
                        throw IndexNotIntegerException("The index '${tokens[i+2].value}' is not an integer!")
                    }
                } else {
                    i++
                }

                if (tokens[i+1].type != TokenType.STRING){
                    generator.closeWebbrowser()
                    throw TypeMismatchException("The Keyword '$keyword[index]' needs to be followed by a string")
                }

                val selector = if (tokens[i].value == "CLASS") By.className(tokens[i+1].value)
                    else By.id(tokens[i+1].value)

                when (keyword){
                    "CLICK" -> {
                        addTestingValue("CLICK[$index]-${tokens[i+1].value}")
                        val element = generator.driver!!.findElements(selector)[index]
                        generator.interactWithElement({element.click()}, selector, index)
                    }
                    "TYPE" -> {
                        if (tokens[i+2].type == TokenType.STRING){
                            addTestingValue("TYPE[$index]-${tokens[i+1].value}-${tokens[i+2].value}")
                            val element = generator.driver!!.findElements(selector)[index]
                            generator.interactWithElement({element.sendKeys(tokens[i+2].value)}, selector, index)
                        }
                        else {
                            throw IllegalArgumentException("'TYPE' requires a string to type after the selector")
                        }

                    }
                    "REMOVE" -> {
                        addTestingValue("REMOVE-${tokens[i+1].value}")
                        var query = ""
                        when (tokens[i].value){
                            "CLASS" -> {
                                query = "document.getElementsByClassName(\"${tokens[i+1].value}\")[$index].remove()"
                            }
                            "ID" -> {
                                query = "document.getElementById(\"${tokens[i+1].value}\").remove()"
                            }
                        }
                        generator.interactWithElement({generator.executeScript(query)}, selector, index)
                    }
                }

            }

            when (tokens[i].type){
                TokenType.WORD -> {
                    when (tokens[i].value){
                        "OPEN" -> { // SYNTAX: OPEN "URL"
                            val url = tokens[i+1]
                            if (url.type == TokenType.STRING && url.value.contains(Regex("http[s]*://"))){
                                addTestingValue("OPEN-${tokens[i+1].value}")
                                generator.openWebsite(generator.driver!!, tokens[i+1].value)
                            }
                            else {
                                addTestingValue("OPEN-INVALID-URL")
                                generator.closeWebbrowser()
                                throw TypeMismatchException("'OPEN' must be followed by a string containing the full url")
                            }
                            i++
                        }
                        "CLOSE" -> { // SYNTAX: CLOSE
                            addTestingValue("CLOSE")
                            generator.closeWebbrowser()
                        }
                        "CLICK", "TYPE", "REMOVE" -> { // SYNTAX: CLICK[INDEX] || CLICK
                            handleFunctionCallWithIndex(tokens[i].value)
                            i++
                        }
                        "ALERT" -> {
                            i++
                            generator.executeScript("alert(\"${tokens[i].value}\")")
                        }
                        "PRINT" -> {
                            i++
                            generator.performPrint(tokens[i].value)
                        }
                        "WAIT" -> {
                            i++
                            addTestingValue("WAIT-${tokens[i].value}")
                            val value = tokens[i].value
                            try {
                                val time: Long = value.toLong()
                                generator.waitTime(time)
                            } catch (ex: Exception){
                                generator.closeWebbrowser()
                                throw TypeMismatchException("The value '$value' specified along 'WAIT'" +
                                        " is not a LongType.")
                            }
                        }
                        else -> {
                            if (!isConstant(tokens[i].value)){
                                addTestingValue("KEYWORD-NOT-FOUND")
                                generator.closeWebbrowser()
                                throw KeywordNotFoundException("'${tokens[i].value}' could not be found!")
                            }
                        }
                    }
                }
                TokenType.OPERATOR -> {
                    when (tokens[i-1].value){
                        "BROWSER" -> {
                            addTestingValue("SET-BROWSER-${tokens[i+1].value}")
                            generator.setWebdriver(tokens[i+1].value)
                        }
                        else -> {
                            addTestingValue("VARIABLE-ASSIGNMENT-COMING-SOON")
                            // TODO: Variable assignment goes here
                        }
                    }
                }
                TokenType.STRING -> {

                }
                TokenType.GROUPING_SYMBOL -> {

                }
                TokenType.INT -> {

                }
            }
            i++
        }


    }

    fun isConstant(word: String) : Boolean {
        // TODO: Modularize to check for other constants
        if (word == "BROWSER"){
            return true
        }
        return false
    }


}
