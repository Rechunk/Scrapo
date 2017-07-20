import org.junit.Test

import org.junit.Assert.*

class ParserTest {
    @Test
    fun parse() {
        val lexer: Lexer = Lexer()
        val tokens = lexer.lex("""
        BROWSER="CHROME"
        OPEN "http://localhost:1234/automationsite/"
        TYPE[0] ID "login-username-field-id"
        CLICK[0] CLASS "create-account-btn-class"
        CLOSE
""")

        val parser: Parser = Parser()

        parser.parse(tokens)
        val data = parser.testingData

        assertEquals(data[0], "SET-BROWSER-CHROME")
        assertEquals(data[1], "OPEN-http://localhost:1234/automationsite/")
        assertEquals(data[2], "TYPE[0]-login-username-field-id")
        assertEquals(data[3], "CLICK[0]-create-account-btn-class")
        assertEquals(data[4], "CLOSE")
    }

}