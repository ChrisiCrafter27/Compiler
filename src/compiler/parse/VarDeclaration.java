package compiler.parse;

public class VarDeclaration implements Statement {
    public final String identifier;
    public final Expression expression;

    public VarDeclaration(String identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }
}
