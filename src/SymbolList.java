import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolList {
    private final List<String> symbols = new ArrayList<>();
    private final List<String> givenSymbols = new ArrayList<>();
    private final List<String> givenKeywords = new ArrayList<>();

    public SymbolList(List<String> givenSymbols, List<String> givenKeywords) {
        this.givenSymbols.addAll(givenSymbols);
        this.givenKeywords.addAll(givenKeywords);
        this.symbols.addAll(givenSymbols);
        this.symbols.addAll(givenKeywords);
    }

    private SymbolList(List<String> givenSymbols, List<String> givenKeywords, List<String> symbols) {
        this.givenSymbols.addAll(givenSymbols);
        this.givenKeywords.addAll(givenKeywords);
        this.symbols.addAll(symbols);
    }

    public void putIfAbsent(String symbol) {
        if(!symbols.contains(symbol)) symbols.add(symbol);
    }

    public List<String> get() {
        return new ArrayList<>(symbols);
    }

    public TokenType tokenType(int index) {
        String s = symbols.get(index);
        if(givenSymbols.contains(s)) return TokenType.SYMBOL;
        if(givenKeywords.contains(s)) return TokenType.KEYWORD;
        else return (Integer.getInteger(s) == null) ? TokenType.SIGNIFIER : TokenType.DIGITS;
    }

    @Override
    public String toString() {
        return symbols.toString();
    }

    public static SymbolList copy(SymbolList symbolList) {
        return new SymbolList(symbolList.givenSymbols, symbolList.givenKeywords, symbolList.symbols);
    }
}
