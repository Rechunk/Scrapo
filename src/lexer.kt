import com.sun.jna.platform.win32.WinNT

class Lexer {

    fun lex(file: String) : MutableList<Token>{
        val tokens: MutableList<Token> = mutableListOf()

        var i = 0
        while (i < file.length){

            if (file[i].isLetter()){ // Words
                var word = ""
                while (file[i].isLetter()){
                    word += file[i]
                    i++
                }
                i--
                tokens.add(Token(TokenType.WORD, word))
            }
            else if (file[i] == '"'){ // Strings
                var string = ""
                i++
                while (file[i] != '"'){
                    string += file[i]
                    i++
                }
                tokens.add(Token(TokenType.STRING, string))
            }
            else if (file[i] == '='){ // Assignment
                tokens.add(Token(TokenType.OPERATOR, "="))
            }
            else if (file[i] == '['){
                tokens.add(Token(TokenType.GROUPING_SYMBOL, "["))
            }
            else if (file[i] == ']'){
                tokens.add(Token(TokenType.GROUPING_SYMBOL, "]"))
            }
            else if (file[i].isDigit()){
                var int: String = ""
                while(file[i].isDigit()){
                    int += file[i]
                    i++
                }
                i--
                tokens.add(Token(TokenType.INT, int))
            }
            else if (file[i] == '#'){
                while (file[i] != '\n'){
                    i++
                }
            }
            else if (file[i] == '/'){
                i++
                while (file[i] != '/'){
                    i++
                }
            }
            else {
                if (file[i] != '\n' && file[i] != '\r' && file[i] != ' ')
                    throw NotImplementedError("Not implemented")

            }

            i++
        }

        tokens.add(Token(TokenType.STRING, ""))
        return tokens
    }

}
