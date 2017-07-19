
class Parser {

    val testingData: MutableList<String> = mutableListOf()

    fun parse(tokens: MutableList<Token>){

        val generator: Generator = Generator()

        fun addTestingValue(value: String){
            testingData.add(value)
        }

        var i = 0
        while (i < tokens.size-1){

            when (tokens[i].type){
                TokenType.WORD -> {
                    when (tokens[i].value){
                        "OPEN" -> { // SYNTAX: OPEN "URL"
                            addTestingValue("OPEN-${tokens[i+1].value}")
                            generator.openWebsite(generator.driver!!, tokens[i+1].value)
                            i++
                        }
                        "CLOSE" -> { // SYNTAX: CLOSE
                            addTestingValue("CLOSE")
                            generator.closeWebbrowser(generator.driver!!)
                        }
                        "CLICK" -> { // SYNTAX: CLICK[INDEX] || CLICK
                            var index: Int = 0
                            if (tokens[i+1].value + tokens[i+3].value == "[]"){
                                if (tokens[i+2].type == TokenType.INT){
                                    index = tokens[i+2].value.toInt()
                                    i += 4
                                }
                            } else {
                                index = 0
                                i++
                                addTestingValue("CLICK")
                            }
                            when (tokens[i].value){
                                "CLASS" -> {
                                    addTestingValue("CLICK[$index]-CLASS-${tokens[i+1].value}")
                                    generator.clickOnClass(generator.driver!!, tokens[i+1].value, index)
                                }
                                "ID" -> {
                                    addTestingValue("CLICK[$index]-ID-${tokens[i+1].value}")
                                    generator.clickOnId(generator.driver!!, tokens[i+1].value, index)
                                }
                            }
                            i++
                        }
                        else -> {
                            if (!isConstant(tokens[i].value)){
                                addTestingValue("KEYWORD-NOT-FOUND")
                                throw KeywordNotFoundException("'${tokens[i].value}' could not be found!")
                            }
                        }
                    }
                }
                TokenType.OPERATOR -> {
                    // TODO: Organize to words
                    if (tokens[i-1].value == "BROWSER"){
                        addTestingValue("SET-BROWSER-${tokens[i+1].value}")
                        generator.setWebdriver(tokens[i+1].value)
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
