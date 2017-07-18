
class Lexer {

    fun lex(file: String) : MutableList<Token>{
        val tokens: MutableList<Token> = mutableListOf()

        var i = 0
        while (i < file.length){

            if (file[i].isLetter()){
                    var word = ""
                while (file[i].isLetter()){
                    word += file[i]
                    i++
                }
                i--
                tokens.add(Token(TokenType.WORD, word))
            }
            else if (file[i] == '"'){
                var string = ""
                i++
                while (file[i] != '"'){
                    string += file[i]
                    i++
                }
                tokens.add(Token(TokenType.STRING, string))
            }
            else if (file[i] == '='){
                tokens.add(Token(TokenType.OPERATOR, "="))
            }
            i++
        }

        return tokens
    }

}
