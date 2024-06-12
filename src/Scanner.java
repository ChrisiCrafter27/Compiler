public class Scanner {
    private final SymbolList symbolList;
    private final TokenGrid tokenGrid;
    private State state;
    private String content;
    private int index = 0;

    public Scanner(SymbolList symbolList, String text) {
        this.symbolList = symbolList;
        content = text;
        tokenGrid = new TokenGrid();
    }

    public boolean scan() {
        state = State.start();
        while(!content.isEmpty()) {
            readNextSymbol();
            if(state == State.fail) return false;
            index ++;
            if(index >= content.length()) break;
        }
        return State.ends(state);
    }

    private void readNextSymbol() {
        if(content.charAt(index) == ' ') {
            if(State.ends(state)) state = State.start();
            else {
                state = State.fail;
                return;
            }
            addTokenAndSymbol(content.substring(0, index));
            content = content.substring(index + 1);
            index = 0;
        }
        switch (state) {
            case q0 -> {
                if(Character.isAlphabetic(content.charAt(index))) {
                    state = State.q1;
                } else if(Character.isDigit(content.charAt(index))) {
                    state = State.q2;
                } else if(content.charAt(index) == ':') {
                    state = State.q3;
                } else {
                    state = State.fail;
                }
            }
            case q1 -> {
                if(Character.isAlphabetic(content.charAt(index))) {
                    state = State.q1;
                } else if(content.charAt(index) == '.') {
                    addTokenAndSymbol(content.substring(0, index));
                    content = content.substring(index + 1);
                    index = 0;
                    state = State.q5;
                } else {
                    state = State.fail;
                }
            }
            case q2 -> {
                if(Character.isDigit(content.charAt(index))) {
                    state = State.q2;
                } else {
                    state = State.fail;
                }
            }
            case q3 -> {
                if(content.charAt(index) == '=') {
                    state = State.q4;
                } else {
                    state = State.fail;
                }
            }
        }
    }

    private void addTokenAndSymbol(String symbol) {
        tokenGrid.add(symbolList, symbol);
    }

    public TokenGrid tokenGrid() {
        return TokenGrid.copy(tokenGrid);
    }

    public SymbolList symbolList() {
        return SymbolList.copy(symbolList);
    }
}