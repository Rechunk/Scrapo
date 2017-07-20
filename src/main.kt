import java.io.File

fun main(args: Array<String>){

    val file: File = File("C:\\Users\\User\\Desktop\\Scrapo\\src\\code.scr")

    val lexer: Lexer = Lexer()
    val tokens = lexer.lex(file.readText() + ' ')
    //tokens.forEach { println(it.value) }

    val parser: Parser = Parser()
    parser.parse(tokens)
}
