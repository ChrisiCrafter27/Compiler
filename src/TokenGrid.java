import java.util.ArrayList;
import java.util.List;

public class TokenGrid {
    private final List<Token> tokens = new ArrayList<>();

    public TokenGrid() {}

    private TokenGrid(List<Token> tokens) {
        this.tokens.addAll(tokens);
    }

    public void add(SymbolList symbolList, String symbol) {
        boolean added = symbolList.putIfAbsent(symbol);
        TokenType tokenType;
        if(added) {
            tokenType = (Integer.getInteger(symbol) == null) ? TokenType.SIGNIFIER : TokenType.DIGITS;
        } else {
            System.out.println(added);
            System.out.println(symbolList);
            System.out.println(this);
            System.out.println(symbol);
            tokenType = symbolList.tokenType(symbolList.get().indexOf(symbol)).getType();
        }
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
