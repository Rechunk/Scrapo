
class Parser {

    fun parse(tokens: MutableList<Token>){

        val generator: Generator = Generator()

        fun isConstant(word: String) : Boolean {
            // TODO: Modularize to check for other constants
            if (word == "BROWSER"){
                return true
            }
            return false
        }

        fun handleWord(value: String, i: Int){
            when (tokens[i].value){
                "OPEN" -> {
                    generator.openWebsite(generator.driver!!, tokens[i+1].value)
                }
                "CLOSE" -> {
                    generator.closeWebbrowser(generator.driver!!)
                }
                "CLICK" -> {
                    when (tokens[i+1].value){
                        "CLASS" -> {
                            generator.clickOnClass(generator.driver!!, tokens[i+2].value)
                        }
                        "ID" -> {
                            // tokens[i+2] is the id to find and click on
                        }
                    }
                }
                else -> {
                    if (!isConstant(tokens[i].value)){
                        throw KeywordNotFoundException("'${tokens[i].value}' could not be found!")
                    }
                }
            }
        }

        for (i in 0..tokens.size-1){

            when (tokens[i].type){

                TokenType.WORD -> {
                    handleWord(tokens[i].value, i)
                }
                TokenType.OPERATOR -> {
                    // TODO: Organize to words
                    if (tokens[i-1].value == "BROWSER"){
                        generator.setWebdriver(tokens[i+1].value)
                    }
                }
                TokenType.STRING -> {

                }
            }
        }


    }


}
