
enum class TokenType {
    WORD, STRING, INT, OPERATOR, GROUPING_SYMBOL
}


class Token(val type: TokenType, val value: String)

