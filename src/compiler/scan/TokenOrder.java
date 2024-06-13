package compiler.scan;

import java.util.ArrayList;
import java.util.List;

public class TokenOrder {
    private final SymbolList symbolList;
    private final List<Token> tokens = new ArrayList<>();

    public TokenOrder(SymbolList symbolList) {
        this.symbolList = symbolList;
    }

    private TokenOrder(SymbolList symbolList, List<Token> tokens) {
        this.symbolList = symbolList;
        this.tokens.addAll(tokens);
    }

    public void add(String symbol) {
        symbolList.putIfAbsent(symbol);
        TokenType tokenType = symbolList.tokenType(symbolList.get().indexOf(symbol));
        tokens.add(new Token(tokenType, symbolList.get().indexOf(symbol)));
    }

    public List<Token> get() {
        return new ArrayList<>(tokens);
    }

    public Token current() {
        return tokens.get(0);
    }

    public String currentValue() {
        return current().getValue(symbolList);
    }

    public void next() {
        tokens.remove(0);
    }

    public Token eat() {
        Token token = current();
        next();
        return token;
    }

    public boolean hasNext() {
        return !tokens.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("### Symbols ###");
        for(String symbol : symbolList.get()) {
            Token token = new Token(symbolList.tokenType(symbolList.get().indexOf(symbol)), symbolList.get().indexOf(symbol));
            builder.append('\n').append(token.getType().getName()).append(' ').append(token.toString()).append(' ').append(symbolList.get().get(token.getSymbolId()));
        }
        builder.append('\n').append("### Tokens ###");
        builder.append('\n').append(tokens);
        return builder.toString();
    }

    public static TokenOrder copy(TokenOrder tokenOrder) {
        return new TokenOrder(tokenOrder.symbolList, tokenOrder.get());
    }
}
