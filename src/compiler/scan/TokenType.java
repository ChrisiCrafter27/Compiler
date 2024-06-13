package compiler.scan;

public enum TokenType {
    SYMBOL("Symbol", "Sy"),
    KEYWORD("Schlüsselwort", "Sw"),
    IDENTIFIER("Bezeichner", "Bz"),
    NUMBER("Ziffernfolge", "Zf");

    private final String name;
    private final String id;

    TokenType(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
}
