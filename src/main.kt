import java.io.File

fun main(args: Array<String>){
    val lexer: Lexer = Lexer()
    val parser: Parser = Parser()

    val file: File = File("C:\\Users\\User\\Desktop\\Scrapo\\src\\code.sp")
    val tokens = lexer.lex(file.readText() + " ")
    parser.parse(tokens)

    tokens.forEach { println(it.value) }

}