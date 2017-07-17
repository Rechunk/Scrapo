import org.junit.Test

import org.junit.Assert.*


class LexerTest {
    @Test
    fun lex() {
        val tokens: MutableList<Token> = Lexer().lex("\"a.+\" word ")

        assertEquals(TokenType.STRING, tokens[0].type)
        assertEquals("a.+", tokens[0].value)

        assertEquals(TokenType.WORD, tokens[1].type)
        assertEquals("word", tokens[1].value)
    }

}