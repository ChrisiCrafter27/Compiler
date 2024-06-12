public enum TokenType {
    SYMBOL("Sy"),
    KEYWORD("Sw"),
    SIGNIFIER("Bz"),
    DIGITS("Zf");

    private final String id;
    TokenType(String id) {
        this.id = id;
    }
    public String id() {
        return id;
    }
}
