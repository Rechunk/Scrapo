
enum class TokenType {
    WORD, STRING, OPERATOR
}


class Token(val type: TokenType, val value: String, val position: IntRange = 0..0)

