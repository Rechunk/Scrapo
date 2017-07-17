
enum class TokenType {
    UNDEFINED, WORD, STRING, INT, FLOAT, BOOLEAN
}


class Token(val type: TokenType, val value: String, val position: IntRange = 0..0)

