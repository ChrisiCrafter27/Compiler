package compiler.parse;

import java.util.ArrayList;
import java.util.List;

public class Program implements Statement {
    private final List<Statement> body = new ArrayList<>();

    public void add(Statement statement) {
        body.add(statement);
    }

    @Override
    public String toString() {
        return body.toString();
    }
}
