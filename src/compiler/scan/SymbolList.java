package compiler.scan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolList {
    private final Map<String, TokenType> givenSymbols = new HashMap<>();
    private final List<String> symbols = new ArrayList<>();

    public SymbolList(Map<List<String>, TokenType> givenSymbols) {
        for(Map.Entry<List<String>, TokenType> entry : givenSymbols.entrySet()) {
            for(String symbol : entry.getKey()) {
                this.givenSymbols.put(symbol, entry.getValue());
                this.symbols.add(symbol);
            }
        }
    }

    private SymbolList(Map<String, TokenType> givenSymbols, List<String> symbols) {
        this.givenSymbols.putAll(givenSymbols);
        this.symbols.addAll(symbols);
    }

    public void putIfAbsent(String symbol) {
        if(!symbols.contains(symbol)) symbols.add(symbol);
    }

    public List<String> get() {
        return new ArrayList<>(symbols);
    }

    public TokenType tokenType(int index) {
        String symbol = symbols.get(index);
        if(givenSymbols.containsKey(symbol)) return givenSymbols.get(symbol);
        else return isInt(symbol) ? TokenType.NUMBER : TokenType.IDENTIFIER;
    }

    private boolean isInt(String symbol) {
        try {
            Integer.parseInt(symbol);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    @Override
    public String toString() {
        return symbols.toString();
    }

    public static SymbolList copy(SymbolList symbolList) {
        return new SymbolList(symbolList.givenSymbols, symbolList.symbols);
    }
}
