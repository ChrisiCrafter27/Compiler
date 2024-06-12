public class Token {
    private final TokenType tokenType;
    private final int symbolId;

    public Token(TokenType tokenType, int symbolId) {
        this.tokenType = tokenType;
        this.symbolId = symbolId;
    }

    public TokenType getType() {
        return tokenType;
    }

    public int getSymbolId() {
        return symbolId;
    }

    @Override
    public String toString() {
        return "(" + tokenType.id() + "," + symbolId + ")";
    }
}
