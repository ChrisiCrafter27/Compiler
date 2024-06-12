import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolList {
    private final List<String> symbols = new ArrayList<>();

    public SymbolList(List<String> symbols) {
        this.symbols.addAll(symbols);
    }

    public boolean putIfAbsent(String symbol) {
        if(!symbols.contains(symbol)) {
            symbols.add(symbol);
            return true;
        }
        return false;
    }

    public List<String> get() {
        return new ArrayList<>(symbols);
    }

    @Override
    public String toString() {
        return symbols.toString();
    }

    public static SymbolList copy(SymbolList symbolList) {
        return new SymbolList(symbolList.get());
    }
}
