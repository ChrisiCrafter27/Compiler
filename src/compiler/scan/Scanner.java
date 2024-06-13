package compiler.scan;

public class Scanner {
    private final TokenOrder tokenOrder;
    private ScannerState state;
    private String content;
    private int index = 0;
    private boolean done = false;

    public Scanner(SymbolList symbolList, String text) {
        content = text;
        tokenOrder = new TokenOrder(symbolList);
    }

    public boolean scan() {
        state = ScannerState.start;
        while(!content.isEmpty()) {
            readNextSymbol();
            if(state == ScannerState.fail) return false;
            if(index >= content.length()) break;
        }
        if(state == ScannerState.end) done = true;
        return state == ScannerState.end;
    }

    private void readNextSymbol() {
        switch (state) {
            case start -> {
                if(content.charAt(index) == ' ') {
                    index ++;
                    content = content.substring(index);
                    index = 0;
                } else if(Character.isAlphabetic(content.charAt(index))) {
                    index ++;
                    state = ScannerState.identifier;
                } else if(Character.isDigit(content.charAt(index))) {
                    index ++;
                    state = ScannerState.number;
                } else if(content.charAt(index) == ':') {
                    index ++;
                    state = ScannerState.colon;
                } else {
                    state = ScannerState.fail;
                }
            }
            case identifier -> {
                if(content.charAt(index) == ' ') {
                    addTokenAndSymbol();
                    state = ScannerState.start;
                } else if(Character.isAlphabetic(content.charAt(index))) {
                    index ++;
                    state = ScannerState.identifier;
                } else if(content.charAt(index) == '.') {
                    addTokenAndSymbol();
                    index ++;
                    addTokenAndSymbol();
                    state = ScannerState.end;
                } else {
                    state = ScannerState.fail;
                }
            }
            case number -> {
                if(content.charAt(index) == ' ') {
                    addTokenAndSymbol();
                    state = ScannerState.start;
                } else if(Character.isDigit(content.charAt(index))) {
                    index ++;
                    state = ScannerState.number;
                } else if(content.charAt(index) == '.') {
                    addTokenAndSymbol();
                    index ++;
                    addTokenAndSymbol();
                    state = ScannerState.end;
                } else {
                    state = ScannerState.fail;
                }
            }
            case colon -> {
                if(content.charAt(index) == '=') {
                    index ++;
                    state = ScannerState.symbol;
                } else {
                    state = ScannerState.fail;
                }
            }
            case symbol -> {
                if(content.charAt(index) == ' ') {
                    addTokenAndSymbol();
                    state = ScannerState.start;
                } else if(content.charAt(index) == '.') {
                    addTokenAndSymbol();
                    index ++;
                    addTokenAndSymbol();
                    state = ScannerState.end;
                } else {
                    state = ScannerState.fail;
                }
            }
            case end -> {
                state = ScannerState.fail;
            }
        }
    }

    private void addTokenAndSymbol() {
        tokenOrder.add(content.substring(0, index));
        content = content.substring(index);
        index = 0;
    }

    public TokenOrder tokenOrder() {
        return done ? TokenOrder.copy(tokenOrder) : null;
    }

    @Override
    public String toString() {
        return tokenOrder().toString();
    }
}