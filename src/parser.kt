/**
 * Created by User on 15.07.2017.
 */

class Parser {

    fun parse(tokens: MutableList<Token>){

        val generator: Generator = Generator()

        fun handleWord(value: String, i: Int){
            when (tokens[i].value){
                "OPEN" -> {
                    generator.openWebsite(tokens[i+1].value)
                }
                "GET" -> {
                    generator.getWebsiteSourceCode(tokens[i+1].value)
                }
                "CLICK" -> {
                    when (tokens[i+1].value){
                        "CLASS" -> {
                            // tokens[i+2] is the class to find and click on
                        }
                        "ID" -> {
                            // tokens[i+2] is the id to find and click on
                        }
                    }
                }
            }
        }

        for (i in 0..tokens.size-1){

            when (tokens[i].type){

                TokenType.UNDEFINED -> {

                }
                TokenType.WORD -> {
                    handleWord(tokens[i].value, i)
                }
                TokenType.STRING -> {

                }
                else -> {

                }
            }
        }


    }


}