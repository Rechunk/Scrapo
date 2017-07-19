import org.junit.Test

import org.junit.Assert.*


class LexerTest {
    @Test
    fun lex(){
        val tokens: MutableList<Token> = Lexer().lex("\"abc\"\n\rword=[165]")

        assertEquals(TokenType.STRING, tokens[0].type)
        assertEquals("abc", tokens[0].value)

        assertEquals(TokenType.WORD, tokens[1].type)
        assertEquals("word", tokens[1].value)

        assertEquals(TokenType.OPERATOR, tokens[2].type)
        assertEquals("=", tokens[2].value)

        assertEquals(TokenType.GROUPING_SYMBOL, tokens[3].type)
        assertEquals("[", tokens[3].value)

        assertEquals(TokenType.INT, tokens[4].type)
        assertEquals("165", tokens[4].value)

        assertEquals(TokenType.GROUPING_SYMBOL, tokens[5].type)
        assertEquals("]", tokens[5].value)
    }

}