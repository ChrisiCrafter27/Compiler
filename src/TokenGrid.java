import java.util.ArrayList;
import java.util.List;

public class TokenGrid {
    private final List<Token> tokens = new ArrayList<>();

    public TokenGrid() {}

    private TokenGrid(List<Token> tokens) {
        this.tokens.addAll(tokens);
    }

    public void add(SymbolList symbolList, String symbol) {
        symbolList.putIfAbsent(symbol);
        TokenType tokenType = symbolList.tokenType(symbolList.get().indexOf(symbol));
        tokens.add(new Token(tokenType, symbolList.get().indexOf(symbol)));
    }

    public List<Token> get() {
        return new ArrayList<>(tokens);
    }

    @Override
    public String toString() {
        return tokens.toString();
    }

    public static TokenGrid copy(TokenGrid tokenGrid) {
        return new TokenGrid(tokenGrid.get());
    }
}
