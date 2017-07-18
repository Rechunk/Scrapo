import java.io.File

fun main(args: Array<String>){

    val file: File = File("C:\\Users\\User\\Desktop\\Scrapo\\src\\code.sp")

    val lexer: Lexer = Lexer()
    val tokens = lexer.lex(file.readText() + ' ')

    val parser: Parser = Parser()
    parser.parse(tokens)

    tokens.forEach { println(it.value) }

}