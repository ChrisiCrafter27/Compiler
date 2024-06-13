package compiler.scan;

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

    public String getValue(SymbolList symbolList) {
        return symbolList.get().get(symbolId);
    }

    @Override
    public String toString() {
        return "(" + tokenType.getId() + "," + symbolId + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token token)) return false;
        return symbolId == token.symbolId && tokenType == token.tokenType;
    }
}
