package compiler.parse;

import compiler.scan.TokenOrder;
import compiler.scan.TokenType;

import java.util.Stack;

public class Parser {
    private final TokenOrder tokenOrder;
    private final Stack<String> stack = new Stack<>();
    private ParserState state;

    public Parser(TokenOrder tokenOrder) {
        this.tokenOrder = tokenOrder;
    }

    public boolean parse() {
        stack.push("#");
        state = ParserState.start;
        while(tokenOrder.hasNext() && !stack.isEmpty()) {
            readNextToken();
            System.out.println(stack);
            if(state == ParserState.fail) return false;
        }
        return !stack.isEmpty() && stack.pop().equals("#");
    }

    private void readNextToken() {
        if(state == ParserState.start) {
            switch (stack.pop()) {
                case "#" -> {
                    if (tokenOrder.current().getType() == TokenType.KEYWORD && tokenOrder.currentValue().equals("PROGRAM")) {
                        stack.push("#");
                        stack.push("Program");
                        tokenOrder.next();
                    } else state = ParserState.fail;
                }
                case "Program" -> {
                    switch (tokenOrder.current().getType()) {
                        case IDENTIFIER -> {
                            stack.push("Program");
                            stack.push("Identifier");
                            tokenOrder.next();
                        }
                        case KEYWORD -> {
                            if (tokenOrder.currentValue().equals("BEGIN")) {
                                stack.push("Program");
                                stack.push("Begin");
                                tokenOrder.next();
                            } else {
                                state = ParserState.fail;
                            }
                        }
                        case SYMBOL -> {
                            if (tokenOrder.currentValue().equals(".")) {
                                tokenOrder.next();
                            } else {
                                state = ParserState.fail;
                            }
                        }
                        default -> state = ParserState.fail;
                    }
                }
                case "Begin" -> {
                    switch (tokenOrder.current().getType()) {
                        case IDENTIFIER -> {
                            stack.push("Begin");
                            stack.push("Identifier");
                            tokenOrder.next();
                        }
                        case KEYWORD -> {
                            switch (tokenOrder.currentValue()) {
                                case "BEGIN" -> {
                                    stack.push("Begin");
                                    stack.push("Begin");
                                    tokenOrder.next();
                                }
                                case "END" -> {
                                    tokenOrder.next();
                                }
                                default -> state = ParserState.fail;
                            }
                        }
                        default -> state = ParserState.fail;
                    }
                }
                case "Identifier" -> {
                    if (tokenOrder.current().getType() == TokenType.SYMBOL && tokenOrder.currentValue().equals(":=")) {
                        stack.push("Equals");
                        tokenOrder.next();
                    } else state = ParserState.fail;
                }
                case "Equals" -> {
                    if(tokenOrder.current().getType() == TokenType.NUMBER) {
                        tokenOrder.next();
                    } else state = ParserState.fail;
                }
                default -> state = ParserState.fail;
            }
        } else state = ParserState.fail;
    }
}
